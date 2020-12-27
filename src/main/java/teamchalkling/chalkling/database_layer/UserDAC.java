package teamchalkling.chalkling.database_layer;

import teamchalkling.chalkling.business_logic_layer.UserService;

public class UserDAC extends DAC{

    private final String table_name = "User";
    private final UserService userService;

    public UserDAC(String dbURL, UserService userService){
        super(dbURL);
        this.userService = userService;
    }

}
