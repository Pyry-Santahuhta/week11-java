package com.example.week11java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class settingsActivity extends AppCompatActivity {

    EditText fontSize, boxWidth, boxHeight, lineCount, displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        fontSize = findViewById(R.id.fontSizeInt);
        boxWidth = findViewById(R.id.textBoxWidthInt);
        boxHeight = findViewById(R.id.textBoxHeightInt);
        lineCount = findViewById(R.id.lineCountInt);
        displayText = findViewById(R.id.displayTextString);
        Intent intent = getIntent();
        fontSize.setText(String.valueOf(intent.getIntExtra("Size", -1)));
        boxWidth.setText(String.valueOf(intent.getIntExtra("Width", -1)));
        boxHeight.setText(String.valueOf(intent.getIntExtra("Height", -1)));
        lineCount.setText(String.valueOf(intent.getIntExtra("lineCount", -1)));

    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("Size", Integer.parseInt(fontSize.getText().toString()));
        intent.putExtra("Width", Integer.parseInt(boxWidth.getText().toString()));
        intent.putExtra("Height", Integer.parseInt(boxHeight.getText().toString()));
        intent.putExtra("lineCount", Integer.parseInt(lineCount.getText().toString()));
        intent.putExtra("displayText", displayText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

}
