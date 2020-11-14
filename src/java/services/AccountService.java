package services;

import dataaccess.UserDB;
import models.User;

public class AccountService {
    
    public User login(String username, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(username);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
}
