package net.hi117.singularity;

import net.hi117.singularity.events.GameStartEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public class Game implements AutoCloseable {
	private final Timeline myTimeline;

	private final BufferedReader myReader;

	private int myValue = 3;

	public Game() throws IOException {
		myTimeline = new Timeline();
		myReader = new BufferedReader(
				new InputStreamReader(System.in, "UTF-8")
		);
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

	public String getUserInput() throws IOException {
		return myReader.readLine();
	}

	@Override
	public void close() throws IOException {
		myReader.close();
	}
}
