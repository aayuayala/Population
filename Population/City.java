
/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	
 *	@since	
 */
public class City implements Comparable<City> {
	
	// fields
	private String city_Name, state_Name, city_Type;
	private int population;
	// constructor
	public City(String city, String state, String type, int pop)
	{
		city_Name = city;
		state_Name = state;
		city_Type = type;
		population = pop;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	@Override
	public int compareTo(City other)
	{	int num = this.population-other.population;
		if(this.population != other.population)
			return num;
		else if(this.state_Name != other.state_Name)
			return this.state_Name.compareTo(other.state_Name);
		else
		return this.city_Name.compareTo(other.state_Name);

	}

	public int compareTo(City other, boolean pop)
	{	int num = this.population-other.population;

		if(!pop)
			return this.city_Name.compareTo(other.city_Name);

		else if(this.population != other.population)
				return num;
		else if(this.state_Name != other.state_Name)
				return this.state_Name.compareTo(other.state_Name);

		return this.city_Name.compareTo(other.city_Name);
	}
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	
	 public boolean equals(City other)
	 {
		 //&& this.city_Name.equals(other.city_Name)
		 if(this.state_Name.equals(other.state_Name) && this.city_Name.equals(other.city_Name))
		 	return true;

		 return false;
		 	

	 }
	/**	Accessor methods */
	public String getName()
	{return city_Name;}

	public String getState()
	{return state_Name;}

	public String getType()
	{return city_Type;}

	public int getPop()
	{ return population;}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state_Name, city_Name, city_Type,
						population);
	}
}