
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller {

	public static ArduinoSerialUSB arduinoSerialUSB;
	public static final int PORT = 50001;
	private static WebServer webServer;
	public static WebSocketServer webSocketServer;


	public static void main(String[] args) throws Exception {

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				webServer.stop();
			}
		});

		webSocketServer = new WebSocketServer();
		webServer = WebServers.createWebServer(PORT)
			                        .add("/hellowebsocket", webSocketServer)
			                        .add(new StaticFileHandler("./web"));
		webServer.start();
		System.out.println("Server running at " + webServer.getUri());
		System.out.println("Pr�v ogs� \"localhost\"");

		arduinoSerialUSB = new ArduinoSerialUSB();
		arduinoSerialUSB.initialize();

	}

}
