package net.hi117.unique;

import net.hi117.unique.events.GameStartEvent;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public class Game {
	private final Timeline myTimeline;

	private int myValue = 3;

	public Game() {
		myTimeline = new Timeline();
		try {
			myTimeline.addEvent(new GameStartEvent(this));
		} catch (CausalityViolationException ignore) {
			// can't happen
			ignore.printStackTrace();
		}
	}

	public final int getValue() {
		return myValue;
	}

	public final int increaseValue() {
		return myValue += 1;
	}

	public final int decreaseValue() {
		return myValue -= 1;
	}

	public final Timeline getTimeline() {
		return myTimeline;
	}

}
