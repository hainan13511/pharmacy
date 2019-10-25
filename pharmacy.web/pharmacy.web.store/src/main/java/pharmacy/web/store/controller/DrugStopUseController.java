package pharmacy.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.utils.StringUtil;
import pharmacy.web.store.model.dto.DrugStopUseDto;
import pharmacy.web.store.service.DrugStopUseService;

/**
 * 药品停用
 * <p>
 * Title : DrugStopUseController
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * DevelopTools : Eclipse_x64_v4.10.0
 * </p>
 * <p>
 * DevelopSystem : Windows7
 * </p>
 * <p>
 * Company : org.kjb
 * </p>
 * 
 * @author : kejianbin
 * @date : 2019年8月29日 下午7:50:21
 * @version : 1.0.0
 */
@Controller
public class DrugStopUseController {

	@Autowired
	private DrugStopUseService drugStopUseService;

	// 查看药的初始化数据
	@RequestMapping("/InitdrugStopUse.action")
	@ResponseBody
	public List<DrugStopUseDto> DrugStopUse(String start, String end, String startTime, String endTime,
			String stateName, String drugName) {
		// 校验
		if (StringUtil.isAllEmpty(start, end)) {
			return null;
		}
		// 数据库操作
		List<DrugStopUseDto> drugStopuse = drugStopUseService.DrugStopuse(start, end, startTime, endTime, stateName,
				drugName);
		return drugStopuse;
	}

	// 查询总数据
	@RequestMapping("/drugStopUseCount.action")
	@ResponseBody
	public int drugStropCount(String startTime, String endTime, String stateName, String drugName) {
		int count = drugStopUseService.count(startTime, endTime, stateName, drugName);
		return count;
	}

	// 药品停用
	@RequestMapping("/drugStopUseBtn.action")
	@ResponseBody
	public int drugStop(String drugId) {
		System.out.println("收到的信息："+drugId);
		// 校验
		if (StringUtil.isAllEmpty(drugId)) {
			return 0;
		}
		// 数据库操作
		int updateState = drugStopUseService.updateState(drugId);
		System.out.println("结果："+updateState);
		return updateState;
	}

	// 药品恢复
	@RequestMapping("/drugStartUseBtn.action")
	@ResponseBody
	public int drugStart(String drugId) {
		// 校验
		if (StringUtil.isAllEmpty(drugId)) {
			return 0;
		}
		System.out.println("收到的信息："+drugId);
		// 数据库操作
		int updateState = drugStopUseService.updateStart(drugId);
		System.out.println("结果："+updateState);
		return updateState;
	}
}
