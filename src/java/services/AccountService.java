package services;

import dataaccess.UserDB;
import models.Users;

public class AccountService {
    
    public Users login(String username, String password) {
        UserDB userDB = new UserDB();
        
        try {
            Users user = userDB.getUser(username);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
}
