package pl.coderslab.model;

import java.sql.Date;

public class Recipe {

    private int id;
    private String name;
    private String ingredients;
    private String description;
    private Date created;
    private Date updated;
    private int preparationTime;
    private String preparation;
    private int adminId;

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", preparationTime='" + preparationTime + '\'' +
                ", preparation='" + preparation + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }

    public Recipe() {

        }

    public Recipe(String name, String ingredients, String description, Date created, Date updated, int preparationTime, String preparation, int adminId) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.preparationTime = preparationTime;
        this.preparation = preparation;
        this.adminId = adminId;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////  GETTERY  ///////////////////////////////////////////////////////

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public String getPreparation() {
        return preparation;
    }

    public int getAdminId() { return adminId ;}

/////////////////////////////////////////////////////////////////////////////////////////
//////////////////SETTETRY//////////////////////////////////////////////////////////////


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setAdminId(int adminId) { this.adminId = adminId; }
}
