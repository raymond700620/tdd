package com.xp.tdd;

public class MyService {
    Authorizer authorizer;


    public MyService(Authorizer authorizer) {
        this.authorizer = authorizer;
    }

    public boolean criticalAction(String user, String password) {
        if (authorizer.authorize(user, password)) {
            //do some business logic for this authorized user
            return true;
        }
        else {
            return false;
        }

    }

    public boolean normalAction() {
        //do some thing
        return true;
    }
}
