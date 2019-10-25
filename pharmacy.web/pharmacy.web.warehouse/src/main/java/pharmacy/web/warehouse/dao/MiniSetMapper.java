package pharmacy.web.warehouse.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.warehouse.model.dto.MiniSetDto;

public interface MiniSetMapper {
	
	//药品低限表格初始化
	ArrayList<MiniSetDto> miniInfInit(RowBounds rb,@Param("drugname")String drugname);
	//药品低限表格分页
	int countMini(@Param("drugname")String drugname);
	//药品低限量设置
	Integer miniSet(@Param("minival")int minival,@Param("drugid")int drugid);
	//药品低限警报
	ArrayList<MiniSetDto> miniInf();
}
