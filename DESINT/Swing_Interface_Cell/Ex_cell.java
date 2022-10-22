import java.awt.*; 
import javax.swing.*;  


public class Ex_cell {  
	public static void main(String[] args) {  
	
	
	//TODO: 3 panes 1 con texto en el medio
	
	JFrame f=new JFrame();//creating instance of JFrame  
	JPanel pCell=new JPanel();

          
	JButton bTop=new JButton("click");//creating instance of JButton  
	JButton bLeft=new JButton("click");
	JButton bRight=new JButton("click");
	JButton bBot=new JButton("click");
	
	//bTop.setBounds(0,0,500, 40);//x axis, y axis, width, height  
	//bLeft.setBounds(0,40,125, 410);//x axis, y axis, width, height  
	//bRight.setBounds(475,40,125, 410);//x axis, y axis, width, height  
	//bBot.setBounds(300,450,500, 40);//x axis, y axis, width, height  
	
	pCell.setBounds(300,0,500,500);//x axis, y axis, width, height  
	
	pCell.add(bTop);//adding button in JPanel
	pCell.add(bLeft);
	pCell.add(bRight);
	pCell.add(bBot);
	
	pCell.setLayout(new GridLayout(3, 4));//3 rows 4 columns grid for panel
	
	f.add(pCell);//adding JPanel to JFrame
          
	f.setSize(800,1000);//800 width and 1000 height  
	f.setLayout(null);//using no layout managers  
	f.setVisible(true);//making the frame visible 
	f.setResizable(false);
	}  
}  