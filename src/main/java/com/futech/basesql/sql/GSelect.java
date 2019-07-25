/**
 * @des 有问题请留言github或者邮箱找我
 * @author 719383495@qq.com
 * @date 2019/7/25 23:22
 */
package com.futech.basesql.sql;

import com.futech.basesql.config.JDBCConfig;
import com.futech.basesql.utils.Res;

import java.util.HashMap;
import java.util.Map;

public class GSelect {

    public Res GSelect(String tableName, Map condition) {
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

        sb.append("select * ");
        sb.append(" from ");
        sb.append(tableName);
        sb.append(" where ");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" = ");
            sb.append("\'");
            sb.append(entry.getValue());
            sb.append("\'");
            sb.append(" and ");
        }
        int len0 = sb.length();
        sb.deleteCharAt(len0-2);
        sb.deleteCharAt(len0-3);
        sb.deleteCharAt(len0-4);

        return jdbcConfig.sqlQueryHandler(sb.toString(), columnNames);
    }
}
