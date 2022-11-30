package org.example.componentes;

import javax.swing.*;
import java.awt.*;

public class JMazmorraLog extends JPanel implements JMazmorraLogInterface {

    private JTextArea logTextArea;

    public JMazmorraLog() {
        super();
        JMazmorraLogBasicConstructor();
    }

    public JMazmorraLog(String initialMessage) {
        super();
        JMazmorraLogBasicConstructor();
        addLogMessage(initialMessage);
    }

    private void JMazmorraLogBasicConstructor() {
        setFieldSettings();
    }

    private void setFieldSettings() {
        this.setBounds(300, 200, 500, 750);
        this.setLayout(null);

        // Settings of logTextArea
        this.logTextArea = new JTextArea();
        this.logTextArea.setLineWrap(true);
        this.logTextArea.setWrapStyleWord(true);
        this.logTextArea.setBackground(Color.pink);
        this.logTextArea.setEditable(false);
        this.logTextArea.setFont(new Font(getFont().getName(), getFont().getStyle(), getFont().getSize() + 4));

        // Settings of scrollPane
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        scrollPane.setBounds(0, 300, 485, 435);
        scrollPane.setVisible(true);

        this.removeAll();
        this.add(scrollPane);
    }

    @Override
    public void addLogMessage(String message) {
        logTextArea.append(message + "\n");
    }


    @Override
    public void clearLog() {
        logTextArea.setText("");
    }


}
