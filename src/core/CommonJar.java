/**
 * @author mike802
 * @version 1.0 - 2/26/2013
 */
package core;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CommonJar extends ConfigJar{
	
	protected Properties properties;
	protected JFrame mainPage;
	
	protected JMenuBar menuBar;
	protected JMenu fileMenu, actionMenu, optionsMenu, helpMenu;
	protected JMenuItem loadUser, createUser, exit, loadGoal, createGoal, 
		deleteGoal, display, progress, about, faq;
	protected JCheckBoxMenuItem allowRemove, showDescription;
	
	protected JLayeredPane layeredPane;
	protected JButton add, remove;
	protected JLabel title, description, background;
	
	protected boolean initialized = false;

}
