package net.hi117.singularity;

import java.io.IOException;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public class Main {

	public static void main(final String[] args) {
		System.out.println("Starting new game...\n");
		try (final Game game = new Game()) {
			while (game.getTimeline().tick()) {
				// do nothing
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Thanks for playing!");
	}

}
