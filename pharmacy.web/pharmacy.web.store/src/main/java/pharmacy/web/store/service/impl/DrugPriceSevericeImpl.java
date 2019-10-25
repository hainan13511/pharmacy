package pharmacy.web.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.web.store.dao.DrugPriceMapper;
import pharmacy.web.store.model.dto.DrugPriceDto;
import pharmacy.web.store.service.DrugPriceSeverice;

@Service
public class DrugPriceSevericeImpl implements DrugPriceSeverice {

	@Autowired
	private DrugPriceMapper drugPriceMapper;
	//查询药品调价前后价格
	@Override
	@Transactional(rollbackFor =Exception.class)
	public List<DrugPriceDto> list(String start, String end, String startTime, String endTime, String operate,
			String drugname) {
		RowBounds rb=new RowBounds(Integer.parseInt(start), Integer.parseInt(end));
		List<DrugPriceDto> list = drugPriceMapper.list(rb, startTime, endTime, operate, drugname);
		return list;
	}
	//查询总数据
	@Override
	public int listCount(String startTime, String endTime, String operate, String drugname) {
		int count = drugPriceMapper.count(startTime, endTime, operate, drugname);
		System.out.println("日志的总数据："+count);
		int allpage = count % 5 == 0 ? (count / 5) : (count / 5 + 1);
		return allpage;
	}

}
