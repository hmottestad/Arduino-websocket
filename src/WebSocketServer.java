
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WebSocketServer extends BaseWebSocketHandler {

	static Map<WebSocketConnection, WebSocketConnection> connected = new HashMap<WebSocketConnection, WebSocketConnection>();

	public void onOpen(WebSocketConnection connection) {
		System.out.println("Added connection: "+connection.toString());
		connected.put(connection, connection);
	}

	public void onClose(WebSocketConnection connection) {
		try{
			connected.remove(connection);
			System.out.println("Removed connection: " + connection.toString());

		}catch (Exception e){
			System.out.println("Something went wrong when removing connection!");
			e.printStackTrace();
		}
	}


	public void onMessage(final WebSocketConnection connection, String message) {
		System.out.println(connection + "\t" + message);

		Date todaysDate = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		String formattedDate = formatter.format(todaysDate);

		notifyAllClients("Received at "+formattedDate+": '"+message+"'");

		if(message.equals("ON")){
			sendToArudino(1);

		}else if(message.equals("OFF")){
			sendToArudino(0);
		}

	}


	static void sendToArudino(int i) {
		if (i > 255 || i < 0) {
			throw new RuntimeException("Wrong int. " + i);
		}
		byte[] send = new byte[1];
		send[0] = (byte) i;
		try {
			Controller.arduinoSerialUSB.output.write(send);
			System.out.println("Sent til arduino: "+send[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void notifyAllClients(int value) {
		notifyAllClients(value+"");
	}

	public static void notifyAllClients(String value) {
		for(WebSocketConnection c : connected.keySet()){
			c.send(value);
		}

	}
}
