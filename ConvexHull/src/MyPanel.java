import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private int diameter;
	private Point[] P_list;
	private String methodName;
	private int runtime;

	public MyPanel(Point[] P_list) {
		// set diameter of the dots
		this.diameter = 5;
		this.P_list = P_list;
		this.methodName = null;
		this.runtime = 0;
	}

	/**
	 * this function do the drawing
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLACK);
		int radius = diameter / 2;
		for (int i = 0; i < P_list.length; i++) {
			Point p = P_list[i];
			g.fillOval(p.x - radius, p.y - radius, diameter, diameter);
		}
		if (this.methodName == null) {
			System.out
					.println("please choose the method ('brutal' or 'quick'):");
			Scanner read = new Scanner(System.in);
			this.methodName = read.next();
			read.close();
		}
		if (this.runtime < 1) {
			if (methodName.equals("brutal")) {
				System.out.println("brutal method starts to run");
				ConvexHull(g);
			} else if (methodName.equals("quick")) {
				System.out.println("quickhull method starts to run");
				QuickHull(g);
			} else {
				throw new IllegalArgumentException("invalid method name");
			}
			runtime++;
			System.out.println("run complete");
		}

	}

	/**
	 * this is the brutal force method to find the convex hull
	 * 
	 * @param g
	 */
	public void ConvexHull(Graphics g) {
		final long startTime = System.currentTimeMillis();
		g.setColor(Color.RED);
		for (int i = 0; i < this.P_list.length; i++) {
			for (int j = i + 1; j < this.P_list.length; j++) {
				Point p1 = P_list[i];
				Point p2 = P_list[j];
				if (checkExtremePoints(p1, p2)) {
					// System.out.println("point 1: "+p1);
					g.drawLine(p1.x, p1.y, p2.x, p2.y);
				}
			}
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime)
				+ "milliseconds");
	}

	/**
	 * this function checks if two points are extremely points
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean checkExtremePoints(Point p1, Point p2) {
		int a = p2.y - p1.y;
		int b = p1.x - p2.x;
		int c = p1.x * p2.y - p1.y * p2.x;
		int countPos = 0;
		int countNeg = 0;
		for (Point p : P_list) {
			if (a * p.x + b * p.y >= c) {
				countPos = countPos + 1;
			}
			if (a * p.x + b * p.y <= c) {
				countNeg++;
			}
		}
		return countPos == P_list.length || countNeg == P_list.length;
	}

	/**
	 * this is the quick hull method
	 * 
	 * @param g
	 */
	public void QuickHull(Graphics g) {
		final long startTime = System.currentTimeMillis();
		g.setColor(Color.RED);
		Point left = findLeftest();
		Point right = findRightest();
		ArrayList pList = new ArrayList<Point>();
		for (Point p : this.P_list) {
			pList.add(p);
		}
		// construct the upper hull
		upperHull(g, left, right, pList);
		// construct the lower hull with revered left and right
		upperHull(g, right, left, pList);
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime)
				+ "milliseconds");
	}

	/**
	 * construct the upper hull
	 * 
	 * @param g
	 * @param left
	 * @param right
	 * @param pList
	 */
	public void upperHull(Graphics g, Point left, Point right,
			ArrayList<Point> pList) {
		ArrayList<Point> leftSet = new ArrayList<Point>();
		double minArea = 0;
		Point minPoint = left;
		for (Point p : pList) {
			double area = calculateArea(left, right, p);
			if (area < 0) {
				leftSet.add(p);
				if (minArea > area) {
					minArea = area;
					minPoint = p;
				}
			}
		}
		if (leftSet.isEmpty()) {
			g.drawLine(left.x, left.y, right.x, right.y);
			return;
		}
		upperHull(g, left, minPoint, leftSet);
		upperHull(g, minPoint, right, leftSet);
	}

	/**
	 * calculate the area of the triangle with 3 points
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return
	 */
	public double calculateArea(Point p1, Point p2, Point p3) {
		double area = (p1.x * p2.y + p3.x * p1.y + p2.x * p3.y - p3.x * p2.y
				- p2.x * p1.y - p1.x * p3.y) / (2.0);
		return area;
	}

	/**
	 * find and return the leftest point of the frame
	 * 
	 * @return
	 */
	public Point findLeftest() {
		Point leftest = this.P_list[0];
		for (Point p : this.P_list) {
			if (leftest.x > p.x) {
				leftest = p;
			}
		}
		return leftest;
	}

	/**
	 * find and return the rightest point on the frame
	 * 
	 * @return
	 */
	public Point findRightest() {
		Point rightest = this.P_list[0];
		for (Point p : this.P_list) {
			if (rightest.x < p.x) {
				rightest = p;
			}
		}
		return rightest;
	}

}
