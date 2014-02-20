/**
 * Jan 25, 2014
 * Aron Aperauch
 * ISM 6257, Section 0305 - Fall 2013
 * Dr. Seema Bandyopadhyay
 *
 * Task.java
 */

package taskmgt.Models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task implements Serializable, Comparable<Task> {
   //Attributes
    private int id;
    private String title;
    private String owner;
    private int projectID;
    private Date startDate;
    private Date endDate;
    private State status;
    private final ModelType type = ModelType.Task;
    private final SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
    
    //Constructors
    public Task(String title,String owner,int projectID, Date startDate, Date endDate){
        this.title=title;
        this.owner=owner;
        this.projectID=projectID;
        this.startDate=startDate;
        this.endDate=endDate;
        this.status=State.New;
    }
    
    public Task(int id, String title,String owner,int projectID, Date startDate, Date endDate){
        this.title=title;
        this.owner=owner;
        this.projectID=projectID;
        this.startDate=startDate;
        this.endDate=endDate;
        this.status=State.New;
        this.id = id;
    }
    
    public Task(String title, String ownerEmail, int projectID, Date start, Date end, State status) {
        this.title=title;
        this.owner=ownerEmail;
        this.projectID=projectID;
        this.startDate=start;
        this.endDate=end;
        this.status=status;
    }

    public Task(String[] strArr) {
        this.id = Integer.valueOf(strArr[0]);
        this.title=strArr[1];
        this.owner=strArr[2];
        this.projectID=Integer.valueOf(strArr[3]);
        
        try {
            this.startDate = simpleDate.parse(strArr[4]);
            this.endDate = simpleDate.parse(strArr[5]);
        } catch (ParseException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.status = State.valueOf(strArr[6]);
    }
    
    //Used for testing purposes only - DEBUG
     public Task(){
        this.title="";
        this.owner="";
        this.projectID=0;
        this.startDate=null;
        this.endDate=null;
        this.status=State.New;
    }
    
    //Get
    public int getID() { return this.id; }
    public String getTitle() { return this.title; }
    public String getOwner() { return this.owner; }
    public int getProjectID(){return this.projectID;}
    public Date getStartDate() { return this.startDate; }
    public Date getEndDate() { return this.endDate; }
    public State getStatus() { return this.status; }
    public ModelType getType() { return this.type; }
    
    //Set
    public void setID(int nextTaskID) { this.id = nextTaskID; }
    public void setTitle(String title){ this.title=title;}
    public void setOwner(String owner){this.owner=owner;}
    public void setProjectID(int projectID){this.projectID=projectID;}
    public void setStartDate(Date startDate){this.startDate=startDate;}
    public void setEndDate(Date endDate){this.endDate=endDate;}
    public void setStatus(State status){this.status=status;}        
    
    @Override
    public int compareTo(Task t)
    {
        return this.id - t.getID();
    }
    
    public String[] toStringArray() {
        LinkedList<String> attrs = new LinkedList<>();

        attrs.add(Integer.toString(id));
        attrs.add(owner);
        attrs.add(title);
        attrs.add(Integer.toString(projectID));
        attrs.add(simpleDate.format(startDate));
        attrs.add(simpleDate.format(endDate));
        attrs.add(status.name());

       return attrs.toArray(new String[attrs.size()]);
    }
    
    public String toCSVStringArray() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(",");
        sb.append(Integer.toString(id)).append(",");
        sb.append(owner).append(",");
        sb.append(title).append(",");
        sb.append(Integer.toString(projectID)).append(",");
        sb.append(simpleDate.format(startDate)).append(",");
        sb.append(simpleDate.format(endDate)).append(",");
        sb.append(status.name());
        
        return sb.toString();
    }
    
    public String[] toTableRow() {
        LinkedList<String> attrs = new LinkedList<>();
        
        attrs.add(Integer.toString(id));
        attrs.add(title);        
        attrs.add(simpleDate.format(startDate));
        attrs.add(simpleDate.format(endDate));    
        attrs.add(owner);
        attrs.add(status.name());

       return attrs.toArray(new String[attrs.size()]);
    }

    
    
}
