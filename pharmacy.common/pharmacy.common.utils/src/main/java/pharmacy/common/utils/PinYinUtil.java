package pharmacy.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {

	public static String hanziToPinyin(String hanzi){
		return hanziToPinyin(hanzi,"");
		}
		/**
		     * 将汉字转换成拼音
		     * @param hanzi
		     * @param separator
		     * @return
		     */
		public static String hanziToPinyin(String hanzi,String separator){
		// 创建汉语拼音处理类
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		// 输出设置，大小写，音标方式
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		
		String pinyingStr="";
		try {
		pinyingStr=PinyinHelper.toHanyuPinyinString(hanzi, defaultFormat, separator);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return pinyingStr;
	}
		

	
}
