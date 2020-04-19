/**
 * @author mike802
 * @version 1.0 - 2/26/2013
 */
package core;

import java.io.File;

public class Properties {
	
	private String rootDir;
	private String userDir;
	private String img;
	private String company;
	private String logo;
	private String userlist;
	private String username;
	private String usersettings;
	private String saveDir;
	
	private String goal;
	private String path = "";
	private int progress = -1;
	
	public Properties(String root, String usrDir){
		rootDir = root;
		userDir = usrDir;
		username = "";
		img = rootDir + File.separator + "img";
		company = img + File.separator + "company.png";
		logo = img + File.separator + "logo.png";
		String companyPath = userDir + File.separator + "AppData" + File.separator + "Local"
				+ File.separator + "brand-aware";
		File companyFolder = new File(companyPath);
		if(!companyFolder.exists()) {
			companyFolder.mkdir();
		}
		
		String productPath = companyPath + File.separator + "goal_jar";
		File productFolder = new File(productPath);
		if(!productFolder.exists()) {
			productFolder.mkdir();
		}
		
		userlist = productPath + File.separator + "userlist.txt";
		String settingsPath = productPath + File.separator + "settings";
		File settingsFolder = new File(settingsPath);
		if(!settingsFolder.exists()) {
			settingsFolder.mkdir();
		}
		usersettings = settingsPath + File.separator + "usersettings.txt";
		saveDir = productPath + File.separator + "save_data";
		File saveFolder = new File(saveDir);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();
		}
	}
	
	public String getRootDir(){
		return rootDir;
	}
	public String getSaveDir(){
		return saveDir;
	}
	public String getUserDir(){
		return saveDir + File.separator + username;
	}
	public String getUserGoals(){
		return saveDir + File.separator + username + File.separator + "goals.txt";
	}
	public String getGoalInfo(){
		return saveDir + File.separator + username + File.separator + "goal_info.txt";
	}
	public String getGoalInfoMore(){
		return saveDir + File.separator + username + File.separator + "goal_info_more.txt";
	}
	public String getCompany(){
		return company;
	}
	public String getLogo(){
		return logo;
	}
	public String getUserlist(){
		return userlist;
	}
	public String getUsersettings(){
		return usersettings;
	}
	
	public String getBackground(){
		return img + File.separator + "jar.png";
	}
	public String getEmptyUser(){
		return img + File.separator + "jar_v2.png";
	}
	public String getJar3Empty(){
		return img + File.separator + "2.3" + File.separator + "jar.png";
	}
	public String getJar5Empty(){
		return img + File.separator + "3.5" + File.separator + "jar_3.0.png";
	}
	public String getJar2_1(){
		return img + File.separator + "2.3" + File.separator + "jar_2.1.png";
	}
	public String getJar2_2(){
		return img + File.separator + "2.3" + File.separator + "jar_2.2.png";
	}
	public String getJar2_3(){
		return img + File.separator + "2.3" + File.separator + "jar_2.3.png";
	}
	public String getJar3_1(){
		return img + File.separator + "3.5" + File.separator + "jar_3.1.png";
	}
	public String getJar3_2(){
		return img + File.separator + "3.5" + File.separator + "jar_3.2.png";
	}
	public String getJar3_3(){
		return img + File.separator + "3.5" + File.separator + "jar_3.3.png";
	}
	public String getJar3_4(){
		return img + File.separator + "3.5" + File.separator + "jar_3.4.png";
	}
	public String getJar3_5(){
		return img + File.separator + "3.5" + File.separator + "jar_3.5.png";
	}
	
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String name){
		username = name;
	}
	public void setGoal(String description){
		goal = description;
	}
	public String getGoal(){
		return goal;
	}
	public void incrementProgress(){
		if(Integer.parseInt(path) == 3 && progress < 3){
			progress++;
			System.out.println("adding");
		}else if(Integer.parseInt(path) == 5 && progress < 5){
			progress++;
			System.out.println("adding");
		}
	}
	public void decrementProgress(){
		if(progress > 0){
			progress--;
		}
	}
	public int getProgress(){
		return progress;
	}
	public void setProgress(int value){
		progress = value;
	}
	public void setPath(String value){
		path = value;
	}
	public String getPath(){
		return path;
	}
	
	public boolean completed(){
		if(path.compareTo("3") == 0 && progress == 3){
			return true;
		}else if(path.compareTo("5") == 0 && progress == 5){
			return true;
		}
		return false;
	}
}
