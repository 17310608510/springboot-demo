package com.example.demo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月30日 下午3:18:10
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class GsonTypeAdapter extends TypeAdapter<Object> {
	@Override
	public Object read(JsonReader in) throws IOException {
		// 反序列化
		JsonToken token = in.peek();
		switch (token) {
		case BEGIN_ARRAY:

			List<Object> list = new ArrayList<Object>();
			in.beginArray();
			while (in.hasNext()) {
				list.add(read(in));
			}
			in.endArray();
			return list;

		case BEGIN_OBJECT:

			Map<String, Object> map = new HashMap<String, Object>();
			in.beginObject();
			while (in.hasNext()) {
				map.put(in.nextName(), read(in));
			}
			in.endObject();
			return map;

		case STRING:

			return in.nextString();

		case NUMBER:

			/**
			 * 改写数字的处理逻辑，将数字值分为整型与浮点型。
			 */
			double dbNum = in.nextDouble();

			// 数字超过long的最大值，返回浮点类型
			if (dbNum > Long.MAX_VALUE) {
				return dbNum;
			}

			// 判断数字是否为整数值
			long lngNum = (long) dbNum;
			if (dbNum == lngNum) {
				return lngNum;
			} else {
				return dbNum;
			}

		case BOOLEAN:
			return in.nextBoolean();

		case NULL:
			in.nextNull();
			return null;

		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public void write(JsonWriter out, Object value) throws IOException {
		// 序列化不处理
	}
}
