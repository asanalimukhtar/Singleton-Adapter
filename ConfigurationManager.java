import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {
    private static volatile ConfigurationManager instance = null;
    private Map<String, String> configMap;
    private ConfigurationManager() {
        configMap = new HashMap<>();
        configMap.put("maxPlayers", "100");
        configMap.put("defaultLanguage", "en");
        configMap.put("gameDifficulty", "medium");
    }
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }
    public String getConfig(String key) {
        return configMap.get(key);
    }
    public void printAllConfigs() {
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}