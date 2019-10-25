package pharmacy.web.store.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import pharmacy.web.store.model.dto.LowLimit;

public interface LowLimitMapper {

	ArrayList<LowLimit> lowLimitTable(@Param("dname")String dname,@Param("limit")int limit,RowBounds rn);
	
	int lowLimitTableCount(@Param("dname")String dname,@Param("limit")int limit);
	
	ArrayList<LowLimit> lowLimit(@Param("limit")int limit);
}
