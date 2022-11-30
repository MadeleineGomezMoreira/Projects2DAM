package org.example.componentes;

import exception.JMazmorraException;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JMazmorraNavigationImpl extends JPanel implements JMazmorraNavigation {

//    pinta el navigation de la room actual, cargando el texto y los botones con sus listeners
//    el listener de los botones llama a la interfaz del dungeonManager
//    el dungeonManager tendra que llamar al metodo newRoom de la interfaz JMazmorraNavigation para que actualice la room
    private final DungeonXMLLoaderImpl dM;
    private List<String> directions;
    private String description;
    private JButton buttonNorte;
    private JButton buttonSur;
    private JButton buttonEste;
    private JButton buttonOeste;
    private TextArea roomInfo;


    public JMazmorraNavigationImpl(DungeonXMLLoaderImpl dM) {
        this.dM = dM;
        this.setMinimumSize(new Dimension(450, 225));
    }

    @Override
    public void newRoom(List<String> list, String desc) throws JMazmorraException {
        this.directions = list;
        this.description = desc;
        createComponents();
        setComponents(this);
    }

    private void createComponents() {
        roomInfo = new TextArea();
        roomInfo.setEditable(false);
        buttonNorte = new JButton("Norte");
        buttonNorte.setPreferredSize(new Dimension(50, 50));
        buttonSur = new JButton("Sur");
        buttonSur.setPreferredSize(new Dimension(50, 50));
        buttonEste = new JButton("Este");
        buttonEste.setPreferredSize(new Dimension(100, 100));
        buttonOeste = new JButton("Oeste");
        buttonOeste.setPreferredSize(new Dimension(100, 100));
    }

    private void setComponents(JPanel navigation) throws JMazmorraException {
        if (description != null) {
            roomInfo.setText(description);
            navigation.add(roomInfo, BorderLayout.CENTER);
        }
        for (String direction : directions) {
            switch (direction) {
                case "Norte" -> {
                    navigation.add(buttonNorte, BorderLayout.NORTH);
                    addListenerButton(buttonNorte);
                }
                case "Sur" -> {
                    navigation.add(buttonSur, BorderLayout.SOUTH);
                    addListenerButton(buttonSur);
                }
                case "Este" -> {
                    navigation.add(buttonEste, BorderLayout.EAST);
                    addListenerButton(buttonEste);
                }
                case "Oeste" -> {
                    navigation.add(buttonOeste, BorderLayout.WEST);
                    addListenerButton(buttonOeste);
                }
                default -> throw new JMazmorraException("Error al añadir el boton");
            }
        }
    }

    private void addListenerButton(JButton button) {
        button.addActionListener(e -> dM.getDungeon().getRooms().nextRoom(button.getText()));
    }
}
