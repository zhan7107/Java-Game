/**
 * Mobile is a abstract class that implements both Boardable and Runnable.
 * 
 * @author Hongli Zhang
 */
public abstract class Mobile implements Runnable, Boardable{
	/**Stores the board*/
	protected Board board;
	
	/**
	 * This is a constructor for Mobile and it's classes. It takes in Board.
	 * 
	 * @param board (Takes in board class)
	 */
	public Mobile(Board board) {
		this.board = board;
	}
	
	/**
	 * This method moves anything that's related to Mobile around the board.
	 */
	protected abstract void move();
}
