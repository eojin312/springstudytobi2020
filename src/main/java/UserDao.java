import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 로컬 클래스로 StatementStrategy 의 구현클래스의 숫자를 줄이기
 * 외부 변수를 사용할 시에는 final 를 붙인다
 * AddStatement를 메소드마다 추가해야했던 클래스 파일 하나 줄임
 * 내부 클래스 특징을 이용해 로컬변수를 바로 사용할 수 있는 장점
 */
public class UserDao {
    private JdbcContext jdbcContext;

    // JdbcContext 를 Setter 로 DI 주입 시킨다
    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }


    public void add(final Users user) throws Exception {
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    public PreparedStatement makePreparedStatement(Connection c) throws Exception {
                        PreparedStatement ps = c.prepareStatement("insert into users(id, ,name, pwd values (?, ?, ?))");
                        ps.setString(1, user.getId());
                        ps.setString(2, user.getName());
                        ps.setString(3, user.getPwd());
                        return ps;
                    }
                }
        );
    }

    public void deleteAll() throws Exception {
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    public PreparedStatement makePreparedStatement(Connection c) throws Exception {
                        PreparedStatement ps = c.prepareStatement("delete from users");
                        return ps;
                    }
                }
        );

   }
}
