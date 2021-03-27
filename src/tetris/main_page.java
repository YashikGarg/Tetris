package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

class controller extends JPanel implements KeyListener
{
	int x=300,i=0;
	JFrame f;
	JPanel panel;
	boolean pannel_visible=false;
	
	public controller()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		setBounds(-10, -10, 12*26+10, 26*23+25);
		setOpaque(false);
		 
	}
	
	public void set_frame(JFrame frame,JPanel panely)
	{
		f=frame;panel=panely;
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("sample.gif")));
		lblNewLabel.setBounds(0, 0, 300, 600);
		f.getContentPane().add(lblNewLabel);
		
		JLabel title = new JLabel("Instruction");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Vivaldi", Font.BOLD, 64));
		title.setBounds(10, 41, 264, 87);
		panel.add(title);
		
		JLabel i1 = new JLabel("<html>\u2191 &nbsp;&nbsp;&nbsp;To Rotate Piece</html>");
		i1.setForeground(Color.WHITE);
		i1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		i1.setBounds(10, 134, 254, 87);
		panel.add(i1);
		
		JLabel lblToMove = new JLabel("<html>\u2193&nbsp;&nbsp;&nbsp; To Move Down Piece</html>");
		lblToMove.setForeground(Color.WHITE);
		lblToMove.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblToMove.setBounds(10, 193, 264, 100);
		panel.add(lblToMove);
		
		JLabel lblToMove_1 = new JLabel("<html>\u2190  &nbsp;To Move Left Piece</html>");
		lblToMove_1.setForeground(Color.WHITE);
		lblToMove_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblToMove_1.setBounds(10, 269, 254, 100);
		panel.add(lblToMove_1);
		
		JLabel lblToMove_2 = new JLabel("<html>\u2192  &nbsp;To Move Right Piece</html>");
		lblToMove_2.setForeground(Color.WHITE);
		lblToMove_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblToMove_2.setBounds(10, 343, 264, 100);
		panel.add(lblToMove_2);
		
		JLabel lblToMove_2_1 = new JLabel("<html><u>SPACE</u> To Drop</html>");
		lblToMove_2_1.setForeground(Color.WHITE);
		lblToMove_2_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblToMove_2_1.setBounds(10, 486, 222, 70);
		panel.add(lblToMove_2_1);
		
		JLabel lblToMove_2_1_1 = new JLabel("<html>P &nbsp;&nbsp;To Pause</html>");
		lblToMove_2_1_1.setForeground(Color.WHITE);
		lblToMove_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblToMove_2_1_1.setBounds(10, 424, 181, 70);
		panel.add(lblToMove_2_1_1);
		
		JButton button = new JButton("X");
		button.setForeground(Color.WHITE);
		button.setHorizontalAlignment(SwingConstants.RIGHT);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(241, 0, 43, 40);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		button.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
		            panel.setVisible(false);
		            pannel_visible=!pannel_visible;
		         }
		      });
	    
		
		panel.add(button);
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
	
	public void show_pannel()
	{
		panel.setVisible(!pannel_visible);
		pannel_visible=!pannel_visible;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code == KeyEvent.VK_DOWN && !pannel_visible)
		{
			i=(i+1)%3;x=(70*i)+300;
		}
		if(code == KeyEvent.VK_UP && !pannel_visible)
		{
			i=(i-1)%3;
			if(i<0)
				i=2;
			x=(70*i)+300;
		}
		
		if(code == KeyEvent.VK_ENTER && !pannel_visible)
		{
			if(i==0)
			{Tetris s=new Tetris();
			s.framy();
			f.dispose();
			}
			if(i==1)
			show_pannel();
			if(i==2)
			System.exit(0);	
		}
		
		if(code == KeyEvent.VK_ESCAPE)
			show_pannel();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
}

public class main_page {
	
	public static void open_frame()
	{
		JFrame f = new JFrame("Tetris");
		controller s=new controller();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 300, 600);
		f.getContentPane().add(panel);
		panel.setBackground(new Color(0, 0, 0, 190));
		panel.setLayout(null);

		panel.setVisible(false);
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(470, 65, 12*26-12, 26*23+8);
		f.add(s);
		s.set_frame(f,panel);
		
		
		f.getContentPane().setLayout(null);
		f.setVisible(true);
	}

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
		
	}

}
