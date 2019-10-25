package pharmacy.web.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.store.dao.DrugBreakageMapper;
import pharmacy.web.store.model.dto.DrugBreakageDto;
import pharmacy.web.store.service.DrugBreakageSeverice;

@Service
public class DrugBreakageSevericeImpl implements DrugBreakageSeverice{

	/**
	 * 查询药品报损表
	 */
	@Autowired
	private DrugBreakageMapper drugBreakageMapper;
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<DrugBreakageDto> breakageList(String start, String end, String startTime, String endTime,
			String operate) {
		RowBounds rb=new RowBounds(Integer.parseInt(start), Integer.parseInt(end));
		List<DrugBreakageDto> breakageList = drugBreakageMapper.breakageList(rb, startTime, endTime, operate);
		return breakageList;
	}
	/**
	 * 查询总数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int breakCount(String startTime, String endTime, String operate) {
		int count = drugBreakageMapper.breakageCount(startTime, endTime, operate);
		System.out.println("日志的总数据："+count);
		int allpage = count % 5 == 0 ? (count / 5) : (count / 5 + 1);
		return allpage;
	}

}
