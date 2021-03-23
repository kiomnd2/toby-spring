package user.dao;

import user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private DataSource datasource;

    public UserDao(DataSource datasource) {
        this.datasource = datasource;
    }

    public void setConnectionMaker(DataSource datasource) {
        this.datasource = datasource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = datasource.getConnection();

        PreparedStatement ps = connection.prepareStatement("insert into USERS (id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection connection = datasource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));


        rs.close();
        ps.close();
        connection.close();

        return user;

    }

/*

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
    }
*/

}
