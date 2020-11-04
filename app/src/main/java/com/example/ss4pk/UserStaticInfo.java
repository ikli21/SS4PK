package com.example.ss4pk;

import java.util.ArrayList;
import java.util.List;

public class UserStaticInfo {

    public final static String LOGIN = "login";

    public final static String POSITION = "position";
    public final static String USERS_SIGN_IN_INFO = "UsersSignInInfo";
    public final static String USERS_PROFILE_INFO = "UserProfileInfo";
    public final static String PASSWORD = "password";
    public final static String PROFILE_ID = "profileId";
    public final static String NAME = "name";
    public final static String AGE = "age";
    public final static String STATE = "state";
    public  static String profileId;
    public static List<User> users = new ArrayList<>();
    public UserStaticInfo(){
        if(users.size()==0)
            AddUsersInList();

    }
    private void AddUsersInList() {
        users.add(new User("A","Hooy",12, 1));
        users.add(new User("Sus", "piz", 15,0));
        users.add(new User("Sus", "piz", 15,2));
    }
}
