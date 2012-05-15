package net.hi117.unique;

import net.hi117.unique.swingui.MainFrame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public class Main {

	public static void main(final String[] args) {
		final AtomicReference<MainFrame> mfRef = new AtomicReference<>();
		try {
			SwingUtilities.invokeAndWait(
					new Runnable() {
						@Override
						public void run() {
							final MainFrame mainFrame;
							try {
								mainFrame = new MainFrame();
							} catch (Exception e) {
								throw new RuntimeException(e);
							}
							mfRef.set(mainFrame);
						}
					}
			);
		} catch (InterruptedException | InvocationTargetException e) {
			System.err.println("An error occurred while creating UI");
			e.printStackTrace();
			return;
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mfRef.get().setVisible(true);
			}
		});
	}

}
