public class Pixel {
private int length;
private int height;
private int grillePixel[][];

Pixel(int grillePixel[][]){
	this.grillePixel = grillePixel.clone();
	this.height = grillePixel[0].length;
	this.length = grillePixel.length;
	inverserMatrice();
	//afficherMatrice();
}

public void inverserMatrice(){
	int [][] matriceInverse= new int [height][length];
	for(int i=0; i<height;i++){
		for(int j=0; j<length;j++){
			matriceInverse[i][j] = grillePixel[j][length-1-i];
		}
	}	
	int [][] matriceInverse2= new int [height][length];
	for(int i=0; i<height;i++){
		for(int j=0; j<length;j++){
			matriceInverse2[i][j] = matriceInverse[length-i-1][length-1-j];
		}
	}	
	grillePixel = matriceInverse2;
	}

public void afficherMatrice(){
System.out.println("La matrice a pour hauteur "+height+ " et pour largeur " +length);
/*for (int u=0; u<grillePixel.length; u++){
    for (int j=0; j<grillePixel[0].length; j++){        	
       System.out.print(grillePixel[u][j]);
       System.out.print(" ");}
       System.out.print("\n");}*/
}

public int getLength(){
	return length;
}

public int getHeight(){
	return height;
}

public int[][] getGrille(){
	return grillePixel;
}

	
}