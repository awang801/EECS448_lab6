 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.NumberFormatException;

public class Control extends JFrame implements ActionListener
{
	JFrame popUpFrame;	
	JPanel content;	
	JPanel rollPanel;
	JPanel rollMain;
	JPanel rollButtons;	
	JPanel viewPanel;
	JPanel viewMain;
	JPanel viewButtons;	
	JLabel rollLabel;
	JLabel viewLabel;
	JTextField text;
	CardLayout myLayout = new CardLayout();
	
	//For getting a random number
	Dice die;
	
	public Control()
	{
		super("Excercise 1: Dice");
		
		//Create the die
		die = new Dice();
		setSize(300, 240);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		content = new JPanel(myLayout);
		content.setPreferredSize(new Dimension(300, 240));
		rollPanel = new JPanel();
		rollPanel.setPreferredSize(new Dimension(300, 200));
		rollMain = new JPanel();
		rollMain.setPreferredSize(new Dimension(300, 100));
		rollButtons = new JPanel(new GridLayout());
		rollButtons.setPreferredSize(new Dimension(300, 100));
		viewPanel = new JPanel();
		viewPanel.setPreferredSize(new Dimension(300, 200));		
		viewMain = new JPanel();
		viewMain.setPreferredSize(new Dimension(300, 100));
		viewButtons = new JPanel( new GridLayout());
		viewButtons.setPreferredSize(new Dimension(300, 100));
		
		//Create the JLabels
		rollLabel = new JLabel("Enter the number of sides:");
		viewLabel = new JLabel("View");
		
		//Create the text field
		text = new JTextField(3);
		rollMain.add(rollLabel);
		viewMain.add(viewLabel);
		rollMain.add(text);
		
		//Create the buttons
	    JButton rollButton = new JButton("Roll"); 
	    JButton reRollButton = new JButton("reRoll");
	    rollButton.addActionListener(this);
	    reRollButton.addActionListener(this);
	    rollButtons.add(rollButton);
	    viewButtons.add(reRollButton);
	    rollPanel.add(rollMain);
	    rollPanel.add(rollButtons);
	    viewPanel.add(viewMain);
	    viewPanel.add(viewButtons);
	    content.add(rollPanel);
	    content.add(viewPanel);

	    //Add to JFrame
	    this.getContentPane().add(content);
		setVisible(true);

		//For pop up messages
		popUpFrame = new JFrame("Dialogue");
	}
	
	public void actionPerformed(ActionEvent event)
	{
		switch(event.getActionCommand())
		{
			case "Roll":
				String inputStr = text.getText();
				int sides;
				try
				{
					sides = Integer.parseInt(inputStr);
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(popUpFrame, "Please enter an integer");
					break;
				}
				if(sides < 1)
				{
					JOptionPane.showMessageDialog(popUpFrame, "Please enter a value greater than 0");
					break;
				}
				
				viewLabel.setText(Integer.toString(die.roll(sides)));
				
				myLayout.next(content);
				break;
			case "reRoll":
				myLayout.next(content);
				break;
			default:
				JOptionPane.showMessageDialog(popUpFrame, "An unexpected error has occured");
				break;
		}
	}
}