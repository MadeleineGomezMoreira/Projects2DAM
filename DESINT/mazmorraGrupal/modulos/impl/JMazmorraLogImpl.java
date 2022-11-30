package modulos.impl;

import modulos.JMazmorraLog;

import javax.swing.*;


public class JMazmorraLogImpl extends JTextArea implements JMazmorraLog {

    public JMazmorraLogImpl() {}

    @Override
    public void log(String mensaje) {
        this.append(mensaje + "\n");
    }
}
