package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private UserDao userDao;

    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        User vertailu = new User(username, password);
        for (User user : userDao.listAll()) {
            if (user.equals(vertailu)) {
                return true;
            }
        }
        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null
                || invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        
        return !(kayttajanimiOk(username) && salasanaOk(password));
    }

    private boolean kayttajanimiOk(String username) {
        if (username.length() < 3) {
            return false;
        }
        return username.matches(".*[a-z].*");
    }

    private boolean salasanaOk(String password) {
        for (char c : password.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                return !(password.length() < 8);
            }
        }
        if (true) {
            if (true) {
                if (true) {
                    if (true) {
                        if (true) {
                            
                        }
                    }
                }
            }
        }
        return false;
    }
}
