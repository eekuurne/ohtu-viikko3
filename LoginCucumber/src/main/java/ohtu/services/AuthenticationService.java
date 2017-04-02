package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        return usernameInvalid(username) || passwordInvalid(password);
    }
    
    private boolean usernameInvalid(String username) {
        if (username.length() < 3) {
            return true;
        }
        for (int i = 0; i < username.length(); i++) {
            char ch = username.charAt(i); 
            if (!(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) {
                return true;
            }
        }
        return false;
    }
    
    private boolean passwordInvalid(String password) {
        if (password.length() < 8) {
            return true;
        }
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isLetter(password.charAt(i))) {
                break;
            }
            if (i == password.length() - 1) {
                return true;
            }
        }
        return false;
    }
}
