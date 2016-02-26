package ec.com.comida.comunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ec.com.comida.bean.Menu;
import ec.com.comida.bean.MenuItem;
import ec.com.comida.serializer.MenuItemSerializer;

public class PedidoCliente {

	private static PedidoCliente instance = null;

	@JsonSerialize(using = MenuItemSerializer.class)
	private List<MenuItem> menuItemList;

	public static PedidoCliente getInstance() {
		if (instance == null) {
			instance = new PedidoCliente();
		}
		return instance;
	}

	public PedidoCliente() {

	}

	@JsonIgnore
	public void sendJSONMessage(List<MenuItem> menuItemList)
			throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		Menu bean = new Menu();
		bean.setMenuItemList(menuItemList);

		String jsonInString = mapper.writeValueAsString(bean);

		String serverName = "127.0.0.1";
		int port = 2040;
		try {
			System.out.println("Connecting to " + serverName + " on port "
					+ port);
			Socket client = new Socket(serverName, port);
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF(jsonInString);
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			System.out.println("Server says " + in.readUTF());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public void setMenuItemList(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
	}

}
