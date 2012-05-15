package net.hi117.unique.swingui;

import net.hi117.unique.Game;
import net.hi117.unique.Timeline;

import javax.swing.*;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class GameWorkerThread extends SwingWorker<Void, Void> {

	private final Game myGame;

	GameWorkerThread(final Game game) {
		myGame = game;
	}

	@Override
	protected Void doInBackground() throws Exception {
		final Timeline timeline = myGame.getTimeline();
		while (timeline.tick()) {
			// do nothing
		}
		return null;
	}
}
