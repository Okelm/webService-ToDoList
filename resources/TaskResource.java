package com.bwidlarz.toDoList.resources


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;



import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;


@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class taskResource {

	TaskService taskService = new TaskService();
	
	@GET
	public List<Task> getTask(@BeanParam TaskFilterBean filterBean){
		if(filterBean.getYear()>0){
			return taskService.gettasksForYeat(filterBean.getYear());
		}
		if(filterBean.getStart()>=0 && filterBean.getSize()>0){
			return taskService. gettasksPagination(filterBean.getStart(), filterBean.getSize());
		}
		return taskService.getAlltasks();
	}
	
	
	
	@POST
	public Response addtask(task task, @Context UriInfo uriInfo) throws URISyntaxException{
		
		task newtask = taskService.addtask(task);
		String newId = String.valueOf(newtask.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newtask).build();
		
	}
	
	@PUT
	@Path("/{taskId}")
	public task updatetask(@PathParam("taskId") long id, task task){
		task.setId(id);
		return taskService.updatetask(task);
	}
	
	
	@DELETE
	@Path("/{taskId}")
	public void deletetask(@PathParam("taskId") long id){
		taskService.removetask(id);
	}
	
	@GET
	@Path("/{taskId}")
	public task gettask(@PathParam("taskId") long id, @Context UriInfo uriInfo){	
		task task =  taskService.gettask(id);
		task.addLink(getUriForSelf(uriInfo, task), "self");
		task.addLink(getUriForProfile(uriInfo, task), "profile");
		task.addLink(getUriForComments(uriInfo, task), "comment");

		return task;
	}
	
	
	@Path("/{taskId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
