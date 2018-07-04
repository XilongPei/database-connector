package com.github.liaochong.database.connector;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaochong
 * @version 1.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableInfo {

    String tableName;

    List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();

}
