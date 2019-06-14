package com.itheima.code.build;

import freemarker.template.Template;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Controller构建
 * @Date 2019/6/14 19:13
 *****/
public class ControllerBuilder {

    /***
     * 构建Controller
     * @param table
     * @param Table
     */
    public static void builder(String table,String Table){
        //生成Controller层文件
        BuilderFactory.builder(table,
                Table,
                "/template/controller",
                "Controller.java",
                TemplateBuilder.PACKAGE_CONTROLLER,
                "Controller.java");
    }

}
