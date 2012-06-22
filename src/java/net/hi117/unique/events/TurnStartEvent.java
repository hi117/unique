package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class TurnStartEvent extends AbstractEvent<Game> {

	TurnStartEvent(final long time) {
		super(time, 0);
	}

	@Override
	public void trigger(final Game game) throws EventException {
		try {
			ourGameUserInterface.updateTime();
			ourGameUserInterface.updateValue();
		} catch (Exception e) {
			throw new EventException(this, e);
		}
		game.setIncreaseThisTurn(false);
		if (game.getValue() < 0) {
			game.getTimeline().addEvent(new DefeatEvent(getTime()));
		} else if (getTime() >= 20) {
			game.getTimeline().addEvent(new VictoryEvent(getTime()));
		} else {
			game.getTimeline().addEvent(new UserInputEvent(getTime()));
		}
	}

}
