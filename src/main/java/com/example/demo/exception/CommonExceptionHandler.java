package com.example.demo.exception;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.entity.ErrorLog;
import com.example.demo.service.ErrorLogService;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.ResultUtil;
import com.example.demo.util.StringUtil;

/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月30日 下午3:04:52 
    * @version 1.0 
    * @parameter 
    * @since 全局异常
    * @return */
@RestControllerAdvice
public class CommonExceptionHandler {
private Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
	
	@Autowired
	private ErrorLogService errorLogService;
	
	/**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest req,Exception ex) {
        Map map = new HashMap();
        map.put("code", 100);
        map.put("url", req.getRequestURL());
        map.put("params", req.getParameterMap());
        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = req.getHeader(key);
            map.put(key, value);
        }
        Date date = new Date(System.currentTimeMillis());
        ErrorLog errorLog = new ErrorLog();
        errorLog.setUrl(StringUtil.getString(map.get("url")));
        errorLog.setExceptionDesc(ex.getMessage());
        errorLog.setParams(StringUtil.getString(map.get("params")));
        errorLog.setProjectName("sms-review");
        errorLog.setCreateTime(date);
        errorLogService.save(errorLog);
        log.error("异常点："+JsonUtil.objectToJson(map));
        log.error("记录异常={}",ex.getMessage(),ex);
        return ResultUtil.result("", "服务器错误");
    }
    
    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(APIException.class)
    public String APIExceptionHandler(APIException e) {
        return e.getMsg();
    }
}
