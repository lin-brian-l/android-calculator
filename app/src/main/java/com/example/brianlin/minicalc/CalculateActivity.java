package com.example.brianlin.minicalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateActivity extends AppCompatActivity {

    public static String condenseEquationList(List<String> equationList) {
        String result = null;
        while (equationList.size() > 1) {
            result =  condenseEquation(equationList);
            equationList.set(0, result);
        }
        return equationList.get(0);
    }

    public static List<String> regexSplitter(String stringToSplit, String desiredRegex) {
        String regexString = null;
        if (desiredRegex == "addSubtract") {
            regexString = "([0-9*/]+|([+-]))";
        } else if (desiredRegex == "multiplyDivide") {
            regexString = "(-?\\d+)|(\\D)";
        }
        Pattern p = Pattern.compile(regexString);
        Matcher m = p.matcher(stringToSplit);
        List<String> equationList = new ArrayList<String>();
        while (m.find()) {
            equationList.add(m.group(0));
        }
        return equationList;
    }

    public static String condenseEquation(List<String> equations) {
        Integer int1 = Integer.parseInt(equations.get(0));
        Integer int2 = Integer.parseInt(equations.get(2));
        equations.remove(2);
        equations.remove(0);
        String result = null;
        String operator = equations.get(0);
        switch(operator) {
            case "*":
                result = Integer.toString(int1 * int2);
                break;
            case "/":
                result = Integer.toString(int1 / int2);
                break;
            case "+":
                result = Integer.toString(int1 + int2);
                break;
            case "-":
                result = Integer.toString(int1 - int2);
                break;
        }
        return result;
    }

    public static String calculateAnswer(String problemLine) {
        String problemToSolve = problemLine.replaceAll("\\s", "");
        List<String> problemList = regexSplitter(problemToSolve, "addSubtract");

        for(Integer i = 0; i < problemList.size(); i++) {
            List<String> equationMultiplyDivide = new ArrayList<String>();
            String equation = problemList.get(i);

            if (equation.contains("*") || equation.contains("/")) {
                equationMultiplyDivide = regexSplitter(equation, "multiplyDivide");
                problemList.set(i, condenseEquationList(equationMultiplyDivide));
            }
        }
        return condenseEquationList(problemList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        Intent intent = getIntent();
        String problem = intent.getStringExtra(MainActivity.PROBLEM);

        TextView textView = findViewById(R.id.textView);
        textView.setText(calculateAnswer(problem));
    }
}
