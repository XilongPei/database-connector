package com.github.liaochong.database.connector;

import java.util.Arrays;

/**
 * @author liaochong
 * @version 1.0
 */
public enum JdbcJavaFieldMapping {
    /**
     * int
     */
    INT("INT", "Long", null),

    TINYINT("TINYINT", "Integer", null),

    SMALLINT("SMALLINT", "Integer", null),

    BIGINT("BIGINT", "Long", null),

    CHAR("CHAR", "String", null),

    /**
     * varchar
     */
    STRING("VARCHAR", "String", null),

    /**
     * datetime
     */
    DATE("DATETIME", "LocalDateTime", "java.time.LocalDateTime"),

    TIMESTAMP("TIMESTAMP", "LocalDateTime", null),

    LONGNVARCHAR("LONGNVARCHAR", "String", null),

    NVARCHAR("NVARCHAR", "String", null),

    NCHAR("NCHAR", "String", null);

    private String jdbcType;

    private String javaTypeAlias;

    private String importString;

    JdbcJavaFieldMapping(String jdbcType, String javaTypeAlias, String importString) {
        this.jdbcType = jdbcType;
        this.javaTypeAlias = javaTypeAlias;
        this.importString = importString;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public String getJavaTypeAlias() {
        return javaTypeAlias;
    }

    public String getImportString() {
        return importString;
    }

    public static JdbcJavaFieldMapping get(String jdbcType) {
        return Arrays.stream(JdbcJavaFieldMapping.values()).filter(val -> jdbcType.startsWith(val.getJdbcType())).findFirst().orElse(null);
    }
}
