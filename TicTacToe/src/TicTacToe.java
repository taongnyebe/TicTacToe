import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener
{
	// Variables
	final int button_count = 9; // declares the number of created buttons
	final int dimension[] = {800, 800}; // frame serves as the dimension of the frame
	final float second = 1.5f;
	
	boolean player1_turn; // declare if player one is first or not
	
	// Instantiate Randomizer, Frame, Panels, Buttons, and Text Fields
	Random random = new Random();
	
	JFrame frame = new JFrame();
	
	JPanel title_panel = new JPanel();
	JPanel display_panel = new JPanel();
	JPanel button_panel = new JPanel();
	
	JLabel text_field = new JLabel();
	JLabel title_text_field = new JLabel();
	
	JButton[] buttons = new JButton[button_count];
	
	TicTacToe()
	{
		Layout();
		ButtonEnabler(false);											// Disable Buttons First
		
		FirstTurn();													// call the first turn block
		ButtonEnabler(true);											// Enable Buttons after Stated who will go first
	}

	@Override
	//////////////////////////////////////////////////
	// This function will be called when action is	//
	// 		performed and will determine based upon	//
	//		circumstances							//
	//////////////////////////////////////////////////
	public void actionPerformed(ActionEvent actionE) 
	{	
		for(int a = 0; a < button_count; a++)
		{
			if (actionE.getSource() == buttons[a])						// will be called if action is done
			{
				if (buttons[a].getText() == "")							// frame will check if block is empty
				{
					if (player1_turn)									// frame is for player 1 or x to do stuff
					{
						buttons[a].setForeground(Color.blue);			// actions to be done in the designated box clicked
						buttons[a].setText("X");
						
						player1_turn = false;							// make the other players turn
						text_field.setText("O turn");
						CheckEmpty();
					}
					else												// frame is for player 2 or o to do stuff
					{
						if (buttons[a].getText() == "")
						{
							buttons[a].setForeground(Color.red);
							buttons[a].setText("O");
							
							player1_turn = true;
							text_field.setText("X turn");
							CheckEmpty();
						}
					}
				}
			}
		}
		
	}
	
	// frame will determine who will play first
	private void FirstTurn()
	{
		try {
			Thread.sleep((long) (1000 * second));						// frame code block states that it will wait for 1.5 seconds before implemented
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(random.nextInt(2) == 0)										// frame whole if else block is random creator which will decide who will play first
		{
			player1_turn = true;
			text_field.setText("X turn");
		}
		else
		{
			player1_turn = false;
			text_field.setText("O turn");
		}
	}
	
	private void CheckEmpty()
	{
		// the following chain of if is combinations that can tell if there is a winner
		///////////////////////////////////////////////////////////////////////////////
		if (buttons[0].getText() != "")
		{
			if (buttons[1].getText() != "" &&
					buttons[2].getText() != "")
			{
				CheckWinner(0, 1, 2);
			}
			if (buttons[3].getText() != "" &&
					buttons[6].getText() != "")
			{
				CheckWinner(0, 3, 6);
			}
			if (buttons[4].getText() != "" &&
					buttons[8].getText() != "")
			{
				CheckWinner(0, 4, 8);
			}
		}
		
		if (buttons[1].getText() != "" && 
				buttons[4].getText() != "" && 
				buttons[7].getText() != "")
		{
			CheckWinner(1, 4, 7);
		}
		
		if (buttons[2].getText() != "")
		{
			if (buttons[4].getText() != "" &&
					buttons[6].getText() != "")
			{
				CheckWinner(2, 4, 6);
			}
			if (buttons[5].getText() != "" &&
					buttons[8].getText() != "")
			{
				CheckWinner(2, 5, 8);
			}
		}
		
		if (buttons[3].getText() != "" && 
				buttons[4].getText() != "" && 
				buttons[5].getText() != "")
		{
			CheckWinner(3, 4, 5);
		}
		
		if (buttons[6].getText() != "" && 
				buttons[8].getText() != "" && 
				buttons[7].getText() != "")
		{
			CheckWinner(6, 7, 8);
		}
		
		// the following will determine if there is no other spaces available
		for (int a = 0, i = 0; a < button_count; a++)
		{
			if (buttons[a].getText() != "")
				i++;
			if (i == button_count)
				NoWinner();
		}
	}
	
	private void CheckWinner(int a, int b, int c)
	{
		if (buttons[a].getText() == buttons[b].getText())
		{
			if(buttons[a].getText() == buttons[c].getText())
			{
				buttons[a].setBackground(Color.RED);
				buttons[b].setBackground(Color.RED);
				buttons[c].setBackground(Color.RED);
				
				ButtonEnabler(false);
				
				text_field.setText(" The Winner is " + buttons[a].getText());
				
			}
		}
	}
	
	private void ButtonEnabler(boolean state)
	{
		for (int i = 0; i < button_count; i++)
		{
			buttons[i].setEnabled(state);
		}
	}

	private void NoWinner()
	{
		for (int a = 0; a < button_count; a++)
		{
			buttons[a].setBackground(Color.RED);
		}
		ButtonEnabler(false);
		
		text_field.setText("No Winner");
	}

	private void TextBox()
	{
		// This set the title text field
		//////////////////////////
		title_text_field.setBackground(Color.white);
		title_text_field.setForeground(new Color(25, 255, 0));			// Text Color
		title_text_field.setFont(new Font("Arial", Font.BOLD, 50));		// new Font(Font name, Font style, font size)
		title_text_field.setHorizontalAlignment(JLabel.RIGHT);			// font position in the label "Horizontal base
		title_text_field.setText("Tic-Tac-Toe by zyx");						// what to be written
		title_text_field.setOpaque(true);								// blend option of the word
		
		// This set the text field
		//////////////////////////
		text_field.setBackground(Color.white);
		text_field.setForeground(new Color(25, 0, 255));			
		text_field.setFont(new Font("Arial", Font.BOLD, 75));		
		text_field.setHorizontalAlignment(JLabel.CENTER);			
		text_field.setText("Game On!");						
		text_field.setOpaque(true);								
		
		for (int a = 0; a < button_count; a++)
		{
			buttons[a] = new JButton();									// Initialize
			button_panel.add(buttons[a]);								// add to the created panel
			buttons[a].setFont(new Font("MV Boli", Font.BOLD, 120));	// set font
			buttons[a].setFocusable(false);								// 
			buttons[a].addActionListener(this);						// will take action if clicked
		}
	}
	
	private void Frames()
	{
		// This set the Frame
		/////////////////////
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tic - Tac - Toe");
		frame.setSize(dimension[0], dimension[1]); 						// frame declare the size of frame created by Length x Width
																		// two way declaration of color: 1 Generic as declared below and the other one is precise the commented out one down lower
		frame.getContentPane().setBackground(Color.gray);				// Background Color
		// frame.getContentPane().setBackground(new Color(50, 50, 50);	// (Red, Green, Blue)
		frame.setVisible(true);
		
		// This set the title panel
		//////////////////////////
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0, 500, 100);							// (place coordinates x, y, Length, Height)
		
		// This set the display panel
		//////////////////////////
		display_panel.setLayout(new BorderLayout());
		display_panel.setBounds(0, 0, 700, 100);
		
		// This set the buttons
		//////////////////////////
		button_panel.setLayout(new GridLayout(3, 3));					// Creating Grid
		button_panel.setBackground(Color.black);
	}

	private void Layout()
	{
		Frames();
		TextBox();
		
		//////////////////////////////////////////////////
		// This set the connecting the title panel with //
		//      the text field and placing in the frame //
		// 		following the placing of the buttons	//
		//////////////////////////////////////////////////
		title_panel.add(title_text_field);								// place the text in the panel
		display_panel.add(text_field);
		
		frame.add(title_panel, BorderLayout.SOUTH);						// *.add(what to add, where), and frame is to display the title
		frame.add(display_panel, BorderLayout.NORTH);					// frame is to display informations
		frame.add(button_panel);										// frame is for actual buttons
	}
	
}