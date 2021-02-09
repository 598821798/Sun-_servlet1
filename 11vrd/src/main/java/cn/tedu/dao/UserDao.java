package cn.tedu.dao;

import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public User login(String username, String password) {
        try (Connection conn= DBUtils.getConn()){
            String sql=" select id from vrduser where usename=?and password=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet set=ps.executeQuery();
                if (set.next()){//满足条件，则登录成功，返回用户对象。
                    int id=set.getInt(1);
                    return new User(id,username,password);
                }
        } catch (Exception e) {
                 e.printStackTrace();
        }
        return null;

    }
}
