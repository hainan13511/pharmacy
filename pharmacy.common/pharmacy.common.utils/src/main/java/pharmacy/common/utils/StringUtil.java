package pharmacy.common.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.isEmpty();
	}
	
	//判断传入的字符串是否为空
	public static boolean isEmpty(String data) {
		if(data == null || data.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	//判断数据库是否不为空
	public static boolean isNotEmpty(String data) {
		if(!isEmpty(data)) {
			return true;
		}
		return false;
		
	}
	
	//判断传入多个字符串是否都为空
	public static boolean isAllEmpty(String... data) {
		for (int i = 0; i < data.length; i++) {
			if(!isEmpty(data[i])) {
				return false;
			}
		}
		return true;
	}
	
	//判断传入多个字符串是否都不为空
	public static boolean isAllNotEmpty(String... data) {
		for (int i = 0; i < data.length; i++) {
			if(!isNotEmpty(data[i])) {
				return false;
			}
		}
		return true;
	}
}
