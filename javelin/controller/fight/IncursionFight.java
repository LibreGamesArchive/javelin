package javelin.controller.fight;

import java.awt.Image;
import java.util.ArrayList;

import javelin.Javelin;
import javelin.model.unit.Combatant;
import javelin.model.world.Incursion;
import javelin.view.screen.BattleScreen;
import tyrant.mikera.tyrant.QuestApp;

/**
 * @see Incursion
 * @author alex
 */
public class IncursionFight extends Fight {
	/** See {@link Javelin#settexture(Image)}. */
	public static final Image INCURSIONTEXTURE = QuestApp
			.getImage("/images/texture2.png");

	/** Incursion being fought. */
	public final Incursion incursion;

	/** Constructor. */
	public IncursionFight(final Incursion incursion) {
		this.incursion = incursion;
		texture = IncursionFight.INCURSIONTEXTURE;
		meld = true;
		hide = false;
	}

	@Override
	public int getel(final int teamel) {
		// throw new RuntimeException(
		// "Shouldn't have to generate an incursion fight.");
		return incursion.getel();
	}

	@Override
	public ArrayList<Combatant> getmonsters(int teamel) {
		return Incursion.getsafeincursion(incursion.squad);
	}

	@Override
	public void bribe() {
		incursion.remove();
	}

	@Override
	public boolean onend() {
		super.onend();
		if (Fight.victory) {
			incursion.remove();
		} else {
			for (Combatant incursant : new ArrayList<Combatant>(
					incursion.squad)) {
				Combatant alive = null;
				for (Combatant inbattle : Fight.state.getcombatants()) {
					if (inbattle.id == incursant.id) {
						alive = inbattle;
						break;
					}
				}
				if (alive == null) {
					incursion.squad.remove(incursant);
				}
			}
		}
		return true;
	}

	@Override
	public ArrayList<Combatant> generate(int teamel) {
		ArrayList<Combatant> foes = super.generate(teamel);
		incursion.squad = Incursion.getsafeincursion(foes);
		return foes;
	}

	@Override
	public void withdraw(Combatant combatant, BattleScreen screen) {
		dontflee(screen);
	}
}