/**
 * @author mike802
 * @version 1.0 - 2/26/2013
 */
package core;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JarScreen extends IOJar {
	
	private ButtonHandler buttonHandler;
	private MenuHandler menuHandler;
	
	public JarScreen(Properties p){
		properties = p;
		buttonHandler = new ButtonHandler();
		menuHandler = new MenuHandler();
	}
	
	private void create(){
		mainPage = new JFrame("goal_jar");
		mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPage.setResizable(false);
		Image company = Toolkit.getDefaultToolkit().getImage(properties.getCompany());
		mainPage.setIconImage(company);
		mainPage.setPreferredSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT + LOGO_HEIGHT));
		mainPage.setBounds(0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT + LOGO_HEIGHT);
		mainPage.setLocation(250, 50);
		
		menuBar = new JMenuBar();
		mainPage.setJMenuBar(menuBar);

		fileMenu = new JMenu("file");
		actionMenu = new JMenu("action");
		actionMenu.setEnabled(false);
		optionsMenu = new JMenu("options");
		optionsMenu.setEnabled(false);
		helpMenu = new JMenu("help");
		
		loadUser = new JMenuItem("load user...");
		loadUser.addActionListener(menuHandler);
		createUser = new JMenuItem("create user...");
		createUser.addActionListener(menuHandler);
		exit = new JMenuItem("exit");
		exit.addActionListener(menuHandler);
		loadGoal = new JMenuItem("load goal...");
		loadGoal.addActionListener(menuHandler);
		createGoal = new JMenuItem("create goal...");
		createGoal.addActionListener(menuHandler);
		deleteGoal = new JMenuItem("delete goal...");
		deleteGoal.addActionListener(menuHandler);
		display = new JMenuItem("display current goal");
		display.addActionListener(menuHandler);
		progress = new JMenuItem("show progress of all goals");
		progress.addActionListener(menuHandler);
		allowRemove = new JCheckBoxMenuItem("allow remove");
		allowRemove.addActionListener(menuHandler);
		showDescription = new JCheckBoxMenuItem("show goal description");
		showDescription.addActionListener(menuHandler);
		about = new JMenuItem("about");
		about.addActionListener(menuHandler);
		faq = new JMenuItem("faq");
		faq.addActionListener(menuHandler);
		
		fileMenu.add(loadUser);
		fileMenu.add(createUser);
		fileMenu.add(exit);
		actionMenu.add(loadGoal);
		actionMenu.add(createGoal);
		actionMenu.add(deleteGoal);
		optionsMenu.add(display);
		optionsMenu.add(progress);
		optionsMenu.add(allowRemove);
		optionsMenu.add(showDescription);
		helpMenu.add(about);
		helpMenu.add(faq);
		
		menuBar.add(fileMenu);
		menuBar.add(actionMenu);
		menuBar.add(optionsMenu);
		menuBar.add(helpMenu);
		
		title = new JLabel();
		ImageIcon logo = new ImageIcon(properties.getLogo());
		title.setIcon(logo);
		title.setPreferredSize(new Dimension(LOGO_WIDTH, LOGO_HEIGHT));
		title.setBounds(0, 0, LOGO_WIDTH, LOGO_HEIGHT);
		
		background = new JLabel();
		ImageIcon backgroundImage = new ImageIcon(properties.getBackground());
		background.setIcon(backgroundImage);
		background.setBounds(0, LOGO_HEIGHT, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		
		int xLine = (int)(BACKGROUND_WIDTH * .7);
		add = new JButton("add");
		add.setBounds(xLine, (BACKGROUND_HEIGHT / 6) + LOGO_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		add.addActionListener(buttonHandler);
		add.setEnabled(false);
		
		remove = new JButton("remove");
		remove.setBounds(xLine, BACKGROUND_HEIGHT/3 + LOGO_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		remove.addActionListener(buttonHandler);
		
		description = new JLabel(EMPTY_GOAL);
		description.setBounds(xLine, BACKGROUND_HEIGHT/2 + LOGO_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, LOGO_HEIGHT, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		layeredPane.add(background);
		layeredPane.moveToFront(background);
		layeredPane.add(add);
		layeredPane.moveToFront(add);
		layeredPane.add(remove);
		layeredPane.moveToBack(remove);
		layeredPane.add(description);
		layeredPane.moveToBack(description);
		
		mainPage.add(title);
		mainPage.add(layeredPane);
		mainPage.pack();
		mainPage.setVisible(true);		
	}
	
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == add){
				properties.incrementProgress();
				updateBackground();
				try {
					updateUserProfile();
					updateGoalInfo();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == remove){
				properties.decrementProgress();
				updateBackground();
				try {
					updateUserProfile();
					updateGoalInfo();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class MenuHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == loadUser){
				try {
					doLoadUser();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == createUser){
				try {
					doCreateUser();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == exit){
				System.exit(0);
			}else if(event.getSource() == loadGoal){
				try {
					doLoadGoal();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == createGoal){
				try {
					doCreateGoal();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == deleteGoal){
				try {
					doDeleteGoal();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == display){
				try {
					doDisplay();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == progress){
				try {
					doProgress();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == showDescription){
				if(showDescription.isSelected()){
					layeredPane.moveToFront(description);
					description.setEnabled(true);
				}else{
					layeredPane.moveToBack(description);
					description.setEnabled(false);
				}
			}else if(event.getSource() == allowRemove){
				if(allowRemove.isSelected()){
					layeredPane.moveToFront(remove);
					if(properties.getProgress() == 0){
						remove.setEnabled(false);
					}else{
						remove.setEnabled(true);
					}
				}else{
					layeredPane.moveToBack(remove);
					remove.setEnabled(false);
				}
			}else if(event.getSource() == about){
				doAbout();
			}else if(event.getSource() == faq){
				doFAQ();
			}
		}
	}
	
	private void doLoadUser() throws IOException{
		String[] list = loadUserList();
		Object input = JOptionPane.showInputDialog(null, 
				"please select a user to load", 
				"load user", 
				JOptionPane.PLAIN_MESSAGE, 
				new ImageIcon(properties.getCompany()), list, list[0]);
		if(input != null){
			String username = input.toString();
			if(username.compareTo(EMPTY_USERNAME) != 0){
				properties.setUsername(username);
				loadUserProfile();
				updateBackground();
			}else{
				actionMenu.setEnabled(false);
				optionsMenu.setEnabled(false);
			}
		}
	}
	
	private void doCreateUser() throws IOException{
		Object input = JOptionPane.showInputDialog(null, 
				"please enter a unique username", 
				"create new user", 
				JOptionPane.PLAIN_MESSAGE, 
				new ImageIcon(properties.getCompany()), 
				null, null);
		if(input != null){
			String username = input.toString();
			properties.setUsername(username);
			properties.setPath("-1");
			if(saveNewUser()){
				updateBackground();
			}else{
				properties.setUsername(EMPTY_USERNAME);
				errMsg("username was not created");
			}
		}else{
			errMsg("username was not created");
		}
	}
	
	private void doLoadGoal() throws IOException{
		String[] goalList = loadGoalList();
		Object input = JOptionPane.showInputDialog(null, 
				"please select a goal to load", 
				"load goal", 
				JOptionPane.PLAIN_MESSAGE, 
				new ImageIcon(properties.getCompany()), 
				goalList, goalList[0]);
		if(input != null){
			String goal = input.toString();
			if(goal.compareTo("<empty>") != 0) {
				properties.setGoal(goal);
				description.setText(goal);
				loadGoal();
				updateBackground();
			}
		}
	}
	
	private void doCreateGoal() throws IOException{
		Object input = JOptionPane.showInputDialog(null, 
				"please enter a goal description", 
				"create new goal", 
				JOptionPane.PLAIN_MESSAGE, 
				new ImageIcon(properties.getCompany()), 
				null, null);
		if(input != null){
			String goal = input.toString();
			String[] choices = new String[]{"3", "5", "cancel"};
			int selection = JOptionPane.showOptionDialog(null, 
					"please select the number of steps requied to complete your goal", 
					"select goal type", 
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					new ImageIcon(properties.getCompany()), 
					choices, choices[0]);
			if(selection == 0 || selection == 1){
				properties.setGoal(goal);
				properties.setPath("" + choices[selection]);
				properties.setProgress(0);
				saveNewGoal();
				updateUserProfile();
				updateGoalInfo();
				saveGoalInfoMore();
				updateBackground();
				description.setText(goal);
			}else{
				errMsg("goal information was not saved");
			}
		}
	}
	
	private void doDeleteGoal() throws IOException{
		if(properties.getGoal().compareTo(EMPTY_GOAL) != 0){
			deleteGoal();
			deleteGoalInfo();
			deleteGoalInfoMore();
			properties.setGoal(EMPTY_GOAL);
			properties.setPath("-1");
			properties.setProgress(-1);
			description.setText(EMPTY_GOAL);
			updateBackground();
			updateUserProfile();
		}else{
			errMsg("no goal loaded");
		}
	}
	
	private void doDisplay() throws IOException{
		String info = getGoalInfoMore();
		JOptionPane.showMessageDialog(null, 
				"current goal:\n" + properties.getGoal()
				+"\n\ncreated on:\n" + info + "\n\nprogress:\n" 
				+ properties.getProgress() 
				+ "/" + properties.getPath(), 
				"goal info",
				JOptionPane.INFORMATION_MESSAGE, 
				new ImageIcon(properties.getCompany()));
	}
	
	private void doProgress() throws IOException{
		String previousGoal = properties.getGoal();
		String[] list = loadGoalList();
		int totalGoals = list.length;
		double avg = 0.0;
		String summary = "";
		DecimalFormat dFormat = new DecimalFormat(".##");
		
		for(int x = 0; x < list.length; x++){
			properties.setGoal(list[x]);
			loadGoal();
			avg = avg + (((double)properties.getProgress()) / ((double)Integer.parseInt(properties.getPath())));
			summary += properties.getGoal() + ": " + properties.getProgress() + "/" + properties.getPath() + "  ";
		}
		avg = avg / (double)totalGoals;
		properties.setGoal(previousGoal);
		loadGoal();
		
		JOptionPane.showMessageDialog(null, "total goals:\n" + totalGoals 
				+ "\n\naverage completion:\n" + dFormat.format(avg) 
				+ "\n\n" + summary,
				"user " + properties.getUsername() + "'s goal summary",
				JOptionPane.PLAIN_MESSAGE,
				new ImageIcon(properties.getCompany()));
	}
	
	private void updateBackground(){
		String path = properties.getPath();
		int progress = properties.getProgress();
		String imgPath = "";
		
		if(path.compareTo("3") == 0){
			if(progress == 0){
				imgPath = properties.getJar3Empty();
			}else if(progress == 1){
				imgPath = properties.getJar2_1();
			}else if(progress == 2){
				imgPath = properties.getJar2_2();
			}else if(progress == 3){
				imgPath = properties.getJar2_3();
			}
			if(properties.completed()){
				add.setEnabled(false);
			}else{
				add.setEnabled(true);
			}
			if(properties.getProgress() == 0){
				remove.setEnabled(false);
			}else{
				remove.setEnabled(true);
			}
			actionMenu.setEnabled(true);
			optionsMenu.setEnabled(true);
		}else if(path.compareTo("5") == 0){
			if(progress == 0){
				imgPath = properties.getJar5Empty();
			}else if(progress == 1){
				imgPath = properties.getJar3_1();
			}else if(progress == 2){
				imgPath = properties.getJar3_2();
			}else if(progress == 3){
				imgPath = properties.getJar3_3();
			}else if(progress == 4){
				imgPath = properties.getJar3_4();
			}else if(progress == 5){
				imgPath = properties.getJar3_5();
			}
			if(properties.completed()){
				add.setEnabled(false);
			}else{
				add.setEnabled(true);
			}
			if(properties.getProgress() == 0){
				remove.setEnabled(false);
			}else{
				remove.setEnabled(true);
			}
			actionMenu.setEnabled(true);
			optionsMenu.setEnabled(true);
		}else if(path.compareTo("-1") == 0){
			imgPath = properties.getEmptyUser();
			add.setEnabled(false);
			remove.setEnabled(false);
			actionMenu.setEnabled(true);
			optionsMenu.setEnabled(false);
		}
		ImageIcon backgroundImage = new ImageIcon(imgPath);
		background.setIcon(backgroundImage);
	}
	
	private void doAbout(){
		JOptionPane.showMessageDialog(null, 
				"product\ngoal_jar\n\nby\n???\n\ncontact:\n"
				+ "mike.drummond.802@hotmail.com", 
				"about ???", 
				JOptionPane.INFORMATION_MESSAGE, 
				new ImageIcon(properties.getCompany()));
	}
	
	private void doFAQ(){
		JOptionPane.showMessageDialog(null, 
				"banned characters include:\n\n,_,\n\nsee about", 
				"FAQ", 
				JOptionPane.INFORMATION_MESSAGE, 
				new ImageIcon(properties.getCompany()));
	}
	
	private void errMsg(String msg){
		JOptionPane.showMessageDialog(null, 
				msg, 
				"NOTICE!!!", 
				JOptionPane.INFORMATION_MESSAGE, 
				new ImageIcon(properties.getCompany()));
	}

	public void init(){
		if(!initialized){
			create();
			initialized = true;
		}
	}
}
