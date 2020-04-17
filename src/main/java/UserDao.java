import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 로컬 클래스로 StatementStrategy 의 구현클래스의 숫자를 줄이기
 * 외부 변수를 사용할 시에는 final 를 붙인다
 * AddStatement를 메소드마다 추가해야했던 클래스 파일 하나 줄임
 * 내부 클래스 특징을 이용해 로컬변수를 바로 사용할 수 있는 장점
 */
public class UserDao {     //UserDao 와 JdbcContext 는 인터페이스를 사용하지않고 DI 를 적용했다.,/ㅏ
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
        excuteSql("delete from users");

    }

    /**
     * 콜백 오브젝트 반복될 가능성이 있기에 따로 빼줌
     * @param query
     * @throws Exception
     */
    private void excuteSql(final String query) throws Exception {
        this.jdbcContext.workWithStatementStrategy(
                new StatementStrategy() {
                    public PreparedStatement makePreparedStatement(Connection c) throws Exception {
                        PreparedStatement ps = c.prepareStatement(query);
                        return ps;  //변하지 않는 콜백 클래스 정의와 오브젝트 생성
                    }
                }
        );
    }
}
