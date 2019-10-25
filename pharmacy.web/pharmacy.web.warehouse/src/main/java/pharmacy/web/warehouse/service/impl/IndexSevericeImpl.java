package pharmacy.web.warehouse.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.warehouse.dao.IndexMapper;
import pharmacy.web.warehouse.model.dto.MinimumDto;
import pharmacy.web.warehouse.model.dto.PurchaseDto;
import pharmacy.web.warehouse.model.dto.StatisticsDrugDto;
import pharmacy.web.warehouse.model.dto.ViewVolumeDto;
import pharmacy.web.warehouse.service.IndexSeverice;


@Service
public class IndexSevericeImpl implements IndexSeverice{

	@Autowired
	private IndexMapper indexMapper;
	//查询药品的总数据
	@Override 
	@Transactional(rollbackFor = Exception.class)
	public int DrugCount() {
		int drugCount = indexMapper.DrugCount();
		return drugCount;
	}
	//查询禁忌药品的总数据
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int tabooCount() {
		int tabooCount = indexMapper.tabooCount();
		return tabooCount;
	}
	//查询药品停用的数据
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int stopDrug() {
		int stopDrug = indexMapper.StopDrug();
		return stopDrug;
	}
	//查询用户的总数据
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int userCount() {
		int userCount = indexMapper.userCount();
		return userCount;
	}
	//查询采购统计数据
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<PurchaseDto> list() {
		List<PurchaseDto> list = indexMapper.list();
		return list;
	}
	//查询访问量表的数据
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int ViewTable() {
		int viewTable = indexMapper.ViewTable();
		return viewTable;
	}
	//查询近7天的访问量
	@Override
	public List<ViewVolumeDto> viewVolume() {
		List<ViewVolumeDto> viewVolume = indexMapper.viewVolume();
		return viewVolume;
	}
	
	
	//添加访问表的信息
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertMess(Integer count, Date localhostTime) {
		System.out.println("Severice层接收到的信息："+count+",时间："+localhostTime);
		int insertMess = indexMapper.insertMess(count, localhostTime);
		return insertMess;
	}
	
	//查询低限表中的信息
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<MinimumDto> minimun() {
		List<MinimumDto> minimun = indexMapper.minimun();
		return minimun;
	}
	
	//查询药品的药库库存
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<StatisticsDrugDto> StatisticsDrug() {
		List<StatisticsDrugDto> drugInventory = indexMapper.drugInventory();
		return drugInventory;
	}
	

	
}
