import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Mojette {
private int nombreBin; // nombre de bins
private Map<Integer,Integer> bins; // hash map où la clé est le numéro du bin, l'autre entier la valeur du bin
private int plusPetitNumBin; // le plus petit numéro de bins
private int binMax; // le plus grand bin
private Vector<Integer> abscisseBin = new Vector<Integer>(); // contient les abscisses des points passant par le plus grand bin
private Vector<Integer> ordonneeBin = new Vector<Integer>(); // contient les ordonnées des points passant par le plus grand bin
private Fraction fraction;
private int numeroBinMax; //numéro du bin le plus élevé
Pixel image;

Mojette(Pixel image, Fraction fraction){ // on effectue la projection sur une image et dans une direction
	this.image = image;
	this.fraction = fraction;
	calculeNombreDeBin();
	bins = new HashMap<Integer, Integer>();
	calculePlusPetitNumBin();
	projectionMojette();
	plusGrandBin();
	calculCoordonneeBinMax();
	updateCoordonneesDroites();
	//afficheInformations();
}

public int getNombreDeBin(){
	return nombreBin;
}

public void calculeNombreDeBin(){
	int result;
	result = 1+(image.getLength()-1)*Math.abs(fraction.getDen())
			+(image.getHeight()-1)*Math.abs(fraction.getNum());
	nombreBin=result;			
}

public void calculePlusPetitNumBin(){ // calcule le plus petit numéro de bin
	int min = 10000;
	for(int k=0;k<image.getLength();k++)
		for(int l=0;l<image.getHeight();l++)
			if(-fraction.getNum()*k+fraction.getDen()*l<min)
				min=-fraction.getNum()*k+fraction.getDen()*l;
	plusPetitNumBin= min;
}

public void projectionMojette(){ // calcule les bins de la direction 
	for(int i=plusPetitNumBin;i<plusPetitNumBin+nombreBin;i++){
		int valeur = 0;
		for(int k=0;k<image.getLength();k++)
			for(int l=0;l<image.getHeight();l++)
				if(i == -fraction.getNum()*k+fraction.getDen()*l){
					valeur += image.getGrille()[k][l];
					}
					bins.put(i, valeur);
		}
}

public void calculCoordonneeBinMax(){ // calcule les coordonnées des points passant par le plus grand bin	
	for(int k=0;k<image.getLength();k++)
		for(int l=0;l<image.getHeight();l++)
			if(numeroBinMax == -fraction.getNum()*k+fraction.getDen()*l){				
				abscisseBin.add(k);
				ordonneeBin.add(l);
				}
}

public void updateCoordonneesDroites(){ 
	fraction.setVecteurX(abscisseBin);
	fraction.setVecteurY(ordonneeBin);
}

public void plusGrandBin(){ // on calcule le plus grand bin et le numéro de ce bin
	int max = -1;
	int num = -1;
	for (int i=plusPetitNumBin; i<plusPetitNumBin+nombreBin;i++){
		if(bins.get(i)>max){
			max = bins.get(i);
			num = i;}
	}
	numeroBinMax = num;
	binMax = max;
}

public Map<Integer,Integer> getBins(){
	return bins;
}

public int getMinimum(){
	return plusPetitNumBin;
}

public Fraction getFraction(){
	return fraction;
}

public int getBinMax(){
	return binMax;
}

public void afficheInformations(){	// affiche les coordonnées des deux points extremes de la fraction 
	System.out.print("Dans la direction ");
	fraction.affiche();
	System.out.println("   La droite du pic passe par le point ( " +fraction.getVecteurX().firstElement()+ " , " +fraction.getVecteurY().firstElement()+ " ) "
			+ "et par le point ( " +fraction.getVecteurX().lastElement()+ " , " +fraction.getVecteurY().lastElement()+ " ) ");

	for(int i=plusPetitNumBin;i<plusPetitNumBin+nombreBin;i++)
		System.out.println("   Le bin numero "+i+ " est égal à " +bins.get(i));	
	
		
}

}