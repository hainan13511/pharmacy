package pharmacy.web.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.store.dao.InventoryMapper;
import pharmacy.web.store.model.dto.InventoryDto;
import pharmacy.web.store.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService{

	
	@Autowired
	private InventoryMapper inventoryMapper;
	
	/**
	 * 查询药品库存查询
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<InventoryDto> ShowInventory(String start, String end, String startTime, String endTime, String operate,
			String drugName) {
		RowBounds rb=new RowBounds(Integer.parseInt(start), Integer.parseInt(end));
		 List<InventoryDto> inventoryList = inventoryMapper.InventoryList(rb, startTime, endTime, operate, drugName);
		return inventoryList;
	}

	/**
	 * 查询药品库存总数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int InventoryCount(String startTime, String endTime, String operate, String drugName) {
		int count = inventoryMapper.InventoryCount(startTime, endTime, operate, drugName);
		System.out.println("日志的总数据："+count);
		int allpage = count % 5 == 0 ? (count / 5) : (count / 5 + 1);
		return allpage;
	}

}
