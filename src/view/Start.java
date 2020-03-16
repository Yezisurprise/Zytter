package view;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import util.Config;

public class Start {
	public static void main(String[] args) {
		
		/*
		 * Test
		 */
		InitGlobalFont(Config.GlobalFont);
		InitToolTipShowTime();
		new Welcome().setVisible(true);
	}
	
	private static void InitToolTipShowTime() {
		ToolTipManager.sharedInstance().setInitialDelay(10);
		ToolTipManager.sharedInstance().setDismissDelay(10000);
	}
	
	private static void InitGlobalFont(Font font) {
		/*
		 * 设置全局字体
		 */
		FontUIResource fontRes=new FontUIResource(font);
		for (Enumeration<Object> keys=UIManager.getDefaults().keys();keys.hasMoreElements();) {
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key,fontRes);
			}
		}
	}
	
}
