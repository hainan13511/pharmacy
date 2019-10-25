package pharmacy.web.warehouse.service;

import java.util.ArrayList;
import org.apache.ibatis.session.RowBounds;
import pharmacy.web.warehouse.model.dto.MiniSetDto;

public interface MiniSetService {
	
	//药品低限表格初始化
	ArrayList<MiniSetDto> miniInfInit(RowBounds rb,String drugname);
	//药品低限表格分页
	int countMini(String drugname);
	//药品低限设置
	boolean miniSet(int minival,int drugid);
	//药品低限警报
	ArrayList<MiniSetDto> miniInf();
}
