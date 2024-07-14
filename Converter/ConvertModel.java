package Converter;

import Converter.observer.Pulisher;

public class ConvertModel extends Pulisher {
    private double resultF, resultC;

    public void c2f(double Celsius){
        this.resultC= Celsius;
        this.resultF = Celsius * 1.8 + 32.0;

        changeSate();
    }

    public void f2c(double Fahrenheit){
        this.resultF= Fahrenheit;
        this.resultC = (Fahrenheit - 32.0) / 1.8;
        changeSate();
    }

    public double getResultF(){
        return resultF;
    }

    public double getResultC(){
        return resultC;
    }

    private void changeSate(){
        notifySubcribers();
    }   

}
