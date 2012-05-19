package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class UserInputEvent extends AbstractEvent {

	UserInputEvent(final long time, final Game game) {
		super(time, 1, game);
	}

	@Override
	public void trigger() throws EventException, InterruptedException {
		ourGameUserInterface.unlockControls();
		Thread.sleep(1000);
		myGame.getTimeline().addEvent(
				new IncreaseValueEvent(getTime(), myGame)
		);
		myGame.getTimeline().addEvent(
				new DecreaseValueEvent(getTime(), myGame)
		);
	}
}
