package com.young;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.Reader;

/**
 * @author YangKun
 * @date 2019/11/19 8:25 PM
 */
public class BaseMapperTest {
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

	public SqlSession getSqlSession(){
		return sqlSessionFactory.openSession();
	}
}
