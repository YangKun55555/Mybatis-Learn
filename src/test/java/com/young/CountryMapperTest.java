package com.young;

import com.young.model.Country;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

/**
 * @author YangKun
 * @date 2019/11/16 2:22 PM
 */
public class CountryMapperTest {
	private static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void init() {
		try {
			//使用Resources读取mybatis配置文件到Reader中
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			//生成sqlSessionFactory，期间会解析mybatis配置文件，并读取全部的mapper映射文件
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testSelectAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//使用selectList，执行映射文件中id=selectAll的查询语句，结果集resultSet会根据resultType转换为指定的Country类型
			List<Country> countryList = sqlSession.selectList("selectAll");
			printCountryList(countryList);
		} finally {
			sqlSession.close();
		}
	}

	private void printCountryList(List<Country> countryList) {
		for (Country country : countryList) {
			System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
		}
	}

}
