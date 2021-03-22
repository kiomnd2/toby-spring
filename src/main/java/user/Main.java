package user;

import user.dao.UserDao;
import user.domain.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserDao dao = new UserDao();

        User user = new User();
        user.setId("whiteship");
        user.setName("김형익");
        user.setPassword("qwer1234");

        dao.add(user);

        System.out.println("user.getId() = " + user.getId() + " 등록 성공");

        User user2 = new User();
        dao.get("a");
        System.out.println("user2.getName() = " + user2.getName());

        System.out.println("user2.getPassword() = " + user2.getPassword());

        System.out.println("user2.getId() = " + user2.getId()+ " 조회성공");
    }
}
