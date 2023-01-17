import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Aayushi Ayalasomayajula
 *	@since	January 16, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities =new ArrayList<City>();
	private int numCities = 0;
	SortMethods sm = new SortMethods();
	long startMillisec;
	long endMillisec;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	/**
	 * Main method to run class
	 * Precondition: called when class is run
	 * @param args
	 */
	public static void main(String [] args)
	{
		Population pop = new Population();
		pop.printIntroduction();
		pop.readFile();
		pop.printMenu();
	}
	
	public List<City> readFile()
	{
		numCities = 0;
		Scanner read = FileUtils.openToRead(DATA_FILE);
		read.useDelimiter("[\t\n]");
		while(read.hasNext())
		{
			String state = read.next();
			String city = read.next();
			String type = read.next();
			int pop = read.nextInt();
			City cit = new City(city, state, type, pop);
			cities.add(cit);
			numCities++;

		}

		return cities;
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {

		Scanner input = new Scanner(System.in);
		int num = 0;
		while(num!=9)
		{
			System.out.println(numCities + " cities in database");
			System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
			System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
			System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
			System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
			System.out.println("5. Fifty most populous cities in named state");
			System.out.println("6. All cities matching a name sorted by population");
			System.out.println("9. Quit");

			do
			{
				num = Prompt.getInt("Enter selection ");
				if(num>9 || num<1)
				{
					System.out.println("\nInvalid number.");
				}
			}while(num>9 || num<1);

			if(num!=9)
			switch(num)
			{
				case 1:
					System.out.println("\n\nFifty least populous cities\n");
					startMillisec = System.currentTimeMillis();
					cities = sm.selectionSort(cities);
					printArr(cities);
					break;
				case 2:
					System.out.println("\n\nFifty most populous cities in USA\n");
					startMillisec = System.currentTimeMillis();
					cities = sm.mergeSort(cities, true);
					printArr(cities);
					break;
				case 3:
					startMillisec = System.currentTimeMillis();
					cities = sm.insertionSort(cities);
					System.out.println("\n\nFirst fifty cities sorted by name\n");
					printArr(cities);
					break;

				case 4:
					startMillisec = System.currentTimeMillis();
					cities = sm.mergeSort(cities, false);
					System.out.println("\n\nLast fifty cities sorted by name descending\n");
					printArr(cities);
					break;

				case 5:
					System.out.print("\n\nFifty most populous cities in named state\n\n\n");
					List<City> temp;
					do
					{
						String state = Prompt.getString("Enter state name (ie. Alabama)");
						startMillisec = System.currentTimeMillis();
						cities = sm.mergeSort(cities, true);
						temp  = sm.stateMatch(cities, state);
						if(temp.size()==0)
							System.out.print("ERROR: " +state+ " is not valid.\n");
						else
							cities = temp;
					}while(temp.size()==0);

					printArr(cities);
					break;

				case 6:
					System.out.print("\n\nAll cities matching a name sorted by population\n\n\n");
					List<City> temp2;
					do
					{
						String name = Prompt.getString("Enter city name ");
						startMillisec = System.currentTimeMillis();
						cities = sm.mergeSort(cities, true);
						temp2  = sm.nameMatch(cities, name);
						if(temp2.size()==0)
							System.out.print("ERROR: " +name+ " is not valid.\n");
						else
							cities = temp2;
					}while(temp2.size()==0);
					System.out.println();
					printArr(cities);
					break;

			}
			else
			{
				System.out.print("\n\n\nThank you for using Population\n\n\n\n");
			}

			readFile();
		}

		input.close();

	}

	public void printArr(List<City> arr)
	{
		endMillisec = System.currentTimeMillis();
		System.out.printf("     %-25s%-25s%-25s%-25s\n", "State", "City", "Type", "Population");
		for(int i = 0; i<50; i++)
		{
			if(i>arr.size()-1)
				break;
			City c = cities.get(i);
			System.out.printf("%2d : %-25s%-25s%-25s%-25d\n", i+1, c.getState(), c.getName(), c.getType(), c.getPop());
		}
		System.out.printf("\n\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds\n\n");

	}
	
}
