package pharmacy.web.store.service;

import java.util.Date;
import java.util.List;

import pharmacy.web.store.model.dto.MinimumDto;
import pharmacy.web.store.model.dto.PurchaseDto;
import pharmacy.web.store.model.dto.StatisticsDrugDto;
import pharmacy.web.store.model.dto.ViewVolumeDto;



public interface IndexSeverice {

	// 查询药品的总数据
	int DrugCount();

	// 查询禁忌药品的总数据
	int tabooCount();

	// 查询药品停用的次数
	int stopDrug();

	// 查询用户人数
	int userCount();

	// 查询采购统计数据
	List<PurchaseDto> list();
	
	//查询低限表中的信息
	List<MinimumDto> minimun();
	
	//查询药品的药库库存
	List<StatisticsDrugDto> StatisticsDrug();
	// 查询访问量表的信息(最新的一条数据)
	int ViewTable();

	// 查询近7天的访问量
	List<ViewVolumeDto> viewVolume();

	// 添加访问表的信息
	int insertMess(Integer count, Date localhostTime);
}
