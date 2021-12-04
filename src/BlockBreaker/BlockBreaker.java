
package blockbreaker;

import javax.swing.JFrame;

public class BlockBreaker {

   
    public static void main(String[] args) {
     
            JFrame jf = new JFrame(); 
            Gameplay gp = new Gameplay();
   
		
       
  
        jf.add(gp);
       jf.setVisible(true);
       jf.setResizable(false);
       jf.setBounds(10, 10,700,600);
       jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
}
