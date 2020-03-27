package raposa_e_coelho;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing attributes and behaviors in common among all animals
 * 
 * @author Arthur Masson, Dian de Grazia and Helena Muniz
 * @version 2019-06-29
 */
public abstract class Animal implements Actor  {
	
    private boolean alive;
    private Location location;
    private Field field;
    private static final Random rand = new Random();
    protected int age;
    
    /**
     * Create a new animal
     * @param field The field
     * @param location Location the animal will occupy
     */
    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        age=0;
    }
    /**
     * @return If the animal is alive
     */
     public boolean isAlive()
    {
        return alive;
    }
	/**
	 * @return If the animal is active
	 */
    @Override
    public boolean isActive(){
         return isAlive();
    }
    /**
	 * Kill the animal
	 */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
    /**
	 * @return The Location
	 */
    public Location getLocation()
    {
        return location;
    }
    /**
	 * @return The field
	 */ 
    public Field getField()
    {
        return field;
    }
    /**
	 * Define the new location
	 * @param newLocation New location that will replace the old
	 */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    /**
	 * @return If the animal can breed.
	 */ 
    protected boolean canBreed()
    {
        return age >= getBreedingAge();
        
    }
    /**
	 * Increment the age of the animal.
	 */ 
    protected void incrementAge()
    {
        age++;
        if(age > getMAX_AGE()) {
            setDead();
        }
    }
    /**
	 * @return The number of births
	 */ 
    protected int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBREEDING_PROBABILITY()) {
            births = rand.nextInt(getMAX_LITTER_SIZE()) + 1;
        }
        return births;
    }  
  
    /**
	 * Give birth to new animals
	 * @param NewActors List of actors
	 */ 
   protected void giveBirth(ArrayList<Actor> NewActors){
        Field field = getField();
        List<Location> free=field.getFreeAdjacentLocations(getLocation());
        int births=breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Animal young = getAnimal(field,loc);
            NewActors.add(young);
        }
    }   
	abstract protected int getBreedingAge();
	abstract protected int getMAX_AGE();
	abstract protected double getBREEDING_PROBABILITY();
	abstract protected int getMAX_LITTER_SIZE();
	abstract protected Animal getAnimal(Field field,Location loc);
	
	@Override
	public abstract void act(ArrayList<Actor> NewActors);
    
}
    
    
    

