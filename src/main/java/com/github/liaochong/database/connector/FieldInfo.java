package com.github.liaochong.database.connector;

import lombok.Data;

/**
 * @author liaochong
 * @version 1.0
 */
@Data
public class FieldInfo {

    boolean isAutoIncrement;

    String fieldName;

    String typeName;

    int displaySize;

    String className;

    boolean isNullable;

    boolean isCurrency;

    JdbcJavaFieldMapping jdbcJavaFieldMapping;
}
