package pharmacy.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.DrugApplyDetailDto;
import pharmacy.web.store.model.dto.DrugInfDto;

public interface DailyWorkMapper {

    // 查询药品申请表
    List<DrugApplyDetailDto> listDrugApply(RowBounds rb, @Param("drugName") String drugName,
            @Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd,
            @Param("checkState") Integer checkState, @Param("userId") Integer userId);

    // 查询药品申请表总数
    Integer countDrugApply(@Param("drugName") String drugName, @Param("dateStart") String dateStart,
            @Param("dateEnd") String dateEnd, @Param("checkState") Integer checkState, @Param("userId") Integer userId);

    // 修改请领单
    Integer updateApply(@Param("id") Integer id, @Param("altNum") Integer altNum);

    // 查询是否有药品
    Integer getDrug(@Param("drugName") String drugName);

    // 新增请领单
    Integer insertDrugApply(@Param("drugName") String drugName, @Param("drugNum") Integer drugNum,
            @Param("userId") Integer userId);

    // 查询药品表
    List<DrugInfDto> listDrugInfDto(RowBounds rb, @Param("drugName") String drugName);

    // 查询药品表总数
    Integer countDrugInfDto(@Param("drugName") String drugName);

    // 调整药品零售价
    Integer updateDrugPrice(@Param("price") Integer price, @Param("drugId") Integer drugId);
    
    // 调整药品零售价记录
    Integer insertDrugPriceLog(@Param("userId") Integer userId,@Param("beforePrice") Integer beforePrice,@Param("price") Integer price, @Param("drugId") Integer drugId);
}
