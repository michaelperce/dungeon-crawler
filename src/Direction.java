public enum Direction {
    DOWN,
    LEFT,
    UP,
    RIGHT;

	public Direction opposite() {
		switch(this) {
			case DOWN:  return UP;
			case LEFT:  return RIGHT;
			case UP:    return DOWN;
			case RIGHT: return LEFT;
		};
		return null;
	}
}
