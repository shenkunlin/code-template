package com.itheima.code.build;

import freemarker.template.Template;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Pojo构建
 * @Date 2019/6/14 19:13
 *****/
public class PojoBuilder {


    /***
     * 构建Pojo
     * @param dataModel
     */
    public static void builder(Map<String,Object> dataModel){
        try {
            //获取模板对象
            Template template = TemplateUtil.loadTemplate(PojoBuilder.class.getResource("/template/pojo").getPath(), "Pojo.java");

            //创建文件夹
            String path = TemplateBuilder.PROJECT_PATH+TemplateBuilder.PACKAGE_POJO.replace(".","/");
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }

            //创建控制器
            TemplateUtil.writer(template,dataModel,path+"/"+dataModel.get("Table").toString()+".java");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
