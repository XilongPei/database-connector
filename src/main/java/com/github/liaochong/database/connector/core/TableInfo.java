package com.github.liaochong.database.connector.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaochong
 * @version 1.0
 */
@Data
public class TableInfo {

    String tableName;

    List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();

}
