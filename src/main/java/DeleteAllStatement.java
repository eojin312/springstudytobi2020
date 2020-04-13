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

            c = dataSource.getConnection();
            StatementStrategy strategy = new DeleteAllStatement();
            ps = strategy.makePreparedStatement(c);
        } catch (Exception e) {
        }
    }
}
