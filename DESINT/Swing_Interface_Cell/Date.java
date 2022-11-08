import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;


public class Date {

    //VARIABLE DEFINITION

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int screenHeight = screenSize.height;
    public static int screenWidth = screenSize.width;

    public static void main(String[] args) {

        //MAIN FRAME
        JFrame dateFrame = new JFrame();

        //DATE PANEL
        JPanel datePanel = new JPanel();

        //CHECK BUTTON
        JButton checkBtn = new JButton();

        //TEXTFIELDS
        JTextField txtDay = new JTextField();
        JTextField txtMonth = new JTextField();
        JTextField txtYear = new JTextField();
		
		//ELEMENT HIERARCHY
		datePanel.add(txtDay);
		datePanel.add(txtMonth);
		datePanel.add(txtYear);
		dateFrame.add(datePanel);

        //LEAVE FOR THE END
		panelLayoutSettings(datePanel,dateFrame);
        frameLayoutSettings(dateFrame);
    }

    private static void frameLayoutSettings(JFrame f) {

        //f.setSize(screenWidth / 2, screenHeight / 2);//width,height
		f.setSize(500, 500);//width,height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setResizable(false);//screen cannot be resized
    }

    private static void panelLayoutSettings(JPanel p, JFrame f) {


        //p.setBounds((frameWidth * 0.2),(frameHeight * 0.2),(frameWidth * 0.8),(frameWidth * 0.5));//x axis, y axis, width, height
		p.setBounds(40,50,400,200);//x axis, y axis, width, height
		p.setLayout(null);//using no layout managers
		p.setBackground(Color.green);//give a colored bg
    }
	
	private static void buttonLayoutSettings(JButton b){
	
		b.setBounds();
		b.setBackground(Color.blue);
	}
	
    //BOTON CHECK RETURNS LOCALDATE (GetFecha)
	
	/*
		Fecha() - constructor vacío
			
		Fecha (dia, mes , anio) - constructor con atributos
		
		void setFecha (dia, mes anio)
		
		LocalDate getFecha ()
		
		
		COMPONENTE - FECHA (getFecha / getFechaVacío)
		EXCEPCION - FECHAEXCEPTION
		
		FECHA DEBERÍA TENER UN JPANEL CON LOS 3 CAMPOS Y LA FUNCIONALIDAD
		
	*/
}