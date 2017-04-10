package javelin.model.world.location.town.labor.military;

import java.util.ArrayList;
import java.util.HashSet;

import javelin.controller.upgrade.Upgrade;
import javelin.controller.upgrade.UpgradeHandler;
import javelin.controller.upgrade.ability.RaiseAbility;
import javelin.controller.upgrade.ability.RaiseIntelligence;
import javelin.controller.upgrade.ability.RaiseStrength;
import javelin.controller.upgrade.classes.Warrior;
import javelin.model.world.location.town.Rank;
import tyrant.mikera.engine.RPG;

/**
 * Allows a player to learn one upgrade set.
 *
 * @author alex
 */
public class MartialAcademy extends Academy {
	public static final ArrayList<Guild> GUILDS = new ArrayList<MartialAcademy.Guild>();

	static {
		UpgradeHandler uh = UpgradeHandler.singleton;
		GUILDS.add(new Guild(uh.expertise, "Academy (combat expertise)",
				RaiseIntelligence.SINGLETON));
		GUILDS.add(new Guild(uh.power, "Academy (power attack)",
				RaiseStrength.SINGLETON));
	}

	public static class BuildMartialAcademy extends BuildAcademies {
		public BuildMartialAcademy() {
			super(Rank.HAMLET);
		}

		@Override
		protected Academy getacademy() {
			return RPG.pick(GUILDS).generate();
		}
	}

	public static class Guild {
		String name;
		HashSet<Upgrade> upgrades;
		RaiseAbility ability;

		public Guild(HashSet<Upgrade> upgrades, String name,
				RaiseAbility ability) {
			super();
			this.name = name;
			this.upgrades = upgrades;
			this.ability = ability;
		}

		public MartialAcademy generate() {
			return new MartialAcademy(upgrades, name, ability);
		}
	}

	/**
	 * See {@link Academy#Academy(String, String, int, int, HashSet)}.
	 *
	 * @param raise
	 */
	public MartialAcademy(HashSet<Upgrade> upgrades, String descriptionknownp,
			RaiseAbility raise) {
		super(descriptionknownp, "An academy", 6, 10, upgrades, raise,
				Warrior.SINGLETON);
	}
}