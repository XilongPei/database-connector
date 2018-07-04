package com.github.liaochong.database.connector;

/**
 * @author liaochong
 * @version 1.0
 */
public enum JdbcJavaFieldMapping {
    /**
     * int
     */
    INT("INT", "java.lang.Long", "Long", false),
    /**
     * int unsigned
     */
    INT_UNSIGNED("INT UNSIGNED", "java.lang.Long", "Long", false),
    /**
     * varchar
     */
    STRING("VARCHAR", "java.lang.String", "String", false),
    /**
     * datetime
     */
    DATE("DATETIME", "java.time.LocalDateTime", "LocalDateTime", true);

    private String jdbcType;

    private String javaType;

    private String javaTypeAlias;

    private boolean toImport;

    JdbcJavaFieldMapping(String jdbcType, String javaType, String javaTypeAlias, boolean toImport) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.javaTypeAlias = javaTypeAlias;
        this.toImport = toImport;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getJavaTypeAlias() {
        return javaTypeAlias;
    }

    public boolean isToImport() {
        return toImport;
    }
}
