package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class EndedTask extends NamedEntity{
    private Integer id;
    private String task;
    private Timestamp term;
    private Timestamp dateOfCompletion;
    private Priority priority;


    public EndedTask(Integer id, String task, Timestamp term, Timestamp dateOfCompletion, Priority priority) {
        super(task);
        this.id = id;
        this.task = task;
        this.term = term;
        this.dateOfCompletion = dateOfCompletion;
        this.priority = priority;
    }

    public EndedTask(String task, Timestamp term, Timestamp dateOfCompletion, Priority priority) {
        super(task);
        this.task = task;
        this.term = term;
        this.dateOfCompletion = dateOfCompletion;
        this.priority = priority;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Timestamp getTerm() {
        return term;
    }

    public void setTerm(Timestamp term) {
        this.term = term;
    }

    public Timestamp getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Timestamp dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return "EndedTask{" +
                " " + id +
                "\t" + task +
                "\t\t" + sdf.format(term) +
                "\t" + sdf.format(dateOfCompletion) +
                "\t" + priority +
                '}';
    }


}
