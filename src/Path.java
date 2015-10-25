import java.util.Random;
import java.util.LinkedList;

public class Path{
	Point location;
	Grid world;
	Random rng = new Random();
	public Path(int x, int y, Grid universe) {
		location = new Point(x, y);
		this.world = universe;
		world.set(new Point(x, y), MapChar.PATH);
	}
	public void move() {
		//
		//Checks to see if 60% of the map is path
		//
		Direction lastDir = Direction.LEFT;
		Direction dir = Direction.UP;
		while (world.percentOfChar(MapChar.PATH) < 0.4d) {
			//
			//choosing the direction
			//
			do {
				dir = Direction.values()[rng.nextInt(4)];
			} while(dir == lastDir.opposite());

			lastDir = dir;
			//
			//moving the path
			//
			for(int i = 0; i < rng.nextInt(20); i ++) {
				location.applyDirection(dir);
				location.wrap(world.getHeight(), world.getWidth());
				world.set(location, MapChar.PATH);
			}
		}
		seedRooms();
	}
	public void seedRooms() {
		LinkedList<Point> list = world.listWithChar(MapChar.PATH);
		for(int i = 0; i < 4; i++) {
			int index = rng.nextInt(list.size());
			Room room = new Room(list.get(index), world);
			list.remove(index);
			world.set(room.getLocation(), MapChar.ROOM);
			room.expand();
		}
	}
	public String toString() {
		for(int row = 0; row < world.getHeight(); row++) {
			for(int col = 0; col < world.getWidth(); col++) {
				Point p = new Point(row, col);
				System.out.print(world.at(p).asChar());
			}
			System.out.println();
		}
		return "";
	}
}
