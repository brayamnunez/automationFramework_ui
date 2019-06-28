package utils;

public class Constant {

	/**Config Properties file **/
	private final static String USER_DIRECTORY = System.getProperty("user.dir");
	public final static String CONFIG_PROPERTIES_DIRECTORY = USER_DIRECTORY + "\\src\\main\\java\\properties\\config.properties";
	private final static String RESOURCE_DIRECTORY = "\\src\\test\\java\\resources";
	public final static String GECKO_DRIVER_DIRECTORY = USER_DIRECTORY + RESOURCE_DIRECTORY + "\\geckodriver.exe";
	public final static String CHROME_DRIVER_DIRECTORY = USER_DIRECTORY + RESOURCE_DIRECTORY + "\\chromedriver.exe";
	public final static String IE_DRIVER_DIRECTORY = USER_DIRECTORY + RESOURCE_DIRECTORY + "\\IEDriverServer.exe";
}
