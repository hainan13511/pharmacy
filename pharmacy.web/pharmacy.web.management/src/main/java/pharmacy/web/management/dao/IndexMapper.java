package pharmacy.web.management.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import pharmacy.web.management.model.dto.PurchaseDto;
import pharmacy.web.management.model.dto.ViewVolumeDto;

public interface IndexMapper {
	
	//查询药品的总数据
	int DrugCount();
	//查询药品的禁忌的个数
	int tabooCount();
	//查询药品停用的次数
	int StopDrug();
	//查询用户人数
	int userCount();
	//查询采购统计数据
	List<PurchaseDto> list();
	
	//查询访问量表的信息(最新的一条数据)
	int ViewTable();
	//查询近7天的访问量
	List<ViewVolumeDto> viewVolume();
	
	
	//添加访问量表
	int insertMess(@Param("count") int count, @Param("localhostTime") Date localhostTime); 
}
