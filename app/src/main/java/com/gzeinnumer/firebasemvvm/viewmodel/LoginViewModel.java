package com.gzeinnumer.firebasemvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gzeinnumer.firebasemvvm.BR;
import com.gzeinnumer.firebasemvvm.model.User;

//todo 1.2 buat class
//todo 1.4 extend
public class LoginViewModel extends BaseObservable {

    User user;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private Context context;

    @Bindable
    public String toastMessage = null;


    public LoginViewModel(Context context, FirebaseDatabase database, DatabaseReference myRef) {
        this.context = context;
        this.database= database;
        this.myRef=myRef;
        user = new User("","");
    }

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void addData(User user){
        myRef.push().setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Data could not be saved. " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });
    }

    public void afterEmailTextChanged(CharSequence s) {
        user.setEmail(s.toString());
    }

    public void afterPasswordTextChanged(CharSequence s) {
        user.setPassword(s.toString());
    }

    public void onLoginClicked() {
        int loginCode = user.isValidData();
        if (loginCode == 0)
            setToastMessage("Masukan Email");
        else if(loginCode == 1)
            setToastMessage("masukan email yang benar");
        else if(loginCode == 2)
            setToastMessage("Harus lebih dari 6 huruf");
        else{
            addData(user);
            setToastMessage("Suksess!!");
        }

    }

}
