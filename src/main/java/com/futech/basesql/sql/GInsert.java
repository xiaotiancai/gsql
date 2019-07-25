/**
 * @des 有问题请留言github或者邮箱找我
 * @author 719383495@qq.com
 * @date 2019/7/25 23:22
 */
package com.futech.basesql.sql;


import com.futech.basesql.config.JDBCConfig;

import java.util.HashMap;
import java.util.Map;

public class GInsert{

    public GInsert(String tableName, Map condition) {

        JDBCConfig jdbcConfig = new JDBCConfig();

        Map<String, String> columnNames = jdbcConfig.queryTableColumnName(tableName);

        Map<String, String> params = new HashMap();

        StringBuffer sb = new StringBuffer();



        for (Map.Entry<String,String> entry: columnNames.entrySet()) {
            if (null == condition.get(entry.getKey()) || "".equals(condition.get(entry.getKey()))) {

            } else {
                params.put(entry.getValue(),condition.get(entry.getKey()).toString());
            }
        }

        sb.append("insert into ");
        sb.append(tableName);
        sb.append(" ( ");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" , ");
        }
        int len0 = sb.length();
        sb.deleteCharAt(len0-2);

        sb.append(" ) ");
        sb.append(" values ");
        sb.append(" ( ");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append("\'");
            sb.append(entry.getValue());
            sb.append("\'");
            sb.append(" , ");
        }
        int len1 = sb.length();
        sb.deleteCharAt(len1 - 2);
        sb.append(" ) ");

        jdbcConfig.sqlInsertHandler(sb.toString());

    }

}
