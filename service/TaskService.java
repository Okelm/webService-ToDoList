package com.bwidlarz.toDoList.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;



public class taskService {
	
	
	private Map<Long, task> tasks = DatabaseClass.gettasks();


	
	public taskService(){
			tasks.put(1L, new task(1, "Hello World", "bartek"));
			tasks.put(2L, new task(2, "Hello Hej", "michał"));

	}
	
	
	public List<task> getAlltasks() {
		return new ArrayList<task>(tasks.values());
	}
	
	public List<task> gettasksForYeat(int year) {
		List<task> tasksForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(task task : tasks.values()){
			cal.setTime(task.getCreated());
			if(cal.get(Calendar.YEAR)==year){
				tasksForYear.add(task);
			}
		}
		return tasksForYear;
	}
	
	public List<task> gettasksPagination(int start, int size) {
		ArrayList<task> list = new ArrayList<task>(tasks.values());
		if(start + size > list.size()) return new ArrayList<task>();
		return list.subList(start, start + size);
		
	}

	public task gettask(long id){
		task task = tasks.get(id);
		if(task == null){
			throw new DataNotFoundException("task with id " + id + " not found");
		}
		return task;
	}
	
	public task addtask(task task){
		task.setId(tasks.size() + 1);
		tasks.put(task.getId(), task);
		return task;
	}
	
	public task updatetask(task task){
		if(task.getId()<=0){
			return null;
		}
		tasks.put(task.getId(), task);
		return task;
	}
	
	public task removetask(long id){
		return tasks.remove(id);
	}
	

}
