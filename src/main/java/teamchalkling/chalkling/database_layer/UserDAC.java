package teamchalkling.chalkling.database_layer;

import teamchalkling.chalkling.business_logic_layer.UserService;

public class UserDAC extends DAC{

    private final String table_name = "User";
    private UserService userService = null;

    public UserDAC(String dbURL, UserService userService){
        super(dbURL);
        this.userService = userService;
    }

    // TODO: Temporary; implement later.
    public void read(){
        userService.addUser("user1", "pass1");
        userService.addUser("user2", "pass2");
        userService.addUser("user3", "pass3");
    }

    // TODO: Temporary; implement later.
    public void write(){

    }

    public UserService getUserService() {
        return userService;
    }
}
