import java.util.LinkedList;
import java.util.Random;
import java.util.List;
import java.util.function.Predicate;

public class World{
	double percentPath;
	double percentRoom;
	int    roomCount;
	Grid   map;
	Random rng;

	public World(int h, int w, double percentPath, double percentRoom, int roomCount) {
		map = new Grid(h, w, MapChar.WALL);
		rng = new Random();
		this.percentPath = percentPath;
		this.percentRoom = percentRoom;
		this.roomCount   = roomCount;
	}

	public void generateMap() {
		int r  = rng.nextInt(map.getHeight());
		int c  = rng.nextInt(map.getWidth());
		new Path(r, c, percentPath, map);
		seedRooms();
		expandRooms();
		handleDoors();
	}

	public void seedRooms() {
		List<Point> list = map.listWithChar(MapChar.PATH);
		for(int i = 0; i < roomCount; i++) {
			int index = rng.nextInt(list.size());
			Room room = new Room(list.get(index), map);
			list.remove(index);
			map.set(room.getLocation(), MapChar.ROOM);
			room.expand();
		}
	}

	public void expandRooms() {
		while (map.percentOfChar(MapChar.ROOM) < 0.2) {
			List<Point> roomSpots = map.listWithChar(MapChar.ROOM);
			for (Point spot : roomSpots) {
				new Room(spot, map).expand();
			}
		}
	}

	public void handleDoors() {
		map.performOnChar(MapChar.PATH, (Point p) -> {
			return map.hasNeighbor(p, MapChar.ROOM) ? MapChar.DOOR : MapChar.PATH;
		});

		Predicate<Point> lonelyDoor = p -> !map.hasNeighbor(p, MapChar.PATH);
		Predicate<Point> shyDoor = p -> map.countNeighborsWithChar(p, MapChar.ROOM) > 1;
		Predicate<Point> crowdedDoor = p -> map.hasNeighbor(p, MapChar.DOOR)
			&& map.countNeighborsWithChar(p, MapChar.PATH) == 1;


		//while (map.listOfChar(MapChar.DOOR).stream().anyMatch(lonelyDoor.or(shyDoor))) {
		boolean repeat = true;
		while(repeat) {
			map.performOnChar(MapChar.DOOR, p -> {
				return lonelyDoor.test(p) ? MapChar.ROOM : MapChar.DOOR;
			});
			map.performOnChar(MapChar.DOOR, p -> {
				if (shyDoor.test(p)) {
					for (Point pt : map.getNeighborPoints(p)) {
						if (map.at(pt) == MapChar.PATH) {
							map.set(pt, MapChar.DOOR);
						}
					}
					return MapChar.ROOM;
				}
				return MapChar.DOOR;
			});
			repeat = false;
			for (Point p : map.listWithChar(MapChar.DOOR)) {
				if (lonelyDoor.or(shyDoor).test(p)) {
					repeat = true;
				}
			}
		}
		repeat = true;
		while(repeat) {
			System.out.println("Pre-de-crowded");
			System.out.println(map);
			map.performOnChar(MapChar.DOOR, p -> {
				return crowdedDoor.test(p) ? MapChar.WALL : MapChar.DOOR;
			});
			repeat = false;
			for (Point p : map.listWithChar(MapChar.DOOR)) {
				if (crowdedDoor.test(p)) {
					repeat = true;
				}
			}
		}
	}

	public void print() {
		System.out.println(map);
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
