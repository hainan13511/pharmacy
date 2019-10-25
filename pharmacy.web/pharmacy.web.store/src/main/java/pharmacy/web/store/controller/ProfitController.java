package pharmacy.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.utils.StringUtil;
import pharmacy.web.store.model.dto.DrugSalesDto;
import pharmacy.web.store.service.ProfitService;

/**
 * 药品盘点盈亏表
 * <p>Title : ProfitController</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月29日 下午5:35:27
 * @version : 1.0.0
 */

@Controller
public class ProfitController {
	
	@Autowired
	private ProfitService profitService;
	
	
	//查询药品的盘点盈亏表信息
	@RequestMapping("/Showprofit.action")
	@ResponseBody
	public List<DrugSalesDto> ShowProfit(String start ,String end ,String startTime,String endTime) {
		//校验
		if(StringUtil.isAllEmpty(start,end)) {
			return null;
		}
		//数据库操作
		List<DrugSalesDto> profitList = profitService.profitList(start, end, startTime, endTime);
		
		return profitList;
	}
	
	//查询药品的盘点盈利表的总数据
	@RequestMapping("/Countprofit.action")
	@ResponseBody
	public int profitCount(String startTime,String endTime) {
		//数据库操作
		int profitCount = profitService.profitCount(startTime, endTime);
		return profitCount;
	};
	

}
