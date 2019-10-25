
package pharmacy.web.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.store.dao.ProfitMapper;
import pharmacy.web.store.model.dto.DrugSalesDto;
import pharmacy.web.store.service.ProfitService;

@Service
public class ProfitServiceImpl implements ProfitService {
	
	
	@Autowired
	private ProfitMapper profitMapper;
	
	/**
	 * 查询药品盘点盈亏信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<DrugSalesDto> profitList(String start, String end, String startTime, String endTime) {
		
		RowBounds rb=new RowBounds(Integer.parseInt(start), Integer.parseInt(end));
		List<DrugSalesDto> profitList = profitMapper.profitList(rb, startTime, endTime);
		return profitList;
	}

	/**
	 * 查询药品盘点盈亏总数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int profitCount(String startTime, String endTime) {
		int count = profitMapper.profitCount(startTime, endTime);
		System.out.println("日志的总数据："+count);
		int allpage = count % 5 == 0 ? (count / 5) : (count / 5 + 1);
		return allpage;
	}
	
	

}
