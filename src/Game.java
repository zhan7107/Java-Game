import java.util.Scanner;
/**
 * This is the main class to run the game.
 * 
 * @author Hongli Zhang
 */
public class Game {
	/**
	 * Main method runs the Board. It validates the height and width of the board. Place player and Jarvis.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input;
		Board b;
		Player p;
		Jarvis j;
		Thread player;
		Thread jarvis;

		boolean validation;
		int height;
		int width;
		
		height     = 0;
		width      = 0;
		input      = new Scanner(System.in);
		validation = false;
		
		while(!validation) { //Validating Height
			System.out.print("Please enter height between [1-100] of the board: ");
			if(!input.hasNextInt()) { //When it's not a integer
				System.out.print("Sorry that is not a valid input. ");
				input.nextLine();
				validation = false;
			}else {
				height = input.nextInt();
				if(height >= 1 && height <= 100) { //When it's in the range
					validation = true;
					input.nextLine();
				}else {
					System.out.print("You enter "+height+". It's out of range. ");
					validation = false;
					input.nextLine();
				}
			}
		}
		
		validation = false;
		while(!validation) { //Validating Width
			System.out.print("Please enter width between [1-100] of the board: ");
			if(!input.hasNextInt()) { //When it's not a integer
				System.out.print("Sorry that is not a valid integer. ");
				input.nextLine();
				validation = false;
			}else {
				width = input.nextInt();
				if(width >= 1 && width <= 100) { //When it's in the range.
					validation = true;
					input.nextLine();
				}else {
					System.out.print("You enter "+width+". It's out of range. ");
					validation = false;
					input.nextLine();
				}
			}
		}
		
		b = new Board(height,width);
	    p = new Player(b);
	    j = new Jarvis(b);
		
		b.placeElement(p, 0, 0);
		if(height == 1 && width == 1) {
			System.out.println(height+" by "+width+" board sigh... You placed Jarvis on top of you! \nGame over!");
		}else {
			b.placeElement(j, (int)(Math.random()*(((height-1) - (height/2)) + 1) + (height/2)), (int)(Math.random()*(((width-1) - (width/2)) + 1) + (width/2)));
			player = new Thread(p);
			player.start();
		
			jarvis = new Thread(j);
			jarvis.start();
			
			if(b.beenHugged()) {
				input.close();
			}
		}
	}
}
