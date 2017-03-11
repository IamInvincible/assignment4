package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.Iterator;
import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */

public abstract class Critter {
	private static String myPackage;
	private static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private static int[][] worldMap1 = new int[Params.world_height][Params.world_width];
	private static List<Critter>[][] worldMap2 = new java.util.ArrayList[Params.world_height][Params.world_width];
	private static Critter[][] worldMap3 = new Critter[Params.world_height][Params.world_width];
	private int moveFlag = 1;
	// Gets the package name. This assumes that Critter and its subclasses are
	// all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	private static java.util.Random rand = new java.util.Random();

	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}

	/*
	 * a one-character long string that visually depicts your critter in the
	 * ASCII interface
	 */
	public String toString() {
		return "";
	}

	private int energy = 0;

	protected int getEnergy() {
		return energy;
	}

	private int x_coord;

	private List<Critter>[][] ls;
	private int y_coord;

	protected final void walk(int direction) {
		if (moveFlag == 1) {
			switch (direction) {
			case 0:
				x_coord = (x_coord + 1) % (Params.world_width);
			case 1:
				x_coord = (x_coord + 1) % (Params.world_width);
				y_coord = (y_coord + Params.world_height - 1) % (Params.world_height);
			case 2:
				y_coord = (y_coord + Params.world_height - 1) % (Params.world_height);
			case 3:
				x_coord = (x_coord + Params.world_width - 1) % (Params.world_width);
				y_coord = (y_coord + Params.world_height - 1) % (Params.world_height);
			case 4:
				x_coord = (x_coord + Params.world_width - 1) % (Params.world_width);
			case 5:
				x_coord = (x_coord + Params.world_width - 1) % (Params.world_width);
				y_coord = (y_coord + 1) % (Params.world_height);
			case 6:
				y_coord = (y_coord + 1) % (Params.world_height);
			case 7:
				x_coord = (x_coord + 1) % (Params.world_width);
				y_coord = (y_coord + 1) % (Params.world_height);
			}
		}
		energy = energy - Params.walk_energy_cost;
		moveFlag -= 1;
	}

	protected final void run(int direction) {
		if (moveFlag == 1) {
			switch (direction) {
			case 0:
				x_coord = (x_coord + 2) % (Params.world_width);
			case 1:
				x_coord = (x_coord + 2) % (Params.world_width);
				y_coord = (y_coord + Params.world_height - 2) % (Params.world_height);
			case 2:
				y_coord = (y_coord + Params.world_height - 2) % (Params.world_height);
			case 3:
				x_coord = (x_coord + Params.world_width - 2) % (Params.world_width);
				y_coord = (y_coord + Params.world_height - 2) % (Params.world_height);
			case 4:
				x_coord = (x_coord + Params.world_width - 2) % (Params.world_width);
			case 5:
				x_coord = (x_coord + Params.world_width - 2) % (Params.world_width);
				y_coord = (y_coord + 2) % (Params.world_height);
			case 6:
				y_coord = (y_coord + 2) % (Params.world_height);
			case 7:
				x_coord = (x_coord + 2) % (Params.world_width);
				y_coord = (y_coord + 2) % (Params.world_height);
			}
		}
		energy = energy - Params.run_energy_cost;
		moveFlag -= 1;
	}

	protected final void reproduce(Critter offspring, int direction) {
		if (energy < Params.min_reproduce_energy) {
			return;
		}
		int energyCopy = energy;
		energy = (energy + 1) / 2;
		offspring.energy = energyCopy / 2;
		offspring.x_coord = x_coord;
		offspring.y_coord = y_coord;
		offspring.walk(direction);
		babies.add(offspring);
	}

	public abstract void doTimeStep();

	public abstract boolean fight(String oponent);

	/**
	 * create and initialize a Critter subclass. critter_class_name must be the
	 * unqualified name of a concrete subclass of Critter, if not, an
	 * InvalidCritterException must be thrown. (Java weirdness: Exception
	 * throwing does not work properly if the parameter has lower-case instead
	 * of upper. For example, if craig is supplied instead of Craig, an error is
	 * thrown instead of an Exception.)
	 * 
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {

		try {
			Class<?> c = Class.forName(critter_class_name);
			boolean validCritter = Critter.class.isAssignableFrom(c);
			if (!validCritter) {
				throw new InvalidCritterException(critter_class_name);
			}
			Critter newCritter = (Critter) c.newInstance();
			newCritter.x_coord = rand.nextInt(Params.world_width);
			newCritter.y_coord = rand.nextInt(Params.world_height);
			worldMap1[newCritter.y_coord][newCritter.x_coord] += 1;
			if (worldMap2[newCritter.y_coord][newCritter.x_coord] == null) {
				worldMap2[newCritter.y_coord][newCritter.x_coord] = new java.util.ArrayList<Critter>();
			}
			worldMap2[newCritter.y_coord][newCritter.x_coord].add(newCritter);

			// delete this later
			worldMap3[newCritter.y_coord][newCritter.x_coord] = newCritter;
			// delete this later

			newCritter.energy = Params.start_energy;
			population.add(newCritter);
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		} catch (InstantiationException e) {
			throw new InvalidCritterException(critter_class_name);
		} catch (IllegalAccessException e) {
			throw new InvalidCritterException(critter_class_name);
		}
	}

	/**
	 * Gets a list of critters of a specific type.
	 * 
	 * @param critter_class_name
	 *            What kind of Critter is to be listed. Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		try {
			Class<?> c = Class.forName(critter_class_name);
			boolean validCritter = Critter.class.isAssignableFrom(c);
			if (!validCritter) {
				throw new InvalidCritterException(critter_class_name);
			}
			Iterator<Critter> itr = population.iterator();
			while (itr.hasNext()) {
				Critter e = (Critter) itr.next();
				if (c.isInstance(e)) {
					result.add(e);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		return result;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * 
	 * @param critters
	 *            List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string, 1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
	}

	/*
	 * the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this
	 * class and then use the setter functions contained here.
	 * 
	 * NOTE: you must make sure that the setter functions work with your
	 * implementation of Critter. That means, if you're recording the positions
	 * of your critters using some sort of external grid or some other data
	 * structure in addition to the x_coord and y_coord functions, then you MUST
	 * update these setter functions so that they correctly update your
	 * grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}

		/*
		 * This method getPopulation has to be modified by you if you are not
		 * using the population ArrayList that has been provided in the starter
		 * code. In any case, it has to be implemented for grading tests to
		 * work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}

		/*
		 * This method getBabies has to be modified by you if you are not using
		 * the babies ArrayList that has been provided in the starter code. In
		 * any case, it has to be implemented for grading tests to work. Babies
		 * should be added to the general population at either the beginning OR
		 * the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		// Complete this method.
		population.clear();
		for (int i = 0; i < Params.world_height; i++) {
			for (int j = 0; j < Params.world_width; j++) {
				worldMap1[i][j] = 0;
			}
		}
		for (int i = 0; i < Params.world_height; i++) {
			for (int j = 0; j < Params.world_width; j++) {
				worldMap2[i][j] = null;
			}
		}
	}

	public static void worldTimeStep() {
		// Complete this method.
		java.util.Iterator itr = population.iterator();
		while (itr.hasNext()) {
			Critter c = (Critter) itr.next();
			c.doTimeStep();
		}
		// resolve fights and stuff by searching worldMap1 for places where
		// 2 or more Critters occupy the same area
		for (int i = 0; i < Params.world_height; i++) {
			for (int j = 0; j < Params.world_width; j++) {
				if (worldMap1[i][j] >= 2) {
					while (worldMap1[i][j] > 1) {
						// fight until there is only one Critter left
					}
				}
			}
		}

		itr = population.iterator();
		while (itr.hasNext()) {
			Critter c = (Critter) itr.next();
			c.energy = c.energy - Params.rest_energy_cost;
			if (c.energy <= 0) {
				itr.remove();
			}
		}
	}

	public static void displayWorld() {
		// Complete this method.
		// display the world based off the information in worldMap

		// print first row
		System.out.print("+");
		for (int i = 0; i < Params.world_width; i++) {
			System.out.print("-");
		}
		System.out.println("+");

		// print map
		for (int i = 0; i < Params.world_height; i++) {
			System.out.print("|");
			for (int j = 0; j < Params.world_width; j++) {
				// change this later
				if (worldMap1[i][j] == 1) {
					System.out.print(worldMap3[i][j].toString());
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("|");
		}

		// print last row
		System.out.print("+");
		for (int i = 0; i < Params.world_width; i++) {
			System.out.print("-");
		}
		System.out.print("+");
	}
}
