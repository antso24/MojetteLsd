import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

// à partir du vecteur de directions finales. connaissant les points extrêmes de chaque direction, on peut dessiner chaque droite.
// et avoir l'image correspondante

public class Dessiner {
Vector<Fraction> v;
BufferedImage image;

Dessiner() throws IOException{
	Algorithme a = new Algorithme();
	v = a.getFractionsRetenues();
	creerImage();
	Graphics2D graphe = (Graphics2D)image.getGraphics();
	for (Fraction f:v){
	graphe.drawLine(f.getVecteurX().firstElement(),512-f.getVecteurY().firstElement(),f.getVecteurX().lastElement(), 512-f.getVecteurY().lastElement());} 
	enregistrerImage(new File("c:/Users/USER/Pictures/pappl/testfinal2.jpg"));
}

public void creerImage(){
	image = new BufferedImage(512,512,BufferedImage.TYPE_INT_RGB);
}

protected void enregistrerImage(File fichierImage) throws IOException
{
	String format ="JPG";
	BufferedImage image1 = this.image;
	ImageIO.write(image1, format, fichierImage);   
}

}