package twodtree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Stack;

/**
 * A 2D Tree implementation.
 * 
 * @author Matt Boutell and TODO: You!
 */
public class TwoDTree {
	/*
	 * TODO: Directions: Implement the methods below. See the specifications for
	 * details.
	 */
	private Node root;
	private Point2 nearestFound = null;
	public final Node NULL_NODE = new Node();

	/** For drawing the plane. */
	public static final double DOT_RADIUS = 5;
	private int planePanelWidth;
	private int planePanelHeight;

	// For drawing the tree
	private static final int MARGIN = 30;
	private static final double RADIUS_SCALE_FACTOR = 0.75;
	private static final double FONT_SCALE_FACTOR = 1.5;
	private static final double DIRECTION_TYPE_SCALE_FACTOR = 1.1;
	private int treePanelWidth;
	private int treePanelHeight;
	// The number of pixels horizontally and vertically between nodes.
	private int xStep, yStep;
	private double radius;
	// font to use for labeling nodes
	private Font font;
	private int fontSize;

	/**
	 * Constructs an empty tree.
	 * 
	 */
	public TwoDTree() {
		this(0, 0, 0, 0); // When called within params, it won't be visualized
	}

	/**
	 * Constructs an empty tree.
	 * 
	 */
	public TwoDTree(int planePanelWidth, int planePanelHeight, int treePanelWidth, int treePanelHeight) {
		root = NULL_NODE;
		this.planePanelWidth = planePanelWidth;
		this.planePanelHeight = planePanelHeight;
		this.treePanelWidth = treePanelWidth;
		this.treePanelHeight = treePanelHeight;
	}

	/**
	 * Inserts the given point into the tree
	 * 
	 * @param p
	 *            A point to insert.
	 */
	public void insert(Point2 p, String label) {
		// TODO: write this.
		if (root==NULL_NODE){
			root = new Node(p);
			root.label=label;
			root.dir= Direction.X;
			root.depth=0;
		}else{
			root.insert(p,label);
		}
	}

	/**
	 * Checks to see if the given query point is in this tree.
	 * 
	 * @param q
	 *            Query point.
	 * @return True if and only if the query point is in this tree.
	 * 
	 */
	public boolean contains(Point2 q) {
		// TODO: write this.
		return this.root.contains(q);
		
		
	}

	/**
	 * Finds the closest point in the tree to the query point.
	 * 
	 * @param q
	 *            The query point
	 * @throws IllegalStateException.
	 *             If the tree is empty.
	 * @return The closest point
	 */
	public Point2 nearestNeighbor(Point2 q) throws IllegalStateException {
		// TODO: write this.
		Point2 best = this.root.p;
		Point2 UL = new Point2(0,0);
		Point2 BR = new Point2(1,1);
		
		// this stack is used to store the node traversed
		Stack<Node> s = new Stack<Node>();
		
		// this is the nearest point from a direct traverse
		best = findDirectNeightbor(this.root,q,best,UL,BR,s);
		
		// cheeck for special cases
		while(!s.isEmpty()){
			Node Ncheck = s.pop();
			// if the rectangle to the q point distance smaller 
			// than the best point found, check for it's children
			if (Ncheck.bounds.distanceTo(q)<best.distanceTo(q)){
				Point2 newUL = new Point2(Ncheck.bounds.xmin(),Ncheck.bounds.ymin());
				Point2 newBR = new Point2(Ncheck.bounds.xmax(),Ncheck.bounds.ymax());
				Stack<Node> newS= new Stack<Node>();
				newS.push(Ncheck);
				best = findDirectNeightbor(Ncheck,q,best,newUL,newBR,newS);	
			}

		}
		this.nearestFound=best;
		return best;
	}
	
	public Point2 findDirectNeightbor(Node n, Point2 q, Point2 best,Point2 UL, Point2 BR, Stack<Node> s){
		if (n==NULL_NODE){
			return best;
		}
		if (q.distanceTo(n.p)<q.distanceTo(best)){
			best = n.p;
		}
		if (n.dir==Direction.X){  // check direction of n
			if (q.x>n.p.x){
				if (n.topLeft!=NULL_NODE){
					n.topLeft.bounds = new RectHV(UL.x,UL.y,n.p.x,BR.y);// construct rectangle
					s.push(n.topLeft);  // push into stack for later check
				}
				UL.x=n.p.x;
				return findDirectNeightbor(n.bottomRight,q,best,UL,BR,s);
			}
			if (q.x<n.p.x){
				if (n.bottomRight!=NULL_NODE){
					n.bottomRight.bounds= new RectHV(n.p.x,UL.y,BR.x,BR.y);// construct rectangle
					s.push(n.bottomRight); // push into stack for later check
				}
				BR.x=n.p.x;
				return findDirectNeightbor(n.topLeft,q,best,UL,BR,s);
			}
		}
		
		if (n.dir==Direction.Y){   // check direction of n
			if (q.y>n.p.y){
				if (n.topLeft!=NULL_NODE){
					n.topLeft.bounds=new RectHV(UL.x,UL.y,BR.x,n.p.y);// construct rectangle
					s.push(n.topLeft); // push into stack for later check
				}
				UL.y=n.p.y;
				return findDirectNeightbor(n.bottomRight,q,best,UL,BR,s); 
			}
			if (q.y < n.p.y){
				if (n.bottomRight!=NULL_NODE){
					n.bottomRight.bounds= new RectHV(UL.x,n.p.y,BR.x,BR.y);// construct rectangle
					s.push(n.bottomRight); // push into stack for later check
				}
				BR.y=n.p.y;
				return findDirectNeightbor(n.topLeft,q,best,UL,BR,s);
			}
		}
		return best;
		
		
	}
	
	/**
	 * construct a rectangle based on n
	 * side represent the side of the rectangle to n
	 * side: false -- TopLeft;   True-- BottomRight
	 * @param n
	 * @param side
	 * @param UL
	 * @param BR
	 * @return
	 */
	public RectHV constructRect(Node n,boolean side, Point2 UL, Point2 BR){
		// if n's direction is X
		if (n.dir==Direction.X){
			if (!side){
				BR.x=n.p.x;
			}else{
				UL.x=n.p.x;
			}
		}
		// if n's direction is Y
		if (n.dir == Direction.Y){
			if (!side){
				BR.y=n.p.y;
			}else{
				UL.y=n.p.y;
			}
		}
		
		return new RectHV(UL.x,UL.y,BR.x,BR.y);
	}

	public void drawTree(Graphics2D g) {
		int nodeCountPlusOne = root.setInOrderNumbers(1);
		this.xStep = (this.treePanelWidth - 2 * MARGIN) / nodeCountPlusOne;
		this.yStep = (this.treePanelHeight - 2 * MARGIN) / (height() + 2);
		this.radius = ((xStep < yStep) ? xStep : yStep) * RADIUS_SCALE_FACTOR;
		this.fontSize = (int) (radius * FONT_SCALE_FACTOR * 96 / 72);
		this.font = new Font("Monospaced", Font.BOLD, fontSize);
		root.drawTree(g, -1, -1);
	}

	public void clear() {
		root = NULL_NODE;
		nearestFound = null;
	}

	@Override
	public String toString() {
		if (root == NULL_NODE) {
			return "()";
		}
		StringBuilder sb = new StringBuilder();
		root.buildString(sb);
		return sb.toString();
	}

	public void draw(Graphics2D g2, double minX, double maxX, double minY, double maxY) {
		root.drawInPlane(g2, minX, maxY, minY, maxY);

		if (nearestFound != null) {
			Ellipse2D.Double nodeDot = new Ellipse2D.Double(screenX(nearestFound.x) - DOT_RADIUS,
					screenY(nearestFound.y) - DOT_RADIUS, DOT_RADIUS * 2, DOT_RADIUS * 2);
			g2.setColor(Color.RED);
			g2.fill(nodeDot);
		}
	}

	private int screenX(double x) {
		return (int) (x * planePanelWidth);
	}

	private int screenY(double y) {
		return (int) (y * planePanelHeight);
	}

	private int height() {
		return root.height();
	}

	/**
	 * The direction of each node is given in this enumeration. The root always
	 * splits the plane depending on its point's x-coordinate, so has direction
	 * of Direction.X. This is shown using a vertical line (see node A in the
	 * screenshot in the specification) since splitting based on X splits the
	 * plane using a vertical line. The root's children split the plane
	 * depending on the y-coordinate, so will be of type Direction.Y (the
	 * horizontal lines on E and B in the screenshot).
	 */
	enum Direction {
		X, Y
	}

	public class Node {
		// The two data fields: a label and a point
		public String label;
		public Point2 p;

		// Children
		Node topLeft;
		Node bottomRight;
		
		// The enumeration above.
		public Direction dir;

		// Each node knows the bounds of the rectangle it is part of. Helpful
		// when searching. See the spec for details.
		public RectHV bounds;

		// For tree drawing
		// depth is used by drawTree below to place the nodes correctly when
		// drawing the tree.
		// You need to set it when you insert a node.
		private int depth;
		// inOrderNumber is both calculated and used by code below. You can
		// ignore it.
		private int inOrderNumber;

		// This one is used by the NULL_NODE.
		public Node() {
			// do nothing
		}

		public boolean contains(Point2 q) {
			if (this==NULL_NODE){
				return false;
			}else{
				if (this.p.x==q.x && this.p.y==q.y){
					// find node
					return true;
				}else{
					// recurse the function if dir is X
					if (this.dir==Direction.X){
						if (q.x>this.p.x){
							return this.bottomRight.contains(q);
						}
						if (q.x < this.p.x){
							return this.topLeft.contains(q);
						}
					}
					// recurse the function if dir is Y
					if (this.dir==Direction.Y){
						if (q.y>this.p.y){
							
							return this.bottomRight.contains(q);
						}
						if (q.y < this.p.y){
							
							return this.topLeft.contains(q);
						}
					}
					System.out.println("this message should never appear");
					return true;
				}
			}
			
		}

		public void insert(Point2 p2, String label2) {
			// if parent's direction is X
			if (this.dir==Direction.X){
				// adding node's x greater
				if (p2.x>this.p.x){
					if (this.bottomRight!=NULL_NODE){
						this.bottomRight.insert(p2, label2);
					}else{
						this.bottomRight =new Node(p2);
						this.bottomRight.label=label2;
						this.bottomRight.dir=Direction.Y;
						this.bottomRight.depth=this.depth+1;
						return;
					}	
				}
				
				if (p2.x<this.p.x){
					if (this.topLeft!=NULL_NODE){
						this.topLeft.insert(p2, label2);
					}else{
						this.topLeft=new Node(p2);
						this.topLeft.label= label2;
						this.topLeft.dir=Direction.Y;
						this.topLeft.depth=this.depth+1;
						return;
					}
				}
			}
			
			if (this.dir==Direction.Y){
				if (p2.y>this.p.y){
					if (this.bottomRight!=NULL_NODE){
						this.bottomRight.insert(p2, label2);
					}else{
						this.bottomRight=new Node(p2);
						this.bottomRight.label=label2;
						this.bottomRight.dir=Direction.X;
						this.bottomRight.depth=this.depth+1;
						return;
					}
				}
				
				if (p2.y<this.p.y){
					if(this.topLeft!=NULL_NODE){
						this.topLeft.insert(p2, label2);
					}else{
						this.topLeft=new Node(p2);
						this.topLeft.label= label2;
						this.topLeft.dir=Direction.X;
						this.topLeft.depth=this.depth+1;
						return;
					}
				}
			}
		}
		
		

		public Node(Point2 p) {
			if (p == null) {
				return;
			}
			this.p = new Point2(p);
			this.topLeft = NULL_NODE;
			this.bottomRight = NULL_NODE;
			this.bounds = null;
		}

		// You will probably use this when writing insert()
		public Node(double x, double y, String label, Direction dir, RectHV bounds, int depth) {
			this.p = new Point2(x, y);
			this.label = label;
			this.dir = dir;
			this.topLeft = NULL_NODE;
			this.bottomRight = NULL_NODE;
			this.bounds = bounds;
			this.depth = depth;
		}

		private void buildString(StringBuilder sb) {
			if (this == NULL_NODE) {
				return;
			}
			if (topLeft != NULL_NODE) {
				sb.append("(");
				topLeft.buildString(sb);
				sb.append(")");
			}
			sb.append(String.format("%s(%4.2f,%4.2f)", label, p.x, p.y));
			if (bottomRight != NULL_NODE) {
				sb.append("(");
				bottomRight.buildString(sb);
				sb.append(")");
			}
		}

		private void drawInPlane(Graphics2D g2, double minX, double maxX, double minY, double maxY) {
			if (this == NULL_NODE) {
				return;
			}

			// Dot
			Ellipse2D.Double nodeDot = new Ellipse2D.Double(screenX(p.x) - DOT_RADIUS, screenY(p.y) - DOT_RADIUS,
					DOT_RADIUS * 2, DOT_RADIUS * 2);
			g2.fill(nodeDot);

			// Label
			int xOffset = this.dir == Direction.X ? 10 : 0;
			int yOffset = this.dir == Direction.X ? 0 : 20;
			g2.drawString(label, (int) screenX(p.x) + xOffset, (int) screenY(p.y) + yOffset);

			if (dir == Direction.X) {
				// Draw vertical line from (x, minY) to (x, maxY)
				Line2D.Double line = new Line2D.Double(new Point2D.Double(screenX(p.x), screenY(minY)),
						new Point2D.Double(screenX(p.x), screenY(maxY)));
				g2.draw(line);
				topLeft.drawInPlane(g2, minX, p.x, minY, maxY);
				bottomRight.drawInPlane(g2, p.x, maxX, minY, maxY);
			} else {
				// VERTICAL separation, so draw horizontal line
				Line2D.Double line = new Line2D.Double(new Point2D.Double(screenX(minX), screenY(p.y)),
						new Point2D.Double(screenX(maxX), screenY(p.y)));
				g2.draw(line);
				topLeft.drawInPlane(g2, minX, maxX, minY, p.y);
				bottomRight.drawInPlane(g2, minX, maxX, p.y, maxY);
			}
		}

		private void drawTree(Graphics2D g, double parentX, double parentY) {
			if (this == NULL_NODE) {
				return;
			}

			double centerX = this.inOrderNumber * xStep + MARGIN;
			double centerY = (this.depth + 1) * yStep + MARGIN;

			if (parentX > 0 && parentY > 0) {
				// Draws line
				g.setColor(Color.BLACK);
				double deltaX = parentX - centerX;
				double deltaY = parentY - centerY;
				double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				double xOffset = deltaX * radius / distance;
				double yOffset = deltaY * radius / distance;
				Point2D.Double selfEdge = new Point2D.Double(centerX + xOffset, centerY + yOffset);
				Point2D.Double parentEdge = new Point2D.Double(parentX - xOffset, parentY - yOffset);
				g.draw(new Line2D.Double(selfEdge, parentEdge));
			}

			// Draws the circle
			Ellipse2D.Double circ = new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
			g.setColor(Color.WHITE);
			g.fill(circ);
			g.setColor(Color.BLACK);
			g.draw(circ);

			// Labels the circle
			g.setFont(font);
			// coefficients on radius determined experimentally
			g.drawString(label.toString(), (int) (centerX - 0.5 * radius), (int) (centerY + 0.6 * radius));

			// Direction
			Point2D.Double first = new Point2D.Double(centerX, centerY);
			Point2D.Double second = new Point2D.Double(centerX, centerY);
			if (dir == Direction.X) {
				first.y -= radius * DIRECTION_TYPE_SCALE_FACTOR;
				second.y += radius * DIRECTION_TYPE_SCALE_FACTOR;
			} else {
				first.x -= radius * DIRECTION_TYPE_SCALE_FACTOR;
				second.x += radius * DIRECTION_TYPE_SCALE_FACTOR;
			}
			g.setStroke(new BasicStroke(2));
			g.draw(new Line2D.Double(first, second));

			// Draw children
			topLeft.drawTree(g, centerX, centerY);
			bottomRight.drawTree(g, centerX, centerY);
		}

		// The next two are helpers for the drawTree.
		private int height() {
			if (this == NULL_NODE) {
				return -1;
			}
			return Math.max(topLeft.height(), bottomRight.height()) + 1;
		}

		private int setInOrderNumbers(int nextNumber) {
			if (this == NULL_NODE) {
				return nextNumber;
			}
			nextNumber = topLeft.setInOrderNumbers(nextNumber);
			this.inOrderNumber = nextNumber++;
			return bottomRight.setInOrderNumbers(nextNumber);
		}
	}
}