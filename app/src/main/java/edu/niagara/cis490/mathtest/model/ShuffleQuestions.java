package edu.niagara.cis490.mathtest.model;


import java.util.Random;

public class ShuffleQuestions {

    private Random random = new Random();
    static Question question = new Question();
    static char operatorChoice;
    int a, b;

    //Method To Randomize First Number
    public int randomizeFirstNumber() {
        a = random.nextInt(100);
        question.setA(a);
        return a;
    }

    //Method To Randomize Second Number
    public int randomizeSecondNumber() {
        b = random.nextInt(100);
        question.setA(b);
        return b;
    }

    public char randomizeOperator() {
        //Choice of Operator
        char[] arr = new char[5];
        arr[0] = '+';
        arr[1] = '-';
        arr[2] = '/';
        arr[3] = '%';
        arr[4] = '*';

        //Loop Through Random Operators
        for (int i = 0; i < 1; i++) {
            int randomIndex = random.nextInt(4);
            operatorChoice = arr[randomIndex];
        }
        return operatorChoice;
    }
}
