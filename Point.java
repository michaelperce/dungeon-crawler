public class Point {
	private int xCoord;
	private int yCoord;
	public Point() {
		xCoord = 0;
		yCoord = 0;
	}
	public Point(int one, int two) {
		xCoord = one;
		yCoord = two;
	}
	public int getX(){
		return xCoord;	
	}
	public int getY(){
		return yCoord;
	}
	public void setX(int new1) {
		xCoord = new1;
	}
	public void setY(int new1) {
		yCoord = new1;
	}
	public String toString() {
		return "("+xCoord+", "+yCoord+")";
	}
}