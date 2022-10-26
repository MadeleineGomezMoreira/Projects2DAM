import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.*; // ActionListener
import java.io.File;
import javax.swing.filechooser.*;
import javax.xml.xpath.*;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Ejercicio7 {

    //DEFINICION VARIABLES GLOBALES
    public static Document xmlDocument; //El documento sobre el cual estamos trabajando
    public static final JFrame f = new JFrame(); //Ventana principal

    public static final JTextArea logTextArea = new JTextArea(); //Area de texto para mostrar el log

    static final JPanel pLog = new JPanel(); //Panel para el log
    static final JPanel pCell = new JPanel(); //Panel para los botones

    public static void main(String[] args) {
        final JPanel treeViewPanel = new JPanel(); //Panel para el arbol
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

        // CREAMOS AREA DE TEXTO PARA EL LOG Y LO AÑADIMOS A DONDE ESTARA EL LOG
        getLogMessage();
        // CREAMOS AREA DE TEXTO PARA EL LOG Y LO AÑADIMOS A DONDE ESTARA EL LOG

        // AÑADIMOS LA CONFIGURACION VISUAL A LOS PANELES
        treeViewPanelLayoutSettings(treeViewPanel);
        pCellLayoutSettings(pCell);
        pLogLayoutSettings(pLog);
        frameLayoutSettings(f);
        // AÑADIMOS LA CONFIGURACION VISUAL A LOS PANELES

        // AÑADIMOS LOS PANELES A LA VENTANA
        f.add(treeViewPanel);
        f.add(pCell);
        f.add(pLog);
        // AÑADIMOS LOS PANELES A LA VENTANA
    }


    private static JMenuItem startMenuItemListener(JPanel pCell) {
        return new JMenuItem(new AbstractAction("Start") {
            // EL USUARIO QUIERE COMENZAR A JUGAR
            public void actionPerformed(ActionEvent e) {
                // SI HAY UN XML CARGADO, SE PUEDE JUGAR
                if (xmlDocument != null) {
                    int contPasos = 1; // CONTADOR DE PASOS PARA EL LOG
                    Node firstRoom = xmlDocument.getElementsByTagName("room").item(0); //COGEMOS LA PRIMERA ROOM
                    logTextArea.append("Comienza la aventura!\n\n");
                    logTextArea.append(contPasos + " - Has comenzado en la habitación " + firstRoom.getAttributes().getNamedItem("id").getNodeValue() + "\n");
                    // FUNCIONALIDAD PRINCIPAL
                    createpCellButtons(firstRoom, pCell, contPasos);
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
                    logTextArea.append("Descripción de la habitación: " + roomChild.getTextContent() + "\n\n");
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
                            showErrorMessage("Error en la ORIENTACIOn de la puerta de la habitación " + room.getAttributes().getNamedItem("id").getNodeValue() + "\n" +
                                    "La ORIENTACIOn de la puerta debe ser norte, sur, este u oeste \n" + "Una vez corregido, vuelve a cargar el archivo y a iniciar el juego.");
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
                            logTextArea.append(finalContPasos + " - Te has movido hacia el " + orientacionElegida + " y has entrado a la habitación " + button.getText() + "\n");
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
	/* NOT WORKING RN
	private static void colourButtons(){
		//Create UIManager object
		UIManager manager=new UIManager();
		//IMPORTANTE: "Button.gradient"
		//COLOR BASE: RGB
		
		//Create linked list that will store all gradient information
		LinkedList<Object> a=new LinkedList<Object>();
		
		//First colour
		a.add(new ColorUIResource(237, 174, 73));
		//Second colour 
		a.add(new ColorUIResource(209, 73, 9));
		//Third colour 
		a.add(new ColorUIResource(237, 174, 73));
		
		//Set Button.gradient key with new value
		manager.put("Button.gradient",a);
		
	}
	*/

    private static void getWelcomeMessage() {
        JTextArea welcomeLabel = new JTextArea("Bienvenido a la mazmorra!!\n Para poder disfrutar de este juego único, sigue los siguientes pasos:\n\n" +
                "1 - En el menú superior, pulsa LOAD.\n\n" +
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
                "3 - Haz doble click sobre él o pulsa \"Abrir\"\n\n" +
                "4 - Una vez cargado el fichero, verás a la izquierda tu mazmorra con sus habitaciones.\n\n" +
                "5 - En el menú superior, pulsa START para comenzar el juego.\n\n" +
                "6 - Listo! Ya puedes navegar por tu mazmorra.");
        welcomeLabel.setBounds(0, 40, 600, 400);
        welcomeLabel.setEditable(false);
        welcomeLabel.setVisible(true);
        pCell.removeAll();
        pCell.add(welcomeLabel);
        pCell.revalidate();
        pCell.repaint();
    }

    private static void getLogMessage() {
        logTextArea.setText("Hola! Este es tu log, donde se registrarán tus movimientos por la mazmorra!\n");
        logTextArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(logTextArea);
        scroller.setBounds(0, 300, 485, 435);
        pLog.removeAll();
        pLog.add(scroller);
    }


    private static JMenuItem loadMenuItemListener(final JPanel treeViewPanel) {
        return new JMenuItem(new AbstractAction("Load") {
            // SE QUIERE CARGAR UN FICHERO
            public void actionPerformed(ActionEvent e) {
                try {
                    // METODO ENCARGADO DE PEDIR EL FICHERO XML Y ASIGNARLO AL XML CON EL QUE ESTAMOS TRABAJANDO
                    xmlDocument = loadXMLFile();
                    // METODO ENCARGADO DE PEDIR EL FICHERO XML
                    // SI HAY UN XML CARGADO
                    if (xmlDocument != null) {
                        // SI EL XML CARGADO TIENE UN ROOT --> dungeon
                        if (xmlDocument.getFirstChild().getNodeName().equals("dungeon")) {
                            // A PARTIR DEL ROOT, CREAMOS EL ARBOL
                            JTree newJt = createTree(xmlDocument.getDocumentElement());
                            // A PARTIR DEL ROOT, CREAMOS EL ARBOL
                            // AUMENTAMOS LA FUENTE DEL ARBOL
                            newJt.setFont(fontPlusTen(newJt.getFont()));
                            // AUMENTAMOS LA FUENTE DEL ARBOL
                            // AÑADIMOS EL ARBOL AL PANEL
                            treeViewPanel.removeAll();
                            // AÑADIMOS EL ARBOL AL SCROLLER PANEL PARA QUE SE PUEDA SCROLLEAR
                            JScrollPane scroller = new JScrollPane(newJt);
                            newJt.setBounds(0, 0, 300, 1000);
                            newJt.expandPath(new TreePath(newJt.getModel().getRoot()));
                            scroller.setLayout(new ScrollPaneLayout());
                            scroller.setPreferredSize(new Dimension(300, 935));
                            // AÑADIMOS EL ARBOL AL SCROLLER PANEL PARA QUE SE PUEDA SCROLLEAR
                            treeViewPanel.add(scroller); // <-- AÑADIMOS EL SCROLLERPANEL AL PANEL DEL ARBOL
                            scroller.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                            // AÑADIMOS EL ARBOL AL PANEL
                            // REPINTAMOS EL PANEL
                            treeViewPanel.revalidate();
                            treeViewPanel.repaint();
                            // REPINTAMOS EL PANEL
                        }// SI EL XML CARGADO TIENE UN ROOT --> dungeon
                        else {
                            //ERROR XML INVALIDO --> EL ROOT NO ES dungeon
                            showErrorMessage("El root element del documento debe ser <dungeon>!");
                            //ERROR XML INVALIDO --> EL ROOT NO ES dungeon
                        }
                    }// SI HAY UN XML CARGADO
                    else {
                        //ERROR --> No hay un xml seleccionado
                        showErrorMessage("No se ha seleccionado ningún fichero XML. Ve a Menú -> Load y selecciona un fichero XML.");
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

    private static Document loadXMLFile() {
        try {
            // SE INTENTA PEDIR UN FICHERO AL USUARIO
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            // ACEPTAMOS SOLO FICHEROS XML
            j.setAcceptAllFileFilterUsed(false);
            j.addChoosableFileFilter(new FileNameExtensionFilter("Only XML", "xml"));
            // ACEPTAMOS SOLO FICHEROS XML
            int r = j.showOpenDialog(null); // <-- ABRIMOS EL DIALOGO PARA QUE EL USUARIO SELECCIONE EL FICHERO

            // SI EL USUARIO HA SELECCIONADO UN FICHERO
            if (r == JFileChooser.APPROVE_OPTION) {
                // SE OBTIENE EL FICHERO SELECCIONADO
                File xmlFile = new File(j.getSelectedFile().getAbsolutePath());
                // SE CREA UN DOCUMENTO XML A PARTIR DEL FICHERO SELECCIONADO
                return readXMLFile(xmlFile);
            }
            // SI EL USUARIO HA SELECCIONADO UN FICHERO
            // SE INTENTA PEDIR UN FICHERO AL USUARIO
        } catch (Exception e) {
            // SI HAY ERROR AL PEDIR EL FICHERO AL USUARIO
            showErrorMessage("ERROR AL CARGAR EL XML SELECCIONADO --> " + e.getMessage());
            // SI HAY ERROR AL PEDIR EL FICHERO AL USUARIO
        }
        // SI HUBO ERROR O NO SE SELECCIONO NINGUN FICHERO, SE DEVUELVE EL FICHERO ACTUAL
        return xmlDocument;
        // SI HUBO ERROR O NO SE SELECCIONO NINGUN FICHERO, SE DEVUELVE EL FICHERO ACTUAL
    }

    private static Document readXMLFile(File xmlSelected) {
        try {
            // SE CREA UN DOCUMENTO XML A PARTIR DEL FICHERO SELECCIONADO
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            getWelcomeMessage(); // REINICIAMOS MENSAJE DE BIENVENIDA
            getLogMessage(); // REINICIAMOS LOG
            return dBuilder.parse(xmlSelected);
            // SE CREA UN DOCUMENTO XML A PARTIR DEL FICHERO SELECCIONADO
        } catch (Exception e) {
            showErrorMessage("ERROR AL CARGAR EL FICHERO SELECCIONADO COMO UN FICHERO XML --> " + e.getMessage());
        }
        // SI HAY ALGUN ERROR, SE DEVUELVE EL DOCUMENTO CON EL QUE SE CONTABA HASTA AHORA
        return xmlDocument;
        // SI HAY ALGUN ERROR, SE DEVUELVE EL DOCUMENTO CON EL QUE SE CONTABA HASTA AHORA
    }

    private static void treeViewPanelLayoutSettings(JPanel treeViewPanel) {
        // FONDO PARA EL PANEL DEL TREEVIEW
        treeViewPanel.setBackground(Color.darkGray);
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

    private static void pLogLayoutSettings(JPanel pLog) {
        pLog.setLayout(null); //Nullify default layout to give the buttons absolute positions
        pLog.setBounds(300, 200, 500, 750);//x axis, y axis, width, height
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
                }
                else if (doorOrDesc.getNodeName().equals("description")) {
                    doorOrDescTN = new DefaultMutableTreeNode(doorOrDesc.getTextContent()); //<-- SI ES UN ELEMENTO DESCRIPTION, LO GUARDAMOS COMO UN NODO DEL JTREE CON SU CONTENIDO
                    roomTN.insert(doorOrDescTN, 0); roomTN.add(doorOrDescTN); // <-- AÑADIMOS EL NODE A LA LISTA DE CHILDREN DEL ROOM NODE EN LA PRIMERA POSICION
                }
                // POR CADA CHILD ELEMENT, VEMOS SI ES UN ELEMENTO DOOR O DESCRIPTION
                // LEEMOS CHILD ELEMENTS DE LA ROOM
            }
            dungeon.add(roomTN); // <-- AÑADIMOS EL ROOM NODE A LA LISTA DE CHILDREN DEL DUNGEON NODE
            // POR CADA ROOM, LEEMOS SUS CHILD ELEMENTS Y LOS GUARDAMOS COMO NODOS DEL JTREE
        }
        JTree j = new JTree(dungeon);
        // LE PONEMOS UN BACKGROUND AL JTREE CREADO
        j.setBackground(Color.darkGray);
        // LE PONEMOS UN BACKGROUND AL JTREE CREADO
        return j;
    }

    public static Font fontPlusTen(Font currentFont) {
        // AUMENTAMOS EL TAMAÑO DE LA FUENTE EN 10
        return new Font(currentFont.getName(), currentFont.getStyle(), currentFont.getSize() + 10);
        // AUMENTAMOS EL TAMAÑO DE LA FUENTE EN 10
    }
	
	//JGRADIENT BUTTON (SPECIAL TO MAKE COLORFUL BUTTONS) I GOTTA MAKE THIS MY OWN
	//AND SIMPLIFY IT
	
	public static class JGradientButton extends JButton{
        Color color1 = Color.GREEN;
        Color color2 = Color.PINK;
        JGradientButton(){
            super("Gradient Button");
            setContentAreaFilled(false);
            setFocusPainted(true); // used for demonstration
            /*addActionListener(new ActionListener() {
				
                @Override
                public void actionPerformed(ActionEvent evt) {
                    color2 = (color2==Color.PINK) ? Color.GREEN : Color.PINK;
                    //System.out.println(button.isSelected());
                }
            });
			*/
        }

        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D)g.create();
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

