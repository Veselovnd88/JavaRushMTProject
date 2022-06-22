import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class Images_1 extends JPanel {
	
   public void paint(Graphics g) {
      Image img = createImageWithText();
      g.drawImage(img, 20,20,this);
   }

   public static Image createImageWithText() {
      BufferedImage bufferedImage = new BufferedImage(200,80,BufferedImage.TYPE_INT_RGB);
      Graphics g = bufferedImage.getGraphics();
      g.fillRect(0, 0, 200, 200);
      Font stringFont = new Font("Arial",Font.PLAIN,12);
      Font stringBold = new Font("Arial",Font.BOLD,12);
      g.setFont(stringFont);
      g.setColor(Color.black);
      
      g.drawString("ADZ-SML-20.11", 30,15);
      
      g.drawString("1 MPa    0.5...5V", 30,30);
      g.drawString("1+, 2-, 3 Out, 4 Gehause", 30,45);
      g.drawString("SN: 123456 123456", 30,60);
      
      g.setFont(stringBold);
      g.drawString("ADZ NAGANO GmbH", 30,75);
      
      return bufferedImage;
   }
   
   public static void main(String[] args) {
	   System.out.println("Hello");
	    Images_1 im = new Images_1();
	    String name = im.getClass().getPackage().getName();
	    System.out.println(im.getClass().getPackage()+"myTestImage.jpg".replace(" ", "/"));
	   //	ImageIO.write((RenderedImage) createImageWithText(),"jpg",outputFile);

	    File f = new File("src/myTestImage.jpg");
	   	System.out.println(f.getAbsolutePath());
	   	BufferedImage bi = (BufferedImage) createImageWithText();
	   	try {
	   	ImageIO.write(bi, "jpg", f);}
	   	catch (IOException e) {
	   		e.printStackTrace();
	   	}
	   	
	   	
   }
	
}
