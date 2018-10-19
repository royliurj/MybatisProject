package com.roy.plugs.plugin;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

/**
 * 完成插件的签名：
 *     告诉Mybatis当前插件用来拦截那个对象的那个方法
 */
@Intercepts(
        {
                @Signature(type = StatementHandler.class, method ="parameterize", args = java.sql.Statement.class )
        }
)
public class MyFirstPlugin implements Interceptor {
    /**
     * 拦截目标对象的目标方法的执行
     */
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("Intercept方法");
        //改变sql参数的值
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象："+target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("参数：" + value);

        metaObject.setValue("parameterHandler.parameterObject",4);
        //执行目标方法
        Object proceed = invocation.proceed();
        return proceed;
    }

    /**
     * 包装目标对象，为目标对象创建一个代理对象。
     */
    public Object plugin(Object target) {

        System.out.println("Plugin: " + target );

        //借助Plugin.wrap，来使用但钱的Intercepotr包装我们的对象
        Object wrap = Plugin.wrap(target, this);

        //返回当前target创建的动态代理
        return wrap;
    }

    /**
     * 将插件注册时property属性设置进来
     */
    public void setProperties(Properties properties) {

        System.out.println("properties" + properties);
    }
}
