package pharmacy.web.management.redis;

import redis.clients.jedis.Jedis;

public class SaveThread implements Runnable {

	Jedis jedis = new Jedis("127.0.0.1", 6379);
	private String time;
	@Override
	public void run() {
			jedis.lpush("informList",1+"");  
			System.out.println("添加："+ 1);
		}
	public SaveThread(Jedis jedis, String time) {
		super();
		this.jedis = jedis;
		this.time = time;
	}
	public SaveThread() {
		super();
	}
	public Jedis getJedis() {
		return jedis;
	}
	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
