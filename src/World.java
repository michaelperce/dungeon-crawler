import java.util.Random;

public class World{
	Grid   universe;
	double percentPath;
	double percentRoom;
	int    roomCount;

	public World(int h, int w, double percentPath, double percentRoom, int roomCount) {
		universe = new Grid(h, w, MapChar.WALL);
		this.percentPath = percentPath;
		this.percentRoom = percentRoom;
		this.roomCount   = roomCount;
	}

	public void generateMap() {
		Random rng = new Random();
		int r = rng.nextInt(universe.getHeight());
		int c = rng.nextInt(universe.getWidth());
		Path p = new Path(r, c, universe);
		p.move();
	}

	public void print() {
		System.out.println(universe);
	}

	public static void main(String[] args){
		double percentPath = 0.4;
		double percentRoom = 0.2;
		int roomCount = 3;

		World w = new World(20, 20, percentPath, percentRoom, roomCount);
		w.generateMap();
		w.print();
	}
}
