package javelin.controller.action.world;

import java.util.ArrayList;

import javelin.model.unit.Combatant;
import javelin.model.world.Squad;
import javelin.model.world.place.unique.MercenariesGuild;
import javelin.view.screen.WorldScreen;

/**
 * Allows members to be removed from a {@link Squad} at any point.
 * 
 * @author alex
 */
public class Dismiss extends WorldAction {
	/** Constructor. */
	public Dismiss() {
		super("Dismiss squad member", new int[] { 'D' }, new String[] { "D" });
	}

	@Override
	public void perform(WorldScreen screen) {
		int choice =
				CastSpells.choose("Which squad member do you want to dismiss?",
						Squad.active.members, true, false);
		if (choice == -1) {
			return;
		}
		Combatant chosen = Squad.active.members.get(choice);
		float dailyupkeep = chosen.source.eat() / 2f;
		if (chosen.mercenary) {
			dailyupkeep += MercenariesGuild.getfee(chosen);
		}
		ArrayList<String> confirm = new ArrayList<String>(2);
		confirm.add("Yes, I am sure.");
		confirm.add("Not really, keep unit.");
		if (CastSpells.choose("Are you sure you want to dismiss " + chosen
				+ ", with a daily cost of $" + Math.round(dailyupkeep) + "?",
				confirm, true, true) == 0) {
			Squad.active.dismiss(chosen);
		}
	}
}