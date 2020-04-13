import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStatement implements StatementStrategy{
    Users user;

    public AddStatement(Users user){
        this.user = user;
    }
    public PreparedStatement makePreparedStatement(Connection c) throws Exception {
        PreparedStatement ps = c.prepareStatement("insert into users(id, ,name, pwd values (?, ?, ?))");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPwd());
        return ps;
    }

    public void add(Users user) throws Exception {
        StatementStrategy st = new AddStatement(user);
    }
}
