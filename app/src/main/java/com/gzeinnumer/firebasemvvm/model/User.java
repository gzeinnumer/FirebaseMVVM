package com.gzeinnumer.firebasemvvm.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

//todo 1.3
public class User {
    @NonNull
    private String email;
    @NonNull
    private String password;

    public User(@NonNull final String email, @NonNull final String password) {
        this.email = email;
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull final String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull final String password) {
        this.password = password;
    }

    public int isValidData() {
        if (TextUtils.isEmpty(getEmail()))
            return 0;
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            return 1;
        else if (getPassword().length() <= 6)
            return 2;
        else
            return -1;
    }
}
