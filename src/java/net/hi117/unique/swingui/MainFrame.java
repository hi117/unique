package net.hi117.unique.swingui;

import net.hi117.unique.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.io.IOException;
/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public class MainFrame extends JFrame {

	private static final String MENU_SCREEN = "Menu screen";

	private static final String GAME_SCREEN = "Game screen";

	private final CardLayout myCardLayout = new CardLayout();

	private final MenuScreen myMenuScreen;

	private final GameScreen myGameScreen;

	private volatile GameWorkerThread myGameWorkerThread;
	
	private String loadFile = "";

	public MainFrame() throws InvocationTargetException, InterruptedException {
		super("Unique");

		getContentPane().setLayout(myCardLayout);

		myMenuScreen = new MenuScreen(new Runnable() {
			@Override
			public void run() {
				myCardLayout.show(getContentPane(), GAME_SCREEN);
				if (loadFile == "") {
					myGameWorkerThread = new GameWorkerThread(new Game());
				}
				else {
					try {
					myGameWorkerThread = new GameWorkerThread(load(loadFile));
					} catch (Exception e) {
					}
				}
				myGameWorkerThread.execute();
			}
		});
		getContentPane().add(myMenuScreen, MENU_SCREEN);

		myGameScreen = new GameScreen(new Callback<Boolean>() {
			@Override
			public void doWith(final Boolean victory) {
				myGameWorkerThread = null;
				myMenuScreen.setLabel(victory);
				myCardLayout.show(getContentPane(), MENU_SCREEN);
			}
		});
		getContentPane().add(myGameScreen, GAME_SCREEN);

		myCardLayout.show(getContentPane(), MENU_SCREEN);

		setMinimumSize(new Dimension(400, 300));
		pack();

		addWindowListener(
				new WindowAdapter() {
					@Override
					public void windowClosing(final WindowEvent e) {
						if (myGameWorkerThread != null) {
							myGameWorkerThread.cancel(true);
							try {
								myGameWorkerThread.get();
							} catch (InterruptedException | ExecutionException |
									CancellationException ignore) {
								// ignore
							}
						}
						MainFrame.this.dispose();
					}
				}
		);
	}
	public Game load(final String filename) throws IOException {
		Game game = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try{
		fis = new FileInputStream(filename);
		in = new ObjectInputStream(fis);
		game = (Game)in.readObject();
		in.close();
		}
		catch (Exception e){
		
		}
		return game;
	}
}
