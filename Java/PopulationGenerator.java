package raposa_e_coelho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class that randomly populates locations with animals
 * 
 * @author Arthur Masson, Dian de Grazia and Helena Muniz
 * @version 2019-06-29
 */
public class PopulationGenerator {
    

    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    
    // The probability that a jaguar will be created in any given grid position.
    private static final double JAGUAR_CREATION_PROBABILITY = 0.01;
    
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;   
    
    
    /**
     * Randomly populates locations with animals
     * @param field Current field
     * @param actors list of simulation actors
     */ 
    public void populate(Field field,ArrayList<Actor> actors)
    {
       
        Random rand = new Random();
       
        //field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
            	if (rand.nextDouble() <= JAGUAR_CREATION_PROBABILITY) {
                	Location location = new Location(row, col);
                	Jaguar jaguar = new Jaguar(true,field,location);
                	actors.add(jaguar);
                	field.place(jaguar, row, col);
                }
            	else if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location=new Location(row,col);
                    Fox fox = new Fox(true,field,location);
                    actors.add(fox);
                    field.place(fox, row, col);
                    
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location=new Location(row,col);
                    Rabbit rabbit = new Rabbit(true,field,location);
                    actors.add(rabbit);
                    field.place(rabbit, row, col);

                }
            }
        }
        Collections.shuffle(actors);
      
    }
}
