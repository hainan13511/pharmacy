package pharmacy.web.warehouse.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 添加日志dao
 * <p>Title : AddDrugLog</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年9月2日 下午1:59:21
 * @version : 1.0.0
 */
public interface AddDrugLogMapper {

	//添加日志
	int addDrugLog(@Param("content") String content,@Param("uid") String uid);
}
