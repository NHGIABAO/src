package Converter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Converter.command_processor.Command;
import Converter.command_processor.CommandProcessor;
import Converter.command_processor.c2f;
import Converter.command_processor.f2c;
import Converter.observer.Subriber;

public class ConvertView extends JFrame implements Subriber {
    private JLabel jLabelRemoteC, jLabelRemoteF;
    private static JTextField jTextFieldRemoteC = null;
    private static JTextField jTextFieldRemoteF = null;
    private JPanel jPanelRemote;
    private static ConvertModel convertModelRemote;
    private JMenuBar menuBarRemote;
    private static MenuController menuControllerRemote;

    public ConvertView(MenuController menuControllerRemote, ConvertModel convertModelRemote, EnterController enterControlRemote) {
        this.menuControllerRemote = menuControllerRemote;
        this.convertModelRemote = convertModelRemote;

        this.convertModelRemote.subribe(this); //đăng ký subscriber với publisher

        buildMenu();
        buildPanel();

        setJMenuBar(menuBarRemote); //đặt thanh MenuBar vào trong cửa sổ
        
        add(jPanelRemote);
        setTitle("Temperature Converter");
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jTextFieldRemoteC.addActionListener(enterControlRemote);
        jTextFieldRemoteF.addActionListener(enterControlRemote);

    }

    public void buildPanel() {
        jPanelRemote = new JPanel();

        jLabelRemoteC = new JLabel("Celsius");
        jTextFieldRemoteC = new JTextField(10);
        jPanelRemote.add(jLabelRemoteC);
        jPanelRemote.add(jTextFieldRemoteC);

        jLabelRemoteF = new JLabel("Fahrenheit");
        jTextFieldRemoteF = new JTextField(10);
        jPanelRemote.add(jLabelRemoteF);
        jPanelRemote.add(jTextFieldRemoteF);


    }

    public void buildMenu() {
        menuBarRemote = new JMenuBar();
        JMenu calMenuRemote = new JMenu("Commands");

        menuBarRemote.add(calMenuRemote); //đặt menu vào thanh menu

        //menu item
        JMenuItem f2cMenuItemRemote = new JMenuItem("f2c");
        f2cMenuItemRemote.addActionListener(menuControllerRemote);

        JMenuItem c2fMenuItemRemote = new JMenuItem("c2f");
        c2fMenuItemRemote.addActionListener(menuControllerRemote);

        JMenuItem exitMenuItemRemote = new JMenuItem("exit");
        exitMenuItemRemote.addActionListener(menuControllerRemote);

        //đặt menu item vào trong menu
        calMenuRemote.add(f2cMenuItemRemote);
        calMenuRemote.add(c2fMenuItemRemote);
        calMenuRemote.add(exitMenuItemRemote);
    }

    static class EnterController implements ActionListener {
        private ConvertModel convertModelRemote;
        private ConvertView.MenuController menuControllerRemote;

        public EnterController(ConvertModel convertModelRemote, ConvertView.MenuController menuControllerRemote) {
            this.convertModelRemote = convertModelRemote;
            this.menuControllerRemote = menuControllerRemote;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField source = (JTextField) e.getSource();

            if (source == jTextFieldRemoteC) {
                if (!jTextFieldRemoteC.getText().isEmpty()) {
                    double celsius = Double.parseDouble(jTextFieldRemoteC.getText());
                    Command command = new c2f(convertModelRemote, celsius);
                    menuControllerRemote.commandProcessorRemote.execute(command);
                }
            } else if (source == jTextFieldRemoteF) {
                if (!jTextFieldRemoteF.getText().isEmpty()) {
                    double fahrenheit = Double.parseDouble(jTextFieldRemoteF.getText());
                    Command command = new f2c(convertModelRemote, fahrenheit);
                    menuControllerRemote.commandProcessorRemote.execute(command);
                }
            }
        }
    }


    static class MenuController implements ActionListener {
        private CommandProcessor commandProcessorRemote;

        public MenuController(CommandProcessor commandProcessor) {
            this.commandProcessorRemote = commandProcessor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            Command commandRemote = null;

            if (command.equals("f2c")) {
                double fahrenheit = Double.parseDouble(jTextFieldRemoteF.getText());
                commandRemote = new f2c(convertModelRemote, fahrenheit);
                commandProcessorRemote.execute(commandRemote);

            } else if (command.equals("c2f")) {
                double celsius = Double.parseDouble(jTextFieldRemoteC.getText());
                commandRemote = new c2f(convertModelRemote, celsius);
                commandProcessorRemote.execute(commandRemote);
                
            } else if (command.equals("exit")) {
                System.exit(0);
            }
        }
    }

    @Override
    public void update(){ 
        jTextFieldRemoteF.setText(String.format("%.2f", convertModelRemote.getResultF()));
        jTextFieldRemoteC.setText(String.format("%.2f", convertModelRemote.getResultC()));
    }
}
