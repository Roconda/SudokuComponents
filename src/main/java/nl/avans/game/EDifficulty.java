package nl.avans.game;

public enum EDifficulty {
	
	EASY (0),
	NORMAL (1),
	HARD (2),
	VERY_HARD (3),
	EXPERT (4);
	
	
	private final int difficulty;
	
	EDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
