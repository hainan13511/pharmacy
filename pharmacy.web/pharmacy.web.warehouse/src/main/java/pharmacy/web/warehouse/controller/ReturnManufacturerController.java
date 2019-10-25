package pharmacy.web.warehouse.controller;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pharmacy.common.utils.StringUtil;
import pharmacy.web.warehouse.model.dto.ReturnManufacturerInfDto;
import pharmacy.web.warehouse.service.ReturnManufacturerService;

@Controller
public class ReturnManufacturerController {
	
	@Autowired
	private ReturnManufacturerService rms;
	
	/**
	 * 	 查询数据 初始化退库页面数据
	 * @param drugname 药品名用于查询
	 * @param supply 供应商 用于查询
	 * @param uname 用户名 用于查询
	 * @param start 显示页数
	 * @param end 从第几条开始显示
	 * @return 返回JSON
	 */
	@RequestMapping("/init.action")
	@ResponseBody
	public JSONObject init(String drugname,String supply,String uname,String start,String end) {
		JSONObject data = new JSONObject();
		RowBounds rb = new RowBounds(Integer.parseInt(start), Integer.parseInt(end));
		List<ReturnManufacturerInfDto> initReturnManufacturer = rms.initReturnManufacturer(drugname, supply, uname, rb);
		int initReturnManufacturerPageNum = rms.initReturnManufacturerPageNum(drugname, supply, uname);
		int pageNum = 0;
		if(initReturnManufacturerPageNum%Integer.parseInt(end) == 0) {
			pageNum = initReturnManufacturerPageNum/Integer.parseInt(end);
		}else {
			pageNum = initReturnManufacturerPageNum/Integer.parseInt(end)+1;
		}
		if(pageNum == 0) {
			pageNum = 1;
		}
		data.put("list", initReturnManufacturer);
		data.put("pageNum", pageNum);
		return data;
	}
	
	@RequestMapping("/reduceInventories.action")
	@ResponseBody
	public JSONObject reduceInventories(String inventoryid,String drug_count) {
		JSONObject data = new JSONObject();
		if(StringUtil.isAllNotEmpty(inventoryid,drug_count)) {
			String reduceInventories = rms.reduceInventories(inventoryid, drug_count);
			data.put("result", reduceInventories);
		}
		return data;
	}
	
	
}
