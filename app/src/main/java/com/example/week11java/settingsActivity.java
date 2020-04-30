package com.example.week11java;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Locale;


public class settingsActivity extends AppCompatActivity {
    Locale userLocale;
    private boolean spinnerTouched = false;
    ArrayList<String> languages;
    ArrayAdapter<String> stringArrayAdapter;
    EditText fontSize, boxWidth, boxHeight, lineCount, displayText;
    Spinner languageSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        userLocale = new Locale("");
        setTitle(R.string.settingsTitle);
        fontSize = findViewById(R.id.fontSizeInt);
        boxWidth = findViewById(R.id.textBoxWidthInt);
        boxHeight = findViewById(R.id.textBoxHeightInt);
        lineCount = findViewById(R.id.lineCountInt);
        displayText = findViewById(R.id.displayTextString);
        languageSpinner = findViewById(R.id.languageSpinner);
        Intent intent = getIntent();
        languages = new ArrayList<>();
        fontSize.setText(String.valueOf(intent.getIntExtra("Size", -1)));
        boxWidth.setText(String.valueOf(intent.getIntExtra("Width", -1)));
        boxHeight.setText(String.valueOf(intent.getIntExtra("Height", -1)));
        lineCount.setText(String.valueOf(intent.getIntExtra("lineCount", -1)));

        languages.add("English");languages.add("Finnish");languages.add("Swedish");

        stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        stringArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        languageSpinner.setAdapter(stringArrayAdapter);

        languageSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                spinnerTouched = true;
                return false;
            }
        });
        if (intent.getStringExtra("lang") != null) {
            switch (intent.getStringExtra("lang")) {
                case "fi":
                    languageSpinner.setSelection(1);
                    break;
                case "se":
                    languageSpinner.setSelection(2);
                    break;
                default:
                    languageSpinner.setSelection(0);
                    break;
            }
        }
        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerTouched) {
                    switch (languageSpinner.getSelectedItem().toString()) {
                        case ("English"):
                            setLocale("gb");
                            break;

                        case ("Finnish"):
                            setLocale("fi");
                            break;

                        case ("Swedish"):
                            setLocale("se");
                            break;
                        default:
                            setLocale("gb");
                            break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
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


    public void setLocale(String lang) {
        userLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = userLocale;
        res.updateConfiguration(conf, dm);
        finish();
        getIntent().putExtra("lang", lang);
        startActivity(getIntent());

    }

}
