package pharmacy.web.store.controller;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pharmacy.common.utils.StringUtil;
import pharmacy.web.store.model.DrugWithdrawingMessage;
import pharmacy.web.store.service.DrugWithdrawingService;

@Controller
public class DrugWithdrawingController {
	
	@Autowired
	private DrugWithdrawingService dws;
	
	/**
	 * 	初始化数据
	 * @param drugname 药名
	 * @param batch 批次
	 * @param start	从哪里开始
	 * @param end 显示几条
	 * @return 返回JSON
	 */
	@RequestMapping("/init.action")
	@ResponseBody
	public JSONObject init(String drugname,String batch,String start,String end) {
		JSONObject data = new JSONObject();
		RowBounds rb = new RowBounds(Integer.parseInt(start), Integer.parseInt(end));
		DrugWithdrawingMessage initDrugWithdrawing = dws.initDrugWithdrawing(drugname, batch, rb);
		int page;
		if(initDrugWithdrawing.getPageNum()%Integer.parseInt(end) == 0) {
			page = initDrugWithdrawing.getPageNum()/Integer.parseInt(end);
		}else {
			page = initDrugWithdrawing.getPageNum()/Integer.parseInt(end)+1;
		}
		if(page == 0) {
			page = 1;
		}
		data.put("list", initDrugWithdrawing.getDrugWithdrawingInfDtos());
		data.put("pageNum", page);
		return data;
	}
	
	
	@RequestMapping("/modifyInventory.action")
	@ResponseBody
	public JSONObject modifyInventory(String houseid, String count, String dwdid, String drugid) {
		JSONObject data = new JSONObject();
		if(StringUtil.isAllNotEmpty(houseid,count,dwdid,drugid)) {
			String modifyInventory = dws.modifyInventory(houseid, Integer.parseInt(count), dwdid, drugid);
			data.put("result", modifyInventory);
		}
		return data;
		
	}
	
	
}
