package com.github.liaochong.database.connector;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liaochong
 * @version 1.0
 */
public class DatabaseConnector {

    private static final String[] TABLE_TYPE = {"TABLE"};

    private ConnectionConfig config;

    private List<TableInfo> tableInfoContainer;

    private DatabaseConnector() {
        // do nothing
    }

    public static DatabaseConnector getConnector(ConnectionConfig config) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.config = config;
        return connector;
    }

    /**
     * 获取表信息
     *
     * @return List<TableInfo>
     */
    public List<TableInfo> getTableInfo() {
        try {
            this.setAllTableInfo();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfoContainer;
    }

    /**
     * 获取数据库连接
     *
     * @return Connection
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException           SQLException
     */
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(config.getDriver());
        if (config.getSchema() != null && !config.getUrl().endsWith(config.getSchema())) {
            config.setUrl(config.getUrl() + "/" + config.getSchema());
        }
        return DriverManager.getConnection(config.getUrl(), config.getUserName(), config.getPassword());
    }

    /**
     * 设置所有表信息
     *
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException           SQLException
     */
    private void setAllTableInfo() throws ClassNotFoundException, SQLException {
        Connection con = getConnection();
        DatabaseMetaData metaDate = con.getMetaData();
        //1.得到数据库下所有数据表
        ResultSet rs = metaDate.getTables(null, config.getSchema(), "%", TABLE_TYPE);

        if (tableInfoContainer == null) {
            tableInfoContainer = new ArrayList<TableInfo>();
        }
        tableInfoContainer.clear();
        while (rs.next()) {
            getTableInfo(con, rs);
        }
    }

    /**
     * 获取某一张表信息
     *
     * @param con 连接
     * @param rs  结果集
     * @throws SQLException SQLException
     */
    private void getTableInfo(Connection con, ResultSet rs) throws SQLException {
        String tableName = rs.getString(3);
        String sql = "SELECT  *  FROM " + tableName + " WHERE 1=2;";
        PreparedStatement prep = con.prepareStatement(sql);
        ResultSet set = prep.executeQuery(sql);
        ResultSetMetaData data = set.getMetaData();

        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);

        //迭代取到所有列信息
        for (int i = 1, size = data.getColumnCount(); i <= size; i++) {
            FieldInfo fieldInfo = new FieldInfo();

            fieldInfo.setFieldName(data.getColumnName(i));
            fieldInfo.setDisplaySize(data.getColumnDisplaySize(i));
            fieldInfo.setTypeName(data.getColumnTypeName(i));
            fieldInfo.setClassName(data.getColumnClassName(i));
            fieldInfo.setNullable(data.isNullable(i) == 1);
            fieldInfo.setCurrency(data.isCurrency(i));
            fieldInfo.setAutoIncrement(data.isAutoIncrement(i));

            tableInfo.getFieldInfos().add(fieldInfo);
        }
        tableInfoContainer.add(tableInfo);
    }

}
