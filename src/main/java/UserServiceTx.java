import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

public class UserServiceTx implements UserService{
    UserService  userService; // Target Object
    PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void add(User user) {
        userService.add(user); // 메소드 구현과 위임
    }

    public void upgradeLevels() throws SQLException { //메소드 구현
        TransactionStatus status = this.transactionManager //부가기능 수행
                .getTransaction(new DefaultTransactionDefinition());
        try {
            userService.upgradeLevels();

            this.transactionManager.commit(status); //부가기능 수행
        } catch (RuntimeException e) {
            this.transactionManager.rollback(status);
        }
    }
}
