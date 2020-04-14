import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 로컬 클래스로 StatementStrategy 의 구현클래스의 숫자를 줄이기
 * 외부 변수를 사용할 시에는 final 를 붙인다
 * 메소드마다 추가해야했던 클래스 파일 하나 줄임
 */
public class UserDao {
   public void add(final Users user) {
       class AddStatement implements StatementStrategy {

           public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
               PreparedStatement ps = c.prepareStatement("insert into users(id, ,name, pwd values (?, ?, ?))");
               ps.setString(1, user.getId());
               ps.setString(2, user.getName());
               ps.setString(3, user.getPwd());
               return ps;
           }
       }

       StatementStrategy st = new AddStatement();
   }
}
