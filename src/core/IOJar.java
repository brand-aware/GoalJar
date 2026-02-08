/**
 * @author mike802
 * @version 1.0 - 2/26/2013
 */
package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class IOJar extends CommonJar{
	
	protected String[] loadUserList() throws IOException{
		ArrayList<String> userList = new ArrayList<String>();
		String[] list;
		File listFile = new File(properties.getUserlist());
		if(listFile.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(listFile));
			while(reader.ready()){
				String line = reader.readLine();
				userList.add(line);
			}
			reader.close();
		}else{
			listFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(listFile));
			writer.write(EMPTY_USERNAME);
			writer.close();
		}
		if(userList.size() > 0){
			list = new String[userList.size()];
			for(int x = 0; x < userList.size(); x++){
				list[x] = userList.get(x);
			}
		}else{
			list = new String[]{EMPTY_USERNAME};
		}
		return list;
	}
	
	protected String[] loadGoalList() throws IOException{
		ArrayList<String> goalList = new ArrayList<String>();
		String[] list;
		File listFile = new File(properties.getUserGoals());
		if(listFile.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(listFile));
			while(reader.ready()){
				String line = reader.readLine();
				goalList.add(line);
			}
			reader.close();
		}
		if(goalList.size() > 0){
			list = new String[goalList.size()];
			for(int x = 0; x < goalList.size(); x++){
				list[x] = goalList.get(x);
			}
			
			return list;
		}
		return new String[] {"<empty>"};
	}
	
	protected boolean saveNewUser() throws IOException{
		String username = properties.getUsername();
		File userList = new File(properties.getUserlist());
		String buffer = "";
		if(userList.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(userList));
			while(reader.ready()){
				String line = reader.readLine();
				if(line.compareTo(username) == 0){
					reader.close();
					return false;
				}else if(line.compareTo(EMPTY_USERNAME) == 0){
					reader.close();
					break;
				}else{
					buffer += username + "\n";
				}
			}
			reader.close();
		}else{
			userList.createNewFile();
		}
		buffer += username;
		BufferedWriter writer = new BufferedWriter(new FileWriter(userList));
		writer.write(buffer);
		writer.close();
		
		return true;
	}
	
	protected boolean saveNewGoal() throws IOException{
		makeUserSaveDir();
		String goal = properties.getGoal();
		File goals = new File(properties.getUserGoals());
		String buffer = "";
		if(goals.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(goals));
			while(reader.ready()){
				String line = reader.readLine();
				if(line.compareTo(goal) == 0){
					reader.close();
					return false;
				}else if(line.compareTo(EMPTY_GOAL) == 0){
					reader.close();
					break;
				}else{
					buffer += line + "\n";
				}
			}
		}else{
			goals.createNewFile();
		}
		buffer += goal;
		BufferedWriter writer = new BufferedWriter(new FileWriter(goals));
		writer.write(buffer);
		writer.close();
		return true;
	}
	
	protected void loadUserProfile() throws IOException{
		boolean loaded = false;
		File settings = new File(properties.getUsersettings());
		String username = properties.getUsername();
		if(settings.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(settings));
			while(reader.ready()){
				String line = reader.readLine();
				String[] data = line.split(SPLIT_PATTERN);
				if(data[0].compareTo(username) == 0){
					properties.setGoal(data[1]);
					properties.setPath(data[2]);
					properties.setProgress(Integer.parseInt(data[3]));
					loaded = true;
					description.setText(data[1]);
					break;
				}
			}
			reader.close();
		}else{
			settings.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(settings));
			writer.write(username + ",_," + EMPTY_GOAL + ",_," + "-1" + ",_," + "-1");
			writer.close();
		}
		if(!loaded){
			properties.setGoal(EMPTY_GOAL);
			properties.setPath("-1");
			properties.setProgress(-1);
		}
	}
	
	protected void loadGoal() throws IOException{
		makeUserSaveDir();
		boolean loaded = false;
		File goalInfo = new File(properties.getGoalInfo());
		String goal = properties.getGoal();
		if(goalInfo.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(goalInfo));
			while(reader.ready()){
				String line = reader.readLine();
				String[] data = line.split(SPLIT_PATTERN);
				if(data[0].compareTo(goal) == 0){
					properties.setPath(data[1]);
					properties.setProgress(Integer.parseInt(data[2]));
					loaded = true;
					break;
				}
			}
			reader.close();
		}
		if(!loaded){
			properties.setPath("-1");
			properties.setProgress(-1);
		}
	}
	
	protected void updateUserProfile() throws IOException{
		File settings = new File(properties.getUsersettings());
		String buffer = "";
		if(settings.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(settings));
			while(reader.ready()){
				String line = reader.readLine();
				String[] data = line.split(SPLIT_PATTERN);
				if(data[0].compareTo(properties.getUsername()) != 0){
					buffer += line + "\n";
				}
			}
			reader.close();
		}else{
			settings.createNewFile();
		}
		buffer += properties.getUsername() + SPLIT_PATTERN +
				properties.getGoal() + SPLIT_PATTERN +
				properties.getPath() + SPLIT_PATTERN +
				properties.getProgress();
		BufferedWriter writer = new BufferedWriter(new FileWriter(settings));
		writer.write(buffer);
		writer.close();
	}
	
	protected void updateGoalInfo() throws IOException{
		makeUserSaveDir();
		String buffer = "";
		File goalInfo = new File(properties.getGoalInfo());
		
		if(goalInfo.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(goalInfo));
			while(reader.ready()){
				String line = reader.readLine();
				String[] data = line.split(SPLIT_PATTERN);
				if(data[0].compareTo(properties.getGoal()) != 0){
					buffer += line + "\n";
				}
			}
			reader.close();
		}else{
			goalInfo.createNewFile();
		}
		buffer += properties.getGoal() + SPLIT_PATTERN +
				properties.getPath() + SPLIT_PATTERN +
				properties.getProgress();
		BufferedWriter writer = new BufferedWriter(new FileWriter(goalInfo));
		writer.write(buffer);
		writer.close();
	}
	
	protected void saveGoalInfoMore() throws IOException{
		makeUserSaveDir();
		String goal = properties.getGoal();
		File goalInfoMore = new File(properties.getGoalInfoMore());
		String buffer = "";
		if(goalInfoMore.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(goalInfoMore));
			while(reader.ready()){
				String line = reader.readLine();
				String[] data = line.split(SPLIT_PATTERN);
				if(data[0].compareTo(goal) != 0){
					buffer += line + "\n";
				}
			}
			reader.close();
		}else{
			goalInfoMore.createNewFile();
		}
		LocalDateTime timePoint = LocalDateTime.now();
		int year = timePoint.getYear();
		Month month = timePoint.getMonth();
		String monthName = month.name();
		int day = timePoint.getDayOfMonth();
		int hour = timePoint.getHour();
		int minute = timePoint.getMinute();
		
		String date = day + " " + monthName + " " + year + ", " + hour + ":" + minute;
		buffer += goal + SPLIT_PATTERN + date;
		BufferedWriter writer = new BufferedWriter(new FileWriter(goalInfoMore));
		writer.write(buffer);
		writer.close();
	}
	
	protected String getGoalInfoMore() throws IOException{
		makeUserSaveDir();
		String goal = properties.getGoal();
		File goalInfoMore = new File(properties.getGoalInfoMore());
		if(goalInfoMore.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(goalInfoMore));
			while(reader.ready()){
				String line = reader.readLine();
				String[] data = line.split(SPLIT_PATTERN);
				if(data[0].compareTo(goal) == 0){
					reader.close();
					return data[1];
				}
			}
			reader.close();
		}
		return "???";
	}
	
	protected void deleteGoal() throws IOException{
		String goal = properties.getGoal();
		BufferedReader reader = new BufferedReader(new FileReader(properties.getUserGoals()));
		String buffer = "";
		while(reader.ready()){
			String line = reader.readLine();
			if(line.compareTo(goal) != 0){
				buffer += line;
			}
		}
		reader.close();
		if(buffer.contains("\n")){
			buffer = buffer.substring(0, buffer.lastIndexOf("\n"));
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(properties.getUserGoals()));
		writer.write(buffer);
		writer.close();
	}
	
	protected void deleteGoalInfo() throws IOException{
		String goal = properties.getGoal();
		BufferedReader reader = new BufferedReader(new FileReader(properties.getGoalInfo()));
		String buffer = "";
		while(reader.ready()){
			String line = reader.readLine();
			String[] data = line.split(SPLIT_PATTERN);
			if(data[0].compareTo(goal) != 0){
				buffer += line + "\n";
			}
		}
		reader.close();
		if(buffer.contains("\n")){
			buffer = buffer.substring(0, buffer.lastIndexOf("\n"));
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(properties.getGoalInfo()));
		writer.write(buffer);
		writer.close();
	}
	
	protected void deleteGoalInfoMore() throws IOException{
		String goal = properties.getGoal();
		BufferedReader reader = new BufferedReader(new FileReader(properties.getGoalInfoMore()));
		String buffer = "";
		while(reader.ready()){
			String line = reader.readLine();
			String[] data = line.split(SPLIT_PATTERN);
			if(data[0].compareTo(goal) != 0){
				buffer += line + "\n";
			}
		}
		reader.close();
		if(buffer.contains("\n")){
			buffer = buffer.substring(0, buffer.lastIndexOf("\n"));
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(properties.getGoalInfoMore()));
		writer.write(buffer);
		writer.close();
	}
	
	private void makeUserSaveDir(){
		File saveDir = new File(properties.getSaveDir());
		if(!saveDir.exists()){
			saveDir.mkdir();
		}
		File userDir = new File(properties.getUserDir());
		if(!userDir.exists()){
			userDir.mkdir();
		}	
	}

}
