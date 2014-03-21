import java.util.Collections;
import java.util.Vector;

public class Farey {
	private int ordre;
	private Vector<Fraction> suite_farey = new Vector<Fraction>();
	
	public Farey(int n){
		ordre=n;
		suite_farey=anglesDiscrets(n);
		//afficheInfoFarey();		
	}
	
	public static Vector<Fraction> anglesDiscrets(int n){
		return addOppose(addSymetrique(algoFarey(n)));
	}
	
	public static Vector<Fraction> algoFarey(int n){	
		Vector<Fraction> result = new Vector<Fraction>();
		if (n == 1){			
			Fraction f0 = new Fraction(0,1);
			Fraction f1 = new Fraction(1,1);
			result.add(f0);
			result.add(f1);
		}	
		else{
		    result = algoFarey(n-1);
		    int size = result.size();
			for(int i=0; i<size; i++){
				int num = result.get(i).getNum() + result.get(i+1).getNum();
				int den = result.get(i).getDen() + result.get(i+1).getDen();
				Fraction f = new Fraction(num,den);
				if (f.testFarey(n))
				 result.add(f);
				}		
			Collections.sort(result);
		}
		return result;
}
	public static Vector<Fraction> addSymetrique(Vector<Fraction> old_vect){
		Vector<Fraction> result = new Vector<Fraction>();
		if (old_vect.size()<2)
			result = old_vect;
		else{
			result = old_vect;
			int size=old_vect.size();
			for(int i=size-2;i>=0;i--){
				int den = result.get(i).getNum();
				int num = result.get(i).getDen();
				Fraction f = new Fraction(num, den);
				result.add(f);
			}
		}
		return result;
	}
	
	public static Vector<Fraction> addOppose(Vector<Fraction> old_vect){
		Vector<Fraction> result = new Vector<Fraction>();
		if (old_vect.size()<2)
			result = old_vect;
		else if (old_vect.size() == 2){
			result = old_vect;
			result.add(new Fraction(-1,1));}
		else{
			result = old_vect;
			int size=old_vect.size();
			for(int i=size-2;i>0;i--){
				int num = result.get(i).getNum();
				int den = -(result.get(i).getDen());
				Fraction f = new Fraction(num, den);
				result.add(f);
			}
		}
			return result;			
	}
	
	public void afficheInfoFarey(){
		System.out.println("Les éléments de la suite de Farey d'ordre "+ordre+ " sont :");
		for (Fraction fraction : suite_farey)
		fraction.affiche();
	}
	 
	public int getOrdre(){
		return ordre;
	}
	
	public Vector<Fraction> getFraction(){
		return suite_farey;
	}
}
