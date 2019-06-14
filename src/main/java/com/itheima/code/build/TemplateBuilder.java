package com.itheima.code.build;

import com.itheima.code.util.JavaTypes;
import com.itheima.code.util.ModelInfo;
import com.itheima.code.util.StringUtils;
import javafx.scene.control.Tab;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/****
 * @Author:shenkunlin
 * @Description:模板创建
 *               有该对象调用其他对象的构建
 * @Date 2019/6/14 19:14
 *****/
public class TemplateBuilder {

    //配置文件
    private static Properties props = new Properties();

    //pojoPackage
    public static String PACKAGE_POJO;

    //mapperPackage
    public static String PACKAGE_MAPPER;

    //serviceInterfacePackage
    public static String PACKAGE_SERVICE_INTERFACE;

    //serviceInterfaceImplPackage
    public static String PACKAGE_SERVICE_INTERFACE_IMPL;

    //controllerPackage
    public static String PACKAGE_CONTROLLER;

    //数据库账号
    public static String UNAME;

    //项目路径
    public static String PROJECT_PATH;

    //是否使用swagger
    public static Boolean SWAGGER;

    static {
        try {
            //加载配置文件
            InputStream is = TemplateBuilder.class.getClassLoader().getResourceAsStream("application.properties");

            //创建Properties对象
            props.load(is);

            //获取对应的配置信息
            PACKAGE_POJO = props.getProperty("pojoPackage");
            PACKAGE_MAPPER = props.getProperty("mapperPackage");
            PACKAGE_SERVICE_INTERFACE = props.getProperty("serviceInterfacePackage");
            PACKAGE_SERVICE_INTERFACE_IMPL = props.getProperty("serviceInterfaceImplPackage");
            PACKAGE_CONTROLLER = props.getProperty("controllerPackage");
            UNAME = props.getProperty("uname");
            SWAGGER = Boolean.valueOf(props.getProperty("enableSwagger"));
            //工程路径
            PROJECT_PATH=TemplateBuilder.class.getClassLoader().getResource("").getPath().replace("/target/classes/","")+"/src/main/java/";

            //加载数据库驱动
            Class.forName(props.getProperty("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 模板构建
     */
    public static void builder(){
        try {
            //获取数据库连接
            Connection conn = DriverManager.getConnection(props.getProperty("url"),props.getProperty("uname"),props.getProperty("pwd"));
            DatabaseMetaData metaData = conn.getMetaData();

            //获取数据库类型：MySQL
            String databaseType = metaData.getDatabaseProductName();

            //针对MySQL数据库进行相关生成操作
            if(databaseType.equals("MySQL")){
                //获取所有表结构
                ResultSet tableResultSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});

                //获取数据库名字
                String database = conn.getCatalog();

                //循环所有表信息
                while (tableResultSet.next()){
                    //获取表名
                    String tableName=tableResultSet.getString("TABLE_NAME");
                    //名字操作,去掉tab_,tb_，去掉_并转驼峰
                    String table = StringUtils.replace_(StringUtils.replaceTab(tableName));
                    //大写对象
                    String Table =StringUtils.firstUpper(table);

                    //创建Controller
                    ControllerBuilder.builder(table,Table);

                    //创建Dao
                    DaoBuilder.builder(table, Table);

                    //创建Service接口
                    ServiceBuilder.builder(table,Table);

                    //创建ServiceImpl实现类
                    ServiceImplBuilder.builder(table,Table);

                    //需要生成的Pojo属性集合
                    List<ModelInfo> models = new ArrayList<ModelInfo>();
                    //获取表所有的列
                    ResultSet cloumnsSet = metaData.getColumns(database, "root", tableName, null);
                    while (cloumnsSet.next()){
                        //列的描述
                        String remarks = cloumnsSet.getString("REMARKS");
                        //获取列名
                       String columnName = cloumnsSet.getString("COLUMN_NAME");
                       //处理列名
                        String propertyName = StringUtils.replace_(columnName);
                       //获取类型，并转成JavaType
                       String javaType = JavaTypes.getType(cloumnsSet.getInt("DATA_TYPE"));

                       //创建该列的信息
                       models.add(new ModelInfo(javaType, JavaTypes.simpleName(javaType),propertyName,StringUtils.firstUpper(propertyName),remarks, false));
                    }
                    //创建该表的JavaBean
                    Map<String,Object> modelMap = new HashMap<String,Object>();
                    //表信息存储
                    modelMap.put("table",table);
                    modelMap.put("Table",Table);
                    modelMap.put("swagger",SWAGGER);
                    modelMap.put("TableName",tableName);
                    modelMap.put("models",models);
                    //创建JavaBean
                    PojoBuilder.builder(modelMap);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
