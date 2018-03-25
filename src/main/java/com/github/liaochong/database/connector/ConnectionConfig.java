package com.github.liaochong.database.connector;

import lombok.Data;

/**
 * @author liaochong
 * @version 1.0
 */
@Data
public class ConnectionConfig {
    /**
     * 数据库url
     */
    String url;
    /**
     * 数据库用户名
     */
    String userName;
    /**
     * 数据库密码
     */
    String password;
    /**
     * 数据库驱动
     */
    String driver;
    /**
     * 数据库
     */
    String schema;
    /**
     * 选择表
     */
    String[] tables;
}
