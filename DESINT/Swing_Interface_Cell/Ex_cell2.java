import java.awt.*; 
import javax.swing.*;  


public class Ex_cell2 {  
	public static void main(String[] args) {  
	
	
	JFrame f=new JFrame();//creating instance of JFrame  
	JPanel pCell=new JPanel();
	JLabel lbCell=new JLabel("In this room there are things...");
	
	pCell.setLayout(null); //Nullify default layout to give the buttons absolute positions

          
	JButton bTop=new JButton("North");//creating instance of JButton  
	JButton bLeft=new JButton("West");
	JButton bRight=new JButton("East");
	JButton bBot=new JButton("South");
	
	bTop.setBounds(0,0,500, 40);//x axis, y axis, width, height  
	bLeft.setBounds(0,40,125, 410);//x axis, y axis, width, height  
	bRight.setBounds(360,40,125, 410);//x axis, y axis, width, height  
	bBot.setBounds(0,450,500, 40);//x axis, y axis, width, height  
	lbCell.setBounds(125,40,250,410);
	
	lbCell.setHorizontalAlignment( SwingConstants.CENTER );

	
	pCell.setBounds(300,0,500,500);//x axis, y axis, width, height  
	
	pCell.add(bTop);//adding button in JPanel
	pCell.add(bLeft);
	pCell.add(bRight);
	pCell.add(bBot);
	pCell.add(lbCell);
	
	f.add(pCell);//adding JPanel to JFrame
          
	f.setSize(800,1000);//800 width and 1000 height  
	f.setLayout(null);//using no layout managers  
	f.setVisible(true);//making the frame visible 
	f.setResizable(false);
	}  
}  