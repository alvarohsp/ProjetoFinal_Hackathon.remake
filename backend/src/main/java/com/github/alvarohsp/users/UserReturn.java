package com.github.alvarohsp.users;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserReturn {
    private long id;
    private String name;
    private String tel;
    private String email;

    public UserReturn(long id, String name, String tel, String email){
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

    public UserReturn(){

    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getTel(){
        return tel;
    }
    public String getEmail(){
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
