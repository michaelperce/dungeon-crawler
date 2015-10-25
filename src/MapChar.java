public enum MapChar {
	DOOR('/'),
	PATH('.'),
	ROOM(' '),
	WALL('#'),
	STAIRSUP('^'),
	STAIRSDOWN('v');

	private final char value;
	public char asChar() {
		return value;
	}

	private MapChar(char value) {
		this.value = value;
	}
}


