package com.example.brianlin.minicalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CalculateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        Intent intent = getIntent();
        String operator = intent.getStringExtra(MainActivity.OPERATOR);
        Integer firstInteger = intent.getIntExtra(MainActivity.FIRST_INTEGER, 0);
        Integer secondInteger = intent.getIntExtra(MainActivity.SECOND_INTEGER, 0);

        Integer answer = null;
        String invalid = null;

        switch (operator) {
            case "+": answer = firstInteger + secondInteger;
                      break;
            case "-": answer = firstInteger - secondInteger;
                      break;
            case "*": answer = firstInteger * secondInteger;
                      break;
            case "/": answer = firstInteger / secondInteger;
                      break;
            case "%": answer = firstInteger % secondInteger;
                      break;
            case "^": answer = firstInteger ^ secondInteger;
                      break;
            default:  invalid = "Operator must be valid.";
        }

        TextView textView = findViewById(R.id.textView);
        if (invalid == null) {
            textView.setText(answer.toString());
        } else {
            textView.setText(invalid);
        }

    }
}
