import java.util.Random;

public class World{
	public static void main(String[] args){
		Grid universe = new Grid(20, 20, MapChar.WALL);
		Random rng = new Random();
		Path p = new Path(rng.nextInt(20),rng.nextInt(20), universe);
		p.move();
		System.out.println(p);
	}
}
