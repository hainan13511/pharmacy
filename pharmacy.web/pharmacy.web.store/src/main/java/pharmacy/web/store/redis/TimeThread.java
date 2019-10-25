package pharmacy.web.store.redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pharmacy.web.store.service.IndexSeverice;
import redis.clients.jedis.Jedis;


public class TimeThread implements Runnable{
	
	private  IndexSeverice indexSeverice;
	@Override
	public void run() {
		while(true) {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
//			System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(df.format(new Date()).equals("23:05:00")) {
				Jedis jedis = new Jedis("127.0.0.1", 6379);//连接redis数据库
				jedis.select(1);//添加的端口
				String string = jedis.get("count");
				jedis.del("count");
				System.out.println("获取count:"+string);
				SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				Date date = null;//字符串转Date类型
				try {
					date = ds.parse(ds.format(new Date()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				jedis.close();//关闭
				//
				if(string==null) {
					string="0";
					System.out.println("string:"+string);
				}
				//new ViewController().insertMess(Integer.parseInt(string), date);
				System.out.println(indexSeverice);
				//待做
//				int insertMess = indexSeverice.insertMess(Integer.parseInt(string), date);
//				System.out.println("添加成功:"+insertMess);
				return;
				
			}
			
		}
	}
	public TimeThread(IndexSeverice indexSeverice) {
		super();
		this.indexSeverice = indexSeverice;
	}
	public TimeThread() {
		super();
	}

}
