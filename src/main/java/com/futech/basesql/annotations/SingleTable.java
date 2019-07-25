/**
 * @des 有问题请留言github或者邮箱找我
 * @author 719383495@qq.com
 * @date 2019/7/25 23:23
 */

package com.futech.basesql.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SingleTable {

    String tableName();

}
