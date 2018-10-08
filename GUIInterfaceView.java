import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GUIInterfaceView extends JFrame implements ActionListener {

	private int SCREEN_HEIGHT = 250;
	private int BUTTON_AREA_HEIGHT = 500;
	private int WINDOW_WIDTH = 390;
	private int WINDOW_PADDING = 20;
	private int UI_CONTAINER_WIDTH;
	private int UI_CONTAINER_HEIGHT;

	private String[] buttonNames = {"Func","View","Calc","Graph","Table","2nd","Settings","Vars","UP","RIGHT","Alpha","Shift","User","LEFT","DOWN","Menu","Matrix","Lists","Del","Clear","x^-1","sin","cos","tan","^","x^2",",","(",")","/","10^x","7","8","9","*","e^x","4","5","6","-","->","1","2","3","+","On","0",".","(-)","Enter"};
	private String[] buttonNamesAlpha = {"Func","View","Calc","Graph","Table","2nd","Settings","Vars","UP","RIGHT","Alpha","Shift","User","LEFT","DOWN","Menu","Matrix","Lists","Del","Clear","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","On","z","Space","(-)","Enter"};
	private String[] buttonNamesAlphaShift = {"Func","View","Calc","Graph","Table","2nd","Settings","Vars","UP","RIGHT","Alpha","Shift","User","LEFT","DOWN","Menu","Matrix","Lists","Del","Clear","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","On","Z","Space","(-)","Enter"};
	private String[] buttonNamesSecond = {"Func","View","Calc","Graph","Table","2nd","Settings","Vars","UP","RIGHT","Alpha","Shift","User","LEFT","DOWN","Menu","Matrix","Lists","Ins","Clear","i","sin^-1","cos^-1","tan^-1","baseroot","sqrt",",","[","]","/","log","7","8","9","*","ln","4","5","6","-","->","1","2","3","+","On","0",".","(-)","Solve"};
	private JButton[] buttons;

	private String screenText = "";
	private JTextArea screen = new JTextArea();

	public static void main(String args[]) {
		JFrame f = new GUIInterfaceView();
		f.setVisible(true);
	}
	
	public GUIInterfaceView() {
		// compute width
		UI_CONTAINER_WIDTH = WINDOW_WIDTH - (2*WINDOW_PADDING);
		UI_CONTAINER_HEIGHT = SCREEN_HEIGHT + BUTTON_AREA_HEIGHT;

		// set JFrame layout
		setLocation(750, 50);
		setTitle("Calculator Prototype Interface");
		setLayout(null);
		setSize((UI_CONTAINER_WIDTH + (WINDOW_PADDING*2)), (UI_CONTAINER_HEIGHT + (WINDOW_PADDING*2)));
		setResizable(false);

		// create simulated screen
		screen.setLocation(20,10);
		screen.setSize(UI_CONTAINER_WIDTH, SCREEN_HEIGHT);
		screen.setEditable(false);
		this.add(screen);

		// initialize buttons and create grid
		JPanel buttonGrid = new JPanel(new GridLayout(10,5));
		buttons = new JButton[buttonNames.length];
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			System.out.println("Button created: " + buttons[i].getText());

			buttonGrid.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		buttonGrid.setLocation(WINDOW_PADDING, (WINDOW_PADDING+SCREEN_HEIGHT));
		buttonGrid.setSize(UI_CONTAINER_WIDTH, BUTTON_AREA_HEIGHT);
		this.add(buttonGrid);

		// catch the windowClosing event to quit the application
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}
	
	public void actionPerformed(ActionEvent e) {
		boolean buttonFoundFlag = false;
		String buttonInput = "";
		for(int i = 0; (i < buttons.length) && (!buttonFoundFlag); i++) {
			if(e.getSource() == buttons[i]) {
				buttonFoundFlag = true;
				if(CalculatorGlobals.sharedInstance.isAlpha()) {
					if(CalculatorGlobals.sharedInstance.isShift()) {
						buttonInput = buttonNamesAlphaShift[i];
					} else {
						buttonInput = buttonNamesAlpha[i];
					}
				} else if(CalculatorGlobals.sharedInstance.isSecond()) {
					buttonInput = buttonNamesSecond[i];
				} else {
					buttonInput = buttonNames[i];
				}
			}
		}
		if(!buttonFoundFlag)
			System.out.println("ERROR: Button pressed and not handled in actionPerformed()");

		System.out.println("Button pressed: " + buttonInput);

		switch(buttonInput) {
			case "2nd":		toggleSecond(); break;
			case "Alpha": 	toggleAlpha(); break;
			case "Shift":	toggleShift(); break;
			case "Clear":	screenText = ""; break;
			case ",":
			case "(":
			case ")":
			case "+":
			case "-":
			case "*":
			case "/":
			case "A":
			case "B":
			case "C":
			case "D":
			case "E":
			case "F":
			case "G":
			case "H":
			case "I":
			case "J":
			case "K":
			case "L":
			case "M":
			case "N":
			case "O":
			case "P":
			case "Q":
			case "R":
			case "S":
			case "T":
			case "U":
			case "V":
			case "W":
			case "X":
			case "Y":
			case "Z":
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":		screenText = screenText + buttonInput; break;
			default:		System.out.println("ERROR: default case reached in actionPerformed switch");
		}

		screen.setText(screenText);
	}

	public void toggleAlpha() {
		if(CalculatorGlobals.sharedInstance.isAlpha()) {
			for(int i = 0; i < buttons.length; i++) {
				buttons[i].setText(buttonNames[i]);
			}
			CalculatorGlobals.sharedInstance.setAlpha(false);
			CalculatorGlobals.sharedInstance.setSecond(false);
			CalculatorGlobals.sharedInstance.setShift(false);
		} else {
			if(CalculatorGlobals.sharedInstance.isShift()) {
				for(int i = 0; i < buttons.length; i++) {
					buttons[i].setText(buttonNamesAlphaShift[i]);
				}
			} else {
				for(int i = 0; i < buttons.length; i++) {
					buttons[i].setText(buttonNamesAlpha[i]);
				}
			}
			CalculatorGlobals.sharedInstance.setAlpha(true);
			CalculatorGlobals.sharedInstance.setSecond(false);
		}
	}

	public void toggleShift() {
		if(CalculatorGlobals.sharedInstance.isAlpha()) {
			if(CalculatorGlobals.sharedInstance.isShift()) {
				for(int i = 0; i < buttons.length; i++) {
					buttons[i].setText(buttonNamesAlpha[i]);
				}
				CalculatorGlobals.sharedInstance.setShift(false);
			} else {
				for(int i = 0; i < buttons.length; i++) {
					buttons[i].setText(buttonNamesAlphaShift[i]);
				}
				CalculatorGlobals.sharedInstance.setShift(true);
			}
		}
	}

	public void toggleSecond() {
		if(CalculatorGlobals.sharedInstance.isSecond()) {
			for(int i = 0; i < buttons.length; i++) {
				buttons[i].setText(buttonNames[i]);
			}
			CalculatorGlobals.sharedInstance.setAlpha(false);
			CalculatorGlobals.sharedInstance.setSecond(false);
		} else {
			for(int i = 0; i < buttons.length; i++) {
				buttons[i].setText(buttonNamesSecond[i]);
			}
			CalculatorGlobals.sharedInstance.setAlpha(false);
			CalculatorGlobals.sharedInstance.setSecond(true);
		}
		CalculatorGlobals.sharedInstance.setShift(false);
	}
}