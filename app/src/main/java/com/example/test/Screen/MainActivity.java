package com.example.test.Screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.Screen.helperClass.helperFunctions;
import com.example.test.ViewModel.viewModelClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton createNewUser;
    helperFunctions helper;
    viewModelClass userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();
        helper.createUser(createNewUser, userViewModel);
        helper.showDetails(recyclerView, userViewModel);
    }

    private void findID() {
        recyclerView = findViewById(R.id.user_list);
        createNewUser = findViewById(R.id.createUser);
        helper = new helperFunctions(MainActivity.this);
        userViewModel = new ViewModelProvider(this).get(viewModelClass.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        helper.showDetails(recyclerView, userViewModel);
    }
}