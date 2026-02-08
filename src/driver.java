/**
 * @author mike802
 * @version 1.0 - 2/26/2013
 */
import core.JarScreen;
import core.Properties;

public class driver {

	public static void main(String[] args) {
		String currentDir = System.getProperty("user.dir");
		String userDir = System.getProperty("user.home");
		Properties properties = new Properties(currentDir, userDir);
		JarScreen jarScreen = new JarScreen(properties);
		jarScreen.init();
	}
}
