package teamchalkling.chalkling.login_system;

public class SignUpJSON {
    private LoginPrompt status;
    public SignUpJSON(LoginPrompt status) {
        this.status = status;
    }

    public String getStatus(){
        switch(status) {
            case SUCCESS:
                return "SUCCESS";
            case EMPTY_USERNAME:
                return "EMPTY_USERNAME";
            case EMPTY_PASSWORD:
                return "EMPTY_PASSWORD";
            case NOT_ALPHANUM:
                return "NOT_ALPHANUM";
            case USER_EXIST:
                return "USER_EXIST";
        }
        return "";
    }

    public void setStatus(LoginPrompt status){
        this.status = status;
    }
}
