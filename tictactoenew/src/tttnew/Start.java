package tttnew;

public class Start {
	public static void main(String[] args)
	{
		Game game = new Game();
		Board board = new Board();
		Player player = new Player();
		//SideSet sideSet = new SideSet();
		SideSet.chooseSide();
		player.choosePos();
		board.printGame();
		game.aiMove();
		
	}
}
