package com.roy.simple;

import com.roy.simple.dao.*;
import com.roy.simple.model.Country;
import com.roy.simple.model.Department;
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
import java.util.*;

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
    public void testGetEmpById() throws IOException {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
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

    @Test
    public void testGetCountryById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            System.out.println(mapper.getClass());
            Country country = mapper.getCountryById(1);
            System.out.println(country);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetCountryByIdAnno(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            CountryMapperAnnotation mapper = sqlSession.getMapper(CountryMapperAnnotation.class);
            System.out.println(mapper.getClass());
            Country country = mapper.getCountryById(1);
            System.out.println(country);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testAddEmployee(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee();
            employee.setLastName("roy");
            employee.setEmail("adfa@asdf");
            employee.setGender("1");

            mapper.addEmployee(employee);
            System.out.println("新ID是：" + employee.getId());
//            sqlSession.commit();
            System.out.println("Add success");

        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testUpdateEmployee(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee();
            employee.setId(1);
            employee.setLastName("roy");
            employee.setEmail("adfa@asdf");
            employee.setGender("1");

            mapper.updateEmployee(employee);
            sqlSession.commit();
            System.out.println("update success");

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteEmployee(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            boolean result = mapper.deleteEmpById(2);
            sqlSession.commit();
            System.out.println("result = " + result);
            System.out.println("Delete success");

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetEmpByIdAndLastName(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employee = mapper.getEmpByIdAndLastName(3,"roy");
            sqlSession.commit();
            System.out.println(employee);
            System.out.println("Delete success");

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetEmpByMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Map<String,Object> map = new HashMap<String, Object>();

            map.put("lastName","roy");
            map.put("id",3);

            Employee employee = mapper.getEmpByMap(map);
            sqlSession.commit();
            System.out.println(employee);
            System.out.println("Delete success");

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetEmpList(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            List<Employee> list = mapper.getEmpList();
            sqlSession.commit();
            System.out.println(list);

        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testGetEmpMayById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Map<String,Object> map = mapper.getEmpMapById(3);
            sqlSession.commit();
            System.out.println(map);

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetEmpMaps(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Map<String,Employee> map = mapper.getEmpMaps();
            sqlSession.commit();
            System.out.println(map);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetEmpByIdPlus(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);

            Employee employee = mapper.getEmpById(3);
            sqlSession.commit();
            System.out.println(employee);

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testGetEmpAndDeptByIdPlus(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);

            Employee employee = mapper.getEmpAndDeptById(3);
            sqlSession.commit();
            System.out.println(employee);

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testgetDeptById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

            Department department = mapper.getDeptById(1);
            sqlSession.commit();
            System.out.println(department);

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testgetEmpByIdStep(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);

            Employee employee = mapper.getEmpByIdStep(3);
            System.out.println(employee);
            System.out.println(employee.getDept());
//            System.out.println(employee.getDept().getDepartmentName());

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testgetDeptByIdPlus(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

            Department department = mapper.getDeptByIdPlus(1);
            System.out.println(department);

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testgetDeptByIdStep(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);

            Department department = mapper.getDeptByIdStep(1);
            System.out.println(department.getDepartmentName());
            System.out.println(department);
//            System.out.println(department.getEmps());

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testgetEmpsByConditionIf(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);

            Employee employee = new Employee();
//            employee.setId(3);
            employee.setLastName("%r%");
            employee.setGender("1");

            List<Employee> list = mapper.getEmpsByConditionIf(employee);

            System.out.println(list);

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testgetEmpsByConditionIfTrim(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);

            Employee employee = new Employee();
            employee.setId(3);
            employee.setLastName("%r%");
//            employee.setGender("1");

            List<Employee> list = mapper.getEmpsByConditionIfTrim(employee);

            System.out.println(list);

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testgetEmpsByConditionChoose(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);

            Employee employee = new Employee();
//            employee.setId(3);
//            employee.setLastName("%r%");
//            employee.setGender("1");

            List<Employee> list = mapper.getEmpsByConditionChoose(employee);

            System.out.println(list);

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testupdateEmp(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);

            Employee employee = new Employee();
            employee.setId(3);
            employee.setLastName("Roy123");
//            employee.setGender("1");

            mapper.updateEmp(employee);

            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testgetEmpsByConditionForeach(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);

            List<Employee> list = mapper.getEmpsByConditionForeach(Arrays.asList(3,4,5));

            System.out.println(list);

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testadmEmps(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);

            Employee employee = new Employee();
            employee.setLastName("new2");
            employee.setGender("1");
            employee.setEmail("new1@123");
            Department department = new Department();
            department.setId(1);
            employee.setDept(department);

            List<Employee> emps = new ArrayList<Employee>();
            emps.add(employee);

            Employee employee1 = new Employee();
            employee1.setLastName("new3");
            employee1.setGender("1");
            employee1.setEmail("new3@123");
            Department department1 = new Department();
            department1.setId(2);
            employee1.setDept(department1);
            emps.add(employee1);

            mapper.admEmps(emps);

            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testgetEmpsBind(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);

            Employee employee = new Employee();
            employee.setLastName("o");

            List<Employee> list = mapper.getEmpsBind(employee);

            System.out.println(list);

        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testFirstLevelCache(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employee = mapper.getEmpById(3);

            System.out.println(employee);
//            sqlSession.clearCache();
            Employee employee1 = mapper.getEmpById(3);
            System.out.println(employee1);
            System.out.println(employee == employee1);

        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void testSecondLevelCache(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        try {
            //获取接口并调用方法返回查询结果
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);

            Employee employee = mapper.getEmpById(3);

            System.out.println(employee);

            sqlSession.close();
            Employee employee2 = mapper2.getEmpById(3);

            System.out.println(employee2);
            sqlSession2.close();
        }finally {
        }
    }
}
