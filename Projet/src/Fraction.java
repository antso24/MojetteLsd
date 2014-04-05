import java.util.Vector;

public class Fraction implements Comparable {
	private int numerateur;
	private int denominateur;
	private int marqueur = 0; 
	private Vector<Integer> vecteurX = new Vector<Integer>(); //contient les abscisses des points passant par le bin d'une direction
	private Vector<Integer> vecteurY = new Vector<Integer>(); //contient les ordonnées des points passant par le bin d'une direction	
	
	public Fraction(int n, int d){
		numerateur=n;
		denominateur=d;
	}
	
	public int getNum(){
		return numerateur;
	}
	
	public int getDen(){
		return denominateur;
	}
	
	public void setVecteurX (Vector<Integer> m){
		vecteurX = m;
	}
	
	public void setVecteurY(Vector<Integer> m){
		vecteurY = m;
	}
		
	public Vector<Integer> getVecteurX(){
		return vecteurX;
	}
	
	public Vector<Integer> getVecteurY(){
		return vecteurY;
	}	
	
	public void setMarqueur(int m){ 
		marqueur = m;
	}
	
	public int getMarqueur(){
		return marqueur;
	}
	
		
	public boolean testFarey(int ordre){ // on rajoute la fraction à la suite de farey d'ordre n si son denominateur est inférieur à l'ordre
		if (denominateur<=ordre) return true;
		else return false;
	}

	public double calculAngle(){
		double angle=Math.atan((double)numerateur/(double) denominateur);
		if(angle<0) angle = angle + Math.PI;
		return angle;
	}	
	
	public void affiche(){
		System.out.println("(" + denominateur + "," + numerateur + ")");}
	
	public boolean appartientSuite(Vector<Fraction> v){ //retourne vrai si la fraction appartient au vecteur de fraction v
		boolean resultat=false;
		for (Fraction f:v){
			if(f.compareTo(this) == 0)
				resultat = true;
		}
		return resultat;
	}
	
	public boolean testVoisin(Fraction f1, Vector<Fraction> v){ //retourne vrai si la fraction "this" est voisine à la fraction f1 dans le vecteur de fraction v
		boolean resultat = false;
		int index = 0;
		int index2 = 0;
		for(Fraction f:v){
			if(f.compareTo(this) == 0)
				index = v.indexOf(f);
			if(f.compareTo(f1) == 0)
				index2 = v.indexOf(f); 
		}
		if ((index-index2==1)||(index-index2==-1))
			resultat = true;
		
		return resultat;
	}
	
	public Fraction getVoisin(int n, Vector<Fraction> v){ // renvoie la fraction décalée de n indices de la fraction "this" dans le vecteur de fraction v
		Fraction nul = new Fraction(0,0);
		Fraction resultat = nul;
		for (Fraction f:v){
			if(f.compareTo(this) == 0){
			int index = v.indexOf(f);
			if((index+n>-1)&&(index+n<v.size()))
			resultat = v.elementAt(index+n);}
		}	
			return resultat;
	}
	
	public static void appliquerMarquage(Vector<Fraction> vFarey, Vector<Fraction> vRetenue){ // si les fractions de vRetenue se suivent dans le vecteur de fraction vFarey, elles ont le même marquage
		int size = vRetenue.size();
		vRetenue.elementAt(0).setMarqueur(1);
		for(int i=1; i<size; i++){
			int m = vRetenue.elementAt(i-1).getMarqueur();
			if(vRetenue.elementAt(i).testVoisin(vRetenue.elementAt(i-1), vFarey))
				vRetenue.elementAt(i).setMarqueur(m);
			else vRetenue.elementAt(i).setMarqueur(m+1);
		}
	}

	@Override
	public int compareTo(Object arg0) {
		double objectAngle=(double)((Fraction)arg0).calculAngle();
		if(this.calculAngle()>objectAngle) return 1;
		if(this.calculAngle()<objectAngle) return -1;
		return 0;
	}

	
	

}
