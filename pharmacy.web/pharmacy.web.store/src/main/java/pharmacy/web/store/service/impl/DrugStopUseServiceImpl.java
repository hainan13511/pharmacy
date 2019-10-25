package pharmacy.web.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.store.dao.DrugStopUseMapper;
import pharmacy.web.store.model.dto.DrugStopUseDto;
import pharmacy.web.store.service.DrugStopUseService;

@Service
public class DrugStopUseServiceImpl implements DrugStopUseService {

	@Autowired
	private DrugStopUseMapper DrugStopUseMapper;

	// 初始化药的信息
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<DrugStopUseDto> DrugStopuse(String start, String end, String startTime, String endTime,
			String stateName, String drugName) {
		RowBounds rb = new RowBounds(Integer.parseInt(start), Integer.parseInt(end));
		List<DrugStopUseDto> drugStopUse = DrugStopUseMapper.drugStopUse(rb, startTime, endTime, stateName, drugName);
		return drugStopUse;
	}

	/**
	 * 查询总数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int count(String startTime, String endTime, String stateName, String drugName) {
		int count = DrugStopUseMapper.count(startTime, endTime, stateName, drugName);
		System.out.println("日志的总数据：" + count);
		int allpage = count % 5 == 0 ? (count / 5) : (count / 5 + 1);
		return allpage;
	}

	// 修改药品的状态
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateState(String drugid) {
		int updateDrugState = DrugStopUseMapper.UpdateDrugState(drugid, "0");

		return updateDrugState;
	}

	// 恢复药品
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateStart(String drugId) {
		int updateDrugState = DrugStopUseMapper.UpdateDrugState(drugId, "1");
		return updateDrugState;
	}

}
