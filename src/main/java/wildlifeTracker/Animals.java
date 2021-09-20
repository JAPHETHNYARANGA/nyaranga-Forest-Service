package wildlifeTracker;
import org.sql2o.*;



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

    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Animals newAnimal)){
            return false;
        }
        else{
            return this.getName().equals(newAnimal.getName()) &&
                    this.getId()==(newAnimal.getId());
        }
    }
    public void save(){
        if (name.equals("") ) {
            throw new IllegalArgumentException("You must Enter Name.");
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