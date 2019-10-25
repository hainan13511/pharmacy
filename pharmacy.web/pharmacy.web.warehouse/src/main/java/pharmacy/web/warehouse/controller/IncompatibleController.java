package pharmacy.web.warehouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pharmacy.common.model.DrugInf;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.warehouse.service.IncompatibleService;

@Controller
public class IncompatibleController {
	
	@Autowired
	private IncompatibleService is;
	
	//禁忌药品展示
	@RequestMapping("/dodrugincomp.action")
	@ResponseBody
	public JSONObject drugincomp(String drugid) {
		JSONObject data = new JSONObject();
		ArrayList<String>list=is.incomDrug(Integer.parseInt(drugid));
		data.put("list", list);		
		return data;
	}
	//未配置禁忌药品展示
	@RequestMapping("/dooutincomp.action")
	@ResponseBody
	public JSONObject outIncomp(String dname) {
		JSONObject data = new JSONObject();
		ArrayList<DrugInf>list=is.outIncomDrug(dname);
		data.put("list", list);		
		return data;
	}
	//药品禁忌配置
	@RequestMapping("/doincomset.action")
	@ResponseBody
	public String incomSet(String dname1,String dname2) {		
		String data ="no";
		List<String>list1= JSON.parseArray(dname1, String.class);
		List<String>list2=JSON.parseArray(dname2, String.class);
		boolean a = is.incomSet(list1, list2);
		if(a) {
			data="yes";
			return data;
		}		
		return data;
	}
	
	//删除禁忌药品
	@RequestMapping("/dodelincomp.action")
	@ResponseBody
	public String delIncomp(String delname1,String delname2) {		
		String data ="no";
		boolean a = is.delIncomp(delname1, delname2);
		if(a) {
			data="yes";
			return data;
		}		
		return data;
	}
	
	

}
