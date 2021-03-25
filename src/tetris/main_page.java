package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

class controller extends JPanel implements KeyListener
{
	int x=300,i=0;
	JFrame f;

	public controller()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		setBounds(-10, -10, 12*26+10, 26*23+25);
		setOpaque(false);
		 
	}
	
	public void set_frame(JFrame frame)
	{
		f=frame;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.setFont(new Font("vivaldi", Font.BOLD, 70));
		g.drawString("Tetris",  65, 200);
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.drawString("Play",  110, 300);
		g.drawString(">",  70, x);
		g.drawString("<",  197, x+1);
		g.drawString("Help",  106, 370);
		g.drawString("Exit",  109, 440);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code == KeyEvent.VK_DOWN)
		{
			i=(i+1)%3;x=(70*i)+300;
		}
		if(code == KeyEvent.VK_UP)
		{
			i=(i-1)%3;
			if(i<0)
				i=2;
			x=(70*i)+300;
		}
		
		if(code == KeyEvent.VK_ENTER)
		{
			if(i==0)
			{Tetris s=new Tetris();
			s.framy();
			f.dispose();
			}
			if(i==2)
			System.exit(0);	
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
}

public class main_page {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
            // start application
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
		JFrame f = new JFrame("Tetris");
		controller s=new controller();
		s.set_frame(f);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 12, 12*26-12, 26*23+8);
		f.add(s);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\new\\gify\\sample.gif"));
		lblNewLabel.setBounds(0, 0, 300, 600);
		f.getContentPane().add(lblNewLabel);
		
		f.getContentPane().setLayout(null);
		f.setVisible(true);

	}

}
