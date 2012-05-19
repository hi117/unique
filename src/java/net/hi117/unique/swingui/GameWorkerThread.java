package net.hi117.unique.swingui;

import net.hi117.unique.CausalityViolationException;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;
import net.hi117.unique.Timeline;

import javax.swing.*;

/**
 * The thread that advances the game along the timeline.
 *
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class GameWorkerThread extends SwingWorker<Void, Void> {

	private final Game myGame;

	GameWorkerThread(final Game game) {
		myGame = game;
	}

	/**
	 * Advances the clock in the game and calls for event processing.
	 *
	 * @return {@code null} .
	 *
	 * @throws EventException if processing of an event fails.
	 * @throws CausalityViolationException if an event attempts to schedule
	 * another event in the past.
	 *
	 * @see Timeline#tick()
	 */
	@Override
	protected Void doInBackground()
			throws EventException, CausalityViolationException {
		final Timeline timeline = myGame.getTimeline();

		try {
			boolean advanced;
			do {
				advanced = timeline.tick();
			} while (advanced);
		} catch (InterruptedException e) {
			// break the loop
		}

		return null;
	}
}
