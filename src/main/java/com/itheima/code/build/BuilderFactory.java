package com.itheima.code.build;

import freemarker.template.Template;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:构建对象的工厂
 * @Date 2019/6/14 23:21
 *****/
public class BuilderFactory {

    /***
     * 构建Controller
     * @param table
     * @param Table
     */
    public static void builder(String table,        //表优化后的名字
                                String Table,        //优化后，首字母大写
                                String templatePath, //模板路径
                                String templateFile, //模板文件
                                String storePath,    //存储路径
                                String suffix){      //生成文件后缀名字
        try {
            //获取模板对象
            Template template = TemplateUtil.loadTemplate(ControllerBuilder.class.getResource(templatePath).getPath(), templateFile);

            //创建模板数据
            Map<String,Object> dataModel = new HashMap<String,Object>();
            dataModel.put("table",table);
            dataModel.put("Table",Table);

            //创建文件夹
            String path = TemplateBuilder.PROJECT_PATH+storePath.replace(".","/");
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }

            //创建文件
            TemplateUtil.writer(template,dataModel,path+"/"+Table+suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
