package adder;


// Import classes needed for appearance
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
// TODO: Import additional classes needed for event handling
// import container for all of the buttons.


/**
 * A simple calculator that only does addition of non-negative numbers.
 * 
 *  TODO
 * @author Claude Anderson and Ruinan(Victor) Zhang. Created Oct 12, 2011.
 */
public class AdderMain {


	@SuppressWarnings("unused")  // You will write code to use it, then this suppression will be unnecessary.
	private static long sum=0;
	private static String d_value = "0";
	
	
	/**
	 * Set up the AdderGUI to respond to button clicks.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("hallo");
		JFrame frame = new JFrame();
		final int WIDTH = 300, HEIGHT = 450;
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Adder");

		// Create the text field that displays numbers.
		final JTextField display = new JTextField((""+0), 20);
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setFont(new Font("Helvetica", Font.BOLD, 24));

		// Make a panel to hold the buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
		
		Font f = new Font("Helvetica", Font.BOLD, 48);

		// Create the top nine buttons (1-9) and add them to the panel.
		// label each button with a string representing the button's value.
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				int button_number = 7 - 3*row + col;
				JButton b = new JButton(7 - 3*row + col + "");
				b.setFont(f);
				buttonList.add(b);
				buttonPanel.add(b);
			}
		}
		// Create and add the bottom three buttons.
		String[] remainingButtonLabels = {"C", "0", "+"};
		for (String s : remainingButtonLabels) {
			JButton b = new JButton(s);
			b.setFont(f);
			buttonList.add(b);
			buttonPanel.add(b);
		}
		
	
		// Add both components to the frame.
		frame.add(buttonPanel);
		frame.add(display, BorderLayout.NORTH);
		frame.add(buttonPanel);
		
		// TODO:  Add the rest of the code needed to get a working "Adding calculator":
		// Most of the additional code (but not all) can be written below this point)
			// 1.  Pressing C should set both the displayed value and the sum to 0.
		    // 2.  Pressing + adds the current displayed value to the sum and displays the new sum.
			// 3. Pressing a number key works like on a real calculator. 
		    //    The value of the number represented by the sequence of key presses is displayed on the screen. 
		
		/* Example of a sequence of button presses and what should be displayed after each:
		 * 
		 *       0  (initial display before any buttons are pressed)
		 *  2    2
		 *  3    23
		 *  +    23
		 *  5    5
		 *  6    56
		 *  +    79
		 *  0    0
		 *  0    0
		 *  1    1
		 *  8    18
		 *  C    0
		 *  3    3
		 *  +    3
		 *  2    2
		 *  +    5
		 *  
		 */
		
		class ListenToSeven implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+7;
				display.setText(d_value);
			}
		}
		
		class ListenToEight implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+8;
				display.setText(d_value);
			}
		}
		
		class ListenToFour implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+4;
				display.setText(d_value);
			}
		}
		
		class ListenToFive implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+5;
				display.setText(d_value);
			}
		}
		
		class ListenToSix implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+6;
				display.setText(d_value);
			}
		}
		
		class ListenToOne implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+1;
				display.setText(d_value);
			}
		}
		
		class ListenToTwo implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+2;
				display.setText(d_value);
			}
		}
		
		class ListenToThree implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+3;
				display.setText(d_value);
			}
		}
		
		class ListenToNine implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+9;
				display.setText(d_value);
			}
		}
		
		class ListenToZero implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Double.parseDouble(d_value)==0){
					d_value = "";
				}
				d_value = d_value+0;
				display.setText(d_value);
			}
		}
		
		class ListenToAdd implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String dis = display.getText();
				d_value = "0";
				sum = sum + Long.parseLong(dis);
				display.setText(""+sum);
			}
		}
		
		class ListenToC implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				sum = 0;
				display.setText(""+0);
			}
		}
		
		// add action listenner for all keys according to their funcitons
		System.out.println(buttonList.get(11).getText());
		buttonList.get(0).addActionListener(new ListenToSeven());
		buttonList.get(1).addActionListener(new ListenToEight());
		buttonList.get(2).addActionListener(new ListenToNine());
		buttonList.get(3).addActionListener(new ListenToFour());
		buttonList.get(4).addActionListener(new ListenToFive());
		buttonList.get(5).addActionListener(new ListenToSix());
		buttonList.get(6).addActionListener(new ListenToOne());
		buttonList.get(7).addActionListener(new ListenToTwo());
		buttonList.get(8).addActionListener(new ListenToThree());
		buttonList.get(9).addActionListener(new ListenToC());
		buttonList.get(10).addActionListener(new ListenToZero());
		buttonList.get(11).addActionListener(new ListenToAdd());
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
	}
}




