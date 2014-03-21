import java.io.IOException;
import java.util.Collections;
import java.util.Vector;


public class Algorithme {
	//int[] pyramide_ordre= {31, 63, 127, 251, 511};
	int[] pyramide_ordre= {3, 4, 5, 6, 7, 8, 9};
	Vector<Pixel> pyramide_image = new Vector<Pixel>();
	Vector<Farey> pyramide_farey = new Vector<Farey>();
	Vector<Fraction> fractions_retenues = new Vector<Fraction>();

	Algorithme() throws IOException{
		remplirPyramideImage();
		remplirPyramideFarey();
		initialisation();
		afficheResultat();
		int n=0;
		do{
			n++;		
			recurrence(n);
			afficheResultat();
		}
		while(n<6);
		affichageFinal();
	}
	
	void remplirPyramideImage() throws IOException{
		Pyramide p = new Pyramide();
		for(int i=0;i<5;i++){
		Pixel pixel = new Pixel(p.getPyramide().elementAt(i));
			pyramide_image.add(pixel) ;
	}
	}
	
	void remplirPyramideFarey(){
		for (int i=0; i<7; i++){
			Farey f = new Farey(pyramide_ordre[i]);
			pyramide_farey.add(f);
		}			
	}
	
	void initialisation(){
		System.out.println("A l'ordre " +pyramide_ordre[0]);
		for (Fraction fraction : pyramide_farey.elementAt(0).getFraction()){
			Mojette m = new Mojette(pyramide_image.elementAt(4),fraction);
			if(fraction.admissible)
				fractions_retenues.add(fraction);
			}
	}
	
	void afficheResultat(){
		System.out.println("Les directions interessantes sont :");	
		for(int i=0;i<fractions_retenues.size();i++)
			fractions_retenues.get(i).affiche();
	}
	
	void recurrence(int n){
		Vector<Fraction> temp = new Vector<Fraction>();
		temp = (Vector<Fraction>) fractions_retenues.clone();
		
		System.out.println("A l'ordre " +pyramide_ordre[n]);
		
		for (Fraction f : temp)	{	
			ajouterLesVoisinsAdmissibles(f,n,pyramide_farey.elementAt(n).getFraction());}	
	}

	void ajouterLesVoisinsAdmissibles(Fraction f, int n, Vector<Fraction> v){

		int i=1;
		do{
			Fraction f_voisin = f.getVoisin(i, v);
			if((f_voisin.getDen()==0 && f_voisin.getNum()==0)||f_voisin.appartientSuite(fractions_retenues))
				break;
			Mojette m = new Mojette(pyramide_image.elementAt(4),f_voisin);
			if (f_voisin.admissible)
			fractions_retenues.add(f_voisin);
			else break;
			i++;
		}
		while (i<200);

		int u=1;
		do{
			Fraction f_voisin = f.getVoisin(-u, v);
			if((f_voisin.getDen()==0 && f_voisin.getNum()==0)||f_voisin.appartientSuite(fractions_retenues))
				break;
			Mojette m = new Mojette(pyramide_image.elementAt(4),f_voisin);
			if (f_voisin.admissible)
			fractions_retenues.add(f_voisin);
			else break;
			u++;
		}
		while (u<200);

		Collections.sort(fractions_retenues);
		
	}	
	
	void affichageFinal(){
		Fraction.appliquerMarquage(pyramide_farey.lastElement().getFraction(), fractions_retenues);
		int marquageMax = fractions_retenues.lastElement().getMarqueur();
		System.out.println("Il y a "+marquageMax+" groupes de directions");
		for (int i=1; i<marquageMax+1; i++){
			System.out.println("Le groupe numero " +i+ " est composé des directions : ");
			for (Fraction f : fractions_retenues){			
				if(f.getMarqueur()==i){
					f.affiche();
				}
			}
	}
	}

	

	


}
