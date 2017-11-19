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

    public static String calculateAnswer(String problemLine) {
        String problemToSolve = problemLine.replaceAll("\\s", "");
        String splitAddSubtract = "([0-9*/]+|([+-]))";
        Pattern p = Pattern.compile(splitAddSubtract);
        Matcher m = p.matcher(problemToSolve);
        List<String> problemList = new ArrayList<String>();

        while (m.find()) {
            problemList.add(m.group(0));
        }

        for(Integer i = 0; i < problemList.size(); i++) {
            List<String> equationMultiplyDivide = new ArrayList<String>();
            String equation = problemList.get(i);

            if (equation.contains("*") || equation.contains("/")) {
                String splitMultiplyDivide = "(-?\\d+)|(\\D)";
                Pattern p2 = Pattern.compile(splitMultiplyDivide);
                Matcher m2 = p2.matcher(equation);

                while (m2.find()) {
                    equationMultiplyDivide.add(m2.group(0));
                }

                Integer intMultiplyDivide1 = null;
                Integer intMultiplyDivide2 = null;
                String resultMultiplyDivide = null;

                while (equationMultiplyDivide.size() > 1) {
                    intMultiplyDivide1 = Integer.parseInt(equationMultiplyDivide.get(0));
                    intMultiplyDivide2 = Integer.parseInt(equationMultiplyDivide.get(2));
                    equationMultiplyDivide.remove(2);
                    equationMultiplyDivide.remove(0);

                    if (equationMultiplyDivide.get(0).equals("*")) {
                        resultMultiplyDivide = Integer.toString(intMultiplyDivide1 * intMultiplyDivide2);
                    } else if (equationMultiplyDivide.get(0).equals("/")) {
                        resultMultiplyDivide = Integer.toString(intMultiplyDivide1 / intMultiplyDivide2);
                    }
                    equationMultiplyDivide.set(0, resultMultiplyDivide);
                }

                problemList.set(i, resultMultiplyDivide);
            }
        }

        Integer intAddSubtract1 = null;
        Integer intAddSubtract2 = null;
        String resultAddSubtract = null;

        while (problemList.size() > 1) {
            intAddSubtract1 = Integer.parseInt(problemList.get(0));
            intAddSubtract2 = Integer.parseInt(problemList.get(2));
            problemList.remove(2);
            problemList.remove(0);

            if (problemList.get(0).equals("+")) {
                resultAddSubtract = Integer.toString(intAddSubtract1 + intAddSubtract2);
            } else if (problemList.get(0).equals("-")) {
                resultAddSubtract = Integer.toString(intAddSubtract1 - intAddSubtract2);
            }
            problemList.set(0, resultAddSubtract);
        }
        return problemList.get(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        Intent intent = getIntent();
        String problem = intent.getStringExtra(MainActivity.PROBLEM);

//        problem = problem.replaceAll("\\s", "");
//        String splitAddSubtract = "([0-9*/]+|([+-]))";
//        Pattern p = Pattern.compile(splitAddSubtract);
//        Matcher m = p.matcher(problem);
//        List<String> problemList = new ArrayList<String>();
//
//        while (m.find()) {
//            problemList.add(m.group(0));
//        }
//
//        for(Integer i = 0; i < problemList.size(); i++) {
//            List<String> equationMultiplyDivide = new ArrayList<String>();
//            String equation = problemList.get(i);
//
//            if (equation.contains("*") || equation.contains("/")) {
//                String splitMultiplyDivide = "(-?\\d+)|(\\D)";
//                Pattern p2 = Pattern.compile(splitMultiplyDivide);
//                Matcher m2 = p2.matcher(equation);
//
//                while (m2.find()) {
//                    equationMultiplyDivide.add(m2.group(0));
//                }
//
//                Integer intMultiplyDivide1 = null;
//                Integer intMultiplyDivide2 = null;
//                String resultMultiplyDivide = null;
//
//                while (equationMultiplyDivide.size() > 1) {
//                    intMultiplyDivide1 = Integer.parseInt(equationMultiplyDivide.get(0));
//                    intMultiplyDivide2 = Integer.parseInt(equationMultiplyDivide.get(2));
//                    equationMultiplyDivide.remove(2);
//                    equationMultiplyDivide.remove(0);
//
//                    if (equationMultiplyDivide.get(0).equals("*")) {
//                        resultMultiplyDivide = Integer.toString(intMultiplyDivide1 * intMultiplyDivide2);
//                    } else if (equationMultiplyDivide.get(0).equals("/")) {
//                        resultMultiplyDivide = Integer.toString(intMultiplyDivide1 / intMultiplyDivide2);
//                    }
//                    equationMultiplyDivide.set(0, resultMultiplyDivide);
//                }
//
//                problemList.set(i, resultMultiplyDivide);
//            }
//        }
//
//        Integer intAddSubtract1 = null;
//        Integer intAddSubtract2 = null;
//        String resultAddSubtract = null;
//
//        while (problemList.size() > 1) {
//            intAddSubtract1 = Integer.parseInt(problemList.get(0));
//            intAddSubtract2 = Integer.parseInt(problemList.get(2));
//            problemList.remove(2);
//            problemList.remove(0);
//
//            if (problemList.get(0).equals("+")) {
//                resultAddSubtract = Integer.toString(intAddSubtract1 + intAddSubtract2);
//            } else if (problemList.get(0).equals("-")) {
//                resultAddSubtract = Integer.toString(intAddSubtract1 - intAddSubtract2);
//            }
//            problemList.set(0, resultAddSubtract);
//        }
//        String answer = problemList.get(0);

        TextView textView = findViewById(R.id.textView);
        textView.setText(calculateAnswer(problem));
    }
}
