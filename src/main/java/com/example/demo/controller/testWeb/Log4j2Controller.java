package com.example.demo.controller.testWeb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** * @author 作者 zuoruibo: 
    * @date 创建时间：2020年10月29日 下午2:55:02 
    * @version 1.0 
    * @parameter 
    * @since 
    * @return */
@RestController
public class Log4j2Controller {
	private static final Logger LOGGER = LoggerFactory.getLogger(Log4j2Controller.class);
    /**
     * 日志级别
     * OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL
     */
    @RequestMapping("/printLog")
    public String printLog (){
        LOGGER.error("ERROR 级别日志");
        LOGGER.warn("WARN 级别日志");
        LOGGER.info("INFO 级别日志");
        LOGGER.debug("DEBUG 级别日志");
        LOGGER.trace("TRACE 级别日志");
        return "success" ;
    }
}
