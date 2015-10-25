import java.util.LinkedList;

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

	public LinkedList<Point> listWithChar(MapChar ch) {
		LinkedList<Point> list = new LinkedList<Point>();
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

	public boolean isInBounds(Point p) {
		return (p.getRow() > -1) && (p.getRow() < this.getHeight())
			&& (p.getCol() > -1) && (p.getCol() < this.getWidth());
	}
}
