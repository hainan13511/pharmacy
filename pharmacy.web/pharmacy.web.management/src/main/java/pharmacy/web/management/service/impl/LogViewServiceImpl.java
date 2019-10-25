package pharmacy.web.management.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.management.dao.DrugLogMapper;
import pharmacy.web.management.model.dto.DrugLogDto;
import pharmacy.web.management.service.LogViewService;

@Service
public class LogViewServiceImpl implements LogViewService {

	//查询日志信息
	@Autowired
	private DrugLogMapper drugLogMapper;
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<DrugLogDto> logList(String start, String end, String beginTime, String endTime, String operate) {
		
		RowBounds rb=new RowBounds(Integer.parseInt(start), Integer.parseInt(end));
		List<DrugLogDto> loglit = drugLogMapper.loglit(rb, beginTime, endTime, operate);
		return loglit;
	}
	/**
	 * 查询日志的总数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int logCount(String beginTime, String endTime, String operate) {
		int count = drugLogMapper.logCount(beginTime, endTime, operate);
		System.out.println("日志的总数据："+count);
		int allpage = count % 5 == 0 ? (count / 5) : (count / 5 + 1);
		return allpage;
	}

}
