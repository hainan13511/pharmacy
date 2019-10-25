package pharmacy.web.warehouse.websocket;

import java.io.IOException;
import java.util.ArrayList;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import oracle.jdbc.proxy.annotation.OnError;
import pharmacy.web.warehouse.service.DrugTypeService;

@ServerEndpoint(value = "/websocket")
public class MyWebSocketServer {
	// 服务端
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	private static DrugTypeService dts;
	private static ArrayList<String>list;
	

	@OnOpen
	public void onOpen(Session session) {

		System.out.println("-----onOpen-----");
		this.session = session;
		WebManger.add(this);
	}

	public void sendMessage(String message) throws IOException {
		
		System.out.println("-----sendMessage-----");
		this.session.getBasicRemote().sendText(message); // 同步方法
	}

	@OnClose
	public void onClose() {
		WebManger.remove(this);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("来自客户端的消息:" + message);
	}

	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}
}
