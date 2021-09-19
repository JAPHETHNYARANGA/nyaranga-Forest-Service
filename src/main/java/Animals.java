package wildlifeTracker;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Animals {
    public int id;
    public String name;
    public boolean endangered;



    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean getEndangered(){
        return endangered;
    }
    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Animals)){
            return false;
        }
        else{
            Animals newAnimal = (Animals) otherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getId()==(newAnimal.getId());
        }
    }
    public void save(){
        if (name.equals("") ) {
            throw new IllegalArgumentException("Please enter a name.");
        }
        try(Connection connect = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, endangered) VALUES (:name, :endangered);";
            this.id = (int) connect.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("endangered", this.endangered)
                    .executeUpdate()
                    .getKey();
        }
    }
}
