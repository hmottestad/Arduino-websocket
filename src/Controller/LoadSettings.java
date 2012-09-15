package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

/**
 * Created by IntelliJ IDEA.
 * User: havardottestad
 * Date: Feb 8, 2010
 * Time: 12:05:02 AM
 * To change this template use File | Controller.Settings | File Templates.
 */
public class LoadSettings {

	public static void load() {

		FileInputStream fis = null;
		try {
			fis = new FileInputStream("settings.xml");
			Settings.config.loadFromXML(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();  //To change body of catch statement use File | Controller.Settings | File Templates.
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();  //To change body of catch statement use File | Controller.Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Controller.Settings | File Templates.
		}

	}

}
