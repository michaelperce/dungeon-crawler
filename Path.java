import java.util.Random;
import java.util.LinkedList;
public class Path{
  	int xValue = 0;
  	int yValue = 0;
  	char[][] world;
  	public enum direction  {down, left, up, right};
  	Random rng = new Random();
  	public Path(int x, int y, char[][] universe){
  		xValue = x;
  		yValue = y;
  		world = universe.clone();
  		world[x][y] = 'p';
  	}
  	public boolean content(double percentage){
  		int count = 0;
  		for(int i = 0; i < world.length; i ++)
  			for(int q =0; q< world.length; q ++)
  				if(world[i][q] == 'p')
  					count ++;
  		if((double)(count)/Math.pow(world.length,2) < percentage)
  			return true;
  		return false;
  		
  	}
  	public void move(){
  		//
  		//Checks to see if 60% of the map is path
  		//
  		direction lastDir = direction.left;
  		direction dir = direction.up;
  		while(content(.4)){
  			//
  			//choosing the direction
  			//
  			switch(rng.nextInt(4)){
  					case 0: dir = direction.down; break;
  					case 1: dir = direction.left; break;
  					case 2: dir = direction.up; break;
  					case 3: dir = direction.right; break;
  					default: break;
  				}
  			while((dir.ordinal() == lastDir.ordinal()-2)||(dir.ordinal() == lastDir.ordinal()+2)){
  				switch(rng.nextInt(4)){
  					case 0: dir = direction.down; break;
  					case 1: dir = direction.left; break;
  					case 2: dir = direction.up; break;
  					case 3: dir = direction.right; break;
  					default: break;
  				}
  			}
  			lastDir = dir;
  			//
  			//moving the path around up or down
  			//
  			if(dir.ordinal() == 2 || dir.ordinal() == 0){
  				for(int i = 0; i < rng.nextInt(20); i ++) {
  					xValue = xValue +(dir.ordinal()-1);
  					if(xValue <= -1){
  						xValue = (xValue + world.length) % world.length;
  						break;
  					}
  					if(xValue >= 20){
  						xValue = (xValue - world.length) %world.length;
  						break;
  					}
  					world[xValue][yValue] = 'p';
  				}
  			}
  			//
  			//moving the path around left or right
  			//
  			if(dir.ordinal() == 1 || dir.ordinal() == 3){
  				for(int i = 0; i < rng.nextInt(20); i ++) {
  					yValue= yValue +(dir.ordinal()-2);
  					if(yValue <= -1){
  						yValue = (yValue + world.length) %world.length;
  						break;
  					}
  					if(yValue >= 20){
  						yValue = (yValue - world.length) %world.length;
  						break;
  					}
  					world[xValue][yValue] = 'p';
  				}
  			}
  		}
  		room();
  	}
  	public void room(){
  		LinkedList<Point> list = new LinkedList<Point>();
  		for(int i = 0; i < world.length; i++)
  			for(int q = 0; q < world.length; q++)
  				if(world[i][q] == 'p'){
  					Point h = new Point(i,q); 
  					list.add(h);
  				}
  		for(int i = 0; i < 4; i++){
  			int index = rng.nextInt(list.size());
  			Room q = new Room(list.get(index), world);
  			list.remove(index);
  			world[q.getX()][q.getY()] = 'O';
  			q.expand();
  		}
  	}
    public String toString(){
    	for(int i = 0;i < 20; i ++){
       		for(int q = 0; q< 20; q++){
    			System.out.print(world[i][q] + "");
       		}
       		System.out.println("");
    	}
    	return "";
    }
  }