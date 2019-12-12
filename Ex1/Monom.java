package Ex1;

import java.beans.Expression;
import java.io.IOException;
import java.util.Comparator;

import javax.imageio.IIOException;


/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */

public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	
	public Monom derivative() {
		
			if(this.get_power()==0) {return new Monom(ZERO);}
			return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
		}
	public double f(double x) {
		double answer=0;
		double p = this.get_power();
		answer = this.get_coefficient()*Math.pow(x, p);
		return answer;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}


	public Monom(String s) {
		s = s.replaceAll("\\s+", "");
		s= s.replaceAll("X", "x");
		boolean valid = isValid(s);
		if(valid) {
			this.set_coefficient(checkCoff(s));
			this.set_power(checkPower(s));
		}	
	}	
	public Double checkTheFirst(String s) {
		Double answer= 0.0;
		if (s.charAt(0)=='-' ){
			answer = -1.0;
		}
		else
			if (s.charAt(0)=='+') {
				answer=1.0;	
			}
			else
				if (s.charAt(0)=='x') {
					answer=1.0;
				}

		return answer;
	}
	public int checkPower (String s) {
		int answer = 0;
		if (s.contains("^")) {
			answer=Integer.parseInt(s.substring(s.indexOf('^')+1));
		}
		else
			if (s.charAt(s.length()-1)=='x') {
				answer = 1;
			}
		
		return answer;
	}
	public Double checkCoff(String s){
		boolean first = Character.isDigit(s.charAt(0));
		Double answer = 0.0;
		if(!first) {
			Double a = checkTheFirst(s); 

			if (s.contains("x")) {
				if (s.charAt(0)!='x' && s.charAt(1)!='x') {
					answer = a*Double.parseDouble(s.substring(1,s.indexOf('x')));		
				}
				else 
					if(s.charAt(0)=='x') {
						answer = 1.0;
					}
					else {
						answer = -1.0;
					}
				return answer; 
			}
			else
				answer = a*Double.parseDouble(s.substring(1));
		}
		else 
			if(s.contains("x")) {
				answer = Double.parseDouble((String) s.subSequence(0, s.indexOf('x')));
			}
			else
			{
				answer = Double.parseDouble(s);
			}
		return answer;
	}
	public boolean isValid(String s)  {
		try {
			char c = s.charAt(0);
			char end = s.charAt(s.length()-1);

			boolean first = Character.isDigit(c);
			boolean last = Character.isDigit(end);
			if(!first && c!='-' && c!='+' && c!= 'x') {
				throw new IOException("wront input, monom can only start with digit,+,- or x");
			}
			if (!last && end!= 'x') {
				throw new IOException("last char of the mon must be digit or x");
			}
			if (s.contains("/") || s.contains(":") || s.contains("!")) {
				throw new IOException("this is beta version, cand deal with complicated operators, sorry");
			}	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void add (Monom m) {

		if (this.isZero()) {
			this._coefficient =+ m._coefficient;
			this._power =+ m._power; 
		}
		else if (this._power == m._power) {
			this._coefficient += m._coefficient;
		}
		else{
			System.out.println("Unable to add two Monoms if their power are different");
		}
	}


	public void multipy(Monom d) {
		this.set_coefficient(this.get_coefficient()*d.get_coefficient());
		this.set_power(this.get_power()+d.get_power());
	}
	public String toString() {
		String answer = "";
		if(this.isZero()) {
			return answer;
		}
		else
		{
			if (this.get_power()!=0) {
				answer = get_coefficient() + "x^" + get_power() ;
			}
			else
				answer = get_coefficient()+"";
		}
		return answer;
	}


	//****************** Private Methods and Data *****************


	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
		
	}
	private double _coefficient; 
	private int _power;
	@Override
	public function initFromString(String s) {
		function f = new Monom(s);
		return f;
	}
	@Override
	public function copy() {
		function f = new Monom(this.toString());
		return f;
	}


}