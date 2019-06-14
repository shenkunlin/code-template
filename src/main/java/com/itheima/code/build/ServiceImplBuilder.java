package com.itheima.code.build;

import freemarker.template.Template;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:ServiceImpl构建
 * @Date 2019/6/14 19:13
 *****/
public class ServiceImplBuilder {

    /***
     * ServiceImpl构建
     * @param table
     * @param Table
     */
    public static void builder(String table,String Table){
        //生成ServiceImpl层文件
        BuilderFactory.builder(table,
                Table,
                "/template/service/impl",
                "ServiceImpl.java",
                TemplateBuilder.PACKAGE_SERVICE_INTERFACE_IMPL,
                "ServiceImpl.java");
    }

}
