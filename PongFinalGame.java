/*One risk that I am taking through this prototype is using a JPanel, which I have never used before, as 
well as using the ActionListener, KeyListener, and MouseMotionListener which I have also never done before.
This prototype shows my ability to create a JPanel, implement all the methods for the three interfaces, and
use them simultaneously in one program*/

import java.awt.Color;
import acm.program.GraphicsProgram;
import acm.graphics.*;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import acm.graphics.*;
import java.lang.Object;
import java.io.*;
import sun.audio.*;
/*
This class creates a JFrame with four buttons, each representing certain content that the main frame would be 
set to, depending on which button is clicked.
*/
public class PongFi extends JFrame implements ActionListener
{
public JFrame g;
public JFrame f;
public JButton singlePlayer;
public JButton singlePlayerRandom;
public JButton multiPlayer;
public JButton multiPlayerRandom;
   /*
   constructor that initializes option buttons, and adds actionPerformed methods that set the content
   of another frame based on the button clicked.
   */
   public PongFi()
   { 
      g = new JFrame();
      g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
      f = new JFrame();
      f.setSize(1500, 1100);
      f.setDefaultCloseOperation(EXIT_ON_CLOSE);
      g.getContentPane().setLayout(new GridLayout(4, 1));
      JButton singlePlayer = new JButton("Single Player: Campaign");
      singlePlayer.setFont(new Font("Papyrus", Font.BOLD,80));
      singlePlayer.setForeground(Color.green);
      singlePlayer.setBackground(Color.black);
      singlePlayer.addActionListener(new ActionListener(){
      @Override
         public void actionPerformed(ActionEvent e) {
            f.setContentPane(new SinglePlayerPanel());
            g.dispose();
            f.setVisible(true);
         }
      });
            
      g.add(singlePlayer);
      JButton multiPlayer = new JButton ("Multi Player: Campaign");
      g.add(multiPlayer);
      multiPlayer.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent e) {
            f.setContentPane(new MultiPlayerPanel());
            g.dispose();
            f.setVisible(true);
         }
      });
      multiPlayer.setFont(new Font("Papyrus", Font.BOLD,80));
      multiPlayer.setForeground(Color.yellow);
      multiPlayer.setBackground(Color.black);
      JButton multiPlayerRandom = new JButton ("MultiPlayer: Ball of Epilepsy");
      g.add(multiPlayerRandom);
      multiPlayerRandom.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent e) {
            f.setContentPane(new MultiPlayerRandomPanel());
            g.dispose();
            f.setVisible(true);
         }
      });
      multiPlayerRandom.setFont(new Font("Papyrus", Font.BOLD,80));
      multiPlayerRandom.setForeground(Color.orange);
      multiPlayerRandom.setBackground(Color.black);
      JButton singlePlayerRandom = new JButton ("Single Player: Ball of Epilepsy"); 
      g.add(singlePlayerRandom);
      singlePlayerRandom.addActionListener (new ActionListener() {
         public void actionPerformed (ActionEvent e) {
            f.setContentPane(new SinglePlayerRandomPanel());
            g.dispose();
            f.setVisible(true);
         }
      });
      
      singlePlayerRandom.setFont(new Font("Papyrus", Font.BOLD,80));
      singlePlayerRandom.setForeground(Color.red);
      singlePlayerRandom.setBackground(Color.black);
      g.pack();
      g.resize(1500,1100);
      g.setVisible(true);
   
	}
   /*
      creates the initial optionPane, which is called Pong in our program.
   */
   public static void main(String[] args)
   {
      PongFi game = new PongFi();
   }
   
   public void actionPerformed(ActionEvent e){}

}
class MultiPlayerRandomPanel extends JPanel implements KeyListener,ActionListener,MouseMotionListener {

private int xPosition;
private double yPosition;
private int playerOneY;
private int playerTwoY;
private int bouncingBallX;
private int bouncingBallY;
private int waitTime;
private Timer t;
private boolean exit;
private boolean one;
private int count;
private int yCount;
private boolean mySwitch;
   /*
   This constructor sets the details of the JFrame(i.e. background color, initializes all
   the variables required for graphics to be able to draw multiple objects in correspondence
   with what is intended( the pong game)
   */
   public MultiPlayerRandomPanel()
   {
      waitTime = 0;
      setBackground(Color.blue);
      one = true;
      exit = false;
      Timer t = new Timer(waitTime, this);
      yPosition = 1;
      xPosition = 1;
      count = 0;
      yCount = 0;
      mySwitch = true;
      playerOneY = 400;
      playerTwoY = 400;
      bouncingBallX = 725;
      bouncingBallY = 475;
      this.addKeyListener(this);
      this.setFocusable(true);
      this.requestFocus();
      addMouseMotionListener(this);
      t.start();
      
   }
   /*
   This method uses variables initialized in the contructor in order to first tell whether the game should be
   over, or whether the ball should keep on moving, what colors the graphics should use to draw the objects,
   what font and size should be used, and how much the ball moves( which in this case, it moves a random amount
   in the x and y direction)
   */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if( bouncingBallX <= 10 || bouncingBallX >= 1460)
        {
            g.fillOval(bouncingBallX, bouncingBallY, 0, 0);
            g.setColor(Color.red);
            g.setFont(new Font("Jokerman", Font.BOLD,50));
            g.drawString("G A M E   O V E R", 500, 490);
        }
        else
        {
            g.setColor(Color.white);
            g.setFont(new Font("Jokerman", Font.BOLD,50));
            g.drawString("L e v e l  L I N D E M A N N", 400, 490);
            Color[] colorOptions = new Color[5];
            colorOptions[0] = Color.green;
            colorOptions[1] = Color.red;
            colorOptions[2] = Color.yellow;
            colorOptions[3] = Color.blue;
            colorOptions[4] = Color.pink;
            if( yCount%2 != 0)
               yPosition = -((int)(Math.random()*5)+1);
            else
               yPosition = (int)(Math.random()*5)+1;
            if( count%2 != 0)
               xPosition = -((int)(Math.random()*5)+1);
            else
               xPosition = (int)(Math.random()*5)+1;
            g.setColor(colorOptions[(int)(Math.random()*5)]);
            g.fillOval(bouncingBallX, bouncingBallY, 50, 50);
            if( count == 0)
            {
               g.setColor(Color.black);
               g.fillRect(1440, playerOneY, 10, 200);
               g.setColor(Color.black);
               g.fillRect(30, playerTwoY, 10, 200);
            }
            if (count%2 == 1)
            {
               if( g.getColor() == Color.blue)
               {
                  g.setColor(Color.black);
                  g.fillRect(1440, playerOneY, 10, 600);
                  g.fillOval(bouncingBallX, bouncingBallY, 50, 50);
                  g.setColor(Color.black);
                  g.fillRect(30, playerTwoY, 10, 100);
                  g.setColor(new Color(50, 50, 200));
                  g.drawString("B L U E", 100, 490);
               }
               else if( g.getColor() == Color.green)
               {
                  g.setColor(Color.black);
                  g.fillRect(1440, playerOneY, 10, 600);
                  g.fillOval(bouncingBallX, bouncingBallY, 50, 50);
                  g.setColor(Color.black);
                  g.fillRect(600, playerTwoY, 10, 100);
                  g.setColor(new Color(50, 50, 200));
                  g.drawString("B L U E", 100, 490);
               }
            }
         }
   }
   //required method with no important implementation
   public void mouseDragged( MouseEvent e){}
   /*
   the mouseMoved method contantly resets the value of PlayerTwoY, which dictates the ycomponent
   of Player two's panel. Additionally, this method checks if the yValue is greater than that which
   is shown in the JFrame, and then moves it back to a y value that is shown in the JFrame
   */
   public void mouseMoved(MouseEvent e){
      playerTwoY = e.getY();
      if( playerTwoY >= 850)
         playerTwoY = 840;
      }
   /*
   This method uses the variable given by the constructor and constantly changed by the mouseMoved and 
   keyPressed methods in order to determine whether it should multiply the xPosition or yPosition by -1,
   decided by whether it "hits" the edge of a JFrame window or the panel. This method additionally checks
   to see if it should stop the program based on whether someone "lost", and catches an exception 
   repectively.
   */
   public void actionPerformed( ActionEvent e)
   {
      if((bouncingBallX + xPosition >= 5))
      {
         if((bouncingBallY + yPosition >= 5) && (bouncingBallY + yPosition <= 980))
         {
            bouncingBallX += xPosition;
            bouncingBallY += yPosition;
         }
         else
         {
            yPosition = -yPosition;
            yCount++;
         }
      }
      
      if(bouncingBallX<= 37 && !one)
      {
         if( (bouncingBallY+55 < playerOneY)&& (Math.abs((playerTwoY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            one = true;
            count++;
         }
         else if( (bouncingBallY+255 > playerTwoY)&& (Math.abs((playerTwoY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            one = true;
            count++;
         }
      }
      
      if((Math.abs(1400 - bouncingBallX) <= 5)&& one)
      {
         if( (bouncingBallY+55 < playerOneY)&& (Math.abs((playerOneY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            one = false;
            count++;
         }
         else if( (bouncingBallY+255 > playerOneY)&& (Math.abs((playerOneY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            one = false;
            count++;
         }
      }
      if( (count  == 3)|| ((count == 6) || (count == 9)))
      {
         if( ((xPosition > 0)&& mySwitch)&& count == 9)
         {
            xPosition+= 3;
            mySwitch = false;
         }
         else if( ((xPosition < 0)&& mySwitch)&& count == 9)
         {
            xPosition-= 3;
            mySwitch = false;
         }
         
         if( (xPosition > 0)&& mySwitch)
         {
            xPosition++;
            mySwitch = false;
         }
         else if( mySwitch)
         {
            xPosition--;
            mySwitch = false;
         }
      }
      try
      {
         if(bouncingBallX>= 1470)
         {
            exit = true;
            t.stop();
         }
         
         if(bouncingBallX<= 30)
         {
            exit = true;
            t.stop();
         }
         repaint();
      }
      catch(java.lang.NullPointerException f)
      {
         repaint();
      }
   //required method with no important implementation
   }
   public void keyTyped(KeyEvent e){}
   /*
   the mouseMoved method contantly resets the value of PlayerOnwY, which dictates the ycomponent
   of Player one's panel. Additionally, this method checks if the yValue is greater than that which
   is shown in the JFrame, and then moves it back to a y value that is shown in the JFrame
   */
    public void keyPressed(KeyEvent e)
   {
      if( playerOneY <= 835&& playerOneY>=10)
      {
          int keyPressed = e.getKeyCode();
          if (keyPressed == KeyEvent.VK_UP) {
              playerOneY -= 20;
          }
      
          if (keyPressed == KeyEvent.VK_DOWN) {
              playerOneY += 20;
          }
       }
       else if( playerOneY >= 830)
       {
         playerOneY = 830;
       }
       else 
       {
         playerOneY = 14;
       }
    }
   //required method with no important implementation
    public void keyReleased(KeyEvent e){}
}
/*
   This class incudes all of the content required to create a JFrame that has a normal ball that gradually gets faster
   and turns into an epileptic ball as it moves and bounces around a JFrame, have paddles change its direction whenever touched, and uses KeyListener
   and MouseMotionListener to move the paddles around.
*/
class MultiPlayerPanel extends JPanel implements KeyListener,ActionListener,MouseMotionListener {

private int xPosition;
private double yPosition;
private int playerOneY;
private int playerTwoY;
private int bouncingBallX;
private int bouncingBallY;
private int waitTime;
private Color[] colorOptions;
private Timer t;
private boolean exit;
private boolean one;
private int count;
private int yCount;
private boolean mySwitch;
private int[] rectangleX;
private int[] rectangleY;

   /*
   This constructor sets the details of the JFrame(i.e. background color, initializes all
   the variables required for graphics to be able to draw multiple objects in correspondence
   with what is intended( the pong game)
   */
   public MultiPlayerPanel()
   {
      waitTime = 0;
      int[] rectangleX = new int[15];
      for( int i : rectangleX )
         rectangleX[i] = 100*i;
      int[] rectangleY = new int[11];
      rectangleY[0] = 20;
      rectangleY[1] = 200;
      rectangleY[2] = 380;
      rectangleY[3] = 560;
      rectangleY[4] = 740;
      rectangleY[5] = 920;
      setBackground(Color.blue);
      one = true;
      exit = false;
      Timer t = new Timer(waitTime, this);
      yPosition = 1;
      xPosition = 1;
      count = 0;
      yCount = 0;
      mySwitch = true;
      playerOneY = 400;
      playerTwoY = 400;
      bouncingBallX = 725;
      bouncingBallY = 475;
      this.addKeyListener(this);
      this.setFocusable(true);
      this.requestFocus();
      addMouseMotionListener(this);
      t.start();
   }
   /*
   This method uses variables initialized in the contructor in order to first tell whether the game should be
   over, whether the ball should keep on moving at a slow speed, medium speed, fast speed, or epileptic speed(
   all according to how many times the ball bounces off of the paddles),what colors the graphics should use to 
   draw the objects,and what font and size should be used.
   */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if( bouncingBallX <= 10 || bouncingBallX >= 1460)
        {
            g.fillOval(bouncingBallX, bouncingBallY, 0, 0);
            g.setColor(Color.red);
            g.setFont(new Font("Jokerman", Font.BOLD,50));
            g.drawString("G A M E   O V E R", 500, 490);
        }
        else
        {
           g.setColor(Color.black);
           g.fillRect(1440, playerOneY, 10, 200);
           g.setColor(Color.black);
           g.fillRect(30, playerTwoY, 10, 200);
        }
        
        if(!(bouncingBallX <= 10 || bouncingBallX >= 1460))
        {
           if( count < 3)
            {
               g.fillOval(bouncingBallX, bouncingBallY, 50, 50);
               g.setColor(Color.green);
               g.setFont(new Font("Jokerman", Font.BOLD,50));
               g.drawString("L e v e l  1", 600, 490);
            }
           if( count >= 3 && (count < 6))
           {
               g.fillOval(bouncingBallX+1, bouncingBallY, 50, 50);
               g.setColor(Color.yellow);
               g.setFont(new Font("Jokerman", Font.BOLD,50));
               g.drawString("L e v e l  2", 600, 490);
           }
           if( count >= 6 && (count < 9))
           {
               g.fillOval(bouncingBallX+2, bouncingBallY, 50, 50);
               g.setColor(Color.red);
               g.setFont(new Font("Jokerman", Font.BOLD,50));
               g.drawString("L e v e l  3", 600, 490);
           }
           if( count >=9)
           {
               g.setColor(Color.white);
               g.setFont(new Font("Jokerman", Font.BOLD,50));
               g.drawString("L e v e l  L I N D E M A N N", 400, 490);
               Color[] colorOptions = new Color[5];
               colorOptions[0] = Color.green;
               colorOptions[1] = Color.red;
               colorOptions[2] = Color.yellow;
               colorOptions[3] = Color.blue;
               colorOptions[4] = Color.pink;
               if( yCount%2 != 0)
                  yPosition = -((int)(Math.random()*5)+1);
               else
                  yPosition = (int)(Math.random()*5)+1;
               if( count%2 != 0)
                  xPosition = -((int)(Math.random()*5)+1);
               else
                  xPosition = (int)(Math.random()*5)+1;
               g.setColor(colorOptions[(int)(Math.random()*5)]);
               g.fillOval(bouncingBallX, bouncingBallY, 50, 50);
               
           }
        }
     }
     
    //required method with no important implementation
    public void mouseDragged( MouseEvent e){}
   /*
   the mouseMoved method contantly resets the value of PlayerTwoY, which dictates the ycomponent
   of Player two's panel. Additionally, this method checks if the yValue is greater than that which
   is shown in the JFrame, and then moves it back to a y value that is shown in the JFrame
   */
   public void mouseMoved(MouseEvent e){
      playerTwoY = e.getY();
      if( playerTwoY >= 850)
         playerTwoY = 840;
      }
   /*
   This method uses the variable given by the constructor and constantly changed by the mouseMoved and 
   keyPressed methods in order to determine whether it should multiply the xPosition or yPosition by -1,
   decided by whether it "hits" the edge of a JFrame window or the panel. This method additionally checks
   to see if it should stop the program based on whether someone "lost", and catches an exception 
   repectively.
   */
   public void actionPerformed( ActionEvent e)
   {
      if((bouncingBallX + xPosition >= 5))
      {
         if((bouncingBallY + yPosition >= 5) && (bouncingBallY + yPosition <= 980))
         {
            bouncingBallX += xPosition;
            bouncingBallY += yPosition;
         }
         else
         {
            yPosition = -yPosition;
            yCount++;
         }
      }
      
      if(bouncingBallX<= 37 && !one)
      {
         if( (bouncingBallY+55 < playerOneY)&& (Math.abs((playerTwoY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            one = true;
            count++;
         }
         else if( (bouncingBallY+255 > playerTwoY)&& (Math.abs((playerTwoY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            one = true;
            count++;
         }
      }
      
      if((Math.abs(1400 - bouncingBallX) <= 5)&& one)
      {
         if( (bouncingBallY+55 < playerOneY)&& (Math.abs((playerOneY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            one = false;
            count++;
         }
         else if( (bouncingBallY+255 > playerOneY)&& (Math.abs((playerOneY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            one = false;
            count++;
         }
      }
      if( (count  == 3)|| ((count == 6) || (count == 9)))
      {
         if( ((xPosition > 0)&& mySwitch)&& count == 9)
         {
            xPosition+= 3;
            mySwitch = false;
         }
         else if( ((xPosition < 0)&& mySwitch)&& count == 9)
         {
            xPosition-= 3;
            mySwitch = false;
         }
         
         if( (xPosition > 0)&& mySwitch)
         {
            xPosition++;
            mySwitch = false;
         }
         else if( mySwitch)
         {
            xPosition--;
            mySwitch = false;
         }
      }
      try
      {
         if(bouncingBallX>= 1470)
         {
            exit = true;
            t.stop();
         }
         
         if(bouncingBallX<= 30)
         {
            exit = true;
            t.stop();
         }
         repaint();
      }
      catch(java.lang.NullPointerException f)
      {
         repaint();
      }
   //required method with no important implementation
   }
   public void keyTyped(KeyEvent e){}
   /*
   the mouseMoved method contantly resets the value of PlayerOnwY, which dictates the ycomponent
   of Player one's panel. Additionally, this method checks if the yValue is greater than that which
   is shown in the JFrame, and then moves it back to a y value that is shown in the JFrame
   */
    public void keyPressed(KeyEvent e)
   {
      if( playerOneY <= 835&& playerOneY>=10)
      {
          int keyPressed = e.getKeyCode();
          if (keyPressed == KeyEvent.VK_UP) {
              playerOneY -= 20;
          }
      
          if (keyPressed == KeyEvent.VK_DOWN) {
              playerOneY += 20;
          }
       }
       else if( playerOneY >= 830)
       {
         playerOneY = 830;
       }
       else 
       {
         playerOneY = 14;
       }
    }
    
    public void keyReleased(KeyEvent e){}
    //required method with no important implementation

}

/*
   This class incudes all of the content required to create a JFrame that has a normal ball that gradually gets faster
   and turns into an epileptic ball as it moves and bounces around a JFrame, have paddles change its direction 
   whenever touched, and uses MouseMotionListener to move the one padde around.
*/
class SinglePlayerPanel extends JPanel implements ActionListener,MouseMotionListener {

private int xPosition;
private double yPosition;
private int playerOneY;
private int bouncingBallX;
private int bouncingBallY;
private int waitTime;
private Timer t;
private boolean exit;
private boolean one;
private int count;
private int yCount;
private boolean mySwitch;
   /*
   This constructor sets the details of the JFrame(i.e. background color, initializes all
   the variables required for graphics to be able to draw multiple objects in correspondence
   with what is intended( the pong game)
   */
   public SinglePlayerPanel()
   {
      waitTime = 0;
      setBackground(Color.blue);
      one = true;
      exit = false;
      Timer t = new Timer(waitTime, this);
      yPosition = 1;
      xPosition = 1;
      count = 0;
      yCount = 0;
      mySwitch = true;
      playerOneY = 400;
      bouncingBallX = 725;
      bouncingBallY = 475;
      addMouseMotionListener(this);
      t.start();
   }
   /*
   This method uses variables initialized in the contructor in order to first tell whether the game should be
   over, whether the ball should keep on moving at a slow speed, medium speed, fast speed, or epileptic speed(
   all according to how many times the ball bounces off of the paddles),what colors the graphics should use to 
   draw the objects,and what font and size should be used.
   */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(bouncingBallX >= 1460)
        {
            g.fillOval(bouncingBallX, bouncingBallY, 0, 0);
            g.setColor(Color.red);
            g.setFont(new Font("Jokerman", Font.BOLD,50));
            g.drawString("G A M E   O V E R", 500, 490);
        }
        else
        {
           g.setColor(Color.black);
           g.fillRect(1440, playerOneY, 10, 200);
        }
        
        if(!(bouncingBallX >= 1460))
        {
           if( count < 2)
            {
               g.fillOval(bouncingBallX, bouncingBallY, 50, 50);
               g.setColor(Color.green);
               g.setFont(new Font("Jokerman", Font.BOLD,50));
               g.drawString("L e v e l  1", 600, 490);
            }
           if( count >= 2 && (count < 4))
           {
            
               g.fillOval(bouncingBallX+1, bouncingBallY, 50, 50);
               g.setColor(Color.yellow);
               g.setFont(new Font("Jokerman", Font.BOLD,50));
               g.drawString("L e v e l  2", 600, 490);
           }
           if( count >= 4 && (count < 6))
           {
           
               g.fillOval(bouncingBallX+2, bouncingBallY, 50, 50);
               g.setColor(Color.red);
               g.setFont(new Font("Jokerman", Font.BOLD,50));
               g.drawString("L e v e l  3", 600, 490);
           }
           if( count >=6)
           {
               g.setColor(Color.white);
               g.setFont(new Font("Jokerman", Font.BOLD,50));
               g.drawString("L e v e l  L I N D E M A N N", 400, 490);
               Color[] colorOptions = new Color[5];
               colorOptions[0] = Color.green;
               colorOptions[1] = Color.red;
               colorOptions[2] = Color.yellow;
               colorOptions[3] = Color.blue;
               colorOptions[4] = Color.pink;
               if( yCount%2 != 0)
                  yPosition = -((int)(Math.random()*5)+1);
               else
                  yPosition = (int)(Math.random()*5)+1;
               if( count%2 != 0)
                  xPosition = -((int)(Math.random()*5)+1);
               else
                  xPosition = (int)(Math.random()*5)+1;
               g.setColor(colorOptions[(int)(Math.random()*5)]);
               g.fillOval(bouncingBallX, bouncingBallY, 50, 50);
               
           }
        }
     }
   //required method with no important implementation
   public void mouseDragged( MouseEvent e){}
   /*
   the mouseMoved method contantly resets the value of PlayerOneY, which dictates the ycomponent
   of Player one's panel. Additionally, this method checks if the yValue is greater than that which
   is shown in the JFrame, and then moves it back to a y value that is shown in the JFrame
   */
   public void mouseMoved(MouseEvent e){
      playerOneY = e.getY();
      if( playerOneY >= 850)
         playerOneY = 840;
      }
   /*
   This method uses the variable given by the constructor and constantly changed by the mouseMoved and 
   keyPressed methods in order to determine whether it should multiply the xPosition or yPosition by -1,
   decided by whether it "hits" the edge of a JFrame window or the panel. This method additionally checks
   to see if it should stop the program based on whether someone "lost", and catches an exception 
   repectively.
   */
   public void actionPerformed( ActionEvent e)
   {
      if((bouncingBallX + xPosition >= 5))
      {
         if((bouncingBallY + yPosition >= 5) && (bouncingBallY + yPosition <= 980))
         {
            bouncingBallX += xPosition;
            bouncingBallY += yPosition;
         }
         else
         {
            yPosition = -yPosition;
            yCount++;
         }
      }
      
      if((Math.abs(1400 - bouncingBallX) <= 5)&& one)
      {
      
         if( (bouncingBallY+55 < playerOneY)&& (Math.abs((playerOneY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            
            one = false;
            count++;
         }
         else if( (bouncingBallY+255 > playerOneY)&& (Math.abs((playerOneY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            
            one = false;
            count++;
         }
      }
      if( bouncingBallX <= 11)
      {
         
         xPosition = -xPosition;
         one = true;
         count++;
      }
      try
      {
         if(bouncingBallX>= 1470)
         {
            exit = true;
            t.stop();
         }
         repaint();
      }
      catch(java.lang.NullPointerException f)
      {
         repaint();
      }
   }

}
/*
   This class incudes all of the content required to create a JFrame that has an epileptic ball move 
   and bounce around a JFrame, have paddles change its direction whenever touched, and uses MouseMotionListener
    to move the paddles around.
*/
class SinglePlayerRandomPanel extends JPanel implements ActionListener,MouseMotionListener {

private int xPosition;
private double yPosition;
private int playerOneY;
private int bouncingBallX;
private int bouncingBallY;
private int waitTime;
private Timer t;
private boolean exit;
private boolean one;
private int count;
private int yCount;
   /*
   This constructor sets the details of the JFrame(i.e. background color, initializes all
   the variables required for graphics to be able to draw multiple objects in correspondence
   with what is intended( the pong game)
   */
   public SinglePlayerRandomPanel()
   {
      waitTime = 0;
      setBackground(Color.blue);
      one = true;
      exit = false;
      Timer t = new Timer(waitTime, this);
      yPosition = 1;
      xPosition = 1;
      count = 0;
      yCount = 0;
      playerOneY = 400;
      bouncingBallX = 725;
      bouncingBallY = 475;
      addMouseMotionListener(this);
      t.start();
   }
   /*
   This method uses variables initialized in the contructor in order to first tell whether the game should be
   over, or whether the ball should keep on moving, what colors the graphics should use to draw the objects,
   what font and size should be used, and how much the ball moves( which in this case, it moves a random amount
   in the x and y direction)
   */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(bouncingBallX >= 1460)
        {
            g.fillOval(bouncingBallX, bouncingBallY, 0, 0);
            g.setColor(Color.red);
            g.setFont(new Font("Jokerman", Font.BOLD,50));
            g.drawString("G A M E   O V E R", 450, 490);
        }
        else
        {
           g.setColor(Color.black);
           g.fillRect(1440, playerOneY, 10, 200);
           Color[] colorOptions = new Color[5];
            colorOptions[0] = Color.green;
            colorOptions[1] = Color.red;
            colorOptions[2] = Color.yellow;
            colorOptions[3] = Color.blue;
            colorOptions[4] = Color.pink;
           g.setColor(colorOptions[(int)(Math.random()*5)]);
           g.fillOval(bouncingBallX, bouncingBallY, 50, 50);
            g.setColor(Color.white);
            g.setFont(new Font("Jokerman", Font.BOLD,50));
            g.drawString("L e v e l  L I N D E M A N N", 400, 490);
            if( yCount%2 != 0)
               yPosition = -((int)(Math.random()*5)+1);
            else
               yPosition = (int)(Math.random()*5)+1;
            if( count%2 != 0)
               xPosition = -((int)(Math.random()*5)+1);
            else
               xPosition = (int)(Math.random()*5)+1;
              
         }
     }
   //required method with no important implementation
    public void mouseDragged( MouseEvent e){}
   /*
   the mouseMoved method contantly resets the value of PlayerOneY, which dictates the ycomponent
   of Player one's panel. Additionally, this method checks if the yValue is greater than that which
   is shown in the JFrame, and then moves it back to a y value that is shown in the JFrame
   */
   public void mouseMoved(MouseEvent e){
      playerOneY = e.getY();
      if( playerOneY >= 850)
         playerOneY = 840;
      }
   /*
   This method uses the variable given by the constructor and constantly changed by the mouseMoved and 
   keyPressed methods in order to determine whether it should multiply the xPosition or yPosition by -1,
   decided by whether it "hits" the edge of a JFrame window or the panel. This method additionally checks
   to see if it should stop the program based on whether someone "lost", and catches an exception 
   repectively.
   */
   public void actionPerformed( ActionEvent e)
   {
      if((bouncingBallX + xPosition >= 10))
      {
         if((bouncingBallY + yPosition >= 10) && (bouncingBallY + yPosition <= 980))
         {
            bouncingBallX += xPosition;
            bouncingBallY += yPosition;
         }
         else
         {
            yPosition = -yPosition;
            yCount++;
         }
      }
      
      if((Math.abs(1400 - bouncingBallX) <= 5)&& one)
      {
      
         if( (bouncingBallY+55 < playerOneY)&& (Math.abs((playerOneY+100) - bouncingBallY) <= 105))
         {
           xPosition = -xPosition;
            one = false;
            count++;
         }
         else if( (bouncingBallY+255 > playerOneY)&& (Math.abs((playerOneY+100) - bouncingBallY) <= 105))
         {
            xPosition = -xPosition;
            
            one = false;
            count++;
         }
      }
      if( bouncingBallX <= 11)
      {
         xPosition = -xPosition;
         one = true;
         count++;
      }
      try
      {
         if(bouncingBallX>= 1470)
         {
            exit = true;
            t.stop();
         }
         repaint();
      }
      catch(java.lang.NullPointerException f)
      {
         repaint();
      }
   }

}
