package net.hi117.singularity.events;

import net.hi117.singularity.AbstractEvent;
import net.hi117.singularity.Game;

/**
* @author Yanus Poluektovich (ypoluektovich@gmail.com)
*/
public class GameStartEvent extends AbstractEvent {

	public GameStartEvent(final Game game) {
		super(0, -1, game);
	}

	@Override
	public void trigger() {
		System.out.println("Welcome to Endgame: Singularity!");
		System.out.println("In this game, you must not allow a value " +
				"to become negative for five turns.");
		System.out.println("Good luck!");
		System.out.println();

		myGame.getTimeline().addEvent(new UserInputEvent(0, myGame));
	}

}
