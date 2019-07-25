/**
 * @des 有问题请留言github或者邮箱找我
 * @author 719383495@qq.com
 * @date 2019/7/25 23:22
 */
package com.futech.basesql;

import com.futech.basesql.sql.GUpdate;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        map1.put("applicationId", "Game1");
        map2.put("processId","234");
//        new GSelect("t_application_attachment", map);
        new GUpdate("t_application_attachment", map1, map2);

    }

}
