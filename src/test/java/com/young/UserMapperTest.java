package com.young;

import com.young.mapper.UserMapper;
import com.young.model.SysRole;
import com.young.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author YangKun
 * @date 2019/11/19 8:30 PM
 */
public class UserMapperTest extends BaseMapperTest {
	@Test
	public void testSelectById(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectById(1L);
			Assert.assertNotNull(user);
			Assert.assertEquals("admin",user.getUserName());
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList = userMapper.selectAll();
			Assert.assertNotNull(userList);
			Assert.assertTrue(userList.size()>0);
		}finally {
			sqlSession.close();
		}
	}
	@Test
	public void testSelectRolesByUserId(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size()>0);
		}finally {
			sqlSession.close();
		}
	}
}
