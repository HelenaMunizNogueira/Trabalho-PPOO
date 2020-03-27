package raposa_e_coelho;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * @author Arthur Masson, Dian de Grazia and Helena Muniz
 * @version 2019-06-29
 */
public class Rabbit extends Animal
{
    // Characteristics shared by all rabbits (static fields).

	private static final int BREEDING_AGE = 5;
	private static final int MAX_AGE = 50;
	private static final double BREEDING_PROBABILITY = 0.15;
	private static final int MAX_LITTER_SIZE = 4;
	// A shared random number generator to control breeding.
    private static final Random rand = new Random();
   
    
    /**
     * Create a rabbit. A rabbit can be created as a new born (age zero)
     * or with random age.
     * 
     * @param randomAge If true, the rabbit will have random age and hunger level.
     */
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
	/**
	 * This is what the rabbit does most of the time: run
	 * In the process, it might breed or die of old age.
	 */
    @Override
	public void act(ArrayList<Actor> NewActors)
	{
	    incrementAge();
	    if(isAlive()) {
	        giveBirth(NewActors);
	        Location newLocation= getField().freeAdjacentLocation(getLocation());
		    if(newLocation !=null){
		        setLocation(newLocation);
		    }
		    else {
		        setDead();
		    }
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
       Rabbit young = new Rabbit(false,field,loc);
       return young;
   }
    
}
    
   
    
   

    
  

   
