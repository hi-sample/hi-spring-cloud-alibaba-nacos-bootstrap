package net.hifor.bootstrap.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

/**
 * @author IKin <br/>
 * @description 第一个springboot  <br/>
 * @date 2019/11/17 <br/>
 */
@RestController
@RefreshScope
public class HelloSpringCloudController {

    @Value("${test:xxx}")
    private String test;

    /**
     * bootstrap模式会覆盖JVM系统属性
     */
    @Value("${pid:xxx}")
    private String pid;

    /**
     * bootstrap模式会覆盖操作系统环境变量
     */
    @Value("${computername:xxx}")
    private String computerName;

    @Value("${abc:xxx}")
    private String abc;

    /**
     * spring cloud 配置中心
     *
     * @return
     */
    @GetMapping("/config")
    public String hello() {
        return "Hello Spring cloud !"
                +"<br/> test: " + test
                +"<br/> computerName: " + computerName
                +"<br/> pid: " + pid
                +"<br/> abc: " + abc
                ;
    }

    /**
     * JVM系统属性+操作系统环境变量
     * @return
     */
    @GetMapping("/env")
    public String env(){
        Properties properties = System.getProperties();
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            System.out.println(key + " = " + value);
        }

        System.out.println("\n\n===============================================================\n\n");

        Map<String, String> envVariables = System.getenv();
        for (Map.Entry<String, String> entry : envVariables.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " = " + value);
        }
        return "env";
    }
}
