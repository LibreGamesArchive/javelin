package javelin.view.frame.arena;

import java.awt.Button;
import java.awt.Container;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.BoxLayout;

import javelin.model.unit.Combatant;
import javelin.model.world.location.unique.Arena;
import javelin.view.frame.Frame;

public class ViewGladiators extends Frame {
	public ViewGladiators(Arena arena) {
		super("View");
	}

	@Override
	protected Container generate() {
		Panel panel = new Panel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		ArenaWindow.arena.gladiators.sort(new Comparator<Combatant>() {
			@Override
			public int compare(Combatant o1, Combatant o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		for (final Combatant c : ArenaWindow.arena.gladiators) {
			Button b = new Button(c.toString());
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new ViewGladiator(c, ArenaWindow.arena)
							.show(ViewGladiators.this);
				}
			});
			panel.add(b);
		}
		panel.add(new Label());
		Button b = new Button("Return");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(b);
		return panel;
	}
}