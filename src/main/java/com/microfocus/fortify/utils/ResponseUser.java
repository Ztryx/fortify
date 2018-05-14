package com.microfocus.fortify.utils;

import com.microfocus.fortify.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andyx on 14/5/18.
 */
public class ResponseUser {
    List<User> users;
    String codeStatus;
    String message;

    public ResponseUser() {}

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
