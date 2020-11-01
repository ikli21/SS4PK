package com.example.ss4pk;

import java.util.ArrayList;
import java.util.List;

public class UserStaticInfo {

    public final static String POSITION = "position";
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
