package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class IncreaseValueEvent extends AbstractEvent {

	IncreaseValueEvent(final long time, final Game game) {
		super(time, 2, game);
	}

	@Override
	public void trigger() throws EventException {
		if (myGame.isIncreaseThisTurn()) {
			myGame.increaseValue();
			ourGameUserInterface.updateValue();
		}
	}
}
