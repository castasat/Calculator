package com.openyogaland.denis.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;

// controller
public class Calculator implements ActionListener
{
    // fields
    double numberInMemory         = 0;
    double currentNumber          = 0;
    String currentArgument        = "";
    String choosedOperation       = "";
    CalculatorView calculatorView = null;

	// methods
	// . main()
	public static void main(String[] args)
	{
		Calculator calculator = new Calculator();
	}

	// . constructor
	public Calculator()
	{
		calculatorView = new CalculatorView(CalculatorView.NUMBERS_WITH_DOT_SIGN_OPERATIONS_AND_FUNCTIONS, this);
	}
    
    // . actionPerformed() from ActionListener interface
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
        String buttonPressed = "";
        Object eventSource   = actionEvent.getSource();

        if(eventSource instanceof JButton)
        {
        	buttonPressed = ((AbstractButton) eventSource).getText();
        	switch(buttonPressed)
		    {		
		    	// in case of any number
		    	case CalculatorModel.ZERO:
			    case CalculatorModel.ONE:
			    case CalculatorModel.TWO:
			    case CalculatorModel.THREE:
			    case CalculatorModel.FOUR:
			    case CalculatorModel.FIVE:
			    case CalculatorModel.SIX:
			    case CalculatorModel.SEVEN:
			    case CalculatorModel.EIGHT:
			    case CalculatorModel.NINE:
			        // check current argument and add number to it's end
			        currentArgument = calculatorView.textField.getText();
			        currentArgument += buttonPressed;
			        calculatorView.textField.setText(currentArgument);
				    break;

			    case CalculatorModel.INVERSION:
			        currentArgument = calculatorView.textField.getText();
			        try
			        {
			        	currentNumber = Double.parseDouble(currentArgument);
			        	currentNumber = 1 / currentNumber;
			        	calculatorView.textField.setText("" + currentNumber);
			        }
			        catch(NumberFormatException e)
			        {
			        	calculatorView.textField.setText("NaN");
			        }
				    break;

			    case CalculatorModel.EXPONENTIAL:
			        currentArgument = calculatorView.textField.getText();
			        try
			        {
			        	currentNumber   = Double.parseDouble(currentArgument);
			            currentNumber   = Math.exp(currentNumber);
			            calculatorView.textField.setText("" + currentNumber);
			        }
			        catch(NumberFormatException e)
			        {
			        	calculatorView.textField.setText("NaN");
			        }
				    break;

			    case CalculatorModel.N_LOGARITHM:
			        currentArgument = calculatorView.textField.getText();
			        try
			        {
			        	currentNumber   = Double.parseDouble(currentArgument);
			            currentNumber   = Math.log(currentNumber);
			            calculatorView.textField.setText("" + currentNumber);
			        }
			        catch(NumberFormatException e)
			        {
			        	calculatorView.textField.setText("NaN");
			        }				    
				    break;

			    case CalculatorModel.PERCENT:
			        try
			        {
			            currentNumber = (numberInMemory == 0) ? 0 : Double.parseDouble(currentArgument);
			            currentNumber *= (numberInMemory / 100);
			            calculatorView.textField.setText("" + currentNumber);
			        }
			        catch(NumberFormatException e)
			        {
			        	calculatorView.textField.setText("NaN");
			        }		
				    break;

			    case CalculatorModel.SQUARE_ROOT:
			        currentArgument = calculatorView.textField.getText();
			        try
			        {
			            currentNumber = Double.parseDouble(currentArgument);
			            currentNumber = Math.sqrt(currentNumber);
			            calculatorView.textField.setText("" + currentNumber);
			        }
			        catch(NumberFormatException e)
			        {
			        	calculatorView.textField.setText("NaN");
			        }			    
				    break;

			    case CalculatorModel.SIGN:
			        // check current argument and change sign
			        currentArgument = calculatorView.textField.getText();
			        // if minus is the first symbol in current argument
			        if (currentArgument.indexOf('-') == 0)
			        {
			        	currentArgument = currentArgument.substring(1);
			        }
			        else
			        {
			        	currentArgument = CalculatorModel.MINUS + currentArgument;
			        }
			        calculatorView.textField.setText(currentArgument);
				    break;

			    case CalculatorModel.DOT:
			        // check current argument and add number to it's end
			        currentArgument = calculatorView.textField.getText();
			        // if dot is the first symbol in current argument
			        if(currentArgument.indexOf('.') == 0)
			        {
			        	currentArgument = CalculatorModel.ZERO + buttonPressed;
			        }
			        // if there is no dot in current argument
			        else if (currentArgument.indexOf('.') == -1)
			        {
			        	currentArgument += buttonPressed;
			        }
			        calculatorView.textField.setText(currentArgument);
				    break;

			    case CalculatorModel.BACKSPACE:
			        currentArgument = calculatorView.textField.getText();
			        if (!"".equals(currentArgument))
			        {
			        	currentArgument = currentArgument.substring(0, currentArgument.length() - 1);
			        }
			        calculatorView.textField.setText(currentArgument);
				break;

			    case CalculatorModel.CLEAR:
                                numberInMemory         = 0;
                                currentNumber          = 0;
                                currentArgument        = "";
                                choosedOperation       = "";
			        calculatorView.textField.setText(currentArgument);
			        break;

			    case CalculatorModel.PLUS:
			        currentArgument = calculatorView.textField.getText();
			        try
			        {	        	
			        	if ((numberInMemory != 0) && (!"".equals(choosedOperation)))
			        	{
			        	    currentNumber = Double.parseDouble(currentArgument);
                                            switch (choosedOperation) 
                                            {
                                                case CalculatorModel.PLUS:
                                                    currentNumber += numberInMemory;
                                                    break;
                                                case CalculatorModel.MINUS:
                                                    currentNumber = numberInMemory - currentNumber;
                                                    break;
                                                case CalculatorModel.MULTIPLY:
                                                    currentNumber *= numberInMemory;
                                                    break;
                                                case CalculatorModel.DIVIDE:
                                                    currentNumber = numberInMemory / currentNumber;
                                                    break;
                                                default:
                                                    break;
                                            }
                                            numberInMemory = currentNumber;
			        	}
			        	else
			        	{
			        		numberInMemory = Double.parseDouble(currentArgument);
                                        }
                                        currentNumber      = 0;
                                        currentArgument    = "";
                                        choosedOperation   = CalculatorModel.PLUS;                        
			                calculatorView.textField.setText(currentArgument);
			        }
			        catch(NumberFormatException e)
			        {
			        	calculatorView.textField.setText("NaN");
			        }				        
				    break;

			    case CalculatorModel.MINUS:
			        currentArgument = calculatorView.textField.getText();
			        try
			        {
			        	if ((numberInMemory != 0) && (!"".equals(choosedOperation)))
			        	{
			        	    currentNumber = Double.parseDouble(currentArgument);
                                            switch (choosedOperation) 
                                            {
                                                case CalculatorModel.PLUS:
                                                    currentNumber += numberInMemory;
                                                    break;
                                                case CalculatorModel.MINUS:
                                                    currentNumber = numberInMemory - currentNumber;
                                                    break;
                                                case CalculatorModel.MULTIPLY:
                                                    currentNumber *= numberInMemory;
                                                    break;
                                                case CalculatorModel.DIVIDE:
                                                    currentNumber = numberInMemory / currentNumber;
                                                    break;
                                                default:
                                                    break;
                                            }
                                            numberInMemory = currentNumber;
			        	}
			        	else
			        	{
			        	    numberInMemory = Double.parseDouble(currentArgument);
                                        }
                                        currentNumber    = 0;
                                        currentArgument  = "";
                                        choosedOperation = CalculatorModel.MINUS;
			                calculatorView.textField.setText(currentArgument);
			        }
			        catch(NumberFormatException e)
			        {
			        	calculatorView.textField.setText("NaN");
			        }				    
				    break;

			    case CalculatorModel.MULTIPLY:
			        currentArgument = calculatorView.textField.getText();
			        try
			        {
			        	if ((numberInMemory != 0) && (!"".equals(choosedOperation)))
			        	{
			        	    currentNumber = Double.parseDouble(currentArgument);
                                            switch (choosedOperation) 
                                            {
                                                case CalculatorModel.PLUS:
                                                    currentNumber += numberInMemory;
                                                    break;
                                                case CalculatorModel.MINUS:
                                                    currentNumber = numberInMemory - currentNumber;
                                                    break;
                                                case CalculatorModel.MULTIPLY:
                                                    currentNumber *= numberInMemory;
                                                    break;
                                                case CalculatorModel.DIVIDE:
                                                    currentNumber = numberInMemory / currentNumber;
                                                    break;
                                                default:
                                                    break;
                                            }
                                            numberInMemory = currentNumber;
			      	        }
			        	else
			        	{
			        	    numberInMemory = Double.parseDouble(currentArgument);
                                        }
                                        currentNumber    = 0;
                                        currentArgument  = "";                        
                                        choosedOperation = CalculatorModel.MULTIPLY;
			                calculatorView.textField.setText(currentArgument);
			        }
			        catch(NumberFormatException e)
			        {
			            calculatorView.textField.setText("NaN");
			        }				    
				    break;

			    case CalculatorModel.DIVIDE:
			        currentArgument = calculatorView.textField.getText();
			        try
			        {
			        	if ((numberInMemory != 0) && (!"".equals(choosedOperation)))
			        	{
			        	    currentNumber = Double.parseDouble(currentArgument);
                                            switch (choosedOperation) 
                                            {
                                                case CalculatorModel.PLUS:
                                                    currentNumber += numberInMemory;
                                                    break;
                                                case CalculatorModel.MINUS:
                                                    currentNumber = numberInMemory - currentNumber;
                                                    break;
                                                case CalculatorModel.MULTIPLY:
                                                    currentNumber *= numberInMemory;
                                                    break;
                                                case CalculatorModel.DIVIDE:
                                                    currentNumber = numberInMemory / currentNumber;
                                                    break;
                                                default:
                                                    break;
                                            }
                                            numberInMemory = currentNumber;
			        	}
			        	else
			        	{
			        	    numberInMemory = Double.parseDouble(currentArgument);
                                        }
                                        currentNumber    = 0;
                                        currentArgument  = "";                        
                                        choosedOperation = CalculatorModel.DIVIDE;
			                calculatorView.textField.setText(currentArgument);
			        }
			        catch(NumberFormatException e)
			        {
			            calculatorView.textField.setText("NaN");
			        }				    
				    break;
				    
			    case CalculatorModel.EQUALS:
			        if(numberInMemory != 0)
			        {
			            currentArgument = calculatorView.textField.getText();
			            try
			            {
			               	currentNumber = Double.parseDouble(currentArgument);
                                        switch (choosedOperation) 
                                        {
                                            case CalculatorModel.PLUS:
                                                currentNumber += numberInMemory;
                                                break;
                                            case CalculatorModel.MINUS:
                                                currentNumber = numberInMemory - currentNumber;
                                                break;
                                            case CalculatorModel.MULTIPLY:
                                                currentNumber *= numberInMemory;
                                                break;
                                            case CalculatorModel.DIVIDE:
                                                currentNumber = numberInMemory / currentNumber;
                                                break;
                                            default:
                                                break;
                                        }
			        	numberInMemory   = currentNumber;
                                        choosedOperation = "";
                                        currentArgument  = "" + currentNumber;
			                calculatorView.textField.setText(currentArgument);
			            }
			            catch(NumberFormatException e)
			            {
			        	    calculatorView.textField.setText("NaN");
			            }
			        }
				    break;
		    }
            } 
	}
}