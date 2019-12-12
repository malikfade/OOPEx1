package Ex1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.regex.Pattern;



public class Polynom implements Polynom_able{
	public ArrayList<Monom> monList = new ArrayList<Monom>();
	private Monom_Comperator c = new Monom_Comperator();

	public Polynom() {
		monList.add(Monom.ZERO);
	}
	/**
	 * @param s: string that shows a polynom
	 */
	public Polynom(String s) {
		try {
			if(s.contains("^-")) {
				throw new Exception("degree cant be negative");
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		s = s.replaceAll("\\s+", ""); 
		String tempCoff[] = s.split("(?=\\-)|(\\+)");
		Monom_Comperator c = new Monom_Comperator();
		for (int i = 0; i < tempCoff.length; i++) {
			Monom tempMon = new Monom(tempCoff[i]);		
			monList.add(tempMon);
			monList.sort(c);
		}
		for(int i=0; i< monList.size()-1; i++) {
			if (monList.get(i).get_power()==monList.get(i+1).get_power()) {
				monList.get(i+1).add(monList.get(i));
				monList.remove(i);
			}
		}
	}


	public String toString(){
		String answer = "";
		Iterator <Monom> i = this.iteretor();
		while(i.hasNext()) {
			answer+=i.next().toString();
			if(i.hasNext()) answer += "+";
		}
		answer = answer.replaceAll("\\s", "");
		answer = answer.replaceAll(Pattern.quote("++"),"+");
		answer = answer.replaceAll(Pattern.quote("++-"),"-");
		answer = answer.replaceAll(Pattern.quote("+-"),"-");
		answer = answer.replaceAll(Pattern.quote("--"),"+");

		return answer;
	}
	@Override
	public double f(double x) {
		Iterator <Monom> i = this.iteretor();
		double answer = 0;
		while(i.hasNext()) {
			answer += i.next().f(x);
		}
		return answer;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator <Monom> i = p1.iteretor();
		while (i.hasNext()) {
			this.add(i.next());
		}
	}


	@Override
	public void add(Monom m1) {

		for(int i=0 ; i < this.monList.size();i++) {
			if(monList.get(i).get_power() == m1.get_power()) {
				Monom m2=new Monom(this.monList.get(i));
				m2.add(m1);
				this.monList.set(i,m2);
				return;
			}

		}

		monList.add(m1);
		monList.sort(c);
		return;

	}



	@Override
	public void substract(Polynom_able p1) {
		Polynom_able c = (Polynom_able) p1.copy();
		c.multiply(Monom.MINUS1);
		this.add(c);

	}
	/**
	
	 * @param p1 is the polynom we wish to multiply.
	 */

	@Override
	public void multiply(Polynom_able p1) {
		Iterator <Monom> it = p1.iteretor();
		ArrayList<Monom> monTemp = new ArrayList<Monom>();
		while (it.hasNext()) {
			Monom temp = it.next();
			for (int i =0 ; i< monList.size() ; i++) {
				Monom monom_temp = new Monom (monList.get(i));
				monom_temp.multipy(temp);
				monTemp.add(monom_temp);
			}
		}
		Polynom p = new Polynom();
		for (int i =0 ; i < monTemp.size() ; i++) {
			p.add(monTemp.get(i));
		}
		this.monList = p.monList;
		for ( int i = 1 ; i< monList.size() ; i++) {
			if ( monList.get(i).equals(Monom.ZERO)) {
				monList.remove(monList.get(i));
				i--;
			}
		}
	}

	@Override
	public void multiply(Monom m1) {
		Iterator <Monom> i = this.iteretor(); 
		while(i.hasNext()) {
			i.next().multipy(m1);
		}
	}

	@Override
	public boolean equals(Object p1) {
		Polynom p =  (Polynom) this.copy();
		Polynom p2 = (Polynom) p1;
		p.substract(p2);
		if (p.isZero() || Math.abs(p.f(10))<0.001) {
			return true;
		}

		return false;
	}


	@Override
	public boolean isZero() {
		Iterator <Monom> i = this.iteretor();
		while(i.hasNext()) {
			if(i.next().get_coefficient()!=0.0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		try {
			if (f(x0)*f(x1)>0) {
				throw new Exception("for using the root function, you need two ponits on diffrent side of the x line");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		double mean = 0;
		while (Math.abs(f(x1)-f(x0))>eps) {
			mean = (x0+x1)/2;
			if (f(mean)*f(x1)>0) {
				x1 = mean;
			}
			else {
				x0 = mean;
			}
		}
		return mean;
	}


	@Override
	public Polynom_able copy() {
		Polynom temp=new Polynom(this.toString());
		return temp;
	}

	@Override
	public Polynom_able derivative() {

		Polynom d = new Polynom();
		for (int i=0; i<this.monList.size();i++) {

			d.add(this.monList.get(i).derivative());

		}
		d.monList.sort(c);
		return (Polynom_able) d;
	}

	@Override
	public double area(double x0, double x1, double eps) {

		double sum = 0.0;

		for (double i = x0; i < x1; i=i+eps) {
			if (this.f(x0)>=0) {
				sum = sum + eps*this.f(i);
			}
		}
		return sum;
	}


	@Override
	public Iterator<Monom> iteretor() {
		return monList.iterator();
	}

	@Override
	public function initFromString(String s) {
		function p1 = new Polynom(s);
		return p1;
	}
}