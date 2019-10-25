package pharmacy.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.store.dao.AddDrugLogMapper;
import pharmacy.web.store.service.AddDrugLogSeverice;

@Service
public class AddDrugLogSevericeImpl implements AddDrugLogSeverice{

	@Autowired
	private AddDrugLogMapper addDrugLogMapper;  
	//添加日志
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int AddDrugLog(String content, String uid) {
		int addDrugLog = addDrugLogMapper.addDrugLog(content, uid);
		return addDrugLog;
	}

}
