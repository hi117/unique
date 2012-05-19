package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class TurnStartEvent extends AbstractEvent {

	TurnStartEvent(final long time, final Game game) {
		super(time, 0, game);
	}

	@Override
	public void trigger() throws EventException {
		try {
			ourGameUserInterface.updateTime();
			ourGameUserInterface.updateValue();
		} catch (Exception e) {
			throw new EventException(this, e);
		}
		myGame.setIncreaseThisTurn(false);
		if (myGame.getValue() < 0) {
			myGame.getTimeline().addEvent(new DefeatEvent(getTime(), myGame));
		} else if (getTime() >= 20) {
			myGame.getTimeline().addEvent(new VictoryEvent(getTime(), myGame));
		} else {
			myGame.getTimeline().addEvent(
					new UserInputEvent(getTime(), myGame)
			);
		}
	}

}
