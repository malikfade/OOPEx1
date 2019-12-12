package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.complex_function;
import Ex1.function;

class ComplexFunctionTEST {

	@Test
	void testComplexFunctionStringFunction2() {
		function p1= new Polynom("x^3+x^2");
		function p2= new Polynom("x+1");
		ComplexFunction f = new ComplexFunction("plus", p1, p2);
		
		assertEquals( f.left().toString(), p1.toString());
		assertEquals( f.right().toString(), p2.toString());
		
		if(!f.getOp().toString().equals("Plus")){
			fail("your Operations test fail");
		}
	}
	@Test
	void testToString() {
		ComplexFunction cf = new ComplexFunction(new Monom("x"));
		 System.out.println(cf.toString());
	}

	@Test
	void testComplexFunctionFunction1() {
		function p1= new Polynom("x^4+2x^2");	
		
		ComplexFunction f = new ComplexFunction(p1 );
		assertEquals(f.toString(), p1.toString());
	}

	@Test
	void testF() {
		function p1= new Polynom("x^3+x^2");
		function p2= new Polynom("x+1");
		double a=0;
		
		String[] s= {"Plus", "mul", "Div"};
		int[] num = {15,36,4};
		for (int i = 0; i < num.length; i++) {
			ComplexFunction f = new ComplexFunction(s[i].toString(),p1,p2 );
			a=f.f(2);
			
			assertEquals(a, num[i]);
		}
		


	}

	@Test
	void testInitFromString() {
		function p= new Polynom("0");
		String s="Plus(1.0x^4,1.0x^1)";
		function f =new ComplexFunction(p);
		f= f.initFromString(s);
	
	assertEquals(f.toString(), s);
	}

	@Test
	void testCopy() {
		function p1= new Polynom("x^3+x^2");
		function p2= new Polynom("x+1");
		ComplexFunction f1= new ComplexFunction("mul", p1, p2);
		function f2=f1.copy();
		
		assertEquals(f2.toString(), f1.toString());
	}

	@Test
	void testPlus() {
		function p1= new Polynom("x^3+x^2");
		function p2= new Polynom("x+1");
		function p3= new Polynom("x^3+x^2");
		String exp="Plus(mul(1.0x^3+1.0x^2,1.0x^1+1.0),1.0x^3+1.0x^2)";
		ComplexFunction f1= new ComplexFunction("mul", p1, p2);
		f1.plus(p3);
		assertEquals(f1.toString(), exp.toString());

	}

	@Test
	void testMul() {
		function p1= new Polynom("x^3+x^2");
		function p2= new Polynom("x+1");
		function p3= new Polynom("x^3+x^2");
		String exp="mul(mul(1.0x^3+1.0x^2,1.0x^1+1.0),1.0x^3+1.0x^2)";
		ComplexFunction f1= new ComplexFunction("mul", p1, p2);
		f1.mul(p3);
		assertEquals(f1.toString(), exp.toString());
	}

	@Test
	void testDiv() {
		function p1= new Polynom("x^3+x^2");
		function p2= new Polynom("x+1");
		function p3= new Polynom("x^3+x^2");
		String exp="div(mul(1.0x^3+1.0x^2,1.0x^1+1.0),1.0x^3+1.0x^2)";
		ComplexFunction f1= new ComplexFunction("mul", p1, p2);
		f1.div(p3);
		assertEquals(f1.toString(), exp.toString());
	}

	@Test
	void testMax() {
		function p1= new Polynom("x^3+x^2");
		function p2= new Polynom("x+1");
		function p3= new Polynom("x^3+x^2");
		String exp="Max(mul(1.0x^3+1.0x^2,1.0x^1+1.0),1.0x^3+1.0x^2)";
		ComplexFunction f1= new ComplexFunction("mul", p1, p2);
		f1.max(p3);
		assertEquals(f1.toString(), exp.toString());	}

	@Test
	void testMin() {
		function p1= new Polynom("x^3+x^2");
		function p2= new Polynom("x+1");
		function p3= new Polynom("x^3+x^2");
		String exp="Min(mul(1.0x^3+1.0x^2,1.0x^1+1.0),1.0x^3+1.0x^2)";
		ComplexFunction f1= new ComplexFunction("mul", p1, p2);
		f1.min(p3);
		assertEquals(f1.toString(), exp.toString());	}

	@Test
	void testComp() {
		function p1= new Polynom("x^3+x^2");
		function p2= new Polynom("x+1");
		function p3= new Polynom("x^3+x^2");
		String exp="Comp(mul(1.0x^3+1.0x^2,1.0x^1+1.0),1.0x^3+1.0x^2)";
		ComplexFunction f1= new ComplexFunction("mul", p1, p2);
		f1.comp(p3);
		assertEquals(f1.toString(), exp.toString());	}

	@Test
	void testEquals() {
		
		ComplexFunction cf = new ComplexFunction("mul", new Monom("x"), new Monom("x"));
		function p1 = new Monom("x^2");
		ComplexFunction cf1 = new ComplexFunction(new Monom("x^2"));
		ComplexFunction cf2 = new ComplexFunction("div",new Monom("x^2"),new Monom("x"));
		function p2 = new Monom("x");
		assertEquals(cf.equals(cf1),true);
		assertEquals(p1.equals(cf),false);
		assertEquals(p2.equals(cf2),false);
	
	}
}