package com.roy.simple.mapper;

import com.roy.simple.model.Country;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CountryMapperTest {

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

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<Country> countryList = sqlSession.selectList("selectAll");
            printList(countryList);
        }finally {
            sqlSession.close();
        }
    }

    private void printList(List<Country> list){
        for (Country country :
                list) {
            System.out.printf("%-4d%4s%4s\n",country.getId(),country.getCountrycode(),country.getCountryname());
        }
    }
}
