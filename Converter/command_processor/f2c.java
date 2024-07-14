package Converter.command_processor;

import Converter.ConvertModel;

public class f2c extends Command {
    private double Fahrenheit;
    public f2c(ConvertModel convertModelRemote, double Fahrenheit){
        super(convertModelRemote);
        this.Fahrenheit = Fahrenheit;
    }
    @Override
    public void execute() {
       convertModelRemote.f2c(Fahrenheit);
    }
}
