package Converter.command_processor;

import Converter.ConvertModel;

public abstract class Command {
    protected ConvertModel convertModelRemote = null;

    public Command(ConvertModel convertModelRemote){
        this.convertModelRemote = convertModelRemote;
    }

    public abstract void execute();
}
