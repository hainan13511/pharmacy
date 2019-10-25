package pharmacy.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.store.model.dto.DrugPriceDto;
import pharmacy.web.store.service.DrugPriceSeverice;
/**
 * 药品调价前后信息查询
 * <p>Title : DrugPriceEnquiryController</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月28日 下午4:55:49
 * @version : 1.0.0
 */
@Controller
public class DrugPriceEnquiryController {

	@Autowired
	private DrugPriceSeverice drugPriceSeverice;
	
	//查询药品调价前后的信息
	@RequestMapping("/showDrugPrice.action")
	@ResponseBody
	public List<DrugPriceDto> showDrugPrice(String start,String end,String startTime,String endTime,String operate,String drugname){
		//校验
		if(StringUtil.isAllEmpty(start,end)) {
			return null;
		}
		System.out.println("查看："+start+","+end+","+startTime+","+endTime+","+operate+","+drugname);
		//数据库操作
		List<DrugPriceDto> list = drugPriceSeverice.list(start, end, startTime, endTime, operate, drugname);
		return list;
		
	}
	
	//查询总数据
	@RequestMapping("/DrugPriceCount.action")
	@ResponseBody
	public int drugPriceCount(String startTime,String endTime,String operate,String drugname) {
		int count = drugPriceSeverice.listCount(startTime, endTime, operate, drugname);
		return count;
	}
}
