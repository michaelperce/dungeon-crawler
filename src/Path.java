import java.util.Random;
import java.util.LinkedList;
import java.util.List;

public class Path{
	Point location;
	Grid world;
	Random rng;
	public Path(int x, int y, double percentPath, Grid universe) {
		rng = new Random();
		location = new Point(x, y);
		this.world = universe;
		world.set(new Point(x, y), MapChar.PATH);

		this.generate(percentPath);
	}
	public void generate(double percentPath) {
		Direction dir = null;
		while (world.percentOfChar(MapChar.PATH) < percentPath) {

			// choosing the direction
			List<Direction> valids = location.validDirections(world);
			if (dir != null) {
				valids.remove(dir.opposite());
			}
			dir = valids.get(rng.nextInt(valids.size()));

			// moving the path
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
	}
}
