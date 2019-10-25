package pharmacy.web.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pharmacy.common.utils.StringUtil;
import pharmacy.web.management.model.dto.DrugLogDto;
import pharmacy.web.management.service.LogViewService;

/**
 * 日志查看
 * <p>Title : LogViewController</p>
 * <p>Description : </p>
 * <p>DevelopTools : Eclipse_x64_v4.10.0</p>
 * <p>DevelopSystem : Windows7</p>
 * <p>Company : org.kjb
</p>
 * @author : kejianbin
 * @date : 2019年8月28日 下午1:04:08
 * @version : 1.0.0
 */
@Controller
public class LogViewController {

	@Autowired
	private LogViewService logViewService;
	/**
	 * 初始化日志数据
	 * @param start
	 * @param end
	 * @param beginTime
	 * @param endTime
	 * @param operate
	 * @return
	 */
	@RequestMapping("/logShow.action")
	@ResponseBody
	public List<DrugLogDto> logView(String start,String end,String startTime,String endTime,String operate){
		//校验
		if(StringUtil.isAllEmpty(start,end)) {
			return null;
		}
		System.out.println("日志接收的信息："+start+","+end+","+startTime+","+endTime+","+operate);
		//数据库操作
		List<DrugLogDto> logList = logViewService.logList(start, end, startTime, endTime, operate);
		System.out.println("日志查看："+logList.toString());
		return logList;
	}
	
	@RequestMapping("/logCount.action")
	@ResponseBody
	public int logCount(String startTime,String endTime,String operate) {
		int logCount = logViewService.logCount(startTime, endTime, operate);
		return logCount;
	}
}
