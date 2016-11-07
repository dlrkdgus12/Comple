package comple.common.dao;

import java.util.HashMap;
import java.util.List;


import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import comple.common.vo.User;
import comple.common.vo.UserExample;

@Repository 
public class UserDAO extends SqlSessionDaoSupport {
    @SuppressWarnings("unchecked")
    public List<User> selectList(UserExample ex, RowBounds rb){
        return getSqlSession().selectList("UserMapper.selectByExample", ex, rb);
    }

	public User selectUserLogin(String loginid, String passwd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", loginid);
		map.put("password", passwd);
		return (User) getSqlSession().selectOne("UserMapper.selectByLoginAndPassword", map);
	}

	public int insertAccount( String email, String passwdDigest,
			String type) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("id", email);
		map.put("password", passwdDigest);
		map.put("role", type);
		return getSqlSession().insert("UserMapper.insertAccount", map);
	}
}