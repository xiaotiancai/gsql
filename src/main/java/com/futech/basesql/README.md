
#### 仅对单表操作(后期维护会增加夺标操作)
- 实现一个数据库的一个表名一个map字段就可以根据map中传入的字段进行sql

&nbsp;&nbsp;&nbsp;&nbsp;例如：
&nbsp;&nbsp;&nbsp;&nbsp;new GUpdate("t_application_attachment", map1, map2);

&nbsp;&nbsp;&nbsp;&nbsp;update是传入两个map,
&nbsp;&nbsp;&nbsp;&nbsp;一般的update语句是 update t_application_attachment set 
&nbsp;&nbsp;&nbsp;&nbsp;(map1即为改变值,可以有多组) where (map2参数即为条件)

&nbsp;&nbsp;&nbsp;&nbsp;同理可知insert和select只需要传入一个map就可以进行操作了。

#### 可以根据公司写的框架集成

我根据公司的框架将我自己写的工具类集成到一起，将这个demo进行了大的更新和优化。
将里面的代码进行了更新，有空的时候会将代码update。

#### 简单的梳理
1. 根据一条语句先拿到数据库中的列名和驼峰规则命名
2. 拿到列名之后和map中的字段进行匹配，然后映射。
3. 拼sql语句
4. 数据库连接池


还有很多需要完善的地方,写的也不是很好,希望可以缩减大家的代码量。

#### 用法
有个demo写在main函数中，我自己结合公司的框架进行了集成以及更新。

直接new一个对象，再也不用写sql了！