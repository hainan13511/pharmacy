package pharmacy.web.warehouse.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import pharmacy.web.warehouse.model.dto.DrugShopInfDto;
import pharmacy.web.warehouse.model.dto.OutPutInfDto;
import pharmacy.web.warehouse.service.DrugShopInfService;

@Controller
public class DrugShopInfController {

	@Autowired
	private DrugShopInfService dsis;
	
	//药品采购表界面展示
	@RequestMapping("/dodrugshopinfinit.action")
	@ResponseBody
	public JSONObject drugShopInit(String start,String end,String drugname,String supply,String uname) {
		JSONObject data = new JSONObject();
		RowBounds rb=new RowBounds(Integer.parseInt(start),Integer.parseInt(end));
		ArrayList<DrugShopInfDto>list=dsis.drugShopInfInit(rb, drugname, supply, uname);
		int a = dsis.drugShopCount(drugname, supply, uname);
		data.put("list", list);
		data.put("count", a);
		return data;
	}
	
	//药品出入库明细表界面展示
	@RequestMapping("/dooutputinfinit.action")
	@ResponseBody
	public JSONObject outPutInfInit(String start,String end,String drugname,String uname) {
		JSONObject data = new JSONObject();
		RowBounds rb=new RowBounds(Integer.parseInt(start),Integer.parseInt(end));
		ArrayList<OutPutInfDto>list=dsis.outPutInfInit(rb, drugname, uname);
		int a = dsis.outPutCount(drugname, uname);
		data.put("list", list);
		data.put("count", a);
		return data;
	}
}
