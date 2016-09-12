package com.bwidlarz.toDoList.database;

import java.util.HashMap;
import java.util.Map;


public class DatabaseClass {
	
	public DatabaseClass(){
		
	}
	
	private static Map<Long, task> tasks = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, task> gettasks(){
		return tasks;
	}
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}
}
