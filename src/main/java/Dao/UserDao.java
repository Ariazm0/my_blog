package Dao;

import model.DBUtil;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Ariazm
 * Date: 2020-07-25
 * Time: 20:33
 */
public class UserDao {
    //注册
    public void add(User user) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "insert into user values(null,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPassword());
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("用户插入失败!");
                return;
            }
            System.out.println("用户插入成功!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //按照名字查找用户
    public User selectByName(String name) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            String sql = "select * from user where name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUerId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,rs);
        }
        return null;
    }
    //根据id查找用户
    public User selectById(int id) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "select * from user where userId = ?";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUerId(rs.getInt("userId"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,rs);
        }
        return null;
    }

}
