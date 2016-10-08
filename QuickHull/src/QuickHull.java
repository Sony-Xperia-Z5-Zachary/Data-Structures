import java.awt.Dimension;
import java.awt.List;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import twodtree.Visualizer;


public class QuickHull {
	public static void main(String[] args) {
		new QuickHull();
	}
	
	public QuickHull(){
		System.out.println("hallo world");
	}
	
//	public static void main(String[] args){
//		System.out.println("hallo");
//		JFrame frame = new JFrame();
//		final int width = 500, height = 300;
//		frame.setSize(width, height);
//		frame.setTitle("QuickHull");
//		
//		JPanel panel = new MyPanel();
//		panel.setSize(width, height);
//		frame.add(panel);
//		
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		
////		String file_name = "25.txt";
////		ArrayList l = getPositions(file_name);
//	}
	
	
//	private static ArrayList<Point> getPositions(String filename)
//	        throws FileNotFoundException, IOException {
//		File file = new File(filename);
//	    if (file == null || !file.canRead()) {
//	        throw new IllegalArgumentException("file not readable: " + file);
//	    }
//
//	    final Scanner s = new Scanner(file).useDelimiter("\\(|\\||\\)?");
//	    final ArrayList<Point> positions = new ArrayList<Point>();
//	    while (s.hasNext()) {
//	    	Point p = new Point();
//	    	p.x=s.nextInt();
//	    	p.y=s.nextInt();
//	        positions.add(p);
//	        s.nextLine(); 
//	    }
//
//	    return positions;
//	}
	
	
}
