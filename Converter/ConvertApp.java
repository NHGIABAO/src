package Converter;

import Converter.command_processor.CommandProcessor;

public class ConvertApp {
    public static void main(String[] args) {
        ConvertModel convertModelRemote = new ConvertModel();
        CommandProcessor commandProcessor = CommandProcessor.makeCommandProcessor();
        ConvertView.MenuController menuControllerRemote =   new ConvertView.MenuController(commandProcessor);
        ConvertView.EnterController  enterControllerRemote = new ConvertView.EnterController(convertModelRemote, menuControllerRemote);

        ConvertView convertViewRemote = new ConvertView(menuControllerRemote, convertModelRemote, enterControllerRemote);
    }
}
