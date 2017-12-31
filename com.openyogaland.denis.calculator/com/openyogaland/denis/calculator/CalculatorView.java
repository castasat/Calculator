package com.openyogaland.denis.calculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Calculator GUI
class CalculatorView extends JFrame
{
    // constants
    // . views
    public static final int NUMBERS                                        = 1;
    public static final int NUMBERS_WITH_DOT_AND_SIGN                      = 2;
    public static final int BASIC_OPERATIONS                               = 3;
    public static final int NUMBERS_WITH_DOT_SIGN_AND_BASIC_OPERATIONS     = 4;
    public static final int EXTENDED_OPERATIONS                            = 5;
    public static final int NUMBERS_WITH_DOT_SIGN_AND_EXTENDED_OPERATIONS  = 6;
    public static final int OPERATIONS_AND_FUNCTIONS                       = 7;
    public static final int NUMBERS_WITH_DOT_SIGN_OPERATIONS_AND_FUNCTIONS = 8;
    private static final long serialVersionUID = -1898655891982170227L;

    // fields  
    JPanel mainPanel         = null;
    JPanel buttonsPanel      = null;
    BorderLayout mainLayout  = null;
    GridLayout buttonsLayout = null;
    JTextField textField     = null;
    JButton buttons[]        = null;
    String buttonNames[]     = null;
    int buttonRows           = 4; // default
    int buttonColumns        = 6; // default

    // methods
    // . constructor
    public CalculatorView(int whatViewToChoose, Calculator calculatorController)
    {
        // frame settings
    	setTitle(CalculatorModel.TITLE);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // main panel
    	mainLayout = new BorderLayout();
    	mainPanel  = new JPanel();
    	mainPanel.setLayout(mainLayout);  	
    	// text field
    	textField = new JTextField();
    	textField.setHorizontalAlignment(JTextField.RIGHT);
        // buttons panel
        // . choosing button names from view
        if (whatViewToChoose == NUMBERS || whatViewToChoose == NUMBERS_WITH_DOT_AND_SIGN || 
        	whatViewToChoose == BASIC_OPERATIONS || whatViewToChoose == NUMBERS_WITH_DOT_SIGN_AND_BASIC_OPERATIONS || 
        	whatViewToChoose == EXTENDED_OPERATIONS || whatViewToChoose == NUMBERS_WITH_DOT_SIGN_AND_EXTENDED_OPERATIONS ||
        	whatViewToChoose == OPERATIONS_AND_FUNCTIONS || whatViewToChoose == NUMBERS_WITH_DOT_SIGN_OPERATIONS_AND_FUNCTIONS)
        {
        	buttonNames = chooseView(whatViewToChoose);
        }
        else
        {
        	buttonNames = chooseView(NUMBERS_WITH_DOT_SIGN_OPERATIONS_AND_FUNCTIONS);
        }
        buttons = new JButton[buttonNames.length];
    	buttonsLayout = new GridLayout(buttonRows, buttonColumns);
        buttonsPanel  = new JPanel();
        buttonsPanel.setLayout(buttonsLayout);
        // adding buttons to button panel
        for (int i = 0; i < buttonNames.length; i++)
        {
        	buttons[i] = new JButton(buttonNames[i]);
        	buttons[i].addActionListener(calculatorController);
        	buttonsPanel.add(buttons[i]);
        }
        // adding text field and buttons panel to main panel
    	mainPanel.add(BorderLayout.NORTH, textField);
        mainPanel.add(BorderLayout.CENTER, buttonsPanel);
        // frame settings
        setContentPane(mainPanel);
    	pack();
        setResizable(false);
        setVisible(true);
    }

    // . choose View
    private String[] chooseView(int whatViewToChoose)
    {
   	  	switch (whatViewToChoose)
   	  	{
   	  		case NUMBERS:
   		        return chooseNumbersView();
   		    case NUMBERS_WITH_DOT_AND_SIGN:
   		        return chooseNumbersWithDotAndSignView();
   		    case BASIC_OPERATIONS:
   		        return chooseBasicOperationsView();
   		    case NUMBERS_WITH_DOT_SIGN_AND_BASIC_OPERATIONS:
   		        return chooseNumbersWithDotSignAndBasicOperationsView();
   		    case EXTENDED_OPERATIONS:
   		        return chooseExtendedOperationsView();
   		    case NUMBERS_WITH_DOT_SIGN_AND_EXTENDED_OPERATIONS:
   		        return chooseNumbersWithDotSignAndExtendedJperationsView();
   		    case OPERATIONS_AND_FUNCTIONS:
   		        return chooseOperationsAndFunctionsView();
   		    case NUMBERS_WITH_DOT_SIGN_OPERATIONS_AND_FUNCTIONS:
   		        return chooseNumbersWithDotSignOperationsAndFunctionsView();
   		}
   		return chooseNumbersWithDotSignOperationsAndFunctionsView();
    }

    // . set number of rows and columns of buttons in chosen view
    private void setButtonRowsColumns(int rows, int columns)
    {
    	if (rows != 0 && columns != 0)
        {
        	buttonRows    = rows;
        	buttonColumns = columns;
        }
        else 
    	{
    		buttonRows    = 4; // default
        	buttonColumns = 6; // default
    	}
    }

    // . choose Numbers
    // . . "7" "8" "9"
    // . . "4" "5" "6"
    // . . "1" "2" "3"
    // . . "0" 
    private String[] chooseNumbersView()
    {
    	setButtonRowsColumns(4, 3);
    	String[] numbersView = 
    	{
    		CalculatorModel.SEVEN, CalculatorModel.EIGHT, CalculatorModel.NINE,
    		CalculatorModel.FOUR,  CalculatorModel.FIVE,  CalculatorModel.SIX,
    		CalculatorModel.ONE,   CalculatorModel.TWO,   CalculatorModel.THREE,
    		CalculatorModel.ZERO
    	};
    	return numbersView;
    }

    // . choose Numbers With Dot And Sign
    // . . "7"  "8"  "9"
    // . . "4"  "5"  "6"
    // . . "1"  "2"  "3"
    // . . "0"  "." "+/-"
    private String[] chooseNumbersWithDotAndSignView()
    {
        setButtonRowsColumns(4, 3);
    	String[] numbersWithDotAndSignView = 
    	{
    		CalculatorModel.SEVEN, CalculatorModel.EIGHT, CalculatorModel.NINE,
    		CalculatorModel.FOUR,  CalculatorModel.FIVE,  CalculatorModel.SIX,
    		CalculatorModel.ONE,   CalculatorModel.TWO,   CalculatorModel.THREE,
    		CalculatorModel.ZERO,  CalculatorModel.DOT,   CalculatorModel.SIGN
    	};
    	return numbersWithDotAndSignView;
    }

    // . choose Basic Operations
    // . . "+"
    // . . "-"
    // . . "*"
    // . . "/"
    private String[] chooseBasicOperationsView()
    {
        setButtonRowsColumns(4, 1);
    	String[] basicOperationsView = 
    	{
    		CalculatorModel.PLUS,
    		CalculatorModel.MINUS,
    		CalculatorModel.MULTIPLY,
    		CalculatorModel.DIVIDE
    	};
    	return basicOperationsView;
    }

    // . choose Numbers With Dot, Sign And Basic Operations
    // . . "7"  "8"  "9"  "+"
    // . . "4"  "5"  "6"  "-"
    // . . "1"  "2"  "3"  "*"
    // . . "0"  "." "+/-" "/"
    private String[] chooseNumbersWithDotSignAndBasicOperationsView()
    {
    	setButtonRowsColumns(4, 4);
    	String[] numbersWithDotSignAndBasicOperationsView = 
    	{
    		CalculatorModel.SEVEN, CalculatorModel.EIGHT, CalculatorModel.NINE,	 CalculatorModel.PLUS,
    		CalculatorModel.FOUR,  CalculatorModel.FIVE,  CalculatorModel.SIX,	 CalculatorModel.MINUS,
    		CalculatorModel.ONE,   CalculatorModel.TWO,   CalculatorModel.THREE, CalculatorModel.MULTIPLY,
    		CalculatorModel.ZERO,  CalculatorModel.DOT,   CalculatorModel.SIGN,  CalculatorModel.DIVIDE
    	};
    	return numbersWithDotSignAndBasicOperationsView;
    }

    // . choose Extended Operations
    // . . "<=" "C"
    // . .  "+" "-"
    // . .  "*" "/"
    // . .  "="
    private String[] chooseExtendedOperationsView()
    {
    	setButtonRowsColumns(4, 2);
    	String[] extendedOperationsView = 
    	{
    		CalculatorModel.BACKSPACE, CalculatorModel.CLEAR,
    		CalculatorModel.PLUS,      CalculatorModel.MINUS,
    		CalculatorModel.MULTIPLY,  CalculatorModel.DIVIDE,
    		CalculatorModel.EQUALS
    	};
    	return extendedOperationsView;
    }

    // . choose Numbers With Dot, Sign And Extended Operations
    // . . "7"  "8"  "9" "<=" "C"
    // . . "4"  "5"  "6"  "+" "-"
    // . . "1"  "2"  "3"  "*" "/"
    // . . "0"  "." "+/-" "="
    private String[] chooseNumbersWithDotSignAndExtendedJperationsView()
    {
    	setButtonRowsColumns(4, 5);
    	String[] numbersWithDotSignAndExtendedOperationsView = 
    	{
    		CalculatorModel.SEVEN, CalculatorModel.EIGHT, CalculatorModel.NINE,  CalculatorModel.BACKSPACE, CalculatorModel.CLEAR,
    		CalculatorModel.FOUR,  CalculatorModel.FIVE,  CalculatorModel.SIX,   CalculatorModel.PLUS,      CalculatorModel.MINUS,
    		CalculatorModel.ONE,   CalculatorModel.TWO,   CalculatorModel.THREE, CalculatorModel.MULTIPLY,  CalculatorModel.DIVIDE,
    		CalculatorModel.ZERO,  CalculatorModel.DOT,   CalculatorModel.SIGN,  CalculatorModel.EQUALS
    	};
    	return numbersWithDotSignAndExtendedOperationsView;
    }

    // . choose Operations And Functions
    // . .  "1/x"     "<="   "C"
    // . . "Exp(x)" "Ln(x)"  "%"
    // . .   "+"      "-"  "Sqrt(x)"
    // . .   "*"      "/"    "="
    private String[] chooseOperationsAndFunctionsView()
    {
    	setButtonRowsColumns(4, 3);
    	String[] operationsAndFunctionsView = 
    	{
    		CalculatorModel.INVERSION,   CalculatorModel.BACKSPACE,   CalculatorModel.CLEAR,
    		CalculatorModel.EXPONENTIAL, CalculatorModel.N_LOGARITHM, CalculatorModel.PERCENT,
    		CalculatorModel.PLUS,        CalculatorModel.MINUS,       CalculatorModel.SQUARE_ROOT,
    		CalculatorModel.MULTIPLY,    CalculatorModel.DIVIDE,      CalculatorModel.EQUALS
    	};
    	return operationsAndFunctionsView;
    }

    // . choose Numbers With Dot, Sign, Operations And Functions
    // . . "7"  "8"  "9"  "1/x"     "<="   "C"
    // . . "4"  "5"  "6" "Exp(x)" "Ln(x)"  "%"
    // . . "1"  "2"  "3"   "+"      "-"  "Sqrt(x)"
    // . . "0"  "." "+/-"  "*"      "/"    "="
    private String[] chooseNumbersWithDotSignOperationsAndFunctionsView()
    {
    	setButtonRowsColumns(4, 6);
    	String[] numbersWithDotSignOperationsAndFunctionsView = 
    	{
    		CalculatorModel.SEVEN, CalculatorModel.EIGHT, CalculatorModel.NINE,  CalculatorModel.INVERSION,   CalculatorModel.BACKSPACE,   CalculatorModel.CLEAR,
    		CalculatorModel.FOUR,  CalculatorModel.FIVE,  CalculatorModel.SIX,   CalculatorModel.EXPONENTIAL, CalculatorModel.N_LOGARITHM, CalculatorModel.PERCENT,
    		CalculatorModel.ONE,   CalculatorModel.TWO,   CalculatorModel.THREE, CalculatorModel.PLUS,        CalculatorModel.MINUS,       CalculatorModel.SQUARE_ROOT,
    		CalculatorModel.ZERO,  CalculatorModel.DOT,   CalculatorModel.SIGN,  CalculatorModel.MULTIPLY,    CalculatorModel.DIVIDE,      CalculatorModel.EQUALS
    	};
    	return numbersWithDotSignOperationsAndFunctionsView;
    }    
}