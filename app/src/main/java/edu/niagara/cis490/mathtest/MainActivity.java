package edu.niagara.cis490.mathtest;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import edu.niagara.cis490.mathtest.model.ShuffleQuestions;

public class MainActivity extends AppCompatActivity {

    TextView textViewNumberA;
    TextView textViewNumberB;
    TextView textViewOperator;
    EditText editTextAnswer;
    TextView scoreLabel;
    TextView scoreValue;
    ShuffleQuestions shuffleQuestions = new ShuffleQuestions();
    private int a, b;
    private char oper;
    int valAnswer;
    private static String NUM_A_KEY = "num_a";
    private static String NUM_B_KEY = "num_b";
    private static String OPERATOR_KEY = "operator";
    boolean isCorrect;
    private static int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewNumberA = findViewById(R.id.tv_num_a);
        textViewNumberB = findViewById(R.id.tv_num_b);
        editTextAnswer = findViewById(R.id.et_answer);
        textViewOperator = findViewById(R.id.tv_operator_type);
        scoreLabel = findViewById(R.id.tv_score_label);
        scoreValue = findViewById(R.id.tv_score_value);
        if (savedInstanceState != null && savedInstanceState.containsKey(NUM_A_KEY) && savedInstanceState.containsKey(NUM_B_KEY)) {
            a = savedInstanceState.getInt(NUM_A_KEY);
            b = savedInstanceState.getInt(NUM_B_KEY);
            oper = savedInstanceState.getChar(OPERATOR_KEY);
        } else {
            generateRandomNumbers();
        }
    }

    //Generate Questions
    public void clickGenerateQuestionButton(View v) {
        generateRandomNumbers();
    }

    //Generate Numbers on Launch
    public void generateRandomNumbers() {
        a = shuffleQuestions.randomizeFirstNumber();
        b = shuffleQuestions.randomizeSecondNumber();
        oper = shuffleQuestions.randomizeOperator();
        if (oper == '+') {
            textViewOperator.setText("+");
        } else if (oper == '-') {
            textViewOperator.setText("-");
        } else if (oper == '/') {
            textViewOperator.setText("/");
        } else if (oper == '%') {
            textViewOperator.setText("%");
        } else if (oper == '*') {
            textViewOperator.setText("*");
        }
        String firstNumber = String.format(Locale.getDefault(), "%d", a);
        String secondNumber = String.format(Locale.getDefault(), "%d", b);
        textViewNumberA.setText(firstNumber);
        textViewNumberB.setText(secondNumber);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        int key_num_a = Integer.parseInt(textViewNumberB.getText().toString());
        int key_num_b = Integer.parseInt(textViewNumberA.getText().toString());
        char key_operator = textViewOperator.getText().charAt(0);
        outState.putInt(NUM_A_KEY, key_num_a);
        outState.putInt(NUM_B_KEY, key_num_b);
        outState.putChar(OPERATOR_KEY, key_operator);
    }

    //Submit Answer And Generate New Question
    public void submitAnswer(View v) {
        calculateAnswer();
        generateRandomNumbers();
    }

    //Calculate Answer Depemding on value of first or second number and operator,if its correct boolean value equals to true else false
    //Clears Edit Text After Submission Of Answer
    public boolean calculateAnswer() {
        String valueA = textViewNumberA.getText().toString();
        String operator = textViewOperator.getText().toString();
        String valueB = textViewNumberB.getText().toString();
        String answer = editTextAnswer.getText().toString();

        if (answer.equals("")) {
            Toast.makeText(MainActivity.this, "Enter A Valid Value", Toast.LENGTH_SHORT).show();
        } else {
            valAnswer = Integer.parseInt(answer);
            int valA = Integer.parseInt(valueA);
            int valB = Integer.parseInt(valueB);
            if (operator.equals("+")) {
                if (valA + valB == valAnswer) {
                    isCorrect = true;
                    score++;
                    Toast.makeText(MainActivity.this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
                } else {
                    isCorrect = false;
                    Toast.makeText(MainActivity.this, getString(R.string.incorrect_answer) + (valA + valB), Toast.LENGTH_LONG).show();
                }
            } else if (operator.equals("-")) {
                if (valA - valB == valAnswer) {
                    isCorrect = true;
                    score++;
                    Toast.makeText(MainActivity.this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
                } else {
                    isCorrect = false;
                    Toast.makeText(MainActivity.this, getString(R.string.incorrect_answer) + (valA - valB), Toast.LENGTH_LONG).show();
                }
            } else if (operator.equals("/")) {
                if (valB != 0) {
                    if (valA / valB == valAnswer) {
                        isCorrect = true;
                        score++;
                        Toast.makeText(MainActivity.this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
                    } else {
                        isCorrect = false;
                        Toast.makeText(MainActivity.this, getString(R.string.incorrect_answer) + (valA / valB), Toast.LENGTH_LONG).show();
                    }
                }
            } else if (operator.equals("%")) {
                if (valA % valB == valAnswer) {
                    isCorrect = true;
                    score++;
                    Toast.makeText(MainActivity.this, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show();
                } else {
                    isCorrect = false;
                    Toast.makeText(MainActivity.this, getString(R.string.incorrect_answer) + (valA % valB), Toast.LENGTH_LONG).show();
                }
            } else if (operator.equals("*")) {
                if (valA * valB == valAnswer) {
                    isCorrect = true;
                    score++;
                    Toast.makeText(MainActivity.this, getString(R.string.correct_answer), Toast.LENGTH_LONG).show();
                } else {
                    isCorrect = false;
                    Toast.makeText(MainActivity.this, getString(R.string.incorrect_answer) + (valA * valB), Toast.LENGTH_LONG).show();
                }
            }
        }
        //Clear Edit text on submissiin
        editTextAnswer.setText("");
        //Set Score Text
        String scoreString = String.valueOf(score);
        scoreValue.setText(scoreString);

        return isCorrect;
    }


    //Radio Button to check whether to display score or not
    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.radio_yes:
                if (checked)
                    scoreValue.setVisibility(View.VISIBLE);
                scoreLabel.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_no:
                if (checked)
                    scoreValue.setVisibility(View.GONE);
                scoreLabel.setVisibility(View.GONE);
                break;
        }
    }
}
