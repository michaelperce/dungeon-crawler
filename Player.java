public static class Player {
	static int xLocation = 0;
	static int yLocation = 0;
	static char represent = ' ';
	public Player(int x, int y, char symbol) {
		xLocation = x;
		yLocation = y;
		represent = symbol;
	}
	static char escCode = 0x1B;
	public static void position() {
		System.out.print(String.format("%c[%d;%df",escCode,xLocation,yLocation));
		System.out.print("" + represent);
	}
	public void moveUp(){
		universe[xLocation][yLocation] = '.';
		xLocation --;
		position();
	}
	public void moveDown(){
		universe[xLocation][yLocation] = '.';
		xLocation ++;
		position();
	}
	public void moveRight(){
		universe[xLocation][yLocation] = '.';
		yLocation ++;
		position();
	}
	public void moveLeft(){
		universe[xLocation][yLocation] = '.';
		yLocation --;
		position();
	}
}