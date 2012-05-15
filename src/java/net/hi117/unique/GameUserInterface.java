package net.hi117.unique;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public interface GameUserInterface {
	void init(final Game game);

	void message(final String message) throws Exception;

	boolean input() throws Exception;

	void endGame(final Boolean victory);
}
