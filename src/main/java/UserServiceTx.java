import java.sql.SQLException;

public class UserServiceTx implements UserService{
    UserService  userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void add(User user) {
        userService.add(user);
    }

    public void upgradeLevels() throws SQLException {
        userService.upgradeLevels();
    }
}
