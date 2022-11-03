package eu.jasha.demo.sbtfragments.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author yaolianhua789@gmail.com
 **/
@Configuration
@ConfigurationProperties("mysql")
@Slf4j
public class MysqlProperties {

    private String host;
    private String password;
    private Integer port;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @PostConstruct
    void print(){
        log.info("【检测mysql环境变量注入】mysql.host='{}', mysql.port= '{}', mysql.password='{}'", host ,port, password);
    }
}
