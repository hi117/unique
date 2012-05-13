package net.hi117.singularity.events;

import net.hi117.singularity.Game;
import net.hi117.singularity.AbstractEvent;
import net.hi117.singularity.CausalityViolationException;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class TurnStartEvent extends AbstractEvent {

	TurnStartEvent(final long time, final Game game) {
		super(time, 0, game);
	}

	@Override
	public void trigger() throws CausalityViolationException {
		System.out.println("It is turn " + getTime());
		System.out.println("Your value is " + myGame.getValue());
		if (myGame.getValue() < 0) {
			myGame.getTimeline().addEvent(new DefeatEvent(getTime(), myGame));
		} else if (getTime() >= 5) {
			myGame.getTimeline().addEvent(new VictoryEvent(getTime(), myGame));
		} else {
			myGame.getTimeline().addEvent(
					new UserInputEvent(getTime(), myGame)
			);
		}
	}

}
