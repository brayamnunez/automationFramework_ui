package utils;

public class FileReaderManager {

    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private static ReadConfigFile configFileReader;
    private static JsonDataReader jsonDataReader;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public ReadConfigFile getConfigReader() {
        return (configFileReader == null) ? new ReadConfigFile() : configFileReader;
    }

    public JsonDataReader getJsonReader(String resource){
        return (jsonDataReader == null) ? new JsonDataReader(resource) : jsonDataReader;
    }
}

