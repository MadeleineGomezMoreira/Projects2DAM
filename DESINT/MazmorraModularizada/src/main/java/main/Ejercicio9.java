package main;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.*;
import javax.xml.xpath.*;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.RenderingHints;
import javax.swing.UIManager;

import org.example.componentes.Dungeon;
import org.example.componentes.DungeonXMLLoaderImpl;
import org.example.componentes.JMazmorraLog;
import org.example.componentes.JMazmorraTreeImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Ejercicio9 {


    //DEFINICION VARIABLES GLOBALES
    public static Document xmlDocument; //El documento sobre el cual estamos trabajando

    public static Dungeon dungeon = null; //La mazmorra que estamos cargando
    public static final JFrame f = new JFrame(); //Ventana principal

//            public static final JTextArea logTextArea = new JTextArea();
    public static JMazmorraLog jMazmorraLog = new JMazmorraLog("Hola! Este es tu log, donde se registraran tus movimientos por la mazmorra!");
    public static DungeonXMLLoaderImpl dungeonXMLLoader = new DungeonXMLLoaderImpl();
    public static JMazmorraTreeImpl jMazmorraTree = new JMazmorraTreeImpl(dungeonXMLLoader);

    //Area de texto para mostrar el log

    static final JPanel pCell = new JPanel(); //Panel para los botones

    public static void main(String[] args) {
        //JPanel with paintComponent to make it gradient
        final JPanel treeViewPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0,
                        getBackground().brighter().brighter(), 0, getHeight(),
                        getBackground().darker().darker());
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());

            }

        };
        //Panel para el arbol
        JMenuBar mb = new JMenuBar(); //Barra de menu
        JMenu menu;
        menu = new JMenu("Menu"); // MENU
        JMenuItem loadMenuItem; //Elemento de menu para cargar el archivo
        JMenuItem startMenuItem; //Elemento de menu para iniciar el programa
        loadMenuItem = loadMenuItemListener(treeViewPanel);
        startMenuItem = startMenuItemListener(pCell);

        /* ADD MENU ITEMS  - LA FUNCIONALIDAD ESTA AQUI */
        menu.add(loadMenuItem);
        menu.add(startMenuItem);
        /* ADD MENU ITEMS  - LA FUNCIONALIDAD ESTA AQUI */

        mb.add(menu); // AÑADIMOS MENU A LA BARRA DE MENU
        f.setJMenuBar(mb); // AÑADIMOS BARRA DE MENU A LA VENTANA

        // CREAMOS MENSAJE DE BIENVENIDA Y LO AÑADIMOS A DONDE ESTARAN LOS BOTONES
        getWelcomeMessage();
        // CREAMOS MENSAJE DE BIENVENIDA Y LO AÑADIMOS A DONDE ESTARAN LOS BOTONES


        // AÑADIMOS LA CONFIGURACION VISUAL A LOS PANELES
        treeViewPanelLayoutSettings(treeViewPanel);
        pCellLayoutSettings(pCell);
        frameLayoutSettings(f);
        // AÑADIMOS LA CONFIGURACION VISUAL A LOS PANELES

        // AÑADIMOS LOS PANELES A LA VENTANA
        f.add(treeViewPanel);
        f.add(pCell);
        f.add(jMazmorraLog);
        // AÑADIMOS LOS PANELES A LA VENTANA
    }


    private static JMenuItem startMenuItemListener(JPanel pCell) {
        return new JMenuItem(new AbstractAction("Start") {
            // EL USUARIO QUIERE COMENZAR A JUGAR
            public void actionPerformed(ActionEvent e) {
                // SI HAY UN XML CARGADO, SE PUEDE JUGAR
                if (dungeon != null) {
                    int contPasos = 1; // CONTADOR DE PASOS PARA EL LOG
                    //Node firstRoom = xmlDocument.getElementsByTagName("room").item(0); //COGEMOS LA PRIMERA ROOM
                    jMazmorraLog.addLogMessage("Comienza la aventura!\n");
                    jMazmorraLog.addLogMessage(contPasos + " - Has comenzado en la habitacion "  /*+ firstRoom.getAttributes().getNamedItem("id").getNodeValue()*/);
                    // FUNCIONALIDAD PRINCIPAL
                    //todo crear botones y moverse
                    //createpCellButtons(firstRoom, pCell, contPasos);
                    //todo crear botones y moverse
                    // FUNCIONALIDAD PRINCIPAL
                } else {
                    // SI NO HAY UN XML CARGADO, NO SE PUEDE JUGAR
                    showErrorMessage("No se ha cargado ningún archivo");
                }
            }
        });
    }

    private static void createpCellButtons(Node room, JPanel pCell, int contPasos) {
        try {
            NodeList roomChildren = room.getChildNodes(); // COGEMOS LOS HIJOS DE LA ROOM (DOOR(s) y DESCRIPTION)

            contPasos++; // AUMENTAMOS EL CONTADOR DE PASOS DEL LOG
            pCell.removeAll(); // LIMPIAMOS EL PANEL DE BOTONES CON LAS PUERTAS Y DESCRIPCION ANTERIORES
            for (int i = 0; i < roomChildren.getLength(); i++) {
                //ANALIZAMOS CADA CHILDREN ELEMENT DE LA ROOM
                Node roomChild = roomChildren.item(i); // <-- El nodo que estamos analizando
                if (roomChild.getNodeName().equalsIgnoreCase("description")) {
                    // SI EL CHILD ES UNA DESCRIPTION, CREAMOS EL LABEL CON SU VALOR
                    JLabel lbCell = new JLabel(roomChild.getTextContent());//label inside the cell panel
                    JScrollPane scroller = new JScrollPane(lbCell);
                    lbCell.setHorizontalAlignment(SwingConstants.CENTER);
                    lbCell.setBounds(125, 40, 250, 410);
                    scroller.setBounds(125, 40, 250, 410);
                    pCell.add(scroller);
                    // SI EL CHILD ES UNA DESCRIPTION, CREAMOS EL LABEL CON SU VALOR
                    //AÑADIMOS LA DESCIPTION DE LA ROOM AL LOG (No es un paso)
                    jMazmorraLog.addLogMessage("Descripcion de la habitacion: " + roomChild.getTextContent() + "\n\n");
//                    logTextArea.append("Descripcion de la habitacion: " + roomChild.getTextContent() + "\n\n");
                    //AÑADIMOS LA DESCIPTION DE LA ROOM AL LOG (No es un paso)
                } else if (roomChild.getNodeName().equalsIgnoreCase("door")) {
                    // SI EL CHILD ES UNA PUERTA, CREAMOS EL BOTON CON SU VALOR
                    final JGradientButton button = new JGradientButton();
                    String orientation = roomChild.getAttributes().getNamedItem("name").getNodeValue();

                    //METODO PARA PONER BOTONES CON COLORES
                    //colourButtons();
                    // TENEMOS QUE VER LA ORIENTACION DE LA DOOR PARA SABER DONDE COLOCAR EL BOTON
                    switch (orientation.toLowerCase()) {
                        case "norte":
                            button.setBounds(0, 0, 500, 40);
                            break;
                        case "sur":
                            button.setBounds(0, 450, 500, 40);
                            break;
                        case "este":
                            button.setBounds(0, 40, 125, 410);
                            break;
                        case "oeste":
                            button.setBounds(360, 40, 125, 410);
                            break;
                        default:
                            // SI NO ES UNA ORIENTACION VALIDA, NO SE CREA EL BOTON y SE MUESTRA EL ERROR
                            showErrorMessage("Error en la orientacion de la puerta de la habitacion " + room.getAttributes().getNamedItem("id").getNodeValue() + "\n" +
                                    "La orientacion de la puerta debe ser norte, sur, este u oeste \n" + "Una vez corregido, vuelve a cargar el archivo y a iniciar el juego.");
                            break;
                    }
                    // TENEMOS QUE VER LA ORIENTACION DE LA DOOR PARA SABER DONDE COLOCAR EL BOTON
                    //LE PONEMOS DE TEXTO EL ID DE LA PUERTA DE DESTINO
                    button.setText(roomChild.getAttributes().getNamedItem("dest").getNodeValue());
                    //LE PONEMOS DE TEXTO EL ID DE LA ROOM DE DESTINO
                    int finalContPasos = contPasos; // SE PASA A UNA VARIABLE FINAL POR LA EXPRESIÓN LAMBDA
                    //ON CLICK LISTENER DEL BOTON
                    button.addActionListener(e -> {
                        try {
                            //CREAMOS UNA QUERY PARA ENCONTRAR LA ROOM DE DESTINO, SABIENDO QUE SU ID ES EL TEXTO DEL BOTON
                            String query = "//room[@id=\"" + button.getText() + "\"]";
                            //CREAMOS UNA QUERY PARA ENCONTRAR LA ROOM DE DESTINO, SABIENDO QUE SU ID ES EL TEXTO DEL BOTON
                            // BUSCAMOS LA ROOM DE DESTINO CON XPATH
                            XPathFactory xPathFactory = XPathFactory.newInstance();
                            XPath xpath = xPathFactory.newXPath();
                            XPathExpression expr = xpath.compile(query);
                            Node resultNode = (Node) expr.evaluate(xmlDocument.getDocumentElement(), XPathConstants.NODE); // <-- La room de destino
                            // BUSCAMOS LA ROOM DE DESTINO CON XPATH
                            //CON LAS COORDENADAS DEL BOTON CLICKADO, SABEMOS LA ORIENTACION HACIA LA QUE SE MUEVE EL JUGADOR (VER COORDENADAS DEL BOTON MAS ARRIBA)
                            String orientacionElegida;
                            if (button.getY() == 0) {
                                orientacionElegida = "norte";
                            } else if (button.getY() == 450) {
                                orientacionElegida = "sur";
                            } else if (button.getX() == 0) {
                                orientacionElegida = "este";
                            } else {
                                orientacionElegida = "oeste";
                            }
                            //CON LAS COORDENADAS DEL BOTON CLICKADO, SABEMOS LA ORIENTACION HACIA LA QUE SE MUEVE EL JUGADOR (VER COORDENADAS DEL BOTON MAS ARRIBA)
                            // AÑADIMOS EL PASO AL LOG CON LA ORIENTACION ELEGIDA, LA ROOM DE DESTINO Y EL CONTADOR DE PASOS
//                            logTextArea.append(finalContPasos + " - Te has movido hacia el " + orientacionElegida + " y has entrado a la habitacion " + button.getText() + "\n");
                            jMazmorraLog.addLogMessage(finalContPasos + " - Te has movido hacia el " + orientacionElegida + " y has entrado a la habitacion " + button.getText() + "\n");
                            // AÑADIMOS EL PASO AL LOG CON LA ORIENTACION ELEGIDA, LA ROOM DE DESTINO Y EL CONTADOR DE PASOS

                            // VOLVEMOS A CREAR LOS BOTONES, PERO AHORA CON LA ROOM DE DESTINO
                            createpCellButtons(resultNode, pCell, finalContPasos);
                            // VOLVEMOS A CREAR LOS BOTONES, PERO AHORA CON LA ROOM DE DESTINO

                        } catch (XPathExpressionException ex) {
                            showErrorMessage("ERROR AL ENCONTRAR LA HABITACION " + button.getText() + ex.getMessage());
                        }
                    });
                    //ON CLICK LISTENER DEL BOTON
                    //AÑADIMOS EL BOTON AL PANEL
                    pCell.add(button);
                    //AÑADIMOS EL BOTON AL PANEL
                }
            }
            //REPINTAMOS LA CELDA CON LOS BOTONES ACTUALIZADOS
            pCell.revalidate();
            pCell.repaint();
            //REPINTAMOS LA CELDA CON LOS BOTONES ACTUALIZADOS
        } catch (Exception e) {
            //ERROR XML INVALIDO --> LO QUE HAY EN UN ATRIBUTO DEST NO CORRESPONDE CON EL ATRIBUTO ID DE NINGUNA ROOM
            showErrorMessage("La room con el id " + room.getAttributes().getNamedItem("id").getNodeValue() + " no tiene hijos.\n" +
                    "Comprueba que el atributo dest de las puertas de la room " + room.getAttributes().getNamedItem("id").getNodeValue() + " corresponde con el atributo id de alguna room.\n" +
                    "Una vez corregido, vuelve a cargar el archivo y a iniciar el juego.");
        }
    }

    private static void getWelcomeMessage() {
        JTextArea welcomeLabel = new JTextArea("Bienvenido a la mazmorra!!\n Para poder disfrutar de este juego unico, sigue los siguientes pasos:\n\n" +
                "1 - En el menu superior, pulsa LOAD.\n\n" +
                "2 - Selecciona un fichero XML cuyo elemento root sea <dungeon> \ny que se valide con el siguiente DTD:\n\n" +
                "<!DOCTYPE Dungeon [\n" +
                "<!ELEMENT dungeon (room+)>\n" +
                "<!ELEMENT room (door+, description)>\n" +
                "<!ELEMENT door EMPTY >\n" +
                "<!ELEMENT description (#PCDATA)>\n" +
                "<!ATTLIST dungeon name CDATA #REQUIRED>\n" +
                "<!ATTLIST room id ID #REQUIRED>\n" +
                "<!ATTLIST door name CDATA #REQUIRED>\n" +
                "<!ATTLIST door dest IDREF #REQUIRED>\n" +
                "]>\n\n" +
                "3 - Haz doble click sobre el o pulsa \"Abrir\"\n\n" +
                "4 - Una vez cargado el fichero, veras a la izquierda tu mazmorra con sus habitaciones.\n\n" +
                "5 - En el menu superior, pulsa START para comenzar el juego.\n\n" +
                "6 - Listo! Ya puedes navegar por tu mazmorra.");
        welcomeLabel.setBounds(0, 40, 600, 400);
        welcomeLabel.setEditable(false);
        welcomeLabel.setVisible(true);
        pCell.removeAll();
        pCell.add(welcomeLabel);
        pCell.revalidate();
        pCell.repaint();
    }


    private static JMenuItem loadMenuItemListener(final JPanel treeViewPanel) {
        return new JMenuItem(new AbstractAction("Load") {
            // SE QUIERE CARGAR UN FICHERO
            public void actionPerformed(ActionEvent e) {
                try {
                    // METODO ENCARGADO DE PEDIR EL FICHERO XML Y ASIGNARLO AL XML CON EL QUE ESTAMOS TRABAJANDO
                    dungeonXMLLoader.loadXMLFile();
                    dungeon = dungeonXMLLoader.getDungeon();
                    // METODO ENCARGADO DE PEDIR EL FICHERO XML
                    // SI HAY UN XML CARGADO
                    if (dungeon != null) {
                        jMazmorraLog.clearLog();
                        jMazmorraLog.addLogMessage("Hola! Este es tu log, donde se registraran tus movimientos por la mazmorra!");
                        System.out.println(dungeon);

                        jMazmorraTree.newJTree();
                    }// SI EL XML CARGADO TIENE UN ROOT --> dungeon
                    // SI HAY UN XML CARGADO
                    else {
                        //ERROR --> No hay un xml seleccionado
                        showErrorMessage("No se ha seleccionado ningun fichero XML. Ve a Menu -> Load y selecciona un fichero XML.");
                        //ERROR --> No hay un xml seleccionado
                    }
                } catch (Exception ex) {
                    showErrorMessage(ex.getMessage());
                }
            }
        });
    }

    private static void showErrorMessage(String message) {
        String error = "ERROR: " + message;
        final JTextArea textArea = new JTextArea(error, 5, 30);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(true);
        JPanel panel = new JPanel(new BorderLayout(3, 3));
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null, panel);
    }

    private static void treeViewPanelLayoutSettings(JPanel treeViewPanel) {

        Color color = new Color(146, 175, 215);
        // FONDO PARA EL PANEL DEL TREEVIEW
        treeViewPanel.setBackground(color);

        // FONDO PARA EL PANEL DEL TREEVIEW
        treeViewPanel.setBounds(0, 0, 300, 1000);
    }

    private static void frameLayoutSettings(JFrame f) {

        f.setSize(800, 1000);//800 width and 1000 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setResizable(false);
    }

    private static void pCellLayoutSettings(JPanel pCell) {
        pCell.setLayout(null); //Nullify default layout to give the buttons absolute positions
        pCell.setBounds(300, 0, 500, 500);//x axis, y axis, width, height
    }


    public static JTree createTree(Element rootEl) {
        // LEEMOS EL ELEMENTO Y LO GUARDAMOS COMO EL ROOT DEL JTREE
        DefaultMutableTreeNode dungeon = new DefaultMutableTreeNode(rootEl.getTagName());
        // LEEMOS LOS CHILD ROOMS
        NodeList rooms = rootEl.getElementsByTagName("room");
        // LEEMOS LOS CHILD ROOMS
        for (int i = 0; i < rooms.getLength(); i++) {
            // POR CADA ROOM, LEEMOS SUS CHILD ELEMENTS Y LOS GUARDAMOS COMO NODOS DEL JTREE
            Node room = rooms.item(i);
            // CREAMOS EL ROOM NODE A PARTIR DEL ELEMENT ROOM Y SU ID
            DefaultMutableTreeNode roomTN = new DefaultMutableTreeNode(room.getNodeName() + " " + room.getAttributes().getNamedItem("id").getNodeValue());
            // CREAMOS EL ROOM NODE A PARTIR DEL ELEMENT ROOM Y SU ID
            for (int j = 0; j < room.getChildNodes().getLength(); j++) {
                // LEEMOS CHILD ELEMENTS DE LA ROOM
                Node doorOrDesc = room.getChildNodes().item(j);
                DefaultMutableTreeNode doorOrDescTN;
                // POR CADA CHILD ELEMENT, VEMOS SI ES UN ELEMENTO DOOR O DESCRIPTION
                if (doorOrDesc.getNodeName().equals("door")) {
                    // SI ES UN ELEMENTO DOOR, LO GUARDAMOS COMO UN NODO DEL JTREE CON SU NAME (ORIENTACION) Y EL ID DE LA ROOM DESTINO
                    doorOrDescTN = new DefaultMutableTreeNode(doorOrDesc.getAttributes().getNamedItem("name").getNodeValue() + " -> " + doorOrDesc.getAttributes().getNamedItem("dest").getNodeValue());
                    // SI ES UN ELEMENTO DOOR, LO GUARDAMOS COMO UN NODO DEL JTREE CON SU NAME (ORIENTACION) Y EL ID DE LA ROOM DESTINO
                    roomTN.add(doorOrDescTN); // <-- AÑADIMOS EL NODE A LA LISTA DE CHILDREN DEL ROOM NODE
                } else if (doorOrDesc.getNodeName().equals("description")) {
                    doorOrDescTN = new DefaultMutableTreeNode(doorOrDesc.getTextContent()); //<-- SI ES UN ELEMENTO DESCRIPTION, LO GUARDAMOS COMO UN NODO DEL JTREE CON SU CONTENIDO
                    roomTN.insert(doorOrDescTN, 0);
                    roomTN.add(doorOrDescTN); // <-- AÑADIMOS EL NODE A LA LISTA DE CHILDREN DEL ROOM NODE EN LA PRIMERA POSICION
                }
                // POR CADA CHILD ELEMENT, VEMOS SI ES UN ELEMENTO DOOR O DESCRIPTION
                // LEEMOS CHILD ELEMENTS DE LA ROOM
            }
            dungeon.add(roomTN); // <-- AÑADIMOS EL ROOM NODE A LA LISTA DE CHILDREN DEL DUNGEON NODE
            // POR CADA ROOM, LEEMOS SUS CHILD ELEMENTS Y LOS GUARDAMOS COMO NODOS DEL JTREE
        }
        //ESTO SIRVE PARA QUE NO SAQUE LOS NODES CON BACKGROUND
        TreeCellRenderer r = new DefaultTreeCellRenderer() {
            {
                updateUI();
                this.setBackgroundSelectionColor(null);
                this.setBorderSelectionColor(null);
                this.setBackgroundNonSelectionColor(null);
            }

            @Override
            public void updateUI() {
                Object old = UIManager.get("Tree.rendererFillBackground");
                try {
                    UIManager.put("Tree.rendererFillBackground", false);
                    super.updateUI();
                } finally {
                    UIManager.put("Tree.rendererFillBackground", old);
                }
            }
        };


        JTree j = new JTree(dungeon);
        j.setCellRenderer(r);


        // LE PONEMOS UN BACKGROUND AL JTREE CREADO
        Color color = new Color(146, 175, 215);
        j.setBackground(color);

        return j;
    }

    public static Font fontPlusTen(Font currentFont) {
        // AUMENTAMOS EL TAMAÑO DE LA FUENTE EN 10
        return new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 10);
        // AUMENTAMOS EL TAMAÑO DE LA FUENTE EN 10
    }

    public static Font fontPlusFour(Font currentFont) {
        // AUMENTAMOS EL TAMAÑO DE LA FUENTE EN 10
        return new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 4);
        // AUMENTAMOS EL TAMAÑO DE LA FUENTE EN 10
    }

    //JGRADIENT BUTTON (SPECIAL TO MAKE COLORFUL BUTTONS) I GOTTA MAKE THIS MY OWN
    //AND SIMPLIFY IT

    public static class JGradientButton extends JButton {
        Color color1 = new Color(90, 118, 132);
        Color color2 = new Color(197, 209, 235);

        JGradientButton() {
            super("Gradient Button");
            setContentAreaFilled(false);
            setFocusPainted(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(new GradientPaint(
                    new Point(0, 0),
                    color1,
                    new Point(0, getHeight()),
                    color2));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();

            super.paintComponent(g);
        }
    }

}
	