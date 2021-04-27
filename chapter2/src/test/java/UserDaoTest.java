import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.dao.UserDao;
import user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {


    User user1;
    User user2;
    User user3;

    UserDao dao;

    @BeforeEach
    public void setUp() {
        DataSource datasource = new SingleConnectionDataSource(
                "jdbc:mysql://localhost/springbook", "root", "qwer1234!", true);

        dao = new UserDao();
        dao.setDatasource(datasource);

        user1 = new User("kiomnd2" , "김형익", "qwer1234");
        user2 = new User("kiomnd3" , "김형익2", "qwer1234");
        user3 = new User("kiomnd4" , "김형익3", "qwer1234");

    }

    @Test
    public void addAndGet() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        dao.add(user2);

        assertThat(dao.getCount()).isEqualTo(2);

        User getUser1 = dao.get(user1.getId());
        assertThat(getUser1.getName()).isEqualTo(user1.getName());
        assertThat(getUser1.getPassword()).isEqualTo(user1.getPassword());
    }

    @Test
    public void count() throws SQLException {


        dao.deleteAll();

        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);

    }

    @Test
    public void getUserFailure() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);


    }
}
