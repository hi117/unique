package net.hi117.singularity;

import java.util.Comparator;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public final class EventComparator implements Comparator<Event> {

	public static final EventComparator INSTANCE = new EventComparator();

	private EventComparator() {
		// singleton
	}

	@Override
	public final int compare(final Event o1, final Event o2) {
		final int timeComparison = Long.compare(o1.getTime(), o2.getTime());
		return (timeComparison != 0) ?
				timeComparison :
				Integer.compare(o1.getPriority(), o2.getPriority());
	}

}
