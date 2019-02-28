package com.gzeinnumer.firebasemvvm;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gzeinnumer.firebasemvvm.databinding.ActivityLoginBinding;
import com.gzeinnumer.firebasemvvm.viewmodel.LoginViewModel;

//todo 1.5
public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user");

        ActivityLoginBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityMainBinding.setViewModel(new LoginViewModel(MainActivity.this,database, myRef));
        activityMainBinding.executePendingBindings();

    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }



}
