/**
 * This is a homework trap. If a player steps on this It would set off a delay. Which the player cannot move certain minutes
 * 
 * @author Hongli Zhang
 */
public class HomeworkTrap implements Boardable {
	/**Stores the board*/
	private Board board;
	
	/**
	 * This is a constructor for Homework Trap. It takes in Board.
	 * 
	 * @param board (Takes in board class)
	 */
	public HomeworkTrap(Board board) {
		this.board = board;
	}
	
	/**
	 * This method checks if Homework Trap can share with another element in same cell.
	 * 
	 * @param elem (Takes in Boardable element)
	 * @return True if a element can share with other element. False it can't share.
	 */
	public boolean share(Boardable elem) { //Jarvis can share with trap.
		if(!(elem instanceof Mobile)) {
			return false;
		}
		if(elem instanceof Jarvis) {
			return true;
		}
		if(elem instanceof Player) {
			((Player)elem).setDelay(5000);
			board.removeElement(HomeworkTrap.this);
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the element is visible. Since HomeworkTrap is invisible. It should be false.
	 * 
	 * @return True when its visible. False when it's not
	 */
	@Override
	public boolean isVisible() {
		return false;
	}
	
	/**
	 * This method returns element's string. To use it to show it's character on board.
	 * 
	 * @return element's string.
	 */
	public String toString() {
		return " ";
	}
}
