import java.awt.Dimension;
import java.awt.List;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Visualizer (main)
 * @author ruinanzhang
 *
 */
public class Visualizer {
	
	public ArrayList<Integer> X_list,Y_list;
	
	public static void main(String[] args) throws IOException {
		new Visualizer();
	}
	
	/**
	 * constructor
	 * @throws IOException
	 */
	public Visualizer() throws IOException{
		this.X_list = new ArrayList<Integer>();
		this.Y_list = new ArrayList<Integer>();
		
		Point[] P_list = readCoordinates();
		
		JFrame frame = new JFrame();
		final int width = 1920, height = 1080;
		frame.setSize(width, height);
		frame.setTitle("QuickHull");
		
		JPanel panel = new MyPanel(P_list);
		panel.setSize(width, height);
		frame.add(panel);
		frame.setTitle("graphy of Quick Hull");
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * read coordinates from text file
	 * return the coordinates in an arraylist of Points
	 * @return
	 * @throws IOException
	 */
	public Point[] readCoordinates() throws IOException{
		Scanner reader = new Scanner(System.in);
		System.out.println("Please Enter the file name (with .txt at the ending):");
		String fileName = reader.next();
		File file = new File(fileName);
		if (file == null || !file.canRead()) {
	        throw new IllegalArgumentException("file not readable: " + file);
	    }
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(",");
            line = br.readLine();
        }
        String doc = sb.toString();
        
        String[] s_list = doc.split(",");
        int size = s_list.length/2;     
        Point[] P_list = new Point[size];
        
        for (int i = 0; i < s_list.length;i++){
        	String s = s_list[i];
        	s=s.replace("(", "");
        	s=s.replace(")", "");
        	s=s.replace(" ", "");
        	s=s.replace("\n","");
        	if (i%2==0){
        		this.X_list.add(Integer.parseInt(s));
        	}else{
        		this.Y_list.add(Integer.parseInt(s));
        	}
        }
        
        
        for (int i =0;i<size;i++){
        	P_list[i]=new Point(X_list.get(i),Y_list.get(i));
        }
        return P_list;	
	}
}





	


