package pharmacy.web.warehouse.websocket;

import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;

import pharmacy.web.warehouse.dao.DrugTypeMapper;
import pharmacy.web.warehouse.model.dto.MiniSetDto;
import pharmacy.web.warehouse.service.DrugTypeService;
import pharmacy.web.warehouse.service.MiniSetService;
import pharmacy.web.warehouse.service.impl.MiniSetServiceImpl;

//loadOnStartup标记为Servlet不是为了其被访问，而是为了便于伴随Tomcat一起启动1
//@WebServlet(urlPatterns = "/MyThread", loadOnStartup = 1)
public class MyThread extends HttpServlet implements Runnable {
	
	private MiniSetService mss;

	public void init(ServletConfig config) {
		new Thread(this).start();	
	}
	
	
	public MyThread(MiniSetService mss) {
		super();
		this.mss = mss;
	}
	
	public MyThread() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public void run() {
		
		
		while (true) {
			int psum=0;
			int val=0;
			String dname="";
			ArrayList<MiniSetDto>list=mss.miniInf();
			String message = "";
			ArrayList<String>list1=new ArrayList<String>();
			String str="";
			try {					
				Thread.sleep(10000);
				for(int i=0;i<list.size();i++) {
					psum=list.get(i).getPsum();
					val=list.get(i).getMinimunVal();
					dname=list.get(i).getDrugName();				
					if(psum<val) {
						message=dname+"库存低限量警报！";
						list1.add(message);	
						str=JSONArray.toJSONString(list1);
					}
				}
				WebManger.broadCast(str);

				//String.format("");		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}






}
