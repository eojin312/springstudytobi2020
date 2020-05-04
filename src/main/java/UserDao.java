import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 로컬 클래스로 StatementStrategy 의 구현클래스의 숫자를 줄이기
 * 외부 변수를 사용할 시에는 final 를 붙인다
 * AddStatement를 메소드마다 추가해야했던 클래스 파일 하나 줄임
 * 내부 클래스 특징을 이용해 로컬변수를 바로 사용할 수 있는 장점
 */
public class UserDao {     //UserDao 와 JdbcContext 는 인터페이스를 사용하지않고 DI 를 적용했다.,/ㅏ
//    private JdbcContext jdbcContext;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

//    // JdbcContext 를 Setter 로 DI 주입 시킨다
//    public void setJdbcContext(JdbcContext jdbcContext) {
//        this.jdbcContext = jdbcContext;
//    }
    //생성자 파라미터로 datasource 주입
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }


    /**
     * jdbcTemplate 내장 콜백 사용하는 메소드 호출하도록 함
     * @throws Exception
     */
    public void deleteAll() throws Exception {
        this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        return connection.prepareStatement("delete from users");
                    }
                }
        );
    }


    public List<User> getAll() throws SQLException {
        List<User> userList;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = getConnection();
            ps = c.prepareStatement("select from users");
            rs = ps.executeQuery();
            return userList;
        } catch(SQLException e) {
            throw e;
        } finally {
            if (rs != null) { try { rs.close(); } catch(SQLException e) {} }
            if (ps != null) { try { ps.close(); } catch(SQLException e) {} }
            if (c != null) { try { c.close(); } catch(SQLException e) {} }
        }
    }

    private Connection getConnection() {
        Connection c = null;
        return c;
    }

    public void update(User user) {

    }
}
