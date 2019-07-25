/**
 * @des 有问题请留言github或者邮箱找我
 * @author 719383495@qq.com
 * @date 2019/7/25 23:22
 */
package com.futech.basesql.sql;

import com.futech.basesql.config.JDBCConfig;

import java.util.HashMap;
import java.util.Map;

public class GUpdate {

    public GUpdate(String tableName, Map condition1, Map condition2) {

        JDBCConfig jdbcConfig = new JDBCConfig();
        Map<String, String> columnNames = jdbcConfig.queryTableColumnName(tableName);

        Map<String, String> params1 = new HashMap();
        Map<String, String> params2 = new HashMap();

        StringBuffer sb = new StringBuffer();


        for (Map.Entry<String, String> entry : columnNames.entrySet()) {
            if (null == condition1.get(entry.getKey()) || "".equals(condition1.get(entry.getKey()))) {

            } else {
                params1.put(entry.getValue(), condition1.get(entry.getKey()).toString());
            }
        }

        for (Map.Entry<String, String> entry : columnNames.entrySet()) {
            if (null == condition2.get(entry.getKey()) || "".equals(condition2.get(entry.getKey()))) {

            } else {
                params2.put(entry.getValue(), condition2.get(entry.getKey()).toString());
            }
        }

        sb.append("update ");
        sb.append(tableName);
        sb.append(" set ");
        for (Map.Entry<String, String> entry : params1.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" = ");
            sb.append("\'");
            sb.append(entry.getValue());
            sb.append("\'");
        }
        sb.append(" where ");

        for (Map.Entry<String, String> entry : params2.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" = ");
            sb.append("\'");
            sb.append(entry.getValue());
            sb.append("\'");
        }

        jdbcConfig.sqlInsertHandler(sb.toString());
    }

}
