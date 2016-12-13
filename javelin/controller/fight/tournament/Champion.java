package javelin.controller.fight.tournament;

import java.util.ArrayList;

import javelin.Javelin;
import javelin.controller.challenge.ChallengeRatingCalculator;
import javelin.controller.exception.battle.StartBattle;
import javelin.controller.fight.ExhibitionFight;
import javelin.model.unit.Combatant;
import javelin.model.unit.Monster;

/**
 * Exhibition against a shingle opponent.
 * 
 * @author alex
 */
public class Champion extends Exhibition {
	/** Constructor. */
	public Champion() {
		super("Champion");
	}

	@Override
	public void start() {
		throw new StartBattle(new ExhibitionFight() {
			@Override
			public ArrayList<Combatant> getmonsters(int teamel) {
				for (Monster m : new CrIterator(Javelin.MONSTERSBYCR)) {
					if (ChallengeRatingCalculator
							.crtoel(m.challengerating) >= teamel) {
						ArrayList<Combatant> opponents =
								new ArrayList<Combatant>();
						opponents.add(new Combatant(m, true));
						return opponents;
					}
				}
				throw new RuntimeException(
						"couldn't generate Champion exhibition");
			}
		});
	}

}
