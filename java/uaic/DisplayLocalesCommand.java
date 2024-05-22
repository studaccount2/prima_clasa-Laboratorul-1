package uaic;

import java.util.Locale;

public class DisplayLocalesCommand implements Command {
    @Override
    public void execute(String commandString) {
        System.out.print(MessageProperties.INSTANCE.getValue().get("locales"));
        Locale[] available = Locale.getAvailableLocales();
        for (Locale locale : available) {
            System.out.println(locale.getDisplayCountry() + " " + locale.getDisplayLanguage(locale));
        }
    }

}
