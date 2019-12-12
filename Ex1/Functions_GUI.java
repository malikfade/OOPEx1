package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

import com.google.gson.Gson;



public class Functions_GUI implements functions{
	private ArrayList<function> function_List = new ArrayList<function>();


	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};

	@Override
	public int size() {
		return function_List.size();
	}

	@Override
	public boolean isEmpty() {
		return function_List.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return function_List.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return function_List.iterator();
	}

	@Override
	public Object[] toArray() {
		return function_List.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return function_List.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return function_List.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return function_List.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return function_List.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return function_List.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return function_List.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return function_List.retainAll(c);
	}

	@Override
	public void clear() {
		function_List.clear();

	}

	@Override
	public void initFromFile(String file) throws IOException {
		function f =  new ComplexFunction(new Monom(Monom.ZERO));
		String line ="";

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				function_List.add(((function) f).initFromString(line));
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveToFile(String file) throws IOException {

		try {
			PrintWriter pw = new PrintWriter(new File(file));
			StringBuilder sb = new StringBuilder();

			Iterator<function> it = function_List.iterator();
			while(it.hasNext()) {

				sb.append(it.next().toString()+"\n");
			}
			pw.write(sb.toString());
			pw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

		
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());

		StdDraw.setPenColor(Color.LIGHT_GRAY);
		StdDraw.setFont(new Font("Arial", Font.BOLD, 15));

		for(double i= ry.get_min(); i<=ry.get_max();i++) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
			StdDraw.text(0.2,i+0.2,i+""); 
		}
	
		for(double j=rx.get_min(); j<=rx.get_max(); j++) {
			StdDraw.line(j, ry.get_min(), j, ry.get_max());
			StdDraw.text(j+0.2,0.2,j+""); 
		}

		
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		
		StdDraw.line(rx.get_min(),0, rx.get_max(), 0);

		StdDraw.line(0, ry.get_min(), 0, ry.get_max());

		double step = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
		for(int i=0; i<this.function_List.size();i++) {
			StdDraw.setPenColor(Colors[i%Colors.length]);
			for(double j =rx.get_min(); j< rx.get_max(); j=j+step) {
				StdDraw.line(j, function_List.get(i).f(j), j+step, function_List.get(i).f(j+step));
			}
		}
	}








	@Override
	public void drawFunctions(String json_file) {
		Gson myGson = new Gson();
		try 
		{
			
			FileReader reader = new FileReader(json_file);
			params params = myGson.fromJson(reader,params.class);
			
			Range rangex = new Range(params.Range_X[0],params.Range_X[1]);
			Range rangey = new Range(params.Range_Y[0],params.Range_Y[1]);
			drawFunctions(params.Width, params.Height, rangex, rangey, params.Resolution);
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}

}