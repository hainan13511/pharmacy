package pharmacy.web.store.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component; 
/** * 获取spring对象的工具类（bean对象） * 使用事列：FrameSpringBeanUtil.getBean(JedisCluster.class); */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {    
	private static ApplicationContext applicationContext;     
	@Override    
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {        
		applicationContext = arg0;    
		}     
	@SuppressWarnings("unchecked")    
		public static <T> T getBean(String name) {        
		return (T) applicationContext.getBean(name);    
		}     
	public static <T> T getBean(Class<T> cls) {        
		return applicationContext.getBean(cls);    
	}
}
