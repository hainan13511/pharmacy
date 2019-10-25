package pharmacy.web.store.utils;

import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;


import com.alibaba.fastjson.JSONArray;

import pharmacy.web.store.model.dto.BeOverdue;
import pharmacy.web.store.model.dto.LowLimit;
import pharmacy.web.store.model.dto.Unsalable;
import pharmacy.web.store.service.DrugWarningService;


public class MyThread extends HttpServlet implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DrugWarningService drugWarningService ;
	
	public void init(ServletConfig config,DrugWarningService drugWarningService) {
		new Thread(this).start();
	}
	
	public MyThread(DrugWarningService drugWarningService) {
		super();
		this.drugWarningService = drugWarningService;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5000);
				
				ArrayList<LowLimit> LowLimitlist = new ArrayList<LowLimit>();
				ArrayList<BeOverdue> beOverduelist =new ArrayList<BeOverdue>();
				ArrayList<Unsalable> drugUnsalablelist = new ArrayList<Unsalable>();
				
				beOverduelist = drugWarningService.BeOverdue();
				drugUnsalablelist = drugWarningService.drugUnsalable();
				LowLimitlist = drugWarningService.drugLowLimit();
				
				String message = "";
				ArrayList<String> list = new ArrayList<String>();
				
				if(LowLimitlist!=null||beOverduelist!=null||LowLimitlist!=null) {
					for (int i = 0; i < LowLimitlist.size(); i++) {
						message = " 药品："+LowLimitlist.get(i).getDname()+"低限 ";
						list.add(message);
					}
					for (int i = 0; i < beOverduelist.size(); i++) {
						message = " 药品："+beOverduelist.get(i).getDname()+"过期 ";
						list.add(message);
					}
					for (int i = 0; i < drugUnsalablelist.size(); i++) {
						message = " 药品："+drugUnsalablelist.get(i).getDname()+"滞销 ";
						list.add(message);
					}
					message = JSONArray.toJSONString(list);
					WebManger.broadCast(message);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
