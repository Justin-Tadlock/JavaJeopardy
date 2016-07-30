/**
 * Extra Credit Jeopardy game for the Gleick, Chapter 15
 * skit for LIS3267-Introduction to Information Science.
 * 
 * The main window of the Jeopardy game.
 * 
 * Credits:
 * Justin Tadlock (Program, questions)
 * Krystal Salerno (Images)
 * William Beachem (Questions)
 * 
 *@author Justin Tadlock
 */

package jeopardy;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Board extends JLayeredPane
{
	//Variables
	protected int boardWidth, boardHeight; 
	
	protected JeopardyButton buttons[];
	protected ImageIcon background, banner;
	protected JLabel bgLabel,bannerLabel;
	protected JPanel content, scoreboard;
	public static PopUp window;
	protected int score, total;
	
	public ButtonListener buttonListener;
	
	//Constructor
	public Board(int boardWidth, int boardHeight) throws IOException
	{
            this.setLayout(null);
            buttonListener = new ButtonListener();

            this.boardWidth = boardWidth;
            this.boardHeight = boardHeight;
            this.scoreboard = new JPanel();
            this.score = 0;

            populateButtons();

            generateBoard();
            this.setOpaque(true);
	}
	
	//Function for loading button information from an input file
	public void populateButtons() throws IOException
	{
            System.out.println("Populating Buttons...");
            FileReader fr = new FileReader("src/jeopardy/resources/buttonInput.txt");
            
            try (BufferedReader br = new BufferedReader(fr)) 
            {
                int numButtons = Integer.parseInt(br.readLine());
            
                //Dummy variables for extracting information from the file.
                String imgName, question, answer, points;

                //Dynamically resizing the array 
                buttons = new JeopardyButton[numButtons];

                //Total point counter
                total = 0; 

                //Loop for loading the array with buttons 
                for(int i = 0; i < numButtons; i++)
                {
                    //Extracting info from the file..
                    imgName = "src/jeopardy/resources/images/" + br.readLine();
                    question = br.readLine();
                    answer = br.readLine();
                    points = br.readLine();

                    //Creating new buttons with the above info.
                    buttons[i] = new JeopardyButton(imgName, question, answer, points, false);
                    System.out.println(imgName + " " + question + " " + answer + " " + points);

                    //First four buttons loaded will be category buttons
                    //that will not be clickable for popups
                    if(i <= 3)
                    {
                        buttons[i].setCategory(true);
                    }
                    else if (i >= 4) //Counting total points for scoring purposes
                    {
                        total += Integer.parseInt(points);
                    }
                }
            }
	}
	
	//Loading the content onto the window
	public void generateBoard()
	{
            //Setting up the content container
            content = new JPanel();
            content.setOpaque(false);
            content.setBackground(new Color(0,0,0,0));

            //Setting up the background container
            JPanel bgPanel = new JPanel();

            //Loading up the background container
            background = new ImageIcon("src/jeopardy/resources/images/bg.jpg");
            bgLabel = new JLabel(background);
            bgPanel.add(bgLabel);

            //Setting up the label for the banner
            banner = new ImageIcon("src/jeopardy/resources/images/banner.png");
            bannerLabel = new JLabel(banner);

            //Setting up the Buttons container
            JPanel btnPanel = new JPanel();
            btnPanel.setOpaque(false); //transparent background, we want to see the wallpaper
            btnPanel.setLayout(new GridLayout(5,4,0,0)); //5 rows, 4 columns, 20 buttons
            
            //Adding buttons to the button panel
            for(int i = 0; i < buttons.length; i++)
            {
                    btnPanel.add(buttons[i]);
                    buttons[i].addActionListener(buttonListener); //adding action listeners for clicking
            }

            //Setting up Main content panel
            content.setLayout(new BorderLayout());
            content.add(bannerLabel, BorderLayout.NORTH);
            content.add(btnPanel, BorderLayout.CENTER);

            //Adding panels to main window pane
            add(bgPanel, new Integer(-3)); //params: component, layer
            bgPanel.setBounds(0,0,boardWidth, boardHeight);

            add(content, new Integer(-2));
            content.setBounds(20, 0, boardWidth-40, boardHeight-50);

            //Setting up the initial scoreboard
            scoreboard.setOpaque(false);
            add(scoreboard, new Integer(0));
            scoreboard.setBounds(500,100, 300, 25);
            updateScore();
	}
	
	//Used for updating the score of the players 
	public void updateScore()
	{
            //Remove outdated information
            remove(scoreboard);

            //Setting up the new panel for updated information
            scoreboard = new JPanel();
            scoreboard.setOpaque(false);
            scoreboard.setLayout(new BorderLayout());
            scoreboard.setBounds(300,10, 300, 25);

            double percentage = (double)score / (double)total * 100;

            DecimalFormat df = new DecimalFormat("#.##");

            String temp = "Score percentage: " + df.format(percentage);

            //Setting up the label for printing score to the screen
            JLabel jl = new JLabel(temp, JLabel.RIGHT);
            jl.setFont(new Font("Times New Roman", Font.BOLD, 16));
            jl.setForeground(Color.black);

            //Adding the updated score to the scoreboard panel
            scoreboard.add(jl, BorderLayout.CENTER);

            //Adding the scoreboard to the main window
            add(scoreboard, new Integer(0));
	}
	
	//Render Graphics
	public void paintComponent(Graphics g)
	{
            super.paintComponent(g);
	}
	
	/* Events */
	class ButtonListener implements ActionListener
	{
            //Button clicked flag
            public void actionPerformed(ActionEvent e)
            {
                //Getting the event source
                if(e.getSource() instanceof JeopardyButton)
                {
                    //Check for which button was clicked.
                    for(JeopardyButton b : buttons)
                    {
                        //Creating a popup window if it's found.
                        if(e.getSource() == b && !b.isCategory() && !b.isDisabled())
                        {
                            //Dynamically changing information to suit the button clicked on
                            window = new PopUp(b.getQuestion(), b.getAnswer(), b.getPoints());

                            //Updating the score if answered correctly
                            score += window.showQuestion();

                            //Disabling the button
                            b.setDisabled(true);

                            updateScore();
                        }
                    }
                }
            }
	}
}
