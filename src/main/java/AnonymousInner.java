import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 익명 내부 클래스
 * 선언과 동시에 오브젝트 생성
 * 자신의 타입을 가질 수 없고 구현한 인터페이스의 변수에만 저장할 수 있다.
 */
public class AnonymousInner {
    StatementStrategy st = new StatementStrategy() {
        Users user;
        public PreparedStatement makePreparedStatement(Connection c) throws Exception {
            PreparedStatement ps = c.prepareStatement("insert into users(id, ,name, pwd values (?, ?, ?))");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPwd());
            return ps;
        }
    };
}
