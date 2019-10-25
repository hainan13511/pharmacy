package pharmacy.web.management.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pharmacy.web.management.service.IndexSeverice;

@Controller
public class ViewController {

	@Autowired
	private  IndexSeverice indexSeverice;
	public  int  insertMess(Integer count, Date localhostTime) {
		
		int insertMess = indexSeverice.insertMess(count, localhostTime);
		System.out.println("sss:"+insertMess);
		return insertMess;
		
	}
}
