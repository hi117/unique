package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class IncreaseValueEvent extends AbstractEvent<Game> {

	IncreaseValueEvent(final long time) {
		super(time, 2);
	}

	@Override
	public void trigger(final Game game) throws EventException {
		if (game.isIncreaseThisTurn()) {
			game.increaseValue();
			ourGameUserInterface.updateValue();
		}
	}
}
