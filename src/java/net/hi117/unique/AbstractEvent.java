package net.hi117.unique;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public abstract class AbstractEvent<G> implements Event<G> {

	public static GameUserInterface ourGameUserInterface;

	private final long myTime;

	private final int myPriority;

	protected AbstractEvent(final long time, final int priority) {
		myTime = time;
		myPriority = priority;
	}

	@Override
	public final long getTime() {
		return myTime;
	}

	@Override
	public final int getPriority() {
		return myPriority;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"time=" + myTime +
				", priority=" + myPriority +
				'}';
	}
}
