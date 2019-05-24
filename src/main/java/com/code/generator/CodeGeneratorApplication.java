package com.code.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动程序
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.code.*.mapper")
public class CodeGeneratorApplication {

    private static final Logger logger = LoggerFactory.getLogger(CodeGeneratorApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        Environment env = SpringApplication.run(CodeGeneratorApplication.class, args).getEnvironment();
        String port = env.getProperty("server.port" , "8089");

        logger.info("Access URLs:\n----------------------------------------------------------\n\t"
                        + "Local: \t\thttp://127.0.0.1:{}\n\t"
                        + "External: \thttp://{}:{}\n\t" ,
                port, InetAddress.getLocalHost().getHostAddress(), port);

    }

}
