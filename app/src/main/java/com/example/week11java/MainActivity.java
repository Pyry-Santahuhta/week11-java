package com.example.week11java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button navigationDrawerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationDrawerButton = findViewById(R.id.BurgerMenu);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;

                if (v == findViewById(R.id.BurgerMenu)){
                    fragment = new NavigationDrawerFragment();
                }
                else {
                    fragment = new NavigationDrawerFragment();
                }


                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.navigationDrawer, fragment);
                transaction.commit();


            }
        };
        navigationDrawerButton.setOnClickListener(listener);
    }
}
