package com.example.demo.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月30日 下午3:12:13
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class JsonUtil {
	public static Logger log = LoggerFactory.getLogger(JsonUtil.class);
	public static Gson gson = null;

	static {
		gson = new GsonBuilder().registerTypeAdapter(new TypeToken<Map<String, Object>>()
        {
        }.getType(), new GsonTypeAdapter()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}

	private JsonUtil() {

	}

	public static String objectToJson(Object obj) {

		try {
			String json = gson.toJson(obj);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Object jsonToObject(String jsonString, Class clazz) {

		Object obj = null;
		try {
			obj = gson.fromJson(jsonString, clazz);
		} catch (JsonSyntaxException e) {

			e.printStackTrace();
		}
		return obj;
	}

	public static ArrayList jsonArrayToArrayList(String jsonArray) {

		ArrayList list = null;
		try {
			Type listType = new TypeToken<ArrayList>() {
			}.getType();

			list = gson.fromJson(jsonArray, listType);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Map<String, String>> jsonArrayToArrayListMap(String jsonArray) {

		ArrayList<Map<String, String>> list = null;
		try {
			Type listType = new TypeToken<ArrayList<Map<String, String>>>() {
			}.getType();

			list = gson.fromJson(jsonArray, listType);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return list;
	}


	public static Map<String, Object> jsonToMap(String json) {

		Map<String, Object> map = null;
		try {
			Type type = new TypeToken<Map<String, Object>>() {
			}.getType();

			map = gson.fromJson(json, type);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String, String> jsonToMapStr(String json) {

		Map<String, String> map = null;
		try {
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();

			map = gson.fromJson(json, type);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		return map;
	}
}
