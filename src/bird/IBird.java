package bird;

import gameCommons.Case;
import gameCommons.Game;

public interface IBird {
	public Case getPos();
	public void down();
	public boolean update();
}
