package edu.niagara.cis490.mathtest.model;


public class Question {
    //Question Model Class
    //Format a + b = c
    private int a;
    private int b;
    private int c;

    Question() {

    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int a, int b) {
        c = a + b;
    }
}
