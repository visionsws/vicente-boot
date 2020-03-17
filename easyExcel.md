Java解析、生成Excel比较有名的框架有Apache poi、jxl。但他们都存在一个严重的问题就是非常的耗内存，poi有一套SAX模式的API可以一定程度的解决一些内存溢出的问题，但POI还是有一些缺陷，比如07版Excel解压缩以及解压后存储都是在内存中完成的，内存消耗依然很大。easyexcel重写了poi对07版Excel的解析，能够原本一个3M的excel用POI sax依然需要100M左右内存降低到几M，并且再大的excel不会出现内存溢出，03版依赖POI的sax模式。在上层做了模型转换的封装，让使用者更加简单方便

### 一、准备工作

使用mybatis-plus来批量保存一个表中的数据

#### 1、新建表

```sql
CREATE TABLE `demo_excel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `page_num` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)
```

#### 2、创建批量插入的方法

在service中添加方法

```java
@Override
    public void saveList(List<DemoExcel> list) {
        this.baseMapper.batchInsert(list);
    }
```

在mapper中添加方法

```java
/**
     * 批量插入
     * @param users
     */
    void batchInsert(List<DemoExcel> users);
```

在mapper.xml中添加批量插入

```xml
<insert id="batchInsert" parameterType="java.util.List" useGeneratedKeys="true" keyColumn="id" keyProperty="id">
        INSERT INTO `demo_excel`
        (
        title, content, page_num, create_time
        )
        VALUES
        <foreach collection="list" item="item" separator=",">
            (
            #{item.title}, #{item.content}, #{item.pageNum}, #{item.createTime}
            )
        </foreach>
    </insert>
```

#### 3、测试

```java
 @Autowired
    private DemoExcelService demoExcelService;

    @Test
    public void testSaveList() {
        DemoExcel excel = new DemoExcel();
        excel.setTitle("标题1");
        excel.setContent("内容11111");
        excel.setPageNum(3);
        excel.setCreateTime(LocalDateTime.now());
        List<DemoExcel> list = new ArrayList<>();
        list.add(excel);
        demoExcelService.saveList(list);
    }
```

#### 4、创建一张excel表

| 标题  | 内容                                               | 页码 | 创建时间        |
| ----- | -------------------------------------------------- | ---- | --------------- |
| 标题1 | 的路上风景萨拉丁解放啦电缆附件是，的附加赛决定弗拉 | 5    | 2019/4/24 16:00 |
| 标题2 | 的路上风景萨拉丁解放啦电缆附件是，的附加赛决定弗拉 | 5    | 2019/4/25 16:00 |
| 标题3 | 的路上风景萨拉丁解放啦电缆附件是，的附加赛决定弗拉 | 5    | 2019/4/26 16:00 |
| 标题4 | 的路上风景萨拉丁解放啦电缆附件是，的附加赛决定弗拉 | 5    | 2019/4/27 16:00 |



### 二、导入

####  1、pom.xml 中添加依赖

```
<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>easyexcel</artifactId>
			<version>2.1.6</version>
		</dependency>
```

#### 3、DemoEasyExcel 用于导入导出的对象

不建议直接在 demoExcel 实体类上加 excel 相关注解，所以我们创建了一个专门用于 excel 的类，属性类型这里全部用 String，防止类型不一致无法转换报异常

```java
@Data
public class DemoEasyExcel  {

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("内容")
    private String content;

    @ExcelProperty("页码")
    private String pageNum;

    @ExcelProperty("创建时间")
    private String createTime;

}
```

#### 4、创建监听器

```java
package com.vicente.vicenteboot.easyexcel;

public class DemoExcelListener extends AnalysisEventListener<DemoEasyExcel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoExcelListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<DemoEasyExcel> list = new ArrayList<DemoEasyExcel>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private DemoExcelService demoExcelService;

    public DemoExcelListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        demoExcelService = new DemoExcelServiceImpl();
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param demoExcelService
     */
    public DemoExcelListener(DemoExcelService demoExcelService) {
        this.demoExcelService = demoExcelService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DemoEasyExcel data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        demoExcelService.saveExcelList(list);
        LOGGER.info("存储数据库成功！");
    }
}
```

这里有一个saveExcelList方法，主要就是将从excel中读取到的DemoEasyExcel 转换成数据库的类型DemoExcel

```java
@Override
    public void saveExcelList(List<DemoEasyExcel> list) {
        List<DemoExcel> demoList = new ArrayList<>();
        for (DemoEasyExcel easyExcel : list) {
            DemoExcel demo = new DemoExcel();
            demo.setTitle(easyExcel.getTitle());
            demo.setContent(easyExcel.getContent());
            demo.setPageNum(Integer.parseInt(easyExcel.getPageNum()));
            LocalDateTime time = LocalDateTime.parse(easyExcel.getCreateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            demo.setCreateTime(time);
            demoList.add(demo);
        }
        this.saveList(demoList);
    }
```

#### 5、测试

测试读取excel，并将内容写入到数据库中

```java
@Autowired
    private DemoExcelService demoExcelService;

    @Test
    public void testReadExcel() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "D://test_excel.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoEasyExcel.class, new DemoExcelListener(demoExcelService)).sheet().doRead();

    }
```

查看数据库表，成功将excel中的数据写进去

读取第N个sheet

```java
 // 写法2：
    String fileName = "D://test_excel.xlsx";
    ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
    ReadSheet readSheet = EasyExcel.readSheet(0).build();
    excelReader.read(readSheet);
    // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
    excelReader.finish();
```

读取全部的sheet

```java
// 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
    EasyExcel.read(fileName, DemoEasyExcel.class, new DemoExcelListener()).doReadAll();
```

读取部分的sheet

```java
ExcelReader excelReader = EasyExcel.read(fileName).build();
    // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
    ReadSheet readSheet1 =
        EasyExcel.readSheet(0).head(DemoEasyExcel.class).registerReadListener(new DemoExcelListener()).build();
    ReadSheet readSheet2 =
        EasyExcel.readSheet(1).head(DemoEasyExcel.class).registerReadListener(new DemoExcelListener()).build();
    // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
    excelReader.read(readSheet1, readSheet2);
    // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
    excelReader.finish();
```



<https://liuyanzhao.com/10060.html>

<https://alibaba-easyexcel.github.io/quickstart/read.html>