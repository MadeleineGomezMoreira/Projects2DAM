package org.example.componentes;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DungeonXMLLoaderImpl implements DungeonXMLLoader {

    private Dungeon dungeon;

    public DungeonXMLLoaderImpl() {
        this.dungeon = null;
    }



@Override
    public void loadXMLFile() throws Exception {
        try {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            j.setAcceptAllFileFilterUsed(false);
            j.addChoosableFileFilter(new FileNameExtensionFilter("Only XML", "xml"));
            int r = j.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File xmlFile = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document xmlDocument = dBuilder.parse(xmlFile);
                    updateList(xmlDocument);
                } catch (Exception e) {
                    throw new Exception(e);
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public Dungeon getDungeon() {
        return dungeon;
    }
    private void updateList(Document xmlDocument) throws Exception {
        try {

            if (xmlDocument != null && xmlDocument.getFirstChild().getNodeName().equals("dungeon")) {
                NodeList rooms = xmlDocument.getDocumentElement().getElementsByTagName("room");
                List<Room> roomsList = new ArrayList<>();
                for (int i = 0; i < rooms.getLength(); i++) {
                    Node room = rooms.item(i);
                    String idRoom = room.getAttributes().getNamedItem("id").getNodeValue();
                    String description ="";
                    List<Door> doors = new ArrayList<>();
                    for (int j = 0; j < room.getChildNodes().getLength(); j++) {
                        Node doorOrDesc = room.getChildNodes().item(j);
                        if (doorOrDesc.getNodeName().equals("door")) {
                            String nameDoor = doorOrDesc.getAttributes().getNamedItem("name").getNodeValue();
                            String destDoor = doorOrDesc.getAttributes().getNamedItem("dest").getNodeValue();
                            Door door = new Door(nameDoor, destDoor);
                            doors.add(door);
                        }
                        else if (doorOrDesc.getNodeName().equals("description")) {
                            description = doorOrDesc.getTextContent();
                        }
                    }
                    Room roomObj = new Room(idRoom, description, doors);
                    roomsList.add(roomObj);
                }
                this.dungeon = new Dungeon(roomsList);
            } else {
                this.dungeon = null;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
