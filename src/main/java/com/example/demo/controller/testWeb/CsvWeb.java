package com.example.demo.controller.testWeb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.test.CsvService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月28日 下午2:53:36
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Api("Csv接口管理")
@RestController
public class CsvWeb {
	@Resource
	private CsvService csvService;

	/**
	 * 入参：F:\file-type\data-info.csv & 3
	 */
	@ApiOperation(value = "文件读取")
	@GetMapping("/csv/readNotify")
	public String readNotify(@RequestParam("path") String path, @RequestParam("column") Integer columnSize)
			throws Exception {
		csvService.readNotify(path, columnSize);
		return "success";
	}

	/**
	 * 入参：F:\file-type\data-info-new.csv
	 */
	@ApiOperation(value = "创建文件")
	@GetMapping("/csv/createCsv")
	public String createCsv(@RequestParam("path") String path) throws Exception {
		List<String> dataList = new ArrayList<>();
		dataList.add("1,北京,beijing");
		dataList.add("2,上海,shanghai");
		dataList.add("3,苏州,suzhou");
		csvService.createCsv(dataList, path);
		return "success";
	}
}
