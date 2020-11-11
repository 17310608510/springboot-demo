package com.example.demo.param;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月29日 上午11:11:34
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public enum BodyType {
	EMAIL_TEXT_BODY("email_text_key", "您好：%s,我是：%s"), EMAIL_IMAGE_BODY("email_image_key", "图片名称：%s"),
	EMAIL_FILE_BODY("email_file_key", "文件名称：%s");

	private String code;
	private String value;

	BodyType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getByCode(String code) {
		BodyType[] values = BodyType.values();
		for (BodyType bodyType : values) {
			if (bodyType.code.equalsIgnoreCase(code)) {
				return bodyType.value;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
