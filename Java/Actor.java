package raposa_e_coelho;

import java.util.ArrayList;

/**
 * The interface provides the behaviors of each simulated animal
 * 
 * @author Arthur Masson, Dian de Grazia and Helena Muniz
 * @version 2019-06-29
 */
public interface Actor {
    
    void act (ArrayList<Actor> NewActors);
    boolean isActive();
    
}
