package com.example.week11java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    TextView displayText;
    String displayTextString;


    int fontSize = 14;
    int textWidth;
    int textHeight;
    int lineCount = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        displayText = findViewById(R.id.displayTextView);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) displayText.getLayoutParams();
        textHeight = params.height;
        textWidth = params.width;
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.settings) {
                    loadActivity();
                }


                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void loadActivity(){
        Intent intent = new Intent(MainActivity.this, settingsActivity.class);
        intent.putExtra("Size", fontSize);
        intent.putExtra("Width", textWidth);
        intent.putExtra("Height", textHeight);
        intent.putExtra("lineCount", lineCount);
        startActivityForResult(intent, 1);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK ){
                fontSize = data.getIntExtra("Size", -1);
                textWidth = data.getIntExtra("Width", -1);
                textHeight = data.getIntExtra("Height", -1);
                lineCount = data.getIntExtra("lineCount", -1);
                displayTextString = data.getStringExtra("displayText");
                displayText.setText(displayTextString);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) displayText.getLayoutParams();
                params.width = textHeight;
                params.height = textHeight;
                displayText.setTextSize(fontSize);
                displayText.setLines(lineCount);
            }
        }
    }
}
