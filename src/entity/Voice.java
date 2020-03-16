package entity;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class Voice extends Thread {
	
	private InputStream is;
    private Player player;

	public Voice(InputStream is) {
        this.is = is;
    }

	@Override
	public void run() {
		super.run();
		try {
	        BufferedInputStream buffer = new BufferedInputStream(is);
	        player = new Player(buffer);
            player.play();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
 
}