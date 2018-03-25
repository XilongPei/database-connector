package com.github.liaochong.database.connector;

import org.junit.Test;

import java.util.List;

/**
 * @author liaochong
 * @version 1.0
 */
public class DatabaseConnectorTest {

    @Test
    public void getTableInfoTest() {
        ConnectionConfig connectionConfig = new ConnectionConfig();
        connectionConfig.setUrl("jdbc:mysql://localhost:3306/manage");
        connectionConfig.setUserName("root");
        connectionConfig.setPassword("123456");
        connectionConfig.setDriver("com.mysql.jdbc.Driver");
        connectionConfig.setSchema("manage");

        List<TableInfo> tb = DatabaseConnector.getConnector(connectionConfig).getTableInfo();
        System.out.println("");
    }

}
