/**
 * Extra Credit Jeopardy game for the Gleick, Chapter 15
 * skit for LIS3267-Introduction to Information Science.
 * 
 * Specialized Buttons for the main window graphics
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
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class JeopardyButton extends JButton
{
    //Variables
    protected String question, answer, points;
    protected boolean isCategory;
    protected boolean disabled;

    /* Constructors */
    public JeopardyButton(String imgName, String question, String answer, String points, boolean disabled)
    {
            super(new ImageIcon(imgName));

            this.setLayout(new BorderLayout());

            this.question = question;
            this.answer = answer;
            this.points = points;
            this.disabled = disabled;
            this.isCategory = false;

            this.setBounds(new Rectangle(80,25));
            
            this.setFont(new Font("Times New Roman", Font.BOLD, 20));
            this.setForeground(Color.white);
            
            this.setText(points);
            this.setHorizontalTextPosition(CENTER);
            this.setVerticalTextPosition(CENTER);
    }

    /* Getters */
    public String getQuestion() { return this.question; }
    public String getAnswer() { return answer; }
    public boolean isDisabled() { return disabled; }
    public boolean isCategory() { return isCategory; }

    public int getPoints() 
    {
        int pnts = Integer.parseInt(points);

        return pnts; 
    }

    /* Setters */
    public void setCategory(boolean state)
    {
        this.isCategory = state;
    }

    //Changing the image of the button
    public void setDisabled(boolean state)
    {
        setIcon(new ImageIcon ("src/jeopardy/resources/images/r_button.png"));
        this.disabled = state;
    }

    //Rendering Graphics
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}
