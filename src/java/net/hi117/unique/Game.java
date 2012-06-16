package net.hi117.unique;

import net.hi117.unique.events.GameStartEvent;

import java.util.concurrent.atomic.AtomicInteger;
import java.io.Serializable;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public class Game implements Serializable {
	private final Timeline myTimeline;

	private AtomicInteger myValue = new AtomicInteger(10);

	private volatile boolean myIncreaseThisTurn;

	/**
	 * Creates a new game.
	 *
	 * A new timeline is created and populated with a single {@link
	 * GameStartEvent}. The value is set to 30.
	 */
	public Game() {
		myTimeline = new Timeline();
		myTimeline.addEvent(new GameStartEvent(this));
	}

	public final int getValue() {
		return myValue.get();
	}

	public final int increaseValue() {
		return myValue.addAndGet(5);
	}

	public final int decreaseValue() {
		return myValue.decrementAndGet();
	}

	public boolean isIncreaseThisTurn() {
		return myIncreaseThisTurn;
	}

	public void setIncreaseThisTurn(final boolean increaseThisTurn) {
		myIncreaseThisTurn = increaseThisTurn;
	}

	public final Timeline getTimeline() {
		return myTimeline;
	}

}
