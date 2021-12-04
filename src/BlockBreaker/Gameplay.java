
package blockbreaker;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;


class Gameplay extends JPanel implements KeyListener, ActionListener{
  //  ArrayList<backgraund> blocks = new ArrayList<>();
    Image background;

  private boolean play =false ;
 private int score =0; // num
 private int playerX =310;
 private Timer time ;
  private int delay =8; // speed 
  private int totleB=21; // total for each destroy
 
  private int ballposx =120;// postion of ball x
   private int ballposy= 350 ; //postion of ball y
  
  
  private int ballxder=-1; // deration of a ball for x
   private int ballyder=-2; // deration of a ball for y
  //private int ballxder=n;
      private map mapG;
       public Gameplay  (){ //constrctor timer
            mapG=new map(3,7);
            addKeyListener(this);//0
           setFocusable(true);//0
            setFocusTraversalKeysEnabled(false);// ****
            time =new Timer(delay ,this ) ;// ob for time and use it 
            time.start();
            
       }
       public void paint(Graphics g){
        //pic = Toolkit.getDefaultToolkit().getImage(s);
// for ball and blocks, three things 1.backgraound 2.borders 3.paddle 4. ball
        //1
      background = Toolkit.getDefaultToolkit().getImage("BR.jpg");

		g.drawImage(background, 1, 1, 692, 592, this);

		g.setColor(Color.white);
           //2
           g.setColor(Color.blue);
           g.fillRect(0, 0, 3, 592);
           g.fillRect(0, 0, 692, 3);
           g.fillRect(691, 0, 3, 592);
           //3
           g.setColor(Color.green);
           g.fillRect(playerX,510,100,10);
           //4
           g.setColor(Color.gray);
           g.fillOval(ballposx,ballposy,20,20);
           //map call
           mapG.draw((Graphics2D)g);
           //score
           g.setColor(Color.white);
		// draw title
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString(""+score, 600, 30);
              //u won
               if(totleB <= 0){
                    play=false;
                    ballxder=0;
                    ballyder=0;
              g.setColor(Color.red);
              g.setFont(new Font("serif", Font.BOLD,30));
              g.drawString("YOU WON  "+score, 260, 300);
               g.setFont(new Font("serif", Font.BOLD,20));
              g.drawString("PRESS ENTER TO RESTART", 230, 350);
               }
                
                //game over
                if(ballposy > 570){
                    play=false;
                    ballxder=0;
                    ballyder=0;
              g.setColor(Color.red);
              
              g.setFont(new Font("serif", Font.BOLD,30));
              g.drawString("GAME OVER   "+score, 190, 300);
              
              
               g.setFont(new Font("serif", Font.BOLD,20));
              g.drawString("PRESS ENTER TO RESTART", 220, 340);



                }
           
           g.dispose();
           
           
           
       }
       
       @Override
    public void actionPerformed(ActionEvent ae) {
        time.start();
        		//Random random = new Random();
                     //  int n= random.nextInt(2+1-2)-2;
                        

        if(play){
           if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerX,510,100,10))){
               ballyder= -ballyder;
           }
           A:  for(int i =0 ; i<mapG.map.length;i++){
               for(int j=0;j<mapG.map[0].length;j++){
                   if(mapG.map[i][j]>0){
                       
                      int brickx = j*mapG.brickwidth+80; 
                      int bricky= i*mapG.brickhight+50;
                      int brickwidth=mapG.brickwidth;
                      int brickhight=mapG.brickhight;
                      Rectangle rect=new Rectangle(brickx,bricky,brickwidth,brickhight);
                      Rectangle ballrect=new Rectangle(ballposx,ballposy,20,20);
                       Rectangle brickrect=rect;
                       
                       if(ballrect.intersects(brickrect)){
                           mapG.setBrValue(0 , i , j);
                           totleB--;
                           score+=5;
                           
                           if(ballposx +19<= brickrect.x || ballposx +1 >= brickrect.x + brickrect.width){
                               ballxder= -ballxder;
                           }
                           else{
                               ballyder= -ballyder;
                           }
                             break A;  
                           }
                       }
               }
           }
                   
            ballposx +=ballxder;
             ballposy +=ballyder;
             
             if(ballposx < 0){//lift border
              ballxder= -ballxder;

             }
             if(ballposy < 0){//top bor
              ballyder= -ballyder;

             }
             if(ballposx > 670){//right bor
              ballxder= -ballxder;

             }
        }
        
       
        repaint();//recall the method
    }
    @Override
    public void keyTyped(KeyEvent ke) {
       //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public void keyReleased(KeyEvent ke) {
        
        
    }
 @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                // check if the player dosnt go out
                
              playerX=600; // bonded on border -stay-
              
        }
            else{
                moveRight();
            }}
         if(ke.getKeyCode() == KeyEvent.VK_LEFT){
              if(playerX < 10){
                // check if the player dosnt go out
                
              playerX=10; // keep it
              }
              else{
                moveLift();
              
        }
         }
              if(ke.getKeyCode()== KeyEvent.VK_ENTER){
                  if(!play){
                  play=true;
                  ballposx=120;
                  ballposy=350;
                   ballxder=-1;
                   ballyder=-2;
                  playerX =310;
                  score =0;
                  mapG=new map(3,7);
                 repaint();
                 }
              } 
            }

    private void moveRight() {
        play=true;
        playerX+=20; // 20 px move to the right
        
    }

    private void moveLift() {
       play=true;
        playerX-=20; 
    }
    
    
    
    

}
