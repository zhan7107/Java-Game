/**
 * Boardable is a interface method for elements.
 * 
 * @author Hongli Zhang
 */
public interface Boardable { //Done
	/**
	 * This methods check if the element is visible.
	 * 
	 * @return True when its visible. False when it's not
	 */
	public boolean isVisible();
	
	/**
	 * This method checks if a element can share with another element in same cell.
	 * 
	 * @param elem (Takes in Boardable element)
	 * @return True if a element can share with other element. False it can't share.
	 */
	public boolean share(Boardable elem);
	
	/**
	 * This method returns element's string. To use it to show it's character on board
	 * 
	 * @return element's string.
	 */
	public String toString();
}
