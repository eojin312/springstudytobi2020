import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DataSource 가 필요한 것은 UserDao 가 아니라 JdbcContext 가 되버린다.
 * Db커넥션이 필요한 코드는 JdbcContext 에 있기때문!
 * JdbcContext 가 DataSource 에 의존하고 있기에
 * DataSource 타입 Bean 을 DI 받게해야함
 */
public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy st) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;

        try{
            c = this.dataSource.getConnection();
            ps = st.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }
}
