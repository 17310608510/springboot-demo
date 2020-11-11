package com.example.demo.controller.testWeb;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.ExcelUtil;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月28日 上午11:23:00
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@RestController
public class ExcelWeb {
	@RequestMapping("/web/outExcel")
	public void outExcel(HttpServletResponse response) throws Exception {
		String exportName = "2020-01-user-data";
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=" + URLEncoder.encode(exportName, "UTF-8") + ".xlsx");
		List<List<Object>> dataList = ExcelUtil.readExcel("F:\\file-type\\user-excel.xlsx");
		String[] headList = new String[] { "用户ID", "用户名", "手机号" };
		ExcelUtil.exportExcel(headList, dataList, response.getOutputStream());
	}
}
