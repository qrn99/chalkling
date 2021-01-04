package com.chalkling.login_system;

public class StatusJSON {
    private boolean status;
    public StatusJSON(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
}
