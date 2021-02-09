package cn.tedu.dao;

import cn.tedu.entity.Image;
import cn.tedu.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDao {
    public void add(Image image) {
        try (Connection conn = DBUtils.getConn()) {
            String sql = "insert into images values(null,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, image.getTitle());
            ps.setString(2, image.getUrl());
            ps.executeUpdate();
            System.out.println("添加完成！");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Image> findall() {
        ArrayList<Image> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn()) {
            String sql = "select id,title,url from images";
            Statement s = conn.createStatement();
            ResultSet set = s.executeQuery(sql);
            while (set.next()) {
                int id = set.getInt(1);
                String title = set.getString(2);
                String url = set.getString(3);
                list.add(new Image(id, title, url));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(int id) {
        try (Connection conn = DBUtils.getConn()) {
            String sql = "delete from images where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("删除完毕!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findUrl(int id) {
        try (Connection conn= DBUtils.getConn()){
          String sql =" select url from images where id=?";
          PreparedStatement ps= conn.prepareStatement(sql);
          ps.setInt(1,id);
          ResultSet set=ps.executeQuery();
          if (set.next()){
              return set.getString(1);
          }
        } catch (Exception e) {
                 e.printStackTrace();
        }
        return  null;
    }
}
