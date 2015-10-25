import java.util.Random;
import java.util.LinkedList;
import java.util.List;

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
		Direction dir = null;
		while (world.percentOfChar(MapChar.PATH) < 0.4d) {
			//
			//choosing the direction
			//
			List<Direction> valids = location.validDirections(world);
			if (dir != null) {
				valids.remove(dir.opposite());
			}
			dir = valids.get(rng.nextInt(valids.size()));

			//
			//moving the path
			//
			int max = world.getHeight();
			if (dir == Direction.LEFT || dir == Direction.RIGHT) {
				max = world.getWidth();
			}
			for(int i = 0; i < rng.nextInt(max); i ++) {
				location.applyDirection(dir);
				if (!world.isInBounds(location)) {
					location.applyDirection(dir.opposite());
					break;
				}
				world.set(location, MapChar.PATH);
			}
		}
		seedRooms();
		expandRooms();
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
	public void expandRooms() {
		while (world.percentOfChar(MapChar.ROOM) < 0.2) {
			LinkedList<Point> roomSpots = world.listWithChar(MapChar.ROOM);
			for (Point spot : roomSpots) {
				new Room(spot, world).expand();
			}
		}
	}
}
