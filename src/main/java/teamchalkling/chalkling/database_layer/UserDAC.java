package teamchalkling.chalkling.database_layer;

import teamchalkling.chalkling.business_logic_layer.UserService;

public class UserDAC extends DAC{

    private final String table_name = "User";
    private UserService userService;

    public UserDAC(String dbURL, UserService userService){
        super(dbURL);
        this.userService = userService;
    }

    // TODO: Temporary; implement later.
    public void read(){
        userService.addUser("user1", "$2a$10$vJ7jWUxKCI7stLVtgtpszO", "$2a$10$vJ7jWUxKCI7stLVtgtpszOnq91hVZttt9TbQ401fjqdz9ct5iW0Ju");
        userService.addUser("user2", "$2a$10$Fxh.tTBv2V3MikJTIXB2.O", "$2a$10$Fxh.tTBv2V3MikJTIXB2.OlN324mu9cvl.ceKpXwAP/cTFU2Bf3UG");
        userService.addUser("user3", "$2a$10$5wS1RHT.rFVyhlNowhD/Fu", "$2a$10$5wS1RHT.rFVyhlNowhD/FuX6OHfvgW0lo6AQB4UEmodWPPmFC8qIC");
    }

    // TODO: Temporary; implement later.
    public void write(){

    }

}
