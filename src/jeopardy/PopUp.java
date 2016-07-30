/**
 * Extra Credit Jeopardy game for the Gleick, Chapter 15
 * skit for LIS3267-Introduction to Information Science.
 * 
 * Customized popup windows for the Jeopardy questions and answers.
 * 
 * Credits:
 * Justin Tadlock (Program, questions)
 * Krystal Salerno (Images)
 * William Beachem (Questions)
 * 
 *@author Justin Tadlock
 */

package jeopardy;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class PopUp extends JOptionPane
{
	//Variables
	protected JLabel label;
	protected String content, contentAnswer;
	protected int points;
	protected JOptionPane jop;
	protected JFrame frame;
	protected JDialog dialog;
	
	//Constructor
	public PopUp(String content, String contentAnswer, int points)
	{
            this.content = content;
            this.contentAnswer = contentAnswer;
            this.points = points;

            this.frame = new JFrame();
	}
	
	//Function for showing the questions and then the answers and returning a point value back to main.
	public int showQuestion()
	{
            //Dimensions for panel sizes and text font size
            Dimension pDim = new Dimension(300,150);
            int textSize = 14;

            //Setting up the panel to hold content
            JPanel panel = new JPanel(new BorderLayout());
            panel.setPreferredSize(pDim);

            //Setting up and formatting the Text Area container
            JTextArea ta = new JTextArea(content);
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            ta.setEditable(false);
            ta.setFont(new Font("Verdana", Font.BOLD, textSize));
            ta.setOpaque(false);
            panel.add(ta, BorderLayout.NORTH);

            //Pop up an OK message box
            String tempStr = "Question Worth: " + points + " Points.";
            showMessageDialog(frame, panel, tempStr, JOptionPane.QUESTION_MESSAGE);

            //New window information for the Answer popup
            panel = new JPanel(new BorderLayout());
            panel.setPreferredSize(pDim);

            //Loading the Answer text into the window
            ta.setText(contentAnswer);

            //Adding the content to the frame
            panel.add(ta, BorderLayout.NORTH);

            //Pop up a confirmation box for the ability to award points for correct answers
            int i = showConfirmDialog(frame, panel, "Answer", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            //Flags for returning points if answered correctly
            if(i == JOptionPane.YES_OPTION)
            {
                return points;
            }
            else
            {
                return 0;
            }
	}
	
	//Rendering Graphics
	public void paintComponent(Graphics g)
	{
            super.paintComponent(g);
	}
}
