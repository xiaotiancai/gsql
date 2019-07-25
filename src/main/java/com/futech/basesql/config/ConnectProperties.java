/**
 * @des 有问题请留言github或者邮箱找我
 * @author 719383495@qq.com
 * @date 2019/7/25 23:22
 */
package com.futech.basesql.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectProperties {

    private Properties properties = new Properties();

    private String driveName;
    private String url;
    private String user;
    private String pwd;

    public void init() {
        loadConfig("application.properties");
    }

    private void loadConfig(String config) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(config);
        try {
            properties.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ConnectProperties() {
        init();
        driveName = properties.getProperty("spring.datasource.driver-class-name");
        url = properties.getProperty("spring.datasource.url");
        user = properties.getProperty("spring.datasource.username");
        pwd = properties.getProperty("spring.datasource.password");
    }

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
