import java.util.HashMap;
import java.util.Map;

public class Mojette {
private int nombreBin;
private Map<Integer,Integer> bins;
private int binMinimum;
private int binMax;
private Fraction fraction;
Pixel image;
final int seuil = 25;

Mojette(Pixel image, Fraction fraction){
	this.image = image;
	this.fraction = fraction;
	calculeNombreDeBin();
	bins = new HashMap<Integer, Integer>();
	calculeBinMinimum();
	projectionMojette();
	plusGrandBin();
	admissible();
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

public void calculeBinMinimum(){
	int min = 10000;
	for(int k=0;k<image.getLength();k++)
		for(int l=0;l<image.getHeight();l++)
			if(-fraction.getNum()*k+fraction.getDen()*l<min)
				min=-fraction.getNum()*k+fraction.getDen()*l;
	binMinimum= min;
}

public void projectionMojette(){
	for(int i=binMinimum;i<binMinimum+nombreBin;i++){
		int valeur = 0;
		for(int k=0;k<image.getLength();k++)
			for(int l=0;l<image.getHeight();l++)
				if(i == -fraction.getNum()*k+fraction.getDen()*l){
					valeur += image.getGrille()[k][l];
					}
					bins.put(i, valeur);
		}
}

public void plusGrandBin(){
	int max = -1;
	for (int i=binMinimum; i<binMinimum+nombreBin;i++){
		if(bins.get(i)>max)
			max = bins.get(i);
	}
	binMax = max;
}

public void admissible(){
	if (binMax>seuil)
		fraction.setAdmissible();
	else;		
}

public Map<Integer,Integer> getBins(){
	return bins;
}

public int getMinimum(){
	return binMinimum;
}

public Fraction getFraction(){
	return fraction;
}

public int getBinMax(){
	return binMax;
}

public void afficheInformations(){	
	if (fraction.admissible){
		System.out.print("Dans la direction ");
		fraction.affiche();
		System.out.println("   Le plus grand bin vaut " +binMax);	
		System.out.println("  La fraction est interessante");}
	//else
		//System.out.println("  La fraction est inutile");
	//for(int i=binMinimum;i<binMinimum+nombreBin;i++)
		//System.out.println("   Le bin numero "+i+ " est égal à " +bins.get(i));
		
}

}