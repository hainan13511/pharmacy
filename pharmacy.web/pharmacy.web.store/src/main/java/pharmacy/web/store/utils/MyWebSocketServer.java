package pharmacy.web.store.utils;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import oracle.jdbc.proxy.annotation.OnError;

@ServerEndpoint(value = "/websocket")
public class MyWebSocketServer {
	// 服务端
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		WebManger.add(this);
	}

	public void sendMessage(String message) throws IOException {
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
