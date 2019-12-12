package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;

class MonomTEST {

	@Test
	void testDerivative() {
		Monom m1 = new Monom(5,3);
		Monom m2 =new Monom(2,5);
		Monom m4 =new Monom(4,5);


		Monom dm1 = new Monom(15,2);
		Monom dm2 = new Monom(10,4);
		Monom dm4 = new Monom(20,4);

		Monom d1 = (Monom) m1.derivative();
		Monom d2 = (Monom) m2.derivative();
		Monom d4 = (Monom) m4.derivative();

		assertEquals(d2.toString(), dm2.toString());
		assertEquals(d1.toString(), dm1.toString());
		assertEquals(d4.toString(),dm4.toString());



	}

	@Test
	void testF() {
		Monom m1 = new Monom(5,3);
				double f1, temp;
		for (int i = 1; i < 5; i++) {
			f1=m1.f(i);
			temp= 5*(Math.pow(i, 3));
			assertEquals(temp, f1);
		}
		
	}

	@Test
	void testIsZero() {
		Monom m1 = new Monom(5,3);
		Monom m2 =new Monom(0,5);
		
		if(m1.isZero()) {
		fail("work on your math");
		}
		if(!m2.isZero()) {
			fail("work on your math");
		}
	}
	@Test
	void testAdd() {
		Monom dm1 = new Monom(15,4);
		Monom dm2 = new Monom(5,4);
		Monom dm4 = new Monom(20,4);
		 dm1.add(dm2);		
		 assertEquals(dm1.toString(), dm4.toString());
		
	}

	@Test
	void testMultipy() {
		Monom m1 = new Monom(4,2);
		Monom m2 = new Monom(5,2);
		Monom m4 = new Monom(20,4);
		m1.multipy(m2);
		assertEquals(m1.toString(), m4.toString());
		
	}

	@Test
	void testToString() {
		Monom dm1 = new Monom(15,4);
		String s="15.0x^4";
		String d=dm1.toString();
		
		assertEquals(d, s);
		
	}

	@Test
	void testInitFromString() {
		String s="15.0x^4";
		Monom m= new Monom(0,0);
		Monom m2= new Monom(15,4);
		Monom f = (Monom) m.initFromString(s);
		
		assertEquals(f.toString(), m2.toString());
	}

	@Test
	void testCopy() {
		Monom m2= new Monom(15,4);
		Monom m1= (Monom) m2.copy();
		assertEquals(m2.toString(), m1.toString());

	}

}