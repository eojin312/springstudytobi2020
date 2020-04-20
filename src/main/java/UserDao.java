import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


//
//    public void add(final Users user) throws Exception {
//        this.jdbcContext.workWithStatementStrategy(
//                new StatementStrategy() {
//                    public PreparedStatement makePreparedStatement(Connection c) throws Exception {
//                        PreparedStatement ps = c.prepareStatement("insert into users(id, ,name, pwd values (?, ?, ?))");
//                        ps.setString(1, user.getId());
//                        ps.setString(2, user.getName());
//                        ps.setString(3, user.getPwd());
//                        return ps;
//                    }
//                }
//        );
//    }

    public void deleteAll() throws Exception {
        this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        return connection.prepareStatement("delete from users");
                    }
                }
        )
    }
}
