package vttp2023.batch3.ssf.frontcontroller.model;

import java.util.Random;

//Captcha display class
public class Captcha {
    
    private String captchaString;
    private double answer;
    private double input;
    private String operationString;
    private boolean verify;

    public Captcha() {
        Random rnd = new Random();
        int number1 = rnd.nextInt(51);
        int number2 = rnd.nextInt(51);
        int operation = rnd.nextInt(5);

        switch (operation) {
            case(1):
                this.answer = number1 + number2;
                this.operationString = "What is " + number1 + "+ " + number2 + "?";
            case(2):
                this.answer = number1 - number2;
                this.operationString = "What is " + number1 + "- " + number2 + "?";
            case(3):
                this.answer = number1 / number2 * 1.0;
                this.operationString = "What is " + number1 + "/ " + number2 + "?";
            case(4):
                this.answer = number1 * number2;
                this.operationString = "What is " + number1 + "* " + number2 + "?";
        
        }
    }

    public static Captcha createCaptcha() {
        Captcha captcha = new Captcha();
        return captcha;
    }

    public String getCaptchaString() {
        return captchaString;
    }
    public void setCaptchaString(String captchaString) {
        this.captchaString = captchaString;
    }
    public double getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public String getOperationString() {
        return operationString;
    }

    public void setOperationString(String operationString) {
        this.operationString = operationString;
    }

    public boolean isCorrect() {
        if (this.getInput() == this.getAnswer()) {
            return true;
        }

        return false;
    }

    

    
}
