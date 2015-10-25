import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Grid {
	private MapChar[][] world;
	public Grid(int rows, int cols) {
		world = new MapChar[rows][cols];
	}

	public Grid(int rows, int cols, MapChar ch) {
		this(rows, cols);
		for (int r = 0; r < this.getHeight(); r++) {
			for (int c = 0; c < this.getWidth(); c++) {
				world[r][c] = ch;
			}
		}
	}

	public Grid(MapChar[][] old) {
		world = old.clone();
	}

	public Grid clone() {
		return new Grid(world);
	}


	public void set(Point p, MapChar c) {
		world[p.getRow()][p.getCol()] = c;
	}

	public MapChar at(Point p) {
		return world[p.getRow()][p.getCol()];
	}

	public int getHeight() {
		return world.length;
	}

	public int getWidth() {
		return world[0].length;
	}

	public int count(MapChar ch) {
		int count = 0;
		for (int r = 0; r < this.getHeight(); r++) {
			for (int c = 0; c < this.getWidth(); c++) {
				if (world[r][c] == ch) {
					count++;
				}
			}
		}
		return count;
	}

	public int area() {
		return this.getHeight() * this.getWidth();
	}

	public double percentOfChar(MapChar ch) {
		return (double)(count(ch))/this.area();
	}

	public List<Point> listWithChar(MapChar ch) {
		List<Point> list = new LinkedList<>();
		for(int r = 0; r < this.getHeight(); r++) {
			for(int c = 0; c < this.getWidth(); c++) {
				Point h = new Point(r, c);
				if(this.at(h) == ch) {
					list.add(h);
				}
			}
		}
		return list;
	}

	public void performOnChar(MapChar ch, Function<Point, MapChar> fn) {
		for (Point p : listWithChar(ch)) {
			this.set(p, fn.apply(p));
		}
	}

	public List<Point> getNeighborPoints(Point p) {
		List<Point> out = new LinkedList<>();
		for (Direction d : Direction.values()) {
			Point temp = new Point(p);
			temp.applyDirection(d);
			if(isInBounds(temp)) {
				out.add(temp);
			}
		}
		return out;
	}

	public List<MapChar> getNeighborChars(Point p) {
		List<MapChar> out = new LinkedList<>();
		for (Point pt : getNeighborPoints(p)) {
			out.add(this.at(pt));
		}
		return out;
	}

	public boolean hasNeighbor(Point p, MapChar ch) {
		return getNeighborChars(p).contains(ch);
	}

	public int countNeighborsWithChar(Point p, MapChar countMe) {
		int count = 0;
		for (MapChar ch : this.getNeighborChars(p)) {
			if (ch == countMe) {
				count++;
			}
		}
		return count;
	}

	public boolean isInBounds(Point p) {
		return (p.getRow() > -1) && (p.getRow() < this.getHeight())
			&& (p.getCol() > -1) && (p.getCol() < this.getWidth());
	}

	public String toString() {
		for(int row = 0; row < this.getHeight(); row++) {
			for(int col = 0; col < this.getWidth(); col++) {
				Point p = new Point(row, col);
				System.out.print(this.at(p).asChar());
			}
			System.out.println();
		}
		return "";
	}
}
