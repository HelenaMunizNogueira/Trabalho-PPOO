package raposa_e_coelho;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 * 
 * @author Arthur Masson, Dian de Grazia and Helena Muniz
 * @version 2019-06-29
 */
public class Fox extends Animal
{
    // Characteristics shared by all foxes (static fields).
    private static final int BREEDING_AGE = 5;
    private static final int MAX_AGE = 150;
    private static final double BREEDING_PROBABILITY = 0.09;
    private static final int MAX_LITTER_SIZE = 3;
    // number of steps a fox can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 7;
    // A shared random number generator to control breeding.
    private static final Random rand = new Random();
    private int foodLevel;

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with random age.
     * 
     * @param randomAge If true, the fox will have random age and hunger level.
     */
    public Fox(boolean randomAge,Field field,Location location)
    {
        super(field,location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        }
        else {
            foodLevel = RABBIT_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     */
    public void act(ArrayList<Actor> NewActors)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(NewActors);
            Location location=getLocation();
            Location newLocation=findFood(location);
            if(newLocation == null){
            	newLocation= getField().freeAdjacentLocation(location);
            }
            if(newLocation != null){
                setLocation(newLocation);
            }
            else{
                setDead();
            }
        }
    }
    
    /**
	 * Instructs the fox to look for rabbits adjacent to their current location, only the first rabbit is eaten
	 * @param location where in the field is located
 	 * @return where food was found or null otherwise
     */
    private Location findFood(Location location){
	    Field field=getField();
	    List<Location> adjacent = field.adjacentLocations(location);
	    Iterator<Location> it=adjacent.iterator();
	    while(it.hasNext()){
	        Location where=it.next();
	        Object animal=field.getObjectAt(where);
	        if(animal instanceof Rabbit){
	            Rabbit rabbit=(Rabbit)animal;
	            if(rabbit.isAlive()){
	                rabbit.setDead();
	                
	                foodLevel=RABBIT_FOOD_VALUE;
	                return where;
	            }
	        }
	    }
	    return null;
    }

    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
           setDead();
        }
    }
   
    /**
     * @return The breeding age.
     */
    @Override
    public int getBreedingAge(){
        return BREEDING_AGE;
    }
    /**
     * @return maximum age.
     */
    @Override
    public int getMAX_AGE(){
        return MAX_AGE;
    }
    /**
     * @return breeding probability.
     */
    @Override
    public double getBREEDING_PROBABILITY(){
        return BREEDING_PROBABILITY;
    }
    /**
     * @return maximum litter size.
     */
    @Override
    public int getMAX_LITTER_SIZE(){
        return MAX_LITTER_SIZE;
    }
    /**
     * @return new young animal.
     */
    @Override
    public Animal getAnimal (Field field,Location loc){
        Fox young = new Fox(false,field,loc);
        return young;
    }
}
