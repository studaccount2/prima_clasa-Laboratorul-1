package app;

import uaic.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LocaleExplore {
    private Map<String, Command> commands;

    public LocaleExplore() {
        commands = new HashMap<>();
        commands.put("displayLocales", new DisplayLocalesCommand());
        commands.put("setLocale", new SetLocaleCommand());
        commands.put("info", new InfoCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName.split(" ")[0]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(MessageProperties.INSTANCE.getValue().get("prompt"));
            String commandString = scanner.nextLine();
            try {
                Command command = new LocaleExplore().getCommand(commandString);
                command.execute(commandString);
            } catch (Exception e) {
                System.out.println(MessageProperties.INSTANCE.getValue().get("invalid"));
            }
        }
    }
}
