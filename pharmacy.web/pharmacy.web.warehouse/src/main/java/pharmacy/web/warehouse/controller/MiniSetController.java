package pharmacy.web.warehouse.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.warehouse.model.dto.MiniSetDto;
import pharmacy.web.warehouse.service.MiniSetService;

@Controller
public class MiniSetController {
	
	@Autowired
	private MiniSetService mss;
	
	//药品低限设置界面展示
	@RequestMapping("/dominisetinfinit.action")
	@ResponseBody
	public JSONObject miniSetInfInit(String start,String end,String drugname) {
		JSONObject data = new JSONObject();
		RowBounds rb=new RowBounds(Integer.parseInt(start),Integer.parseInt(end));
		ArrayList<MiniSetDto>list=mss.miniInfInit(rb, drugname);
		int a = mss.countMini(drugname);
		data.put("list", list);
		data.put("count", a);
		return data;
	}
	
	//药品低限设置
	@RequestMapping("/dominiset.action")
	@ResponseBody
	public String miniSet(String minival,String drugid) {
		
		String data ="no";		
		if(!StringUtil.isAllNotEmpty(minival,drugid)) {
			data="empty";
			return data;
		}else {		
			boolean a = mss.miniSet(Integer.parseInt(minival), Integer.parseInt(drugid));
			if(a) {
				data="yes";
				return data;
			}
		}
		return data;	
	}
	
	
	
	
	
	

}
