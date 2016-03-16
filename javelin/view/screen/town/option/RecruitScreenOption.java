package javelin.view.screen.town.option;

import javelin.model.world.town.Town;
import javelin.view.screen.town.RecruitScreen;
import javelin.view.screen.town.SelectScreen;

/**
 * @see RecruitScreen
 * @author alex
 */
public class RecruitScreenOption extends ScreenOption {

	public RecruitScreenOption(String name, Town t) {
		super(name, t);
	}

	@Override
	public SelectScreen show() {
		return new RecruitScreen("Recruit:", t);
	}

}
