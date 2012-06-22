package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class UserInputEvent extends AbstractEvent<Game> {

	UserInputEvent(final long time) {
		super(time, 1);
	}

	@Override
	public void trigger(final Game game) throws EventException, InterruptedException {
		ourGameUserInterface.unlockControls();
		Thread.sleep(1000);
		game.getTimeline().addEvent(new IncreaseValueEvent(getTime()));
		game.getTimeline().addEvent(new DecreaseValueEvent(getTime()));
	}
}
