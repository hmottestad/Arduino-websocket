package Controller;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller {

	static SerialTest serialMain;
	public static final int PORT = 50001;
	public static WebServer webServer;
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

		serialMain = new SerialTest();
		serialMain.initialize();

	}


	public static void setStatus(String status) {
		FileWriter outFile = null;
		try {
			outFile = new FileWriter("status.txt");
			PrintWriter out = new PrintWriter(outFile);
			out.println(status);
			out.close();
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
