package user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import user.dao.DaoFactory;
import user.dao.UserDao;
import user.domain.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        UserDao dao = context.getBean("userDao", UserDao.class);

        ApplicationContext context =
                new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("UserDao", UserDao.class);

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
