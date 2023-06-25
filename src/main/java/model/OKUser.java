package model;

public class OKUser {
    private final String email;
    private final String password;

    public OKUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
