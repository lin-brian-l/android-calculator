package com.example.brianlin.minicalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public static final String PROBLEM = "com.example.firstapp.PROBLEM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view) {
        Intent intent = new Intent(this, CalculateActivity.class);

        EditText problemText = (EditText) findViewById(R.id.editText2);
        String problem = problemText.getText().toString();

        intent.putExtra(PROBLEM, problem);
        startActivity(intent);
    }
}
