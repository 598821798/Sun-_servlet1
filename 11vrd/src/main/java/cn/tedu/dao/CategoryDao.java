package cn.tedu.dao;

import cn.tedu.entity.Category;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public List<Category> findAll() {
        try (Connection conn = DBUtils.getConn()) {
            String sql = "select id,name from category";
            Statement s = conn.createStatement();
            ResultSet set = s.executeQuery(sql);
            ArrayList<Category> list = new ArrayList<>();
            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                list.add(new Category(id,name));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
      return null;
    }
}
