package ui.dungeonManager.impl;

import exception.JMazmorraException;
import modelo.Door;
import modelo.Dungeon;
import modelo.Room;
import modulos.JMazmorraLog;
import modulos.JMazmorraNavigation;
import modulos.JMazmorraTree;
import modulos.JMazmorraXML;
import modulos.impl.JMazmorraLogImpl;
import modulos.impl.JMazmorraNavigationImpl;
import modulos.impl.JMazmorraTreeImpl;
import modulos.impl.JMazmorraXMLImpl;
import ui.dungeonManager.DungeonRoomManager;
import ui.dungeonManager.DungeonXML;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DungeonManagerImpl extends JFrame implements DungeonXML, DungeonRoomManager {

    private JSplitPane splitHorizontal;
    private JSplitPane splitVertical;
    private Dungeon dungeon;
    private Room roomActual;
    private JMazmorraXML xml;
    private JMazmorraTree tree;
    private JMazmorraNavigation navigation;
    private JMazmorraLog log;


    public DungeonManagerImpl() throws JMazmorraException {
        this.xml = new JMazmorraXMLImpl();
        this.tree = new JMazmorraTreeImpl(this);
        this.navigation = new JMazmorraNavigationImpl(this);
        this.log = new JMazmorraLogImpl();

    }

    private void setMenuBar() {
        //setting up the menuBar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setVisible(true);
        menuBar.setOpaque(true);
        menuBar.setPreferredSize(new Dimension(1600, 20));

        JMenu menu = new JMenu("Options");
        JMenuItem menuItem = new JMenuItem("Load Dungeon");
        JMenuItem menuItem2 = new JMenuItem("Start Run");

        menuItem.addActionListener(e -> cargarPantalla());
        menuItem2.addActionListener(e -> setRightPanel());

        menu.add(menuItem);
        menu.add(menuItem2);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    private void cargarPantalla() {
        //setting up the main panel
        splitHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitHorizontal.setDividerLocation(250);
        //left panels
        setLeftPanel();
        this.getContentPane().add(splitHorizontal);
        this.validate();
        this.repaint();
    }

    private void setLeftPanel() {
        //eliges el xml y lo cambia a dungeon y con la dungeon creas el tree que se añade al scrollPane

//        esto cambia dependiendo de la interfaz que haya creado tomas
//        pero tendria que devolverme la dungeon en algun metodo
//        this.dungeon = xml.getDungeonFromXML();

//        Creas un nuevo tree y se añade al scrollPane, el scrollPane es el propio JMazmorraTreeImpl
        splitHorizontal.setLeftComponent();
//        tree.newJTree();
    }

    private void setRightPanel() {
        //splitVertical panel is divided into 2 parts
        splitVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitVertical.setDividerLocation(350);
        splitVertical.setMinimumSize(new Dimension(450, 450));


//        Creas la roomPrincipal y llamando a la interfaz de JMazmorraNavigationImpl, añado los botones de la nueva room y su desc
//        añado xq el JMazmorraNavigationImpl es el panel
//        roomActual = dungeon.getRooms().stream().findFirst().orElse(null);
//        List<String> directions = roomActual.getDoors().stream().map(Door::getDest).toList();
//        navigation.newRoom(directions, roomActual.getDescription());

        splitVertical.setTopComponent();
        splitVertical.setBottomComponent();

//        El log solo lo llamas cuando cambies de room en el metodo newRoom
//        log.log("Bienvenido al Dungeon");

        splitHorizontal.setRightComponent(splitVertical);
    }


    @Override
    public Dungeon getDungeon() {
        return dungeon;
    }

    @Override
    public void nextRoom(String direction) {
        List<Room> rooms = dungeon.getRooms();
        String id = roomActual.getId();
        rooms.stream().filter(room -> room.getId().equals(id)).findFirst().ifPresent(room -> {
            List<Door> doors = room.getDoors();
            doors.stream().filter(door -> door.getDest().equals(direction)).findFirst()
                    .ifPresent(door -> {
                        roomActual = rooms.stream().filter(room1 -> room1.getId().equals(door.getDest())).findFirst().orElse(null);
                        log.log("Has entrado en la habitacion " + roomActual.getId());
                        List<String> directions = roomActual.getDoors().stream().map(Door::getDest).toList();
                        try {
                            navigation.newRoom(directions, roomActual.getDescription());
                        } catch (JMazmorraException e) {
                            throw new RuntimeException(e);
                        }
                    });
        });
    }
}
