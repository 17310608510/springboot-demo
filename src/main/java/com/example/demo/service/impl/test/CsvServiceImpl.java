package com.example.demo.service.impl.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.test.DataInfo;
import com.example.demo.service.test.CsvService;

/**
 * * @author 作者 zuoruibo:
 * 
 * @date 创建时间：2020年10月28日 下午2:50:43
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class CsvServiceImpl implements CsvService {
	private static Logger LOGGER = LoggerFactory.getLogger(CsvServiceImpl.class);

	@Async
	@Override
	public void readNotify(String path, Integer columnSize) throws Exception {
		File file = new File(path);
		String fileName = file.getName();
		int lineNum = 0;
		if (fileName.startsWith("data-")) {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
			BufferedReader reader = new BufferedReader(isr);
			List<DataInfo> dataInfoList = new ArrayList<>(4);
			String line;
			while ((line = reader.readLine()) != null) {
				lineNum++;
				String[] dataArray = line.split(",");
				if (dataArray.length == columnSize) {
					String cityName = new String(dataArray[1].getBytes(), "UTF-8");
					dataInfoList.add(new DataInfo(Integer.parseInt(dataArray[0]), cityName, dataArray[2]));
				}
				if (dataInfoList.size() >= 4) {
					LOGGER.info("容器数据：" + dataInfoList);
					dataInfoList.clear();
				}
			}
			if (dataInfoList.size() > 0) {
				LOGGER.info("最后数据：" + dataInfoList);
			}
			reader.close();
		}
		LOGGER.info("读取数据条数：" + lineNum);
	}

	@Async
	@Override
	public void createCsv(List<String> dataList, String path) throws Exception {
		File file = new File(path);
		boolean createFile = false;
		if (file.exists()) {
			boolean deleteFile = file.delete();
			LOGGER.info("deleteFile：" + deleteFile);
		}
		createFile = file.createNewFile();
		OutputStreamWriter ost = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
		BufferedWriter out = new BufferedWriter(ost);
		if (createFile) {
			for (String line : dataList) {
				if (!StringUtils.isEmpty(line)) {
					out.write(line);
					out.newLine();
				}
			}
		}
		out.close();
	}
}
