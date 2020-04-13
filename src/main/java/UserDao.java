import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 로컬 클래스로 StatementStrategy 의 구현클래스의 숫자를 줄이기
 */
public class UserDao {
    class AddStatement implements StatementStrategy {
        Users user;

        public AddStatement(Users user) {
            this.user = user;
        }

        public PreparedStatement makePreparedStatement(Connection c) throws Exception {
            PreparedStatement ps = c.prepareStatement("insert into users(id, ,name, pwd values (?, ?, ?))");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPwd());
            return ps;
        }
    }
}
