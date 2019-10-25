package pharmacy.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.utils.StringUtil;
import pharmacy.web.store.model.dto.DrugBreakageDto;
import pharmacy.web.store.service.DrugBreakageSeverice;

/**
 * 药品报损明细表操作
 * <p>Title : DrugBreakageController</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月29日 下午1:21:02
 * @version : 1.0.0
 */
@Controller
public class DrugBreakageController {
	
	@Autowired
	private DrugBreakageSeverice drugBreakeSeverice;
	
	/**
	 * 查询明细信息
	 * @return
	 */
	@RequestMapping("/drugBreakage.action")
	@ResponseBody
	public List<DrugBreakageDto> drugBreakage(String start, String end, String startTime, String endTime,
			String operate){
		//校验
		if(StringUtil.isAllEmpty(start,end)) {
			return null;
		}
		System.out.println("收到的信息："+start+","+end+","+startTime+","+endTime+","+operate);
		//数据库操作
		List<DrugBreakageDto> breakageList = drugBreakeSeverice.breakageList(start, end, startTime, endTime, operate);
		return breakageList;
	}
	
	/**
	 * 查询总数据
	 */
	@RequestMapping("/drugBreakageCount.action")
	@ResponseBody
	public int drugBreakageCount(String startTime, String endTime,String operate) {
		int breakCount = drugBreakeSeverice.breakCount(startTime, endTime, operate);
		return breakCount;
	}
}
