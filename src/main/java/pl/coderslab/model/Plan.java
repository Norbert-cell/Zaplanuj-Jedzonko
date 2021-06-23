package pl.coderslab.model;

import pl.coderslab.dao.PlanDao;

import java.sql.Date;

public class Plan {
    private int id;
    private String name;
    private String description;
    private Date created;
    private int adminID;

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", adminID=" + adminID +
                '}';
    }

    public Plan(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Plan(String name, String description, Date created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Plan(){}

    //dodaje nowy parametr, getter i setter


    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
