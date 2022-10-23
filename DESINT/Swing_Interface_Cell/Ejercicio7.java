
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.*; // ActionListener
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.*;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio7 {

    // DEFINICION DE VARIABLES GLOBALES
    public static File xmlFile = new File("dungeon.xml");
    public static final JFrame f = new JFrame();//creating instance of JFrame

    public static void main(String[] args) {
        // DIMENSIONES FRAME
        final int frameWidth = 800;
        final int frameHeight = 600;
        // LEER FICHERO XML

        try {
            final JPanel treeViewPanel = new JPanel();
            JMenuBar mb = new JMenuBar();
            JMenu menu;
            menu = new JMenu("Menu");
            JMenuItem loadMenuItem;
            JMenuItem startMenuItem;
            JTree jt = createTree(readXMLFile().getDocumentElement());
            loadMenuItem = loadMenuItemListener(treeViewPanel);
            startMenuItem = new JMenuItem(new AbstractAction("Start") {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Start!");
                }
            });

            /* ADD MENU ITEMS */
            menu.add(loadMenuItem);
            menu.add(startMenuItem);
            mb.add(menu);
            f.setJMenuBar(mb);
            /* ADD MENU ITEMS */
            jt.setFont(fontPlusTen(jt.getFont()));

            treeViewPanel.add(jt);
            treeViewPanelLayoutSettings(treeViewPanel, frameWidth, frameHeight);
            frameLayoutSettings(f);
            f.add(treeViewPanel);

        } catch (Exception e) {
            // SEGUIR POR ACA
            frameLayoutSettings(f);
            showErrorMessage(e.getMessage());
        }
    }

    private static JMenuItem loadMenuItemListener(final JPanel treeViewPanel) {
        return new JMenuItem(new AbstractAction("Load") {
            public void actionPerformed(ActionEvent e) {
                try {
                    Document newXML = loadXMLFile();
                    if (newXML != null) {
                        JTree newJt = createTree(newXML.getDocumentElement());
                        newJt.setFont(fontPlusTen(newJt.getFont()));
                        treeViewPanel.removeAll();
                        treeViewPanel.add(newJt);
                        treeViewPanel.revalidate();
                        treeViewPanel.repaint();
                    }
                } catch (Exception ex) {
                    showErrorMessage(ex.getMessage());
                }
            }
        });
    }

    private static void showErrorMessage(String message) {
        String error = "ERROR: " + message;
        final JTextArea textArea = new JTextArea(error,5,30);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(true);
        JPanel panel = new JPanel(new BorderLayout(3,3));
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null, panel);
    }

    private static Document loadXMLFile() throws IOException, ParserConfigurationException, SAXException {
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        j.setAcceptAllFileFilterUsed(false);
        j.addChoosableFileFilter(new FileNameExtensionFilter("Only XML", "xml"));
        // invoke the showsSaveDialog function to show the save dialog
        int r = j.showOpenDialog(null);


        // if the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected file
            System.out.println("Se eligio " + j.getSelectedFile().getAbsolutePath());
            xmlFile = new File(j.getSelectedFile().getAbsolutePath());
            return readXMLFile();
        }
        // if the user cancelled the operation
        else
            return null;
    }

    private static Document readXMLFile() throws IOException, SAXException, ParserConfigurationException {
        /* LEER XML VALIDO*/
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        return docBuilder.parse(xmlFile);
    }

    private static void treeViewPanelLayoutSettings(JPanel treeViewPanel, int frameWidth, int frameHeight) {
        treeViewPanel.setBackground(Color.darkGray);
        treeViewPanel.setBounds(0, 0,  Toolkit.getDefaultToolkit().getScreenSize().width/4, Toolkit.getDefaultToolkit().getScreenSize().height);
    }

    private static void frameLayoutSettings(JFrame f) {
//        f.setSize(800, 500);//400 width and 500 height
        f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setResizable(false);
        f.setFont(fontPlusTen(f.getFont()));
    }

    public static JTree createTree(Element rootEl) {


        DefaultMutableTreeNode dungeon = new DefaultMutableTreeNode(rootEl.getTagName());
        NodeList rooms = rootEl.getElementsByTagName("room");
        for (int i = 0; i < rooms.getLength(); i++) {
            Node room = rooms.item(i);
            DefaultMutableTreeNode roomTN = new DefaultMutableTreeNode(room.getNodeName() + " " + room.getAttributes().getNamedItem("id").getNodeValue());
            for (int j = 0; j < room.getChildNodes().getLength(); j++) {
                Node doorOrDesc = room.getChildNodes().item(j);
                DefaultMutableTreeNode doorOrDescTN;
                if (doorOrDesc.getNodeName().equals("door")) {
                    doorOrDescTN = new DefaultMutableTreeNode(doorOrDesc.getAttributes().getNamedItem("name").getNodeValue() + " -> " + doorOrDesc.getAttributes().getNamedItem("dest").getNodeValue());
                    roomTN.add(doorOrDescTN);
                }
                if (doorOrDesc.getNodeName().equals("description")) {
                    doorOrDescTN = new DefaultMutableTreeNode(doorOrDesc.getTextContent());
                    roomTN.insert(doorOrDescTN, 0);
                }
            }
            dungeon.add(roomTN);
            System.out.println("Agregada room " + rooms.item(i).getNodeName() + rooms.item(i).getAttributes().item(0));
        }

        JTree j = new JTree(dungeon);
        j.setBackground(Color.darkGray);
        return j;

    }

    public static Font fontPlusTen(Font currentFont) {
        return new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 10);
    }

}  