import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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

    //스프링에서 제공하는 connection 생성과 트랜잭션 경계설정 기능응ㄹ 모두 이용가능
    private PlatformTransactionManager transactionManager;

    //수정자 메소드 추가해서 DI 가능하게 해줌
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
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
        //TransactionSynchronizationManager.initSynchronization();
        TransactionStatus status = this.transactionManager
                .getTransaction(new DefaultTransactionDefinition());

        //스프링이 제공하는 유틸리티 메소드 사용(트랜잭션 동기화에 사용하도록 저장소에 바인딩시켜주기때문)
        Connection c = DataSourceUtils.getConnection(dataSource);
        c.setAutoCommit(false);

        try{
            List<User> users = (List<User>) userDao.getAll();
            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    upgradeLevels();
                }
            }
            this.transactionManager.commit(status);
        } catch (Exception e) {
            this.transactionManager.rollback(status);
        }


    }

    private boolean canUpgradeLevel(User user) {
        

    }
}
