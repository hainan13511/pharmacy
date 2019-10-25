package pharmacy.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.DrugPriceDto;

public interface DrugPriceMapper {

	//查询药品调价前后价格信息
	List<DrugPriceDto> list(RowBounds rb,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("operate") String operate,@Param("drugname") String drugname);

	//查询总数据
	int count(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("operate") String operate,@Param("drugname") String drugname);
}
