package com.zqh.rxjava.realmapp.infor;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Serializable{
    @PrimaryKey
    private String id;
    private String name;
    private int age;
    private RealmList<User> friends;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<User> getFriends() {
        return friends;
    }

    public void setFriends(RealmList<User> friends) {
        this.friends = friends;
    }
}