/**
 * Extra Credit Jeopardy game for the Gleick, Chapter 15
 * skit for LIS3267-Introduction to Information Science.
 * 
 * Main Window initializer.
 * 
 * Credits:
 * Justin Tadlock (Program, questions)
 * Krystal Salerno (Images)
 * William Beachem (Questions)
 * 
 *@author Justin Tadlock
 */

package jeopardy;

import java.io.IOException;
import javax.swing.JFrame;

public class Jeopardy extends JFrame
{
  private int boardWidth = 650, boardHeight = 350;
  
	public Jeopardy()
	{
            try
            {
                add(new Board(boardWidth, boardHeight));
            }
            catch(IOException e) {}
		
            //Setting up the window frame
            setTitle("Extra Credit Jeopardy");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close the program when x is clicked
            setSize(boardWidth, boardHeight); //Window resolution
            setLocationRelativeTo(null); //Use absolute x,y coordinates
            setVisible(true); //Window is not being hidden
            setResizable(false); //Can resize the window if need be
	}
	
	public static void main(String args[])
	{
            new Jeopardy();
	}
}
