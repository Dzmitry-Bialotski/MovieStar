package by.belotskiy.movie_star.model.entity;

public class User extends BaseEntity {

    private String login;

    private String email;

    private String passwordHash;

    private Role role;

    private String avatar_path;

    public User(int id){
        super(id);
    }

    public User(int id, String login, String email, String passwordHash, Role role, String avatar_path) {
        super(id);
        this.login = login;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.avatar_path = avatar_path;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }
}
