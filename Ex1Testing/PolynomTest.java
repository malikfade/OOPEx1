package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;

class PolynomTEST {


	@Test
	void testToString() {
		Polynom p1=new Polynom("x^2+3x+1");

		String s="1.0x^2+3.0x^1+1.0";

		assertEquals(s.toString(), p1.toString());
	}

	@Test
	void testF() {
		Polynom p1=new Polynom("x^3+3x^2+1");
		double b =p1.f(2);
		double a=21;
		assertEquals(a, b);


	}

	@Test
	void testAddPolynom_able() {
		Polynom_able p1 = new Polynom("-4.7x^2-1.0x+6.0");
		Polynom p2=new Polynom("x^2+3x+1");
		p2.add(p1);
		Polynom p3= new Polynom("-3.7x^2+2.0x^1+7.0");
		assertEquals(p3,p2);
	}

	@Test
	void testAddMonom() {
		Polynom p3= new Polynom("-3.7x^2+2.0x^1+7.0");
		Polynom p2= new Polynom("-3.7x^2+2.0x^1+7.0");
		Polynom p1= new Polynom("-3.7x^2+2.0x^1+7.0");
		Monom m1 =new Monom("3x^2");
		Monom m2 =new Monom("3.7x^2");
		Monom m3 =new Monom("1333x^5");
		p3.add(m1);
		p2.add(m2);
		p1.add(m3);

		//epsilon = 0.000002	//	assertEquals("-0.7x^2+2.0x^1+7.0", p3.toString());
		assertEquals("+2.0x^1+7.0", p2.toString());
		assertEquals("1333.0x^5-3.7x^2+2.0x^1+7.0", p1.toString());

	}

	@Test
	void testSubstract() {
		Polynom p3= new Polynom("-3.7x^2+2.0x^1+7.0");
		Polynom p2= new Polynom("-3.7x^2+2.0x^1+7.0");
		Polynom p1= new Polynom("x^2+4+3x^5");
		p3.substract(p2);

		p1.substract(p2);

		assertEquals("+", p3.toString());
		assertEquals("3.0x^5+4.7x^2-2.0x^1-3.0" , p1.toString());
	}

	@Test
	void testMultiplyPolynom_able() {
		Polynom_able p= new Polynom("x-1");
		Polynom_able p2= new Polynom("x+1");
		p.multiply(p2);
		assertEquals("1.0x^2-1.0", p.toString());
		Polynom_able p3= new Polynom("x^2-x");
		Polynom_able p5= new Polynom("x^2+x");
		p3.multiply(p5);
		assertEquals("1.0x^4-1.0x^2", p3.toString());
	}

	@Test
	void testMultiplyMonom() {
		Polynom a= new Polynom("x^3+x^2+x-1");
		Monom m=new Monom("x^2");
		a.multiply(m);
		assertEquals("1.0x^5+1.0x^4+1.0x^3-1.0x^2", a.toString());
	}

	@Test
	void testEqualsObject() {

		Polynom p1 = new Polynom("-6.0x^3+3.0x^2+9.0x^1-2.0");
		Polynom p2 = new Polynom("6.0x^1-5.0");
		Polynom p3 = new Polynom("-5.9999999999x^3+3.00000001x^2+9.000000001x^1-2.0");

		if(!p1.equals(p3)) {
			fail("wrong equation");

		}
		if(p1.equals(p2)) {
			fail("wrong equation");
		}

	}

	@Test
	void testIsZero() {
		Polynom p = new Polynom("x^2+6x+2");
		Polynom p2 = new Polynom("x^2+6x+2");

		p.substract(p2);

		
		Polynom p3 = new Polynom("x^2+6x+2-x^2-6x-2");

		assertEquals("+", p3.toString());
	}

	@Test
	void testRoot() {
		String[][] polynoms = {{"3x^2","-6x^3","9x","-2"},
				{"x","5x","0","-5"}
		};
		double[][] res = {{0,0.21350860595703125},{0,0.8333282470703125}}; 
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p = new Polynom();
			for (int j = 0; j < polynoms[i].length; j++) {
				Monom temp = new Monom(polynoms[i][j]);
				p.add(temp);
			}
			double a1= p.root(0, 1, 0.0001);

			assertEquals(res[i][1], a1);
		}
	}

	@Test
	void testCopy() {
		String[] a= {"x^3+x^2+x-1"  , "x^2-x" ,"-3.7x^2+2.0x^1+7.0"};
		for (int i = 0; i < a.length; i++) {
			Polynom p = new Polynom(a[i]);
			Polynom copyP=(Polynom) p.copy();
			assertEquals(p,copyP);

		}

	}

	@Test
	void testDerivative() {
		Polynom a= new Polynom("x^3+5x^2+2x");
		Polynom p1= new Polynom("3x^2+10x+2");
		Polynom_able p2= (Polynom) a.derivative();
		
		assertEquals(p2.toString(),p1.toString());
	}

	@Test
	void testArea() {
		String[][] polynoms = {{"3x^2","-6x^3","9x","-2"},
				{"x","5x","0","-5"},
		};
		double[][] res = {{0,0.2135},{0,0.83334}}; 
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p = new Polynom();
			for (int j = 0; j < polynoms[i].length; j++) {
				Monom temp = new Monom(polynoms[i][j]);
				p.add(temp);
			}
			double a1= p.area(-1, 0, 0.0001);

			assertEquals(res[i][0], a1);
		}


	}


	@Test
	void testInitFromString() {
		String S="1.0x^3+15.0x^2+9.0";
		function p= new Polynom();
		Polynom p2= new Polynom("x^3+15x^2+9");

		Polynom p3=  (Polynom) p.initFromString(S);


		assertEquals(p2.toString(), p3.toString());
	}

}