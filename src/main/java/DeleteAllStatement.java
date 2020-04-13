import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 전략 패턴
 * 인터페이스를 상속해서 실제 전략(=바뀌는 부분)인 PreparedStatement 를 생성하는 클래스
 */
public class DeleteAllStatement implements StatementStrategy {
    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
        PreparedStatement ps = c.prepareStatement("delete from users");
        return ps;
    }

    public void deleteAll() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            DriverManager dataSource = null;

            c = dataSource.getConnection("url");
            StatementStrategy strategy = new DeleteAllStatement();
            jdbcContextWithStatementStrategy(strategy); //컨텍스트 호출 전략 오브젝트 전달
            ps = strategy.makePreparedStatement(c);
        } catch (Exception e) {
        }
    }

    /**
     * StatementStrategy 타입의 전략을 오브젝트를 제공받고 JDBC 구조로 만들어진 컨텍스트 내에서 작업
     * 중요한 건 PreparedStatement 가 필요한 시점에 호출해서 사용
     * @param stmt
     * @throws SQLException
     */
    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException{ //클라이언트가 컨텍스트로 호출할 때 넘겨줄 전략 파라미터
        Connection c = null;
        PreparedStatement ps = null;

        try {
            DataSource dataSource = null;
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }
}
