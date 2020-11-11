package com.example.demo.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月30日 下午3:09:49
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class StringUtil {
	// 集群
	private static int machineId = 1;

	private static final Pattern patternAmount = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,3})?$");

	public static Logger log = LoggerFactory.getLogger(StringUtil.class);

	public static String getString(Object obj) {
		return obj == null ? null : obj.toString();

	}

	public static long getLong(Object obj, Long defaultData) {
		Long lnum = defaultData;

		if (isEmpty(obj)) {
			return lnum;
		}
		try {
			lnum = Long.valueOf(obj.toString().trim()).longValue();
		} catch (NumberFormatException e) {
			log.warn("把String 转换�? long======== " + obj.toString());
		}
		return lnum;

	}

	public static Boolean getBoolean(Object obj, Boolean defaultData) {
		Boolean lnum = defaultData;

		if (isEmpty(obj)) {
			return lnum;
		}
		try {
			lnum = Boolean.valueOf(obj.toString().trim()).booleanValue();
		} catch (NumberFormatException e) {
			log.warn("把String 转换�? long======== " + obj.toString());
		}
		return lnum;

	}

	/**
	 * 把String转换成int数据
	 * 
	 * @param str
	 * @param defaultData
	 * @return
	 */
	public static int getInt(Object obj, Integer defaultData) {
		int inum = defaultData;

		if (isEmpty(obj)) {
			return inum;
		}

		try {
			inum = Integer.valueOf(obj.toString().trim()).intValue();
		} catch (NumberFormatException e) {
			log.warn("把String转换成int数据========== " + obj.toString());
		}
		return inum;
	}

	public static String getNullforString(Object obj) {
		isEmpty(obj);
		if (!isEmpty(obj)) {
			return String.valueOf(obj);
		}
		return "0";
	}

	/**
	 * 把String转换成double数据
	 * 
	 * @param str
	 * @param defaultData
	 * @return
	 */
	public static double getDouble(Object obj, Double defaultData) {
		double dnum = defaultData;
		if (isEmpty(obj)) {
			return dnum;
		}
		try {
			dnum = Double.valueOf(obj.toString().trim()).doubleValue();
		} catch (NumberFormatException e) {
			log.warn("把String转换成double数据========== " + obj.toString());
		}
		return dnum;
	}

	/**
	 * 把String转换成float数据
	 * 
	 * @param str
	 * @param defaultData
	 * @return
	 */
	public static float getFloat(Object obj, Float defaultData) {
		float dnum = defaultData;
		if (isEmpty(obj)) {
			return dnum;
		}
		try {
			dnum = Float.valueOf(obj.toString().trim()).floatValue();
		} catch (NumberFormatException e) {
			log.warn("把String转换成float数据========== " + obj.toString());
		}
		return dnum;
	}

	public static Boolean isEmpty(Object obj) {

		String s = getString(obj);
		if (s == null || s.length() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 按code截取字符chuan
	 * 
	 * @return
	 */
	public static String[] split(String str, String code) {
		String[] split;
		if (isEmpty(str)) {
			split = null;
		} else {
			split = str.split(code);
		}
		return split;
	}

	/**
	 * 吧字符串按code 转换为List<Long> 默认不过滤掉null
	 * 
	 * @param str
	 * @return
	 */
	public static List<Long> changeStringToLong(String str, String code) {

		return changeStringToLong(str, code, false);
	}

	/**
	 * 
	 * @param str
	 * @param code
	 * @param notNull true过滤掉null
	 * @return 王强
	 */
	public static List<Long> changeStringToLong(String str, String code, boolean notNull) {
		String[] split = split(str, code);
		List<Long> lnums = new ArrayList<>();
		for (String s : split) {
			if (!isEmpty(s)) {
				long lnum = getLong(s, 0l);
				if (!notNull) {
					lnums.add(lnum);
				} else {
					if (lnum > 0l) {
						lnums.add(lnum);
					}
				}

			}
		}
		return lnums;
	}

	/**
	 * 吧字符串按code 转换为List<String>
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> changeStringToString(String str, String code) {
		String[] split = split(str, code);
		List<String> lnums = new ArrayList<>();
		for (String s : split) {
			// long lnum = getLong(s, 0l);
			lnums.add(s);
		}
		return lnums;
	}

	/**
	 * 吧字符串按code 转换为List<String>
	 * 
	 * @param str
	 * @param code
	 * @param notNull true过滤掉null
	 * @return 2019年6月11日 上午11:21:56 王强
	 */
	public static List<String> changeStringToString(String str, String code, boolean notNull) {
		String[] split = split(str, code);
		List<String> lnums = new ArrayList<>();
		for (String s : split) {
			// long lnum = getLong(s, 0l);
			if (notNull) {
				if (!isEmpty(s)) {
					lnums.add(s);
				}
			} else {
				lnums.add(s);
			}
		}
		return lnums;
	}

	/**
	 * 吧字符串按code 转换为List<Long>
	 * 
	 * @param str
	 * @return
	 */
	public static List<Integer> changeStringToInteger(String str, String code) {
		String[] split = split(str, code);
		List<Integer> inums = new ArrayList<>();
		for (String s : split) {
			int inum = getInt(s, 0);
			inums.add(inum);
		}
		return inums;
	}

	/**
	 * 生成唯一订单
	 * 
	 * @return
	 */
	public static String getOrderNumberByUUID() {

		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		String orderNumber = machineId + String.format("%015d", hashCodeV);
		return orderNumber;

	}

	/**
	 * 获取用户的唯一标识
	 * 
	 * @return 王强
	 */
	public static String getUserOneCode() {
		String str = machineId + UUID.randomUUID().toString();

		return str;
	}


	/**
	 * list小于0的数据就过滤 把list的数组变1,1,1,1,1,1,
	 * 
	 * @param stringList
	 * @return
	 */
	public static String listToString(List<Long> list, String code) {
		return listToString(list, code, false, true);
	}

	/**
	 * list小于0的数据就过滤 把list的数组变String
	 * 
	 * @param list
	 * @param code
	 * @param havePre false 1,1,1,1,1,1, true ,1,1,1,1,1,1,
	 * @return 王强
	 */
	public static String listToString(List<Long> list, String code, boolean havePre) {

		return listToString(list, code, true, true);
	}

	/**
	 * 
	 * @param list     列表
	 * @param code     code分割码
	 * @param havePre  是否在最前面拼接code
	 * @param haveLast 是否在最后面拼接code
	 * @return 王强
	 */
	public static String listToString(List<Long> list, String code, boolean havePre, boolean haveLast) {
		String s = "";
		if (list == null || list.size() <= 0) {
			return s;
		}
		for (Long l : list) {
			if (l.longValue() > 0) {
				s = s + l + code;
			}
		}
		if (havePre && !isEmpty(s)) {
			s = code + s;
		}
		if (!haveLast && !isEmpty(s)) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	public static String listStringToString(List<String> list, String code, boolean havePre, boolean haveLast) {

		StringBuffer strBuffer = new StringBuffer();

		if (list == null || list.size() <= 0) {
			return "";
		}
		for (String l : list) {
			strBuffer.append(l + code);
		}
		if (havePre && strBuffer.length() > 0) {
			strBuffer.insert(0, ",");
		}
		if (!haveLast && strBuffer.length() > 0) {
			return strBuffer.substring(0, strBuffer.length() - 1);
		}
		return strBuffer.toString();

	}


	public static BigDecimal getBigDecimal(Object obj) {
		BigDecimal inum = new BigDecimal("0.00");

		if (isEmpty(obj)) {
			return inum;
		}

		try {
			inum = new BigDecimal(obj.toString());
		} catch (NumberFormatException e) {
			log.warn("把String转换成BigDecimal数据========== " + obj.toString());
		}
		return inum;
	}

	public static void main(String[] args) {
		String TRAN_ID = StringUtil.getOrderNumberByUUID();
		boolean flag = true;
		int i = 4;
		while (flag) {
			if (TRAN_ID.substring(i, TRAN_ID.length()).length() > 12) {
				i = i + 1;
			} else {
				TRAN_ID = TRAN_ID.substring(i, TRAN_ID.length());
				flag = false;
			}
		}
		System.out.println(TRAN_ID);
	}

	/**
	 * 判断是否为金额数字
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumberic(String s) {
		return patternAmount.matcher(s).matches();
	}
}
