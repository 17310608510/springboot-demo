package com.example.demo.service.test;

import java.util.List;

/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月28日 下午2:50:02 
    * @version 1.0 
    * @parameter 
    * @since 
    * @return */
public interface CsvService {
	void readNotify (String path, Integer columnSize) throws Exception ;

    void createCsv (List<String> dataList,String path) throws Exception ;
}
