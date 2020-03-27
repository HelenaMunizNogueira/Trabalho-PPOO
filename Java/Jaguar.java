package raposa_e_coelho;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
	
/**
 * A simple model of a jaguar.
 * Jaguars age, move, eat rabbits and foxes, and die.
 * 
 * @author Arthur Masson, Dian de Grazia and Helena Muniz
 * @version 2019-06-29
 */


public class Jaguar extends Animal {

	private static final int BREEDING_AGE = 5;
	private static final int MAX_AGE = 300;
	private static final double BREEDING_PROBABILITY = 0.09;
	private static final int MAX_LITTER_SIZE = 3;
	private static final int RABBIT_FOOD_VALUE = 5;
	private static final int FOX_FOOD_VALUE = 10;
	// A shared random number generator to control breeding.
	private static final Random rand = new Random();
   
	private int foodLevel;
	
	/**
     * Create a Jaguar. A Jaguar can be created as a new born (age zero
     * and not hungry) or with random age.
     * 
     * @param randomAge If true, the jaguar will have random age and hunger level.
     */
	public Jaguar(boolean randomAge,Field field,Location location)
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
     * This is what the jaguar does most of the time: it hunts for
     * rabbits and foxes. In the process, it might breed, die of hunger,
     * or die of old age.
     */
	public void act(ArrayList<Actor> NewActors)
	{
	    incrementAge();
	    incrementHunger();
	    if(isAlive()) {
	        giveBirth(NewActors);
	        Location location = getLocation();

	        Location newLocation = findFood(location);
	        if(newLocation == null){
	        	
	        	newLocation= getField().freeAdjacentLocation(location);
	        }

	        if(newLocation != null){
	            setLocation(newLocation);
	        }
	        else {
	                setDead();
	        }
	    }
	}
	
	/**
	 * Instructs the jaguar to look for rabbits and foxes adjacent to their current location, 
	 * only the first rabbit or fox is eaten.
	 * @param location where in the field is located
 	 * @return where food was found or null otherwise
     */
	private Location findFood(Location location) {
		Field field=getField();
		List<Location> adjacent = field.adjacentLocations(location);
		Iterator<Location> it=adjacent.iterator();
		while(it.hasNext()){
		    Location where = it.next();
		    Object animal=field.getObjectAt(where);
		    if(animal instanceof Rabbit){
		        Rabbit rabbit = (Rabbit)animal;
		        if(rabbit.isAlive()){
		            rabbit.setDead();
		            foodLevel=RABBIT_FOOD_VALUE;
		            return where;
		        }
		    }
		    else if (animal instanceof Fox) {
		    	Fox fox = (Fox)animal;
		        if(fox.isAlive()){
		            fox.setDead();
		            foodLevel=FOX_FOOD_VALUE;
		            return where;
		        }
		    }
		}
		return null;
	}
	
	/**
     * Make this jaguar more hungry. This could result in the jaguar's death.
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
        Jaguar young = new Jaguar(false,field,loc);
        return young;
    }
}
