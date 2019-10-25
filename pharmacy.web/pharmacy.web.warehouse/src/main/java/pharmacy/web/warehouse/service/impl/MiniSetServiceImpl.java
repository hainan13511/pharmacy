package pharmacy.web.warehouse.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.warehouse.dao.MiniSetMapper;
import pharmacy.web.warehouse.model.dto.MiniSetDto;
import pharmacy.web.warehouse.service.MiniSetService;
@Service
public class MiniSetServiceImpl implements MiniSetService {

	@Autowired
	private MiniSetMapper msm;
	//药品低限表格初始化
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<MiniSetDto> miniInfInit(RowBounds rb, String drugname) {
		ArrayList<MiniSetDto>list=msm.miniInfInit(rb, drugname);
		return list;
	}
	//药品低限表格分页
	@Transactional(rollbackFor = Exception.class)
	public int countMini(String drugname) {
		int a =msm.countMini(drugname);
		return a;
	}
	//药品低限 设置
	@Transactional(rollbackFor = Exception.class)
	public boolean miniSet(int minival, int drugid) {		
		Integer a =msm.miniSet(minival, drugid);
		if(a>0) {
			return true;
		}
		return false;
	}
	//药品低限 警报
	@Transactional(rollbackFor = Exception.class)
	public ArrayList<MiniSetDto> miniInf() {
		ArrayList<MiniSetDto>list=msm.miniInf();
		return list;
	}

}
