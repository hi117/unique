package net.hi117.unique;

import net.hi117.unique.events.GameStartEvent;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public class Game implements Serializable {
	private final Timeline<Game> myTimeline;

	private AtomicInteger myValue = new AtomicInteger(10);

	private volatile boolean myIncreaseThisTurn;

	/**
	 * Creates a new game.
	 *
	 * A new timeline is created and populated with a single {@link
	 * GameStartEvent}. The value is set to 30.
	 */
	public Game() {
		myTimeline = new Timeline<>();
		myTimeline.addEvent(new GameStartEvent());
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

	public final Timeline<Game> getTimeline() {
		return myTimeline;
	}

}
