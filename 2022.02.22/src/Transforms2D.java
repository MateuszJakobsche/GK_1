import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;
import java.io.IOException;

public class Transforms2D extends JPanel {

private class Display extends JPanel {
	
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	g2.translate(300,300);  // Moves (0,0) to the center of the display.
	int whichTransform = transformSelect.getSelectedIndex();
	
	switch(whichTransform)
	{
    	case 0: break;
    	case 1:
    		g2.scale(0.5,  0.5);
    		break;

    	case 2: 
    		g2.rotate(3*Math.PI/4);
    		break;

    	case 3:
    		g2.rotate(Math.PI);
    		g2.scale(0.75, 1.25);
    		break;

    	case 4:
    		g2.shear(0.25, 0);
    		break;

    	case 5:
    		g2.translate(0, -230);
    		g2.scale(1, 0.5);
    		break;

    	case 6:
    		g2.rotate(Math.PI/2);
    		g2.shear(0.25, 0);
    		break;

    	case 7:
    		g2.rotate(Math.PI);
    		g2.scale(0.75, 1.25);
    		break;
        

    	case 8:
    		g2.translate(-50, 100);
    		g2.rotate(1*Math.PI/8);
    		g2.scale(1, 0.5);
        	break;

    	case 9:
    		g2.translate(150, 0);
    		g2.rotate(Math.PI);
    		g2.shear(0, 0.25);
			break;
		}

	
	int[] x = new int[8];
    int[] y = new int[8];
    
    double theta = 2*Math.PI/8;
    
    for(int i = 0; i < 8; i++)
    {
        x[i] = (int) (150*Math.cos((theta)*i));
        y[i] = (int) (150*Math.sin((theta)*i));
    }
    
	Polygon osmiokat = new Polygon(x, y, 8);
	g2.rotate(7*Math.PI/8);
	g2.fillPolygon(osmiokat);
	}
}

	private Display display;

	private JComboBox<String> transformSelect;

	public Transforms2D() throws IOException {

	display = new Display();
	display.setBackground(Color.RED);
	display.setPreferredSize(new Dimension(600,600));
	
	transformSelect = new JComboBox<String>();
	transformSelect.addItem("None");
	for (int i = 1; i <= 9; i++) {
		transformSelect.addItem("No. " + i);
	}
	transformSelect.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			display.repaint();
		}
	});
	
	setLayout(new BorderLayout(3,3));
	setBackground(Color.BLUE);
	setBorder(BorderFactory.createLineBorder(Color.BLUE,10));
	
	JPanel top = new JPanel();
	
	top.setLayout(new FlowLayout(FlowLayout.CENTER));
	top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
	top.add(new JLabel("Transform: "));
	top.add(transformSelect);
	
	add(display,BorderLayout.CENTER);
	add(top,BorderLayout.NORTH);
	}

	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new Transforms2D());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}
}