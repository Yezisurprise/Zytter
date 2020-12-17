package entity;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Random;

import javazoom.jl.player.*;
import util.Config;

public class BGM extends Thread {
    
	Player player;
    
    ArrayList<String> playerlist = new ArrayList<String>();
    
    int m = 0;
    
    boolean isplaying = true;
        
    public BGM() {
    	playerlist.add("/bgm/main1.mp3");//0
    	playerlist.add("/bgm/main2.mp3");//1
    	playerlist.add("/bgm/heroselect.mp3");//2
    	playerlist.add("/bgm/fight1.mp3");//3
    	playerlist.add("/bgm/fight2.mp3");//4
    	playerlist.add("/bgm/fight3.mp3");//5
    	playerlist.add("/bgm/willwin.mp3");//6
    	playerlist.add("/bgm/lastbattle.mp3");//7
    	playerlist.add("/bgm/lastonehero.mp3");//8
    }
    
    public void setBGM(int m) {
    	this.m = m;
    }
    
    public void stopBGM() {
    	isplaying = false;
    	if(Config.isbgm) player.close();
    	interrupt();
    }
    
    public void playerBGM() {
    	start();
    }
    	
	@Override
	public void run() {
		try {
			if(Config.isbgm) {
				while(isplaying) {
					String filename = playerlist.get(m);
					BufferedInputStream buffer = new BufferedInputStream((getClass().getResourceAsStream(filename)));
			        player = new Player(buffer);
		        	player.play();
		        	switch(m) {
			    		case 0:{
			    			m = 1;
			    			break;
			    		}
			    		case 1:{
			    			m = 0;
			    			break;
			    		}
			    		case 2:{
			    			break;
			    		}
			    		case 3:{
			    			int b = new Random().nextInt(3);
			    			if(b!=0) m = b+3;
			    			else m = 4;
					    	break;
			    		}
			    		case 4:{
			    			int b = new Random().nextInt(3);
			    			if(b!=1) m = b+3;
			    			else m = 4;
					    	break;
			    		}
			    		case 5:{
			    			int b = new Random().nextInt(3);
			    			if(b!=2) m = b+3;
			    			else m = 4;
					    	break;
			    		}
			    		case 6:{
					    	break;
			    		}
			    		case 7:{
					    	break;
			    		}
			    		case 8:{
					    	break;
			    		}
			    	}
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
}
