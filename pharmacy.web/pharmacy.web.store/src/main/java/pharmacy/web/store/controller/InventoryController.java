package pharmacy.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.utils.StringUtil;
import pharmacy.web.store.model.dto.InventoryDto;
import pharmacy.web.store.service.InventoryService;

/**
 * 查询药品库存查询信息
 * <p>Title : InventoryController</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月29日 下午6:54:32
 * @version : 1.0.0
 */

@Controller
public class InventoryController {

	/*
	 * 查询药品库存数据信息
	 */
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping("/showInventory.action")
	@ResponseBody
	public List<InventoryDto> inventoryShow(String start, String end, String startTime, String endTime, String operate,
			String drugName) {
		//校验
		if(StringUtil.isAllEmpty(start,end)) {
			return null;
		}
		//数据库操作
		List<InventoryDto> showInventory = inventoryService.ShowInventory(start, end, startTime, endTime, operate, drugName);
		return showInventory;
	}
	
	
	/*
	 * 查询药品库存数据信息的总数据
	 */
	@RequestMapping("/inventoryCount.action")
	@ResponseBody
	public int inventoryCount(String startTime, String endTime, String operate,String drugName) {
		int inventoryCount = inventoryService.InventoryCount(startTime, endTime, operate, drugName);
		return inventoryCount;
	}
	
}
