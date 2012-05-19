package net.hi117.unique;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public interface GameUserInterface {
	void init(final Game game);

	void updateTime();

	void updateValue();

	void unlockControls();

	void message(final String message) throws Exception;

	void endGame(final Boolean victory);
}
