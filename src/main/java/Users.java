public class Users {
    private Long userNo;
    private String id;
    private String name;
    private String pwd;


    public Users(Long userNo, String id, String name, String pwd) {
        this.userNo = userNo;
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }


    public Long getUserNo() {
        return userNo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }


}
