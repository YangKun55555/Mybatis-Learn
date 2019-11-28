package com.young;

import com.young.mapper.UserMapper;
import com.young.model.SysRole;
import com.young.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author YangKun
 * @date 2019/11/19 8:30 PM
 */
public class UserMapperTest extends BaseMapperTest {
	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectById(1L);
			Assert.assertNotNull(user);
			Assert.assertEquals("admin", user.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList = userMapper.selectAll();
			Assert.assertNotNull(userList);
			Assert.assertTrue(userList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@young.com");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1, 2, 3});
			user.setCreateTime(new Date());
			int result = userMapper.insert(user);
			Assert.assertEquals(1, result);
			Assert.assertNull(user.getId());
		} finally {
			//不造成测试数据冗余，设置为回滚，默认是不自动提交的，实际也不会写入数据库
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testInsert2() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@young.com");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1, 2, 3});
			user.setCreateTime(new Date());
			int result = userMapper.insert2(user);
			Assert.assertEquals(1, result);
			Assert.assertNotNull(user.getId());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testInsert3() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@young.com");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1, 2, 3});
			user.setCreateTime(new Date());
			int result = userMapper.insert3(user);
			Assert.assertEquals(1, result);
			Assert.assertNotNull(user.getId());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testUpdateById(){
		SqlSession sqlSession=getSqlSession();
		try {
			UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
			SysUser sysUser = userMapper.selectById(1L);
			Assert.assertEquals("admin",sysUser.getUserName());
			sysUser.setUserName("admin_test");
			sysUser.setUserEmail("test@mybatis.tk");
			int result = userMapper.updateById(sysUser);
			Assert.assertEquals(1,result);
			sysUser = userMapper.selectById(1L);
			Assert.assertEquals("admin_test",sysUser.getUserName());
		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testDeleteById(){
		SqlSession sqlSession =getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user1 = userMapper.selectById(1L);
			Assert.assertNotNull(user1);
			Assert.assertEquals(1,userMapper.deleteById(1L));
			Assert.assertNull(userMapper.selectById(1L));
			SysUser user2 = userMapper.selectById(1001L);
			Assert.assertNotNull(user2);
			Assert.assertEquals(1,userMapper.deleteById(user2));
			Assert.assertNull(userMapper.selectById(1001L));

		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
}
