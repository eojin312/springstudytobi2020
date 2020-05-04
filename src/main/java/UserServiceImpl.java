import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao;
    MailSender mailSender;


    public void add(User user) {

    }

    public void upgradeLevels() throws SQLException {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (canUpgradeLevlels()) {
                upgradeLevels();
            }
        }
    }

    private boolean canUpgradeLevlels() {
        return false;
    }
}
