package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Ariazm
 * Date: 2020-07-21
 * Time: 17:31
 */
    //管理数据库连接
    //1）建立连接
    //2）断开连接
    //JDBC中用dateSource来管理连接
    //DBUtil 相当于是给 DataSource 再稍微包装一层
    //DataSource 每个只应用程序应该只有一个实例 （单例）
    //DBUtil 本质上就是实现一个单例模式，管理了唯一一个DataSource 实例
    //单例模式有两种实现方法
    //1）饿汉模式
    //2）懒汉模式
    //此处用懒汉模式
public class DBUtil {
    private static volatile DataSource dataSource = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/my_blog?characterEncoding=utf-8&useSSL=true";
    private static final String USERName = "root";
    private static final String PASSWORD = "110603";

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    //还需要给 DataSource 设置一些属性
                    ((MysqlDataSource)dataSource).setURL(URL);
                    ((MysqlDataSource)dataSource).setUser(USERName);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }
    //通过这个方法来获取连接
    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //通过这个方法来断开连接
    public static void close(Connection connection, PreparedStatement statement,
                             ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
