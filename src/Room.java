public class Room {
	Point location;
	Grid world;
	public Room(Point q, Grid universe) {
		location = q;
		world = universe;
	}
	public Point getLocation() {
		return location;
	}

	public void expand() {
		for (int dr = -1; dr < 2; dr++) {
			for (int dc = -1; dc < 2; dc++) {
				Point p = location.add(new Point(dr, dc));
				if (world.isInBounds(p)) {
					world.set(p, MapChar.ROOM);
				}
			}
		}
	}
}
