# Advanced Structural Patterns Homework

This project implements two design patterns:
1. **Singleton Pattern** for a global Configuration Manager.
2. **Adapter Pattern** for integrating a legacy Chat Service with the new application interface.

## Components

### Part 1: Configuration Manager (Singleton)
- **ConfigurationManager.java**: Implements the Singleton pattern with lazy initialization and thread-safety (double-check locking). It holds hardcoded configuration key-value pairs.
- **ConfigManagerDemo.java**: A demo class to test the ConfigurationManager.

**Expected Output:**
maxPlayers → “100”
Все настройки:
maxPlayers → “100”
defaultLanguage → “en”
gameDifficulty → “medium”

### Part 2: Chat Service Adapter (Adapter)
- **LegacyChatService.java**: A legacy chat service with its own method for sending messages.
- **ChatService.java**: The target interface expected by the new system.
- **ChatServiceAdapter.java**: The adapter that maps calls from `sendMessage` to the legacy method.
- **ChatAdapterDemo.java**: A demo class to test the ChatServiceAdapter.

**Expected Output:**
Legacy Chat: Hello world!

Part 1: Configuration Manager (Singleton Pattern)

  ConfigurationManager.java

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

   ConfigManagerDemo.java

    public class ConfigManagerDemo {
    public static void main(String[] args) {
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        System.out.println("maxPlayers : " + configManager.getConfig("maxPlayers"));
        System.out.println("Все настройки:");
        configManager.printAllConfigs();
    }
    }


Part 2: Chat Service Adapter (Adapter Pattern)

  LegacyChatService.java

    public class LegacyChatService {
    public LegacyChatService() { }
    
    public void sendLegacyMessage(String message) {
        System.out.println("Legacy Chat: " + message);
    }
    }

   ChatService.java

    public interface ChatService {
    void sendMessage(String message);
    }

    ChatServiceAdapter.java
    public class ChatServiceAdapter implements ChatService {
    private LegacyChatService legacyChat;
    
    public ChatServiceAdapter(LegacyChatService legacyChat) {
        this.legacyChat = legacyChat;
    }
    
    @Override
    public void sendMessage(String message) {
        legacyChat.sendLegacyMessage(message);
    }
    }


  ChatAdapterDemo.java

    public class ChatAdapterDemo {
    public static void main(String[] args) {
        LegacyChatService legacyChat = new LegacyChatService();
        ChatService chatService = new ChatServiceAdapter(legacyChat);
        chatService.sendMessage("Hello world!");
    }
    }
