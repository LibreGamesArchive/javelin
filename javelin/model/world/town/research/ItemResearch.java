package javelin.model.world.town.research;

import javelin.model.item.Item;
import javelin.model.world.town.Town;
import javelin.view.screen.town.option.ResearchScreenOption.ResearchScreen;

/**
 * Allows a new native or foreign (double cost) item to be sold in this
 * {@link Town}.
 * 
 * @author alex
 */
public class ItemResearch extends Research {

	/**
	 * This is roughly defined by diving the price of the most expensive item by
	 * the maximum {@link Town#size}.
	 */
	private static final int LABORINCREMENT = 1000;
	private Item i;

	public ItemResearch(Item i, int price) {
		super(i.toString(), sanitizeprice(i, price));
		this.i = i;
	}

	private static double sanitizeprice(Item i, int price) {
		int cost = i.price / LABORINCREMENT;
		return cost < price ? price : cost * price;
	}

	@Override
	public void apply(Town t, ResearchScreen s) {
		t.items.add(i);
	}

	@Override
	protected boolean isrepeated(Town t) {
		return t.items.contains(i);
	}

}