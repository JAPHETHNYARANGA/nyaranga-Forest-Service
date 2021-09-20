
package wildlifeTracker;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;

public class NonEndangeredTest {
    @Rule
    public DatabaseRules database = new DatabaseRules();
    @Test
    public void NonEndangered_instantiatesCorrectly_true(){
        NonEndangered testanimal = new NonEndangered("bees");
        assertEquals(testanimal instanceof NonEndangered, true);
    }
    @Test
    public void NonEndangered_instantiatesWithName_String(){
        NonEndangered testanimal = new NonEndangered("bees");
        assertEquals("bees", testanimal.getName());
    }
    @Test
    public void equals_returnsTrueIfAnimalNamesAreTrue_True(){
        NonEndangered firstAnimal = new NonEndangered("bees");
        NonEndangered secondAnimal = new NonEndangered("bees");
        assertTrue(firstAnimal.equals(secondAnimal));
    }
    @Test
    public void save_savesNonEndangeredAnimalsIntoDatabase(){
        NonEndangered testanimal = new NonEndangered("bees");
        testanimal.save();
        assertTrue(NonEndangered.all().get(0).equals(testanimal));
    }
    @Test
    public void save_saveAssignsIdToNonEndangeredAnimal_int(){
        NonEndangered testAnimal = new NonEndangered("bees");
        testAnimal.save();
        NonEndangered savedAnimal = NonEndangered.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }
    @Test
    public void all_returnsAllInstancesOfNonEndangeredAnimals_true(){
        NonEndangered firstAnimal = new NonEndangered("bees");
        firstAnimal.save();
        NonEndangered secondAnimal = new NonEndangered("zebra");
        secondAnimal.save();
        assertEquals(true, NonEndangered.all().get(0).equals(firstAnimal));
        assertEquals(true, NonEndangered.all().get(1).equals(secondAnimal));
    }
    @Test
    public void find_returnsNonEndangeredAnimalsWithSameId_firstAnimal(){
        NonEndangered firstAnimal = new NonEndangered("bees");
        firstAnimal.save();
        NonEndangered secondAnimal = new NonEndangered("zebra");
        secondAnimal.save();
        assertEquals(NonEndangered.find(firstAnimal.getId()), firstAnimal);
    }
}
