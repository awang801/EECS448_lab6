import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Control extends JFrame implements ActionListener
{
	

	JFrame popUpFrame;
	
	//JPanels
	JPanel content;
	
	JPanel votePanel;
	JPanel voteMain;
	JPanel voteCandidates;
	JPanel voteButtons;
	JPanel viewPanel;
	JPanel viewMain;
	JPanel viewButtons;
	JLabel voteLabel;
	JLabel firstNameLabel;
	JLabel lastNameLabel;
	JLabel viewLabel;

	JRadioButton candidate1;
	JRadioButton candidate2;
	JRadioButton candidate3;
	JRadioButton candidate4;
	JTextField firstName;
	JTextField lastName;
	CardLayout myLayout = new CardLayout();
	VoteManager voteManager;
	
	public Control()
	{
		super("Excercise 3: Voting Machine");
		
		voteManager = new VoteManager();
		setSize(500, 540);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		content = new JPanel(myLayout);
		content.setPreferredSize(new Dimension(500, 540));

		votePanel = new JPanel();
		votePanel.setPreferredSize(new Dimension(500, 500));
		
		voteMain = new JPanel(new GridLayout(0, 1));
		voteMain.setPreferredSize(new Dimension(500, 300));
		voteButtons = new JPanel(new GridLayout());
		voteButtons.setPreferredSize(new Dimension(500, 200));
		
		viewPanel = new JPanel();
		viewPanel.setPreferredSize(new Dimension(500, 500));
		
		viewMain = new JPanel();
		viewMain.setPreferredSize(new Dimension(500, 300));
		viewButtons = new JPanel( new GridLayout());
		viewButtons.setPreferredSize(new Dimension(500, 200));
		
		//Create the JLabels
		voteLabel = new JLabel("Enter your name and choose your candidate");
		firstNameLabel = new JLabel("First:");
		lastNameLabel = new JLabel("Last:");
		viewLabel = new JLabel("");
		firstName = new JTextField(8);
		lastName = new JTextField(8);
		JPanel firstField = new JPanel();
		JPanel lastField = new JPanel();
		candidate1 = new JRadioButton("Trump");
		candidate2 = new JRadioButton("Link");
		candidate3 = new JRadioButton("Zelda");
		candidate4 = new JRadioButton("Justin Bieber");
		ButtonGroup candidateGroup = new ButtonGroup();
		candidateGroup.add(candidate1);
		candidateGroup.add(candidate2);
		candidateGroup.add(candidate3);
		candidateGroup.add(candidate4);
		firstField.add(firstNameLabel);
		firstField.add(firstName);

		lastField.add(lastNameLabel);
		lastField.add(lastName);

		voteCandidates = new JPanel(new GridLayout(0, 1));
		voteCandidates.add(candidate1);
		voteCandidates.add(candidate2);
		voteCandidates.add(candidate3);
		voteCandidates.add(candidate4);

		voteMain.add(firstField);
		voteMain.add(lastField);
		voteMain.add(voteCandidates);
		
		viewMain.add(viewLabel);

		
		//Create the buttons
	    JButton voteButton = new JButton("Vote"); 
	    JButton backButton = new JButton("Back");
	    voteButton.addActionListener(this);
	    backButton.addActionListener(this);
	    voteButtons.add(voteButton);
	    viewButtons.add(backButton);
	    votePanel.add(voteMain);
	    votePanel.add(voteButtons);
	    viewPanel.add(viewMain);
	    viewPanel.add(viewButtons);
	    content.add(votePanel);
	    content.add(viewPanel);
	    this.getContentPane().add(content);
		setVisible(true);
		popUpFrame = new JFrame("Dialogue");
	}

	public String getVote()
	{
		if(candidate1.isSelected())
		{
			return "Trump";
		}
		else if(candidate2.isSelected())
		{
			return "Link";
		}
		else if(candidate3.isSelected())
		{
			return "Zelda";
		}
		else if(candidate4.isSelected())
		{
			return "Justin Bieber";
		}
		return "X";
	}
	
	public void actionPerformed(ActionEvent event)
	{
		switch(event.getActionCommand())
		{
			case "Vote":
				String firstStr = firstName.getText();
				String lastStr = lastName.getText();
				if(!isValid(firstStr) || !isValid(lastStr))
				{
					JOptionPane.showMessageDialog(popUpFrame, "Please enter a valid name");
					break;
				}
				else if(voteManager.hasVoted(firstStr, lastStr))
				{
					JOptionPane.showMessageDialog(popUpFrame, "You have already voted");
					break;
				}
				else if(getVote().equals("X"))
				{
					JOptionPane.showMessageDialog(popUpFrame, "Please select a candidate");
					break;
				}
				else
				{
					voteManager.submitVote(firstStr, lastStr, getVote());
					viewLabel.setText("Ballot cast by " + firstStr + " " + lastStr + " for candidate " + getVote());
				}
								
				myLayout.next(content);
				break;
			case "Back":
				viewLabel.setText("");
				myLayout.next(content);
				break;
			default:
				JOptionPane.showMessageDialog(popUpFrame, "An unexpected error has occured");
				break;
		}
	}
}
	public boolean isValid(String str)
	{
		if (str.length() == 0)
		{
			return false;
		}
		for(int i=0; i<str.length(); i++)
		{
			if(str.charAt(i) == '_')
			{
				return false;
			}
		}
		return true;
	}
