import java.util.Vector;

public class Fraction implements Comparable {
	private int numerateur;
	private int denominateur;
	private int marqueur = 0;
	boolean admissible = false;
	
	public void setMarqueur(int m){
		marqueur = m;
	}
	
	public int getMarqueur(){
		return marqueur;
	}
	
	public void setAdmissible(){
		admissible = true;
	}
	
	public boolean getAdmissible(){
		return admissible;
	}
	
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
		
	public boolean testFarey(int ordre){
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
	
	public boolean appartientSuite(Vector<Fraction> v){
		boolean resultat=false;
		for (Fraction f:v){
			if(f.compareTo(this) == 0)
				resultat = true;
		}
		return resultat;
	}
	
	public boolean testVoisin(Fraction fraction, Vector<Fraction> v){
		boolean resultat = false;
		int index = 0;
		int index2 = 0;
		for(Fraction f:v){
			if(f.compareTo(this) == 0)
				index = v.indexOf(f);
			if(f.compareTo(fraction) == 0)
				index2 = v.indexOf(f); 
		}
		if ((index-index2==1)||(index-index2==-1))
			resultat = true;
		
		return resultat;
	}
	
	public Fraction getVoisin(int n, Vector<Fraction> v){
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
	
	public static void appliquerMarquage(Vector<Fraction> vFarey, Vector<Fraction> vRetenue){
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
