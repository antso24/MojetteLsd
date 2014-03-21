import java.io.IOException;
import java.util.Vector;

public class Pyramide {
	private Vector<int[][]> pyramide = new Vector<int[][]>();
	
	public Pyramide() throws IOException{
		Image img32 = new Image("c:/Users/USER/Pictures/pappl/32.jpg");
		Image img64 = new Image("c:/Users/USER/Pictures/pappl/64.jpg");
		Image img128 = new Image("c:/Users/USER/Pictures/pappl/128.jpg");
		Image img256 = new Image("c:/Users/USER/Pictures/pappl/256.jpg");
		Image img512 = new Image("c:/Users/USER/Pictures/pappl/512.jpg");
		
		pyramide.add(img32.getMatrix());
		pyramide.add(img64.getMatrix());
		pyramide.add(img128.getMatrix());
		pyramide.add(img256.getMatrix());
		pyramide.add(img512.getMatrix());
		
		//afficherPyramide();	
	}
	
	public void afficherPyramide(){
		for (int i=0; i<pyramide.size();i++){
			System.out.println("La matrice numero "+i+ ": " );		
		
		int height = pyramide.elementAt(i).length;
	    System.out.println("  hauteur : " + height);

		int width = (pyramide.elementAt(i))[0].length;
		System.out.println("  largeur : " + width);}
		
		System.out.println("La matrice :");
	    for (int u=0; u<pyramide.elementAt(0).length; u++){
	        for (int j=0; j<(pyramide.elementAt(0))[0].length; j++){        	
		       System.out.print((pyramide.elementAt(0))[u][j]);
		       System.out.print(" ");}
	           System.out.print("\n");}
		}
	
	public Vector<int[][]> getPyramide(){
		return pyramide;
	}
	
}
