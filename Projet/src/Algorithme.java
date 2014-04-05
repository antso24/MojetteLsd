import java.io.IOException;
import java.util.Collections;
import java.util.Vector;


public class Algorithme {
	int[] pyramide_ordre= {3, 4, 5, 6, 7, 8, 9}; // pyramide des ordre de farey. a l'initialisation, on appliquera mojette à l'ordre 3. puis, a l'ordre 4. jusqu'à 9.
	int[] pyramide_seuil= {25, 25, 25, 25, 25, 25, 25}; // pyramide des seuils à chaque itération
	Vector<Pixel> pyramide_image = new Vector<Pixel>(); // contient la pyramide des images
	Vector<Farey> pyramide_farey = new Vector<Farey>(); // contient la pyramide des suites de farey
	Vector<Fraction> fractions_retenues = new Vector<Fraction>(); // vecteur de fractions finales, mis à jour à chaque itération

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
	
	Vector<Fraction> getFractionsRetenues(){
		return fractions_retenues;
	}
	
	void remplirPyramideImage() throws IOException{
		Pyramide p = new Pyramide();
		for(int i=0;i<6;i++){
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
	
	void initialisation(){ // on applique mojette à l'ordre 3 sur l'image.
		System.out.println("A l'ordre " +pyramide_ordre[0]);
		for (Fraction fraction : pyramide_farey.elementAt(0).getFraction()){
			Mojette m = new Mojette(pyramide_image.elementAt(5),fraction);
			if(m.getBinMax()>pyramide_seuil[0]) // si la projection a son bin le plus élevé supérieur au seuil, la direction est ajoutée au vecteur fractions_retenues
				fractions_retenues.add(fraction);
			}
	}
	
	void afficheResultat(){
		System.out.println("Les directions interessantes sont :");	
		for (Fraction f : fractions_retenues){
			f.affiche();
			System.out.println(" passant par le point ( "+f.getVecteurX().firstElement()+ " , " +f.getVecteurY().firstElement()+ " ) "
					+" et par le point ( "+f.getVecteurX().lastElement()+ " , " +f.getVecteurY().lastElement()+ " )");
		}
	}
	
	void recurrence(int n){
		Vector<Fraction> temp = new Vector<Fraction>();
		temp = (Vector<Fraction>) fractions_retenues.clone();
		
		System.out.println("A l'ordre " +pyramide_ordre[n]);
		
		for (Fraction f : temp)	{	
			ajouterLesVoisinsAdmissibles(f,n,pyramide_farey.elementAt(n).getFraction());}	
	}
	
	// on parcourt le vecteur de fractions. 
	// pour chaque fraction, on teste son voisin dans la suite de farey d'ordre supérieur.
	// si ce voisin a un bin le plus élevé supérieur au seuil, on l'ajoute au vecteur fractions_retenues et on teste le voisin de ce voisin.

	void ajouterLesVoisinsAdmissibles(Fraction f, int n, Vector<Fraction> v){

		int i=1;
		do{
			Fraction f_voisin = f.getVoisin(i, v);
			if((f_voisin.getDen()==0 && f_voisin.getNum()==0)||f_voisin.appartientSuite(fractions_retenues))
				break;
			Mojette m = new Mojette(pyramide_image.elementAt(5),f_voisin);
			if (m.getBinMax()>pyramide_seuil[n])
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
			Mojette m = new Mojette(pyramide_image.elementAt(5),f_voisin);
			if (m.getBinMax()>pyramide_seuil[n])
			fractions_retenues.add(f_voisin);
			else break;
			u++;
		}
		while (u<200);

		Collections.sort(fractions_retenues);
		
	}	
	
	void affichageFinal(){ // affiche les directions retenues. ainsi que leurs points extrêmes. afin de pouvoir les dessiner
		Fraction.appliquerMarquage(pyramide_farey.lastElement().getFraction(), fractions_retenues);
		int marquageMax = fractions_retenues.lastElement().getMarqueur();
		System.out.println("Il y a "+marquageMax+" groupes de directions");
		for (int i=1; i<marquageMax+1; i++){
			System.out.println("Le groupe numero " +i+ " est composé des directions : ");
			for (Fraction f : fractions_retenues){			
				if(f.getMarqueur()==i){
					f.affiche();	
					System.out.println(" passant par le point ( "+f.getVecteurX().firstElement()+ " , " +f.getVecteurY().firstElement()+ " ) "
							+" et par le point ( "+f.getVecteurX().lastElement()+ " , " +f.getVecteurY().lastElement()+ " )");
				}
			}
	}
	}

	

	


}
