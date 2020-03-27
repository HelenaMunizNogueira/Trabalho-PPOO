package raposa_e_coelho;

/**
 * Class that manages the system of preys by predator.
 * 
 * @author Arthur Masson, Dian de Grazia and Helena Muniz
 * @version 2019-06-29
 */
public class Predation {
	private static float numRabbit = 0;
	private static float numFox = 0;
	private static float numJaguar = 0;
	
	/**
     * @return The number of prey per jaguar
     */ 
	public static String calculatePredationJaguar() {
		return "Jaguar: " + round2Dec((numRabbit + numFox)/numJaguar);
				
	}
	
	/**
     * @return The number of prey per fox
     */ 
	public static String calculatePredationFox() {
		return "Fox: " + round2Dec(numRabbit/numFox);
	}
	
	/**
     * Increment the number of Rabbits.
     */ 
	public static void incrementRabbit() {
		numRabbit += 1;
	}
	/**
     * Increment the number of Foxes.
     */ 
	public static void incrementFox() {
		numFox += 1;
	}
	/**
     * Increment the number of Jaguars.
     */ 
	public static void incrementJaguar() {
		numJaguar += 1;
	}

	/**
     * Limit the decimal value.
     * @param val The decimal value
     * @return The decimal value with two decimal places
     */ 
	private static String round2Dec(float val)
	{
		return String.format("%.2f", val);
	}
	/**
     * Clears all counters to zero
     */ 
	public static void clearAll() {
		numRabbit = 0;
		numFox = 0;
		numJaguar = 0;
	}
	
}
