package ohtu.domain;

public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!o.getClass().equals(this.getClass())) {
            return false;
        }
        
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 47 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }
}
