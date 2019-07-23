package com.itheima.code.swagger;

import java.util.List;

/*****
 * @Author: shenkunlin
 * @Date: 2019/7/23 15:11
 * @Description: com.itheima.code.util
 *  Swagger请求路径配置
 ****/
public class SwaggerPath {

    //请求路径
    private String path;

    //方法配置
    private List<SwaggerMethod> methods;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SwaggerMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<SwaggerMethod> methods) {
        this.methods = methods;
    }
}
