package by.belotskiy.movie_star.model.entity;

import by.belotskiy.movie_star.model.entity.enums.Role;
import by.belotskiy.movie_star.model.entity.enums.Status;

import java.io.Serializable;
import java.util.Objects;
/**
 * Entity of user.
 *
 * @author Dmitriy Belotskiy
 */
public class User extends BaseEntity implements Serializable {

    private String login;

    private String email;

    private String passwordHash;

    private Role role;

    private String avatar_path;

    private boolean emailConfirmed;

    private String firstName;

    private String secondName;

    private String userHash;

    public User(int id){
        super(id);
    }

    public User(int id, String login, String email, String passwordHash, Role role,
                String avatar_path, boolean emailConfirmed, String firstName,
                String secondName, String userHash, Status status) {
        super(id);
        this.login = login;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.avatar_path = avatar_path;
        this.emailConfirmed = emailConfirmed;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userHash = userHash;
        this.setStatus(status);
    }

    public User(String login, String email, String passwordHash, Role role,
                String avatar_path, boolean emailConfirmed, String firstName,
                String secondName, String userHash, Status status) {
        this.login = login;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.avatar_path = avatar_path;
        this.emailConfirmed = emailConfirmed;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userHash = userHash;
        this.setStatus(status);
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
    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!getId().equals(user.getId())) return false;
        if (login != null ? !login.equals(user.getLogin()) : user.getLogin() != null) return false;
        if (firstName != null ? !firstName.equals(user.getFirstName()) : user.getFirstName() != null) return false;
        if (secondName != null ? !secondName.equals(user.getSecondName()) : user.getSecondName() != null) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(passwordHash, user.passwordHash)) return false;
        if (!Objects.equals(userHash, user.userHash)) return false;
        if (!Objects.equals(role, user.role)) return false;
        return getStatus() != null ? getStatus().equals(user.getStatus()) : user.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (userHash != null ? userHash.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(getId());
        sb.append(", login='").append(login).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", passwordHash='").append(passwordHash).append('\'');
        sb.append(", userHash='").append(userHash).append('\'');
        sb.append(", role=").append(role);
        sb.append(", status=").append(getStatus());
        sb.append('}');
        return sb.toString();
    }
}
