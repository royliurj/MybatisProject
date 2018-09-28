package com.roy.simple;

import com.roy.simple.model.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

public class MybatisTest {


    static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init(){
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. 根据xml配置文件创建SqlSessionFactory
     * 2. 创建SqlSession对象
     */
    @Test
    public void test() throws IOException {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            Employee employee = sqlSession.selectOne("com.roy.simple.EmployeeMapper.selectOne",1);
            System.out.println(employee);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() throws IOException {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            List<Employee> list = sqlSession.selectList("com.roy.simple.EmployeeMapper.selectAll");
            System.out.println(list);

        }finally {
            sqlSession.close();
        }
    }
}
