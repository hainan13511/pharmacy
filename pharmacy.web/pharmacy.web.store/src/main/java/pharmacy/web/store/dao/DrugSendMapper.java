package pharmacy.web.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pharmacy.common.model.DrugInf;
import pharmacy.common.model.DrugType;
import pharmacy.common.model.HouseInventory;

public interface DrugSendMapper {

    // 根据药品大类ID查询下属分类
    List<DrugType> listDrugType(@Param("fid") Integer fid);

    // 根据药品分类ID查询下属药品
    List<DrugInf> listDrugInf(@Param("dtId") Integer dtId);

    // 药品销售记录
    Integer drugSendLog(@Param("drugId") Integer drugId, @Param("userId") Integer userId, @Param("num") Integer num,
            @Param("cost") Integer cost, @Param("sale") Integer sale);

    // 查询销售药品剩余库存
    Integer getDrugCount(@Param("drugId") Integer drugId);
    
    //修改药品库存
    Integer updateDrugCount(@Param("houseId") Integer houseId,@Param("num") Integer num);
    
    //查询销售药品剩余库存分批列表
    List<HouseInventory> listHouseInventory(@Param("drugId") Integer drugId);
    
    //配伍禁忌查询
    Integer drugTabooCheck(@Param("drugId") Integer drugId,@Param("sDrugId") Integer sDrugId);
    
}
