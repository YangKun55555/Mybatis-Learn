package com.young;

import com.young.mapper.UserMapper;
import com.young.model.SysRole;
import com.young.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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

	@Test
	public void testSelectRolesByUserIdAndEnabled(){
		SqlSession sqlSession =getSqlSession();
		try {
			UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
			List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(1L,1);
			Assert.assertNotNull(userList);
			Assert.assertTrue(userList.size()>0);
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectByUser(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser query = new SysUser();
			query.setUserName("ad");
			List<SysUser> userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size()>0);
			query = new SysUser();
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size()>0);
			query=new SysUser();
			query.setUserName("ad");
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size()==0);
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testUpdateByIdSelective(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setId(1L);
			user.setUserEmail("test@mybatis.tk");
			int result = userMapper.updateByIdSelective(user);
			Assert.assertEquals(1,result);
			user = userMapper.selectById(1L);
			Assert.assertEquals("admin",user.getUserName());
			Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testUpdate2Selective(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test-selective");
			user.setUserPassword("123456");
			user.setUserInfo("test info");
			user.setCreateTime(new Date());
			int result = userMapper.insert2(user);
			Assert.assertEquals(1,result);
			user = userMapper.selectById(user.getId());
			Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testSelectByIdOrUserName(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser query = new SysUser();
			query.setId(1L);
			query.setUserName("admin");
			SysUser user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);
			query.setId(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);
			query.setUserName(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNull(user);
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectByIdList(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<Long> idList=new ArrayList<>();
			idList.add(1L);
			idList.add(1001L);
			List<SysUser> userList = userMapper.selectByIdList(idList);
			Assert.assertEquals(2,userList.size());
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testInsertList(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList=new ArrayList<>();
			for (int i=0;i<2;i++){
				SysUser user=new SysUser();
				user.setUserName("test"+i);
				user.setUserPassword("123456");
				user.setUserEmail("test@mybatis.tk");
				userList.add(user);
			}
			int result = userMapper.insertList(userList);
			Assert.assertEquals(2,result);
		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testUpdateByMap(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			Map<String,Object> map=new HashMap<>();
			map.put("id",1L);
			map.put("user_email","test@mybatis.tk");
			map.put("user_password","12345678");
			userMapper.updateByMap(map);
			SysUser user = userMapper.selectById(1L);
			Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
		}finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUserAndRoleById(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectUserAndRoleById(1001L);
			Assert.assertNotNull(user);
			Assert.assertNotNull(user.getRole());
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUserAndRoleById2(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectUserAndRoleById2(1001L);
			Assert.assertNotNull(user);
			Assert.assertNotNull(user.getRole());
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUserAndRoleById3(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectUserAndRoleById3(1001L);
			Assert.assertNotNull(user);
			Assert.assertNotNull(user.getRole());
		}finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUserAndRoleByIdSelect(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
			Assert.assertNotNull(user);
			Assert.assertNotNull(user.getRole());
		}finally {
			sqlSession.close();
		}
	}
}
