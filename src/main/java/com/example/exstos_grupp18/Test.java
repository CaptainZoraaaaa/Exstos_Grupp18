package com.example.exstos_grupp18;

import Model.User;
import Model.UserManager;

public class Test {
    public static void main(String[] args) {
        UserManager manager = new UserManager();

        User user = manager.createNewUser("Pelle", "123", null);

        System.out.println(user.getUsername() + user.getPassword());
    }
}
