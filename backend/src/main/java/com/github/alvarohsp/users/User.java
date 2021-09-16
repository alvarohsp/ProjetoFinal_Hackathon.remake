package com.github.alvarohsp.users;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.*;

@Entity
@Table(name = "users")
@RegisterForReflection
public class User extends PanacheEntity{

    @Column(length = 50)
    private String name;

    @Column(length = 256)
    private String password;

    @Column(length = 70)
    private String email;

    @Column(length = 17, name = "telephone")
    private String tel;

    @Column(name = "admin", updatable = false)
    private Boolean isAdmin;

    public Boolean getAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
