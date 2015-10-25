public class Room {
	int xValue;
	int yValue;
	char[][] world;
	public enum side {UL,U,UR,R,BR,B,BL,L}
	boolean[] toFill = new boolean[8];
	public Room(Point q, char[][] universe){
		xValue = q.getX();
		yValue = q.getY();
		world = universe.clone();
		for(int i = 0; i < 8; i ++)
			toFill[i] = true;
	}
	public int getX(){
		return xValue;
	}
	public int getY(){
		return yValue;
	}
	private void expandHelper(side temp){
		for(int i = 0; i < 3; i++){
				toFill[temp.ordinal()] = false;
				rotate(temp);
			}
	}
	public void expand(){
		//top of the screen
		side temp;
		if(xValue == 0){
			temp = side.UL;
			expandHelper(temp);
		}
		if(xValue == 19){
			temp = side.BR;
			expandHelper(temp);
		}
		if(yValue == 0){
			temp = side.BL;
			expandHelper(temp);
		}		
		if(yValue == 19){
			temp = side.UR;
			expandHelper(temp);
		}
		fill();	
	}
	private void fill(){
		
	}
	private side rotate(side pointer){
		side temp = pointer;
		temp = side.values()[(temp.ordinal()+1)%8];
		return temp;
	}
}