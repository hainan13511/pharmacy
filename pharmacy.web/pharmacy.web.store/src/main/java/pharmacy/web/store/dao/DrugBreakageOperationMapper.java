package pharmacy.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.DrugBreakageOperationDto;

public interface DrugBreakageOperationMapper {

    // 查询药品表与药房药库库存
    List<DrugBreakageOperationDto> listDrugBreakageOperationDto(RowBounds rb, @Param("drugName") String drugName);

    // 查询药品表总数
    Integer countDrugBreakageOperationDto(@Param("drugName") String drugName);

    // 药品报损
    Integer drugBreakage(@Param("drugId") Integer drugId, @Param("num") Integer num, @Param("batch") Integer batch);

    // 报损记录
    Integer insertDrugBreakageLog(@Param("drugId") Integer drugId, @Param("userId") Integer userId,
            @Param("num") Integer num, @Param("cause") String cause);
    //计入销售成本
    Integer insertDrugSales(@Param("drugId") Integer drugId, @Param("userId") Integer userId,@Param("num") Integer num);
}
