package com.example.s.myquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startEvaluation(View view) {
        String[] answers = evaluate();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    public String[] evaluate() {
        String[] ret = new String[5];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2Int = findViewById(R.id.question_2_int);
        CheckBox checkBoxQuestion2Float = findViewById(R.id.question_2_float);
        CheckBox checkBoxQuestion2Super= findViewById(R.id.question_2_super);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2Int.isChecked() == true && checkBoxQuestion2Float.isChecked() == true && checkBoxQuestion2Super.isChecked() == false) {
            answerQuestion2 = true;
        }

        CheckBox checkBoxQuestion4Clas = findViewById(R.id.question_4_clas);
        CheckBox checkBoxQuestion4Object = findViewById(R.id.question_4_object);
        CheckBox checkBoxQuestion4Door = findViewById(R.id.question_4_door);

        Boolean answerQuestion4 = false;

        Boolean clas = checkBoxQuestion4Clas.isChecked();
        Boolean Object = checkBoxQuestion4Object.isChecked();
        Boolean Door = checkBoxQuestion4Door.isChecked();


        if (clas == true && Object == true && Door == false) {
            answerQuestion4 = true;
        }

        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();

        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"oak", "true", "package", "true", "throwable"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    public void toastResult(int result) {
        String message = result + " out of 5. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Quite nice.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        } else if (result == 5) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_int);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_float);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_super);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question_4_clas);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_object);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_door);
        checkBox.setChecked(false);

        radioGroup = findViewById(R.id.radio_group_question_5);
        radioGroup.clearCheck();
    }
}

