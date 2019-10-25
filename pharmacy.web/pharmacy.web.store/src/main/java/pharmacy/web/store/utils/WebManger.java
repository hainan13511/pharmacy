package pharmacy.web.store.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebManger {
	public static List<MyWebSocketServer> list=Collections.synchronizedList(new ArrayList<MyWebSocketServer>());		
	public static void broadCast(String message){	
		for(MyWebSocketServer ws:list){		
			try {			
				ws.sendMessage(message);	
				} catch (IOException e) {			
					// TODO Auto-generated catch block		
					e.printStackTrace();			
			}		
		}	
	}	
	public static int getTotal(){	      
		return list.size();	   
	}	    
	public static void add(MyWebSocketServer server){	
		list.add(server);	   
		System.out.println("有新连接加入！ 当前总连接数是："+ list.size());	 
	}	  
	
	public static void remove(MyWebSocketServer server){	
		list.remove(server);	  
		System.out.println("有连接退出！ 当前总连接数是："+ list.size());	      
	}
	
}

