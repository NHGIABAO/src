package Converter.command_processor;

import Converter.ConvertModel;

public class c2f extends Command {
    private double Celsius;
    public c2f(ConvertModel convertModelRemote, double Celsius){
        super(convertModelRemote);
        this.Celsius = Celsius;
    }
    @Override
    public void execute() {
        convertModelRemote.c2f(Celsius);
    }

}
