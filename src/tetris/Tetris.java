package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

 class try_this extends JPanel implements ActionListener,KeyListener{
	 int x=26,y=26,vx=26,vy=26;
	 boolean is_paused=false;
	 Timer t=new Timer(800,this);
	 int[][] arr = { { 1,1 },{ 1,1 } };
	 Integer prev_color,prev_shape;
	 Color random_color;
	 Color[] color_array= {Color.decode("#FFAEBC"),Color.decode("#A0E7E5"),Color.decode("#B4F8C8"),Color.decode("#FBE7C6"),Color.decode("#0E86D4"),Color.decode("#F56B39"),Color.decode("#E21B32")};
	 int score=0;
	 boolean game_over=false;
	 int arrow=0;
	 
	 int[][][] shape= {
		
			 //square shape
			 { { 1,1 },{ 1,1 } },
			 
			 //t shape
			 { { 0,1,0 },{ 1,1,1 } },
			 
			 //L shape
			 { { 1,0 },{ 1,0 },{1,1} },
			 
			 //_| shape
			 { { 0,1 },{0,1},{ 1,1 } },
			 
			 // ---- shape
			 { { 1,1,1,1 } },
			 
			 //Z shape
			 { { 1,1,0 },{ 0,1,1 } },
			 
			 //_/- shape
			 { { 0,1,1 },{ 1,1,0 } }
	 };
	 
	 Color[][] main_table;
	 
	 public try_this()
	 {
		 addKeyListener(this);
		 setFocusable(true);
		 setFocusTraversalKeysEnabled(true);
		 setBackground(Color.black);
		 setBounds(-10, -10, 12*26+10, 26*23+25);
		 init();
	 }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i=0;i<12;i++)
		for(int j=0;j<23;j++)
		{
			g.setColor(main_table[i][j]);
			if(main_table[i][j]==Color.black)
				g.fillRect(i*26, j*26,26, 26);
			else
			g.fillRect(i*26, j*26,25, 25);
		}

		
		g.setColor(random_color);
		for(int i=0;i<arr.length;i++)
		for(int j=0;j<arr[0].length;j++)
		if(arr[i][j]!=0)	
	    g.fillRect(x+(j*26), y+(i*26), 25, 25);
//		System.out.println(color_array.length);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 17));
		g.drawString("Score - " + score, 190, 43);
		
		if(is_paused)
		{
			g.setFont(new Font("TimesRoman", Font.BOLD, 40));
			g.drawString("Pause", 105, 295);
		}
		
		if(game_over)
		{
			g.setFont(new Font("TimesRoman", Font.BOLD, 40));
			g.drawString("Game Over", 55, 265);
			g.setFont(new Font("TimesRoman", Font.BOLD, 25));
			g.drawString("(Want To Continue ?)", 40, 310);
			g.setFont(new Font("TimesRoman", Font.BOLD, 27));
			g.drawString("Yes", 130, 360);
			g.drawString(">        <", 108, 360+(arrow*28));
			g.drawString("No", 134, 388);
			
		}
		
		if(!is_paused && !game_over)
	    t.start();
	}
	public void actionPerformed(ActionEvent e){ 
//		x+=vx;
		down();
		repaint();
          
}
	
	void init()
	{
		score=0;
		main_table=new Color[12][26];
		for(int i=0;i<12;i++)
			 for(int j=0;j<23;j++)
			 {	if(i==0 || j==0 || j==22 ||i==11)
				 main_table[i][j]=Color.black;
				else
				 main_table[i][j]=Color.gray;
			 }
			 
			 
			 Random rand = new Random();
			 int i=rand.nextInt(7);
			 prev_shape=i;
			 arr=shape[i];
			 i=rand.nextInt(7);
			 prev_color=i;
			 random_color=color_array[i];
	}
	
	void right()
	{	x+=vx;
		if(!check_strike(arr))
		repaint();
		else
		x-=vx;	
			
	}
	void down()
	{
		y+=vx;
		if(check_strike(arr))
		{
			t.stop();
			if((y/26-arr.length)<=1)
			game_over=true;
			
			mark_down();
			clear_row();
			if(!game_over)
			draw_new_piece();
		}
		repaint();
	}
	
	void left()
	{
		x-=vx;
		if(!check_strike(arr))
		repaint();
		else
		x+=vx;	
	}
	
	void mark_down()
	{
		    int org_x=x/26;
			int org_y=y/26;	
			y-=vy;
			org_y--;
			for(int j=0;j<arr[0].length;j++)
			for(int i=0,k=0;i<arr.length;i++,k++)
			if(arr[i][j]!=0)
				main_table[org_x+j][org_y+k]=random_color;
			
//			for(int j=0;j<23;j++)
//			{for(int i=0;i<12;i++)
//			{
//			if(main_table[i][j] == Color.black)	
//			System.out.print("b ");
//			if(main_table[i][j] == Color.yellow)	
//				System.out.print("y ");
//			if(main_table[i][j] == Color.gray)	
//				System.out.print("g ");
//			}
//			System.out.println(j);
//			}	
//			
//			main_table[11][1]=Color.blue;
			
//			main_table[org_x][org_y]=Color.blue;

				
		
	}
	
	void draw_new_piece()
	{
		x=26;y=26;
		Random rand = new Random();
		int i=prev_shape;
		while(i==prev_shape)
			i=rand.nextInt(7);
		prev_shape=i;
		arr=shape[i];
		i=prev_color;
		while(i==prev_color)
		i=rand.nextInt(7);
		prev_color=i;
		random_color=color_array[i];
	}
	
	void clear_row()
	{
		int org_y=y/26;
		int count=0;
		for(int j=org_y;j<org_y+arr.length;j++)
		{
			boolean flag=true;
			for(int i=0;i<12;i++)
			if(main_table[i][j]==Color.gray)
				{flag=false;break;}
			if(flag)
				{count+=1;remove_row(j);}
		}
		if(count == 1)
			score+=30;
		else if(count == 2)
			score+=40;
		else if(count>=3 && count<=4)
			score+=70;
		else if(count >=5)
			score+=100;
		
	}
	
	void remove_row(int row)
	{
		for(int j=row-1;j>0;j--)
			for(int i=1;i<=10;i++)
				main_table[i][j+1]=main_table[i][j];
	}
	
	boolean check_strike(int[][] arr)
	{
		int org_x=x/26;
		int org_y=y/26;	

		for(int i=0,k=0;i<arr.length;i++,k++)
		for(int j=0;j<arr[0].length;j++)	
		if(arr[i][j]!=0 && main_table[org_x+j][org_y+k]!=Color.gray)
		return true;	
		
		return false;
	}
	
	void rotate()
	{
		int h=arr[0].length;
	      int w=arr.length;
	      int [][] mirror = new int[h][w];
	           for(int i = 0 ; i < h; i++){
	               for(int j = 0 ; j < w; j++){
	                   mirror[i][j] = arr[j][h-i-1];
	               }
	           }
	           
	         if(!check_strike(mirror))  
	         {arr = mirror;
	         repaint();}
	}
	
	void dropdown()
	{
		t.stop();
		for(int i=y/26;i<23 && !check_strike(arr);i++,y+=26);
		mark_down();
		clear_row();
		draw_new_piece();
		repaint();
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code=e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT && !is_paused && !game_over)
			right();
		if(code == KeyEvent.VK_DOWN && !is_paused && !game_over) 
			{score+=1;down();}
		if(code == KeyEvent.VK_UP && !is_paused&& !game_over)
			rotate();
		if(code == KeyEvent.VK_LEFT && !is_paused && !game_over)
			left();
		if(code == KeyEvent.VK_SPACE && !is_paused && !game_over)
			{score+=20;dropdown();}
		if(code == KeyEvent.VK_P && !game_over)
		{
			if(is_paused)
				t.start();
			else
				t.stop();
			repaint();
			is_paused=!is_paused;
		}
		if(code == KeyEvent.VK_DOWN && game_over)
			{arrow=(arrow+1)%2;repaint();}
		if(code == KeyEvent.VK_UP && game_over)
			{arrow=(arrow-1);if(arrow<0)arrow=1;repaint();}
		if(code == KeyEvent.VK_ENTER && game_over)
		{
			if(arrow==1)
				System.exit(0);
				init();
				game_over=false;
				repaint();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	  
}

public class Tetris {
	
	public static void framy()
	{
		try_this s= new try_this();
		JFrame f = new JFrame("Tetris");
		f.add(s);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(470, 65, 12*26-12, 26*23+8);
		f.getContentPane().setLayout(null);f.setVisible(true);
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
		
		main_page s=new main_page();
		s.open_frame();
	}

}
