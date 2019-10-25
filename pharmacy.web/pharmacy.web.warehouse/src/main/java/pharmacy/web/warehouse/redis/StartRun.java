package pharmacy.web.warehouse.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pharmacy.web.warehouse.service.IndexSeverice;


@Component
public class StartRun {
	
	@Autowired
	private IndexSeverice indexSeverice;
	
	public void test(){
		new Thread(new ScheduleMQ()).start();//redis中总数据++
       new  Thread(new TimeThread(indexSeverice)).start();//获取当前时间线程
    }
}
