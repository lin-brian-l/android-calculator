package com.example.brianlin.minicalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String FIRST_INTEGER = "com.example.firstapp.FIRSTINTEGER";
    public static final String OPERATOR = "com.example.firstapp.MESSAGE";
    public static final String SECOND_INTEGER = "com.example.firstapp.SECONDINTEGER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view) {
        Intent intent = new Intent(this, CalculateActivity.class);

        EditText firstIntegerText = (EditText) findViewById(R.id.editText2);
        Integer firstInteger = Integer.parseInt(firstIntegerText.getText().toString());

        EditText secondIntegerText = (EditText) findViewById(R.id.editText4);
        Integer secondInteger = Integer.parseInt(secondIntegerText.getText().toString());

        EditText operatorText = (EditText) findViewById(R.id.editText3);
        String operator = operatorText.getText().toString();

        intent.putExtra(FIRST_INTEGER, firstInteger);
        intent.putExtra(OPERATOR, operator);
        intent.putExtra(SECOND_INTEGER, secondInteger);
        startActivity(intent);

    }
}
