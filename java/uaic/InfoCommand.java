package uaic;

import lombok.RequiredArgsConstructor;

import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Currency;
import java.util.Locale;

public class InfoCommand implements Command {
    @Override
    public void execute(String commandString) throws Exception {
        String type = commandString.split(" ")[1];
        Locale requestedLocale = null;
        if (type.equals("current"))
            requestedLocale = Locale.getDefault();
        else if (type.equals("ro"))
            requestedLocale = new Locale("ro", "RO");
        else
            requestedLocale = new Locale("en", "US");
        if (requestedLocale == null) {
            throw new Exception("Invalid locale");
        }
        System.out.println(MessageProperties.INSTANCE.getValue().get("info") + " " + requestedLocale.getDisplayName(requestedLocale));
        System.out.println(MessageProperties.INSTANCE.getValue().get("country") + " " + requestedLocale.getDisplayCountry());
        System.out.println(MessageProperties.INSTANCE.getValue().get("language") + " " + requestedLocale.getDisplayLanguage(requestedLocale));
        System.out.println(MessageProperties.INSTANCE.getValue().get("currency") + " " + Currency.getInstance(requestedLocale).getDisplayName()
                + " (" + Currency.getInstance(requestedLocale).getCurrencyCode() + ")");
        System.out.print(MessageProperties.INSTANCE.getValue().get("weekdays"));
        printWeekdays(requestedLocale);
        System.out.print(MessageProperties.INSTANCE.getValue().get("months"));
        printMonths(requestedLocale);
        System.out.print(MessageProperties.INSTANCE.getValue().get("today"));
        printTime(requestedLocale);
    }

    public static void printWeekdays(Locale loc) {
        WeekFields wf = WeekFields.of(loc);
        DayOfWeek day = wf.getFirstDayOfWeek();
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            System.out.print(day.getDisplayName(TextStyle.SHORT, loc) + ", ");
            day = day.plus(1);
        }
        System.out.println();
    }

    public static void printMonths(Locale locale) {
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        for (int i = 0; i < dfs.getMonths().length - 1; i++) {
            System.out.print(dfs.getMonths()[i] + ", ");
        }
        System.out.println();
    }

    public static void printTime(Locale locale) {
        ZonedDateTime zoned = ZonedDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale);
        System.out.println(zoned.format(pattern));
    }
}
