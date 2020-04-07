/**
 * @author mike802
 * @version 1.0 - 2/26/2013
 */
import core.JarScreen;
import core.Properties;

public class driver {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Program syntax: java driver <root dir>");
			System.exit(0);
		}
		Properties properties = new Properties(args[0]);
		JarScreen jarScreen = new JarScreen(properties);
		jarScreen.init();
	}
}
