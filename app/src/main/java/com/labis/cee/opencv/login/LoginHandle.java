package com.labis.cee.opencv.login;

import com.labis.cee.opencv.model.MyUser;
/**
 * Created by stefano on 25/02/15.
 */
public class LoginHandle {
    public static final String BASE_URL = "http://picstv.herokuapp.com";

    private MyUser loggedUser;
    private StatefulRestTemplate statefulRestTemplate = new StatefulRestTemplate();

    public MyUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(MyUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public StatefulRestTemplate getStatefulRestTemplate() { return statefulRestTemplate; }

    public void setStatefulRestTemplate(StatefulRestTemplate statefulRestTemplate){
        this.statefulRestTemplate = statefulRestTemplate;
    }

    private static final LoginHandle login = new LoginHandle();
    public static LoginHandle getInstance() {return login;}
}
