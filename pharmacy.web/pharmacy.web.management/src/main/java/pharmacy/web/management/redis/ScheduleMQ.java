package pharmacy.web.management.redis;

import java.util.List;

import redis.clients.jedis.Jedis;


public class ScheduleMQ extends Thread {
	 Jedis jedis = new Jedis("127.0.0.1", 6379);
    @Override
    public void run() {
    	int count=0;
    	 jedis.select(0);
    	System.out.println("redis中的总和:"+jedis.get("count"));
    	if(jedis.get("count")!=null) {
    		count=Integer.parseInt(jedis.get("count"));
    	}
    	
        while(true) {
        	 count++;
            //阻塞式brpop，List中无数据时阻塞
            //参数0表示一直阻塞下去，直到List出现数据
            List<String> list = jedis.brpop(0, "informList");
            for(String s : list) {
                System.out.println(s);
                //添加数据
                jedis.set("count", count+"");
            }
         
            jedis.close();
 
        }
    }
}
