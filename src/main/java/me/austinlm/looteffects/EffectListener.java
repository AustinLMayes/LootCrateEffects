package me.austinlm.looteffects;

import java.util.concurrent.atomic.AtomicBoolean;
import me.austinlm.looteffects.utils.CommandUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EffectListener implements Listener {

	private final AtomicBoolean cooldown = new AtomicBoolean(false);
	private final EffectsManager manager;
	private CrateOpenEffect preSelected = null;

	public EffectListener(EffectsManager manager) {
		this.manager = manager;
	}

	public void selectEffect(CrateOpenEffect effect) {
		this.preSelected = effect;
	}

	@EventHandler
	public void showEffect(PlayerInteractEvent event) {
		if (cooldown.get()) {
			return;
		}
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null
				&& event.getClickedBlock().getType() == Material.END_PORTAL_FRAME) {

			cooldown.set(true);
			Bukkit.getScheduler().runTaskLater(LootEffectsPlugin.INSTANCE, () -> cooldown.set(false), 5);

			if (this.manager.isRunningEffect()) {
				CommandUtils.sendError(event.getPlayer(), "Only one effect may be displayed at a time!");
				return;
			}

			Location where = event.getClickedBlock().getLocation().toCenterLocation();

			event.setCancelled(true);
			if (this.preSelected != null) {
				this.manager
						.showEffect(this.preSelected, where, event.getPlayer());
			} else {
				CrateOpenEffect shown = this.manager
						.showRandomEffect(where, event.getPlayer());
				Bukkit.broadcast(Component.text()
						.append(Component.text("Effect: ").color(NamedTextColor.AQUA))
						.append(Component.text(shown.getName()).color(NamedTextColor.GOLD))
						.asComponent());
			}

		}
	}
}
