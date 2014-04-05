import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//pour transformer une image en matrice binaire, on charge l'image, puis on la binarise, puis on prend sa matrice

public class Image { 
BufferedImage image;
int[][] matrice;

public Image(String url) throws IOException{ 
	image = ImageIO.read(new File(url));
	binariser();
	convert();
	//affiche();
}

public int[][] getMatrix(){
	return matrice;
}

public void convert(){
	Raster tramePixel = image.getRaster();
	ColorModel modeleCouleur = image.getColorModel();
      int h = image.getHeight();
      int w = image.getWidth();
      int [][] result = new int[h][w];
	for(int x=0;x< h; x++){
		 for(int y=0; y<w; y++){
			Object objCouleur   = tramePixel.getDataElements(x, y, null);
			if(modeleCouleur.getRed(objCouleur)==255)
				result[x][y]=1;				
			else 
				result[x][y]=0;			
		}
	}
	matrice = result;
}

public void binariser()
{   	
	BufferedImage imgBinaire = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
	Graphics2D surfaceImg = imgBinaire.createGraphics();
	surfaceImg.drawImage(image, null, null);      
	image = imgBinaire;
}

public void affiche(){
	int height = matrice.length;
    System.out.println("  hauteur : " + height);

	int width = matrice[0].length;
	System.out.println("  largeur : " + width);
	
	System.out.println("La matrice :");
    for (int u=0; u<height; u++){
        for (int j=0; j<width; j++){        	
	       System.out.print(matrice[u][j]);
	       System.out.print(" ");}
           System.out.print("\n");}
}

}
