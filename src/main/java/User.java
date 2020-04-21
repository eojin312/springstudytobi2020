public class User {
    private static final int BASIC = 1;
    private static final int SILVER = 2;
    private static final int GOLD = 3;
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECCOMEND_FOR_GOLD = 30;

    Level level;
    int login;
    int recommend;
    String id;
    String pwd;
    String name;

    public User(Level level, int login, int recommend, String id, String pwd, String name) {
        this.level = level;
        this.login = login;
        this.recommend = recommend;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if (nextLevel == null) {
            throw new IllegalStateException(this.level + "은 업그레이드가 불가능합니다");
        } else {
            this.level = nextLevel;
        }
    }

    /**
     * 최소 로그인 횟수가 변경될 때도 한 번만 수정할 수 있게 해주는 메소드
     * @param user
     * @return
     */
    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC: return (user.getLogin() >- MIN_LOGCOUNT_FOR_SILVER);
            case SILVER: return (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
            case GOLD: return false;
            default: throw new IllegalStateException("Unknown Level:" + currentLevel);
        }
    }

}
