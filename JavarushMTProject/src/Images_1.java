import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import junit.framework.Test;

public class Images_1 extends JPanel {
	
   public void paint(Graphics g) {
      Image img = createImageWithText();
      g.drawImage(img, 20,20,this);
   }

   public static Image createImageWithText() {
      BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
      Graphics g = bufferedImage.getGraphics();
      g.fillRect(0, 0, 200, 200);
      g.setColor(Color.black);
      g.drawString("My coordinates are 0 and 0", 50,50);
      g.drawString("www.tutorialspoint.com", 20,40);
      g.drawString("www.tutorialspoint.com", 20,60);
      g.drawString("www.tutorialspoint.com", 20,80);
      g.drawString("www.tutorialspoint.com", 20,100);
      
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
