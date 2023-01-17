import java.util.ArrayList;
import java.util.List;

/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Aayushi Ayalasomayajula
 *	@since	Jan 16 20223
 */
public class SortMethods {
    private List<City> temp = new ArrayList<City>();
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private List<City> swap(List<City> arr, int x, int y) {
        if(x==y)
            return arr;
        City temp = arr.get(x);
        arr.set(x, arr.get(y));
        arr.set(y, temp);

        return arr;
    }
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public List<City> selectionSort(List<City> arr) {
        int small_ind = 0;
        for(int i = 0; i<arr.size(); i++)
        {
            small_ind = i;
            for(int j = i+1; j<arr.size(); j++)
            {
                if(arr.get(j).compareTo(arr.get(small_ind))<0)
                    small_ind = j;

            }
            
            if(small_ind!=i)
            {
               arr = swap(arr, small_ind, i);
            }

        }

        return arr;

        

    }
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public List<City> insertionSort(List<City> arr) {

        for(int i = 1; i<arr.size(); i++)
        {
            City check = arr.get(i);
            for(int j = 0; j<i; j++)
            {
                City check2 = arr.get(j);

                if(check.getName().compareTo(check2.getName())<0)
                {
                    arr.add(j, check);
                    arr.remove(i);
                    break;
                }
                else if(check.getName().compareTo(check2.getName())==0)
                    if(check.compareTo(check2)>0)
                    {
                        arr.add(j, check);
                        arr.remove(i);
                        break;
                    }
            }
        }

        return arr;
    }
	
	/**
	 *	Merge Sort algorithm - in descending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public List<City> mergeSort(List<City> arr, boolean isPop) {
        for(int i = 0; i<arr.size(); i++)
            temp.add(i, new City(null,null,null,0));
        recSort(arr, 0, arr.size()-1, isPop);

        return arr;
	
    }

    private void recSort(List<City> arr, int start, int end, boolean isPop)
    {
        if(end-start<2)
        {
            if(end>start && arr.get(start).compareTo(arr.get(end), isPop)<0)
            {
                City temp = arr.get(start);
                arr.set(start, arr.get(end));
                arr.set(end, temp);
            }
            return;
        }
        else
        {
                int middle = (start+end)/2;
                recSort(arr, start, middle, isPop);
                recSort(arr, middle+1, end, isPop);
                merge(arr, start, middle, end, isPop);

        }
    }

    private void merge(List<City> arr, int start, int middle, int end, boolean isPop)
    {

        int a = start;
        int c = start;
        int b = middle+1;

        while(a<=middle && b<= end)
        {
            if(arr.get(a).compareTo(arr.get(b), isPop)>0)
            {
                temp.set(c, arr.get(a));
                a++;
            }
            else
            {
                temp.set(c, arr.get(b));
                b++;
            }
            c++;
        }

        while(a<=middle)
        {
            temp.set(c, arr.get(a));
            a++;
            c++;
        }

        while(b<=end)
        {
            temp.set(c, arr.get(b));
            b++;
            c++;
        }

        for(c = start; c<=end; c++)
            arr.set(c, temp.get(c));

    }

    public List<City> stateMatch(List<City> arr, String input)
    {
        List<City> temp = new ArrayList<City>();
        for(int i = 0; i<arr.size(); i++)
        {
            if(arr.get(i).getState().equals(input))
            {
                temp.add(arr.get(i));
                i++;
            }
        }
        return temp;
    }

    public List<City> nameMatch(List<City> arr, String input)
    {
        List<City> temp = new ArrayList<City>();
        for(int i = 0; i<arr.size(); i++)
        {
            if(arr.get(i).getName().equals(input))
            {
                temp.add(arr.get(i));
                i++;
            }
        }
        return temp;
    }
}
