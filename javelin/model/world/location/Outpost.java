package javelin.model.world.location;

import java.util.List;

import javelin.model.unit.Combatant;
import javelin.model.unit.Squad;
import javelin.model.world.World;
import javelin.model.world.location.fortification.Fortification;
import javelin.view.screen.WorldScreen;

/**
 * An outpost allows for vision of a wide area around it.
 * 
 * TODO fog of war: daily obscure all unseen squares. non-hostile locations
 * become source of vision. outposts become hostile (easy) and
 * non-sacrificeable. Probably better to make it progressive (like 1/7 chance a
 * day of a unseen spot disappearing - as to not negate the benefire of Gather
 * Information and such).
 * 
 * @see World#makemap()
 * @author alex
 */
public class Outpost extends Fortification {
	/** How many squares away to help vision with. */
	public static final int VISIONRANGE = 3;
	private static final String DESCRIPTION = "Outpost";

	/** Constructor. */
	public Outpost() {
		super(DESCRIPTION, DESCRIPTION, 1, 5);
		gossip = true;
		vision = VISIONRANGE;
	}

	/** Puts a new instance in the {@link World} map. */
	public static void build() {
		new Outpost().place();
	}

	@Override
	public boolean interact() {
		Squad.active.view(Squad.active.perceive(false, true) + 10);
		return super.interact();
	}

	/**
	 * Given a coordinate shows a big amount of land around that.
	 * 
	 * @param range
	 *            How far squares away will become visible.
	 * @see WorldScreen#DISCOVERED
	 */
	static public void discover(int xp, int yp, int range) {
		for (int x = xp - range; x <= xp + range; x++) {
			for (int y = yp - range; y <= yp + range; y++) {
				WorldScreen.setVisible(x, y);
			}
		}
	}

	@Override
	protected void generate() {
		x = -1;
		while (x == -1 || isnear(Outpost.class)) {
			generateawayfromtown();
		}
	}

	@Override
	public List<Combatant> getcombatants() {
		return garrison;
	}
}
