package model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "table_name")
public class User {
    @Id
    @GenericGenerator(name = "lev", strategy = "increment")
    @GeneratedValue(generator = "lev")
    //@Column(name="id")
    private int id;
    // @Column(name="name")
    private String name;
    // @Column(name="login")
    private String login;
    // @Column(name="password")
    private String password;
    // @Column(name="role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || !(object instanceof User)) return false;
        User user = (User) object;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = hash * name.hashCode();
        hash = hash * login.hashCode();
        hash = hash * id;
        hash = hash * password.hashCode();
        return hash;
    }
}
