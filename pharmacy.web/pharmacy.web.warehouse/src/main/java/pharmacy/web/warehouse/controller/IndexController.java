package pharmacy.web.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pharmacy.web.warehouse.model.dto.MinimumDto;
import pharmacy.web.warehouse.model.dto.PurchaseDto;
import pharmacy.web.warehouse.model.dto.StatisticsDrugDto;
import pharmacy.web.warehouse.service.IndexSeverice;


/**
 * 主页面的查询
 * <p>Title : IndexController</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年9月2日 下午8:46:03
 * @version : 1.0.0
 */

@Controller
public class IndexController {

	@Autowired
	private IndexSeverice indexSeverice;
	//查询药品的总数据
	@RequestMapping("/welcome.action")
	@ResponseBody
	public JSONObject Index() {
		JSONObject obj=new JSONObject();
		int drugCount = indexSeverice.DrugCount();
		int tabooCount = indexSeverice.tabooCount();
		int stopDrug = indexSeverice.stopDrug();
		int userCount = indexSeverice.userCount();
		//访问量
		int viewTable = indexSeverice.ViewTable();
		obj.put("drugCount", drugCount);
		obj.put("tabooCount", tabooCount);
		obj.put("stopDrug", stopDrug);
		obj.put("userCount", userCount);
		obj.put("viewTable", viewTable);
		return obj;
	}
	/**
	 * 查询采购统计
	 */
	@RequestMapping("/drugInventory.action")
	@ResponseBody
	public List<StatisticsDrugDto> list(){
		List<StatisticsDrugDto> statisticsDrug = indexSeverice.StatisticsDrug();
		return statisticsDrug;
	}
	
	/**
	 * 查询低限表的信息
	 */
	@RequestMapping("/miniMum.action")
	@ResponseBody
	public List<MinimumDto> viewVolume(){
		List<MinimumDto> minimun = indexSeverice.minimun();
		return minimun;
	}
}
