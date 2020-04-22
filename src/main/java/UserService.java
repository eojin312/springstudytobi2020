import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserDao userDao;
    DataSource dataSource;

    //트랜잭션 동기화 적용하기
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     *
     * @throws SQLException
     */
    public void upgradeLevels() throws SQLException {
        //스프링이 제공하는 트랜잭션 동기화 관리 클래스(동기화 작업 초기화)
        TransactionSynchronizationManager.initSynchronization();

        //스프링이 제공하는 유틸리티 메소드 사용(트랜잭션 동기화에 사용하도록 저장소에 바인딩시켜주기때문)
        Connection c = DataSourceUtils.getConnection(dataSource);
        c.setAutoCommit(false);

        List<User> users = (List<User>) userDao.getAll();
        for (User user : users) {
            Boolean changed = null;
            if (user.getLevel() == Level.BASIC && user.getLogin() >= 50)  {
                user.setLevel(Level.BASIC);
                changed = true;
            } else if (user.getLevel() == Level.SILVER && user.getRecommend()  >= 30) {
                user.setLevel(Level.GOLD);
                changed = true;
            } else if (user.getLevel() == Level.GOLD) {
                changed = false;
            } else {
                changed = false;
            } if (changed) {
                userDao.update(user);
            }
        }
    }
}
