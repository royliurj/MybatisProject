package com.roy.mybatis.plus;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.roy.plugs.bean.Employee;
import com.roy.plugs.bean.EmployeeExample;
import com.roy.plugs.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

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

    @org.junit.Test
    public void aaa() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    //测试j
    @org.junit.Test
    public void testSimple() throws IOException {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.selectByPrimaryKey(3);
            System.out.println(employee);

        }finally {
            sqlSession.close();
        }
    }
    @org.junit.Test
    public void testMyBatis3() throws IOException {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            //Criteria就是拼装查询条件的
            criteria.andLastNameLike("%1%");
            criteria.andDeptIdEqualTo(2);

            //Or另一个条件
            EmployeeExample.Criteria criteria2 = employeeExample.createCriteria();
            criteria2.andGenderEqualTo("0");

            employeeExample.or(criteria2);
            List<Employee> list = mapper.selectByExample(employeeExample);

            for (Employee employee : list) {
                System.out.println(employee);
            }
        }finally {
            sqlSession.close();
        }
    }

    @org.junit.Test
    public void testPage() throws IOException {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Page<Object> objects = PageHelper.startPage(1, 5);
            List<Employee> list = mapper.getEmps();
            System.out.println("个数:"+list.size());
            for (Employee e : list){
                System.out.println(e);
            }

            System.out.println("当前页码：" + objects.getPageNum() + "， 总记录数：" + objects.getTotal() + ", 总页数：" + objects.getPages());

        }finally {
            sqlSession.close();
        }
    }
}
