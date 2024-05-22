package uaic;


import java.util.Locale;

public class SetLocaleCommand implements Command {
    @Override
    public void execute(String commandString) {
        String localeName = commandString.split(" ")[1];
        if (localeName.equals("ro"))
            Locale.setDefault(new Locale("ro", "RO"));
        else
            Locale.setDefault(new Locale("en", "US"));
        MessageProperties.INSTANCE.setValue(localeName);
    }
}
