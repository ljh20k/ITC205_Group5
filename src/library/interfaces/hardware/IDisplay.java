package library.interfaces.hardware;

import javax.swing.JPanel;

public interface IDisplay {
	
	JPanel getDisplay();
	void setDisplay(JPanel panel, String identifier);
	
}
