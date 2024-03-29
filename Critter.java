package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Jason Fang
 * jhf649
 * 16238
 * Cejay Zhu
 * cz4723
 * 16238
 * Slip days used: 1
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

	private static int idGenerator = 0;

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
	private int y_coord;
	private boolean hasMoved;
	private int critterId;
	private boolean isFleeing;

	protected final void walk(int direction) {
		energy = energy - Params.walk_energy_cost;
		if (energy <= 0) {
			worldMap1[y_coord][x_coord] -= 1;
			Iterator<Critter> iter = worldMap2[y_coord][x_coord].iterator();
			while (iter.hasNext()) {
				Critter r = iter.next();
				if (r.critterId == this.critterId) {
					iter.remove();
				}
			}
			return;
		}
		int orig_x_coord = x_coord;
		int orig_y_coord = y_coord;
		if (hasMoved == false) {
			switch (direction) {
			case 0:
				x_coord = (x_coord + 1) % (Params.world_width);
				break;
			case 1:
				x_coord = (x_coord + 1) % (Params.world_width);
				y_coord = (y_coord + Params.world_height - 1) % (Params.world_height);
				break;
			case 2:
				y_coord = (y_coord + Params.world_height - 1) % (Params.world_height);
				break;
			case 3:
				x_coord = (x_coord + Params.world_width - 1) % (Params.world_width);
				y_coord = (y_coord + Params.world_height - 1) % (Params.world_height);
				break;
			case 4:
				x_coord = (x_coord + Params.world_width - 1) % (Params.world_width);
				break;
			case 5:
				x_coord = (x_coord + Params.world_width - 1) % (Params.world_width);
				y_coord = (y_coord + 1) % (Params.world_height);
				break;
			case 6:
				y_coord = (y_coord + 1) % (Params.world_height);
				break;
			case 7:
				x_coord = (x_coord + 1) % (Params.world_width);
				y_coord = (y_coord + 1) % (Params.world_height);
				break;
			}
		}
		if (isFleeing && (worldMap1[y_coord][x_coord] > 0)) {
			x_coord = orig_x_coord;
			y_coord = orig_y_coord;
		}
		worldMap1[orig_y_coord][orig_x_coord] -= 1;
		Iterator<Critter> iter = worldMap2[orig_y_coord][orig_x_coord].iterator();
		while (iter.hasNext()) {
			Critter c = iter.next();
			if (c.critterId == this.critterId) {
				iter.remove();
			}
		}
		worldMap1[y_coord][x_coord] += 1;
		if (worldMap2[y_coord][x_coord] == null) {
			worldMap2[y_coord][x_coord] = new java.util.ArrayList<Critter>();
		}
		worldMap2[y_coord][x_coord].add(this);
		hasMoved = true;
	}

	protected final void run(int direction) {
		energy = energy - Params.run_energy_cost;
		if (energy <= 0) {
			worldMap1[y_coord][x_coord] -= 1;
			Iterator<Critter> iter = worldMap2[y_coord][x_coord].iterator();
			while (iter.hasNext()) {
				Critter r = iter.next();
				if (r.critterId == this.critterId) {
					iter.remove();
				}
			}
			return;
		}
		int orig_x_coord = x_coord;
		int orig_y_coord = y_coord;
		if (hasMoved == false) {
			switch (direction) {
			case 0:
				x_coord = (x_coord + 2) % (Params.world_width);
				break;
			case 1:
				x_coord = (x_coord + 2) % (Params.world_width);
				y_coord = (y_coord + Params.world_height - 2) % (Params.world_height);
				break;
			case 2:
				y_coord = (y_coord + Params.world_height - 2) % (Params.world_height);
				break;
			case 3:
				x_coord = (x_coord + Params.world_width - 2) % (Params.world_width);
				y_coord = (y_coord + Params.world_height - 2) % (Params.world_height);
				break;
			case 4:
				x_coord = (x_coord + Params.world_width - 2) % (Params.world_width);
				break;
			case 5:
				x_coord = (x_coord + Params.world_width - 2) % (Params.world_width);
				y_coord = (y_coord + 2) % (Params.world_height);
				break;
			case 6:
				y_coord = (y_coord + 2) % (Params.world_height);
				break;
			case 7:
				x_coord = (x_coord + 2) % (Params.world_width);
				y_coord = (y_coord + 2) % (Params.world_height);
				break;
			}
		}
		if (isFleeing && (worldMap1[y_coord][x_coord] > 0)) {
			x_coord = orig_x_coord;
			y_coord = orig_y_coord;
		}
		worldMap1[orig_y_coord][orig_x_coord] -= 1;
		Iterator<Critter> iter = worldMap2[orig_y_coord][orig_x_coord].iterator();
		while (iter.hasNext()) {
			Critter c = iter.next();
			if (c.critterId == this.critterId) {
				iter.remove();
			}
		}
		worldMap1[y_coord][x_coord] += 1;
		if (worldMap2[y_coord][x_coord] == null) {
			worldMap2[y_coord][x_coord] = new java.util.ArrayList<Critter>();
		}
		worldMap2[y_coord][x_coord].add(this);
		hasMoved = true;
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
		switch (direction) {
		case 0:
			offspring.x_coord = (offspring.x_coord + 1) % (Params.world_width);
			break;
		case 1:
			offspring.x_coord = (offspring.x_coord + 1) % (Params.world_width);
			offspring.y_coord = (offspring.y_coord + Params.world_height - 1) % (Params.world_height);
			break;
		case 2:
			offspring.y_coord = (offspring.y_coord + Params.world_height - 1) % (Params.world_height);
			break;
		case 3:
			offspring.x_coord = (offspring.x_coord + Params.world_width - 1) % (Params.world_width);
			offspring.y_coord = (offspring.y_coord + Params.world_height - 1) % (Params.world_height);
			break;
		case 4:
			offspring.x_coord = (offspring.x_coord + Params.world_width - 1) % (Params.world_width);
			break;
		case 5:
			offspring.x_coord = (offspring.x_coord + Params.world_width - 1) % (Params.world_width);
			offspring.y_coord = (offspring.y_coord + 1) % (Params.world_height);
			break;
		case 6:
			offspring.y_coord = (y_coord + 1) % (Params.world_height);
			break;
		case 7:
			offspring.x_coord = (x_coord + 1) % (Params.world_width);
			offspring.y_coord = (y_coord + 1) % (Params.world_height);
			break;
		}
		offspring.critterId = idGenerator;
		idGenerator++;
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
			String qualified_critter_class_name = myPackage+"."+critter_class_name;
			Class<?> c = Class.forName(qualified_critter_class_name);
			boolean validCritter = Critter.class.isAssignableFrom(c);
			if (!validCritter) {
				throw new InvalidCritterException(critter_class_name);
			}
			Critter newCritter = (Critter) c.newInstance();
			newCritter.x_coord = rand.nextInt(Params.world_width);
			newCritter.y_coord = rand.nextInt(Params.world_height);
			newCritter.critterId = idGenerator;
			idGenerator++;
			worldMap1[newCritter.y_coord][newCritter.x_coord] += 1;
			if (worldMap2[newCritter.y_coord][newCritter.x_coord] == null) {
				worldMap2[newCritter.y_coord][newCritter.x_coord] = new java.util.ArrayList<Critter>();
			}
			worldMap2[newCritter.y_coord][newCritter.x_coord].add(newCritter);
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
			String qualified_critter_class_name = myPackage+"."+critter_class_name;
			Class<?> c = Class.forName(qualified_critter_class_name);
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
			if (super.energy <= 0) {
				Iterator<Critter> itr = population.iterator();
				while (itr.hasNext()) {
					Critter c = (Critter) itr.next();
					if (c.critterId == super.critterId) {
						itr.remove();
					}
				}
				worldMap1[super.y_coord][super.x_coord] -= 1;
				Iterator<Critter> iter = worldMap2[super.y_coord][super.x_coord].iterator();
				while (iter.hasNext()) {
					Critter r = iter.next();
					if (r.critterId == super.critterId) {
						iter.remove();
					}
				}
			}
		}

		protected void setX_coord(int new_x_coord) {
			worldMap1[super.y_coord][super.x_coord] -= 1;
			Iterator<Critter> iter = worldMap2[super.y_coord][super.x_coord].iterator();
			while (iter.hasNext()) {
				Critter c = iter.next();
				if (c.critterId == super.critterId) {
					iter.remove();
				}
			}
			super.x_coord = new_x_coord;
			worldMap1[super.y_coord][super.x_coord] += 1;
			if (worldMap2[super.y_coord][super.x_coord] == null) {
				worldMap2[super.y_coord][super.x_coord] = new java.util.ArrayList<Critter>();
			}
			worldMap2[super.y_coord][super.x_coord].add(this);

		}

		protected void setY_coord(int new_y_coord) {
			worldMap1[super.y_coord][super.x_coord] -= 1;
			Iterator<Critter> iter = worldMap2[super.y_coord][super.x_coord].iterator();
			while (iter.hasNext()) {
				Critter c = iter.next();
				if (c.critterId == super.critterId) {
					iter.remove();
				}
			}
			super.y_coord = new_y_coord;
			worldMap1[super.y_coord][super.x_coord] += 1;
			if (worldMap2[super.y_coord][super.x_coord] == null) {
				worldMap2[super.y_coord][super.x_coord] = new java.util.ArrayList<Critter>();
			}
			worldMap2[super.y_coord][super.x_coord].add(this);
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
		Iterator<Critter> itr = population.iterator();
		while (itr.hasNext()) {
			Critter c = (Critter) itr.next();
			c.hasMoved = false;
			c.isFleeing = false;
			c.doTimeStep();
			c.isFleeing = true;
		}
		itr = population.iterator();
		while (itr.hasNext()) {
			Critter c = (Critter) itr.next();
			if (c.energy <= 0) {
				itr.remove();
			}
		}
		// resolve encounters by searching worldMap1 for places where
		// 2 or more Critters occupy the same area
		for (int i = 0; i < Params.world_height; i++) {
			for (int j = 0; j < Params.world_width; j++) {
				while (worldMap1[i][j] > 1) {
					Critter a = worldMap2[i][j].get(0);
					Critter b = worldMap2[i][j].get(1);
					boolean aFights = a.fight(b.toString());
					boolean bFights = b.fight(a.toString());
					int aRoll = 0;
					int bRoll = 0;
					if (a.energy > 0 && b.energy > 0 && a.x_coord == b.x_coord && a.y_coord == b.y_coord) {
						if (aFights) {
							aRoll = Critter.getRandomInt(a.energy);
						} else {
							aRoll = 0;
						}
						if (bFights) {
							bRoll = Critter.getRandomInt(b.energy);
						} else {
							bRoll = 0;
						}
						if (aRoll >= bRoll) {
							Iterator<Critter> it = worldMap2[a.y_coord][a.x_coord].iterator();
							while (it.hasNext()) {
								Critter z = it.next();
								if (z.critterId == a.critterId) {
									z.energy = z.energy + (b.energy / 2);
								}
							}
							itr = population.iterator();
							while (itr.hasNext()) {
								Critter c = (Critter) itr.next();
								if (c.critterId == b.critterId) {
									itr.remove();
								}
							}
							worldMap1[b.y_coord][b.x_coord] -= 1;
							Iterator<Critter> iter = worldMap2[b.y_coord][b.x_coord].iterator();
							while (iter.hasNext()) {
								Critter r = iter.next();
								if (r.critterId == b.critterId) {
									iter.remove();
								}
							}
						} else {
							Iterator<Critter> it = worldMap2[b.y_coord][b.x_coord].iterator();
							while (it.hasNext()) {
								Critter z = it.next();
								if (z.critterId == b.critterId) {
									z.energy = z.energy + (a.energy / 2);
								}
							}
							itr = population.iterator();
							while (itr.hasNext()) {
								Critter c = (Critter) itr.next();
								if (c.critterId == a.critterId) {
									itr.remove();
								}
							}
							worldMap1[a.y_coord][a.x_coord] -= 1;
							Iterator<Critter> iter = worldMap2[a.y_coord][a.x_coord].iterator();
							while (iter.hasNext()) {
								Critter r = iter.next();
								if (r.critterId == a.critterId) {
									iter.remove();
								}
							}
						}
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
				worldMap1[c.y_coord][c.x_coord] -= 1;
				Iterator<Critter> iter = worldMap2[c.y_coord][c.x_coord].iterator();
				while (iter.hasNext()) {
					Critter r = iter.next();
					if (r.critterId == c.critterId) {
						iter.remove();
					}
				}
			}
		}

		Iterator<Critter> babyIter = babies.iterator();
		while (babyIter.hasNext()) {
			Critter b = babyIter.next();
			babyIter.remove();
			population.add(b);
			worldMap1[b.y_coord][b.x_coord] += 1;
			if (worldMap2[b.y_coord][b.x_coord] == null) {
				worldMap2[b.y_coord][b.x_coord] = new java.util.ArrayList<Critter>();
			}
			worldMap2[b.y_coord][b.x_coord].add(b);
		}

		for (int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				makeCritter("Algae");
			} catch (InvalidCritterException e) {
				System.out.println("Invalid Critter Exception: Algae not created");
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
				if (worldMap1[i][j] >= 1) {
					System.out.print(worldMap2[i][j].get(0).toString());
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
		System.out.println("+");
	}
}