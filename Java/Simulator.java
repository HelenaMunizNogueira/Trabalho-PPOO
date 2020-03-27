package raposa_e_coelho;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a field containing
 * rabbits and foxes.
 * 
 * @author Arthur Masson, Dian de Grazia and Helena Muniz
 * @version 2019-06-29
 */
public class Simulator
{
    // The private static final variables represent 
    // configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 80;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    private ArrayList<Actor> actors;
    private Field field;
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    private PopulationGenerator populationGenerator;
   
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
     public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
     
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        actors = new ArrayList<>();
        
        field = new Field(depth, width);

        view = new SimulatorView(depth, width);
        view.setColor(Fox.class, Color.red);
        view.setColor(Rabbit.class, Color.black);
        view.setColor(Jaguar.class, Color.yellow);
        populationGenerator = new PopulationGenerator();
        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
    public void runLongSimulation()
    {
        simulate(50);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox, rabbit and jaguar.
     */
    public void simulateOneStep()
    {
      step++;
      ArrayList<Actor> newActors=new ArrayList<>();
      for (Iterator<Actor> it = actors.iterator(); it.hasNext();){
          Actor actor =  it.next();
          actor.act(newActors);
          if(!actor.isActive()){
              it.remove();
          }
      }
     
      actors.addAll(newActors);
      view.showStatus(step, field);

    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        actors.clear();
        field.clear();
        populationGenerator.populate(field,actors);
        
        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Populate the field with foxes and rabbits.
     */
    
}
