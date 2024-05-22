package uaic;

import java.io.FileReader;
import java.util.Properties;

public enum MessageProperties {
    INSTANCE;

    Properties properties;

    public Properties getValue() {
        try {
            if (properties == null) {
                properties = new Properties();
                properties.load(new FileReader("D:\\Facultate\\Semestrul4\\Java\\AdvancedProgrammingLabs\\Lab13\\src\\main\\java\\res\\Messages.properties"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void setValue(String type) {
        try {
            if (type.equals("ro")) {
                properties = new Properties();
                properties.load(new FileReader("D:\\Facultate\\Semestrul4\\Java\\AdvancedProgrammingLabs\\Lab13\\src\\main\\java\\res\\Messages_ro.properties"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
