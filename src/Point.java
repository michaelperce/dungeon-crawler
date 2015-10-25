import java.util.List;
import java.util.LinkedList;

public class Point {
	private int row;
	private int col;
	public Point() {
		row = 0;
		col = 0;
	}
	public Point(int r, int c) {
		row = r;
		col = c;
	}
	public Point(Point old) {
		row = old.getRow();
		col = old.getCol();
	}

	public int getRow() {
		return row;
	}
	public void setRow(int r) {
		row = r;
	}

	public int getCol() {
		return col;
	}
	public void setCol(int c) {
		col = c;
	}

	public void addToRow(int dr) {
		row += dr;
	}
	public void addToCol(int dc) {
		col += dc;
	}

	public Point add(Point p) {
		return new Point(row + p.getRow(), col + p.getCol());
	}

	public void applyDirection(Direction d) {
		switch(d) {
			case UP:    row--; break;
			case DOWN:  row++; break;
			case LEFT:  col--; break;
			case RIGHT: col++; break;
		}
	}

	public List<Direction> validDirections(Grid world) {
		List<Direction> out = new LinkedList<>();
		for (Direction d : Direction.values()) {
			Point temp = new Point(this);
			temp.applyDirection(d);
			if (world.isInBounds(temp)) {
				out.add(d);
			}
		}
		return out;
	}

	public void wrap(int rows, int cols) {
		row = (row + rows) % rows;
		col = (col + cols) % cols;
	}

	public String toString() {
		return String.format("(%d, %d)", row, col);
	}
}
