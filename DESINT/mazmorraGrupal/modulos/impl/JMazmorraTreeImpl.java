package modulos.impl;

import exception.JMazmorraException;
import modulos.JMazmorraTree;
import ui.dungeonManager.DungeonXML;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class JMazmorraTreeImpl extends JScrollPane implements JMazmorraTree {

//   JMazmorraTreeImpl es un scollPane que contiene un Tree
//    solamente recibe el dungeonXML y dungeon manager tiene un metodo que devuelve la dungeon
//    y el tree se crea con la dungeon
    private final DungeonXML dT;
    private JTree tree;

    public JMazmorraTreeImpl(DungeonXML dT) {
        this.dT = dT;
    }

    @Override
    public void newJTree() throws JMazmorraException {
        this.setPreferredSize(new Dimension(150, 450));
        createTree();
        this.add(tree);
    }

    private void createTree() throws JMazmorraException {
        if (dT.getDungeon() != null) {
            DefaultMutableTreeNode dungeonTreeNode = new DefaultMutableTreeNode(dT.getDungeon().getName());

            dT.getDungeon().getRooms().forEach(room -> {
                DefaultMutableTreeNode roomTreeNode = new DefaultMutableTreeNode(room.getId());
                DefaultMutableTreeNode descTreeNode = new DefaultMutableTreeNode(room.getDescription());
                roomTreeNode.add(descTreeNode);
                room.getDoors().forEach(door -> {
                    DefaultMutableTreeNode doorTreeNode = new DefaultMutableTreeNode(door.getName() + "--> " + door.getDest());
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
