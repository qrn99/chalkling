package teamchalkling.chalkling.business_logic_layer;

public class LoginJSON {
    private String username;
    private String password;
    private boolean isLogin;

    public LoginJSON(String username, String password, boolean isLogin) {
        this.username = username;
        this.password = password;
        this.isLogin = isLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

}
