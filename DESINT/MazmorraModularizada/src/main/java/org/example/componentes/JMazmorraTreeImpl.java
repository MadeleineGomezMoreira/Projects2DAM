package org.example.componentes;

import exception.JMazmorraException;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class JMazmorraTreeImpl extends JScrollPane implements JMazmorraTree {

//   JMazmorraTreeImpl es un scollPane que contiene un Tree
//    solamente recibe el dungeonXML y dungeon manager tiene un metodo que devuelve la dungeon
//    y el tree se crea con la dungeon
    private final DungeonXMLLoaderImpl dT;
    private JTree tree;

    public JMazmorraTreeImpl(DungeonXMLLoaderImpl dT) {
        this.dT = dT;
    }

    @Override
    public void newJTree() throws JMazmorraException {
        //this.setPreferredSize(new Dimension(150, 450));
        this.setPreferredSize(new Dimension(300, 935));
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        createTree();
        this.add(tree);
        tree.revalidate();
        tree.repaint();
    }

    private void createTree() throws JMazmorraException {
        if (dT.getDungeon() != null) {
            DefaultMutableTreeNode dungeonTreeNode = new DefaultMutableTreeNode(dT.getDungeon().getRooms());

            dT.getDungeon().getRooms().forEach(room -> {
                DefaultMutableTreeNode roomTreeNode = new DefaultMutableTreeNode(room.getId());
                DefaultMutableTreeNode descTreeNode = new DefaultMutableTreeNode(room.getDescription());
                roomTreeNode.add(descTreeNode);
                room.getDoors().forEach(door -> {
                    DefaultMutableTreeNode doorTreeNode = new DefaultMutableTreeNode(door.getName() + "--> " + door.getDestination());
                    roomTreeNode.add(doorTreeNode);
                });
                dungeonTreeNode.add(roomTreeNode);
            });

            tree = new JTree(dungeonTreeNode);
        } else {
            throw new JMazmorraException("No se ha podido cargar el JTree");
        }
    }

}
