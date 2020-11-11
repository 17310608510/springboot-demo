package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月30日 下午3:07:32
 * @version 1.0
 * @parameter
 * @since 返回统一接口
 * @return
 */
public class ResultUtil {
	public static Map<String, Object> result(String code, Object data) {
		return result(code, 0, data);

	}

	public static Map<String, Object> result(String code, Integer status, Object data) {

		if (StringUtil.isEmpty(status)) {
			return result(code, data);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("code", code);
			map.put("status", status);
			map.put("data", data);
			return map;
		}
	}

	/**
	 * 
	 * @param code
	 * @param data
	 * @return
	 */
	public static String result_Json(String code, Object data) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("data", data);

		ObjectMapper objectMapper = new ObjectMapper();
		/**
		 * 序列换成json时,将所有的long变成string 因为js中得数字类型不能包含所有的java long值
		 */
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

		objectMapper.registerModule(simpleModule);

		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return writeValueAsString;
	}
}
