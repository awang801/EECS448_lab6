 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.NumberFormatException;
import java.lang.UnsupportedOperationException;

public class Control extends JFrame implements ActionListener
{
	
	//Used for pop up messages
	JFrame popUpFrame;
	JPanel content;
	JPanel radioButtonPanel;
	JPanel radioFromPanel;
	JPanel radioToPanel;
	JLabel fromLabel;
	JLabel toLabel;
	JLabel convertLabel;
	JTextField text;
	JRadioButton fromC;
	JRadioButton fromK;
	JRadioButton fromF;
	JRadioButton toC;
	JRadioButton toK;
	JRadioButton toF;
	
	public Control()
	{
		super("Excercise 2: Temperature Converter");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create JPanels
		content = new JPanel();
		content.setPreferredSize(new Dimension(400, 300));
		radioButtonPanel = new JPanel(new GridLayout(1, 2));
		radioFromPanel = new JPanel(new GridLayout(4, 1));
		radioToPanel = new JPanel(new GridLayout(4, 1));
		
		//Create the JLabels
		fromLabel = new JLabel("From Unit");
		toLabel = new JLabel("To Unit");
		convertLabel = new JLabel("");
		text = new JTextField(3);
		fromC = new JRadioButton("C");
		fromC.setSelected(true);
		fromK = new JRadioButton("K");
		fromF = new JRadioButton("F");

		ButtonGroup fromGroup = new ButtonGroup();
		fromGroup.add(fromC);
		fromGroup.add(fromK);
		fromGroup.add(fromF);

		toC = new JRadioButton("C");
		toC.setSelected(true);
		toK = new JRadioButton("K");
		toF = new JRadioButton("F");

		ButtonGroup toGroup = new ButtonGroup();
		toGroup.add(toC);
		toGroup.add(toK);
		toGroup.add(toF);

		//Add Radio Buttons and Labels to appropriate JPanels
		radioFromPanel.add(fromLabel);
		radioFromPanel.add(fromC);
		radioFromPanel.add(fromK);
		radioFromPanel.add(fromF);

		radioToPanel.add(toLabel);
		radioToPanel.add(toC);
		radioToPanel.add(toK);
		radioToPanel.add(toF);

		radioToPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		radioFromPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));


		radioButtonPanel.add(radioFromPanel);
		radioButtonPanel.add(radioToPanel);
		content.add(radioButtonPanel);

		//Add the text field
		content.add(text);
		
		//Create the buttons
	    JButton convertButton = new JButton("Convert"); 
	    convertButton.addActionListener(this);
	    content.add(convertButton);
		content.add(convertLabel);
	    this.getContentPane().add(content);
		setVisible(true);
		popUpFrame = new JFrame("Dialogue");
	}

	public char getUnitFrom()
	{
		if(fromC.isSelected())
		{
			return 'C';
		}
		else if(fromK.isSelected())
		{
			return 'K';
		}
		else if(fromF.isSelected())
		{
			return 'F';
		}
		return 'X';
	}

	public char getUnitTo()
	{
		if(toC.isSelected())
		{
			return 'C';
		}
		else if(toK.isSelected())
		{
			return 'K';
		}
		else if(toF.isSelected())
		{
			return 'F';
		}
		return 'X';
	}
	
	public void actionPerformed(ActionEvent event)
	{
		switch(event.getActionCommand())
		{
			case "Convert":
				char fromUnit = getUnitFrom();
				char toUnit = getUnitTo();
				String inputStr = text.getText();
				double temperature;
				try
				{
					temperature = Double.parseDouble(inputStr);
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(popUpFrame, "Please enter a valid number");
					break;
				}

				try
				{
					convertLabel.setText(temperature + " " + fromUnit + " is " + TemperatureConverter.convert(fromUnit, toUnit, temperature) + " " + toUnit);
				}
				catch(UnsupportedOperationException e)
				{
					JOptionPane.showMessageDialog(popUpFrame, "Please select a unit");
					break;
				}

				break;
			default:
				JOptionPane.showMessageDialog(popUpFrame, "An unexpected error has occured");
				break;
		}
	}
}