package me.austinlm.looteffects.effects.common;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import me.austinlm.looteffects.EffectStep;
import me.austinlm.looteffects.runner.EffectExecutionInfo;
import me.austinlm.looteffects.utils.EntityMover;
import me.austinlm.looteffects.utils.SoundRepeater;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Spawn a stand with a random item on it and raise it up to just above the frame.
 */
public class StarterStep implements EffectStep {

	private static final Random RANDOM = new Random();
	private static final List<Material> SAFE_MATS = Arrays.stream(Material.values())
			.filter(m -> m.isSolid() && !m.isInteractable()).collect(Collectors.toList());

	@Override
	public void begin(EffectExecutionInfo info) {
		Location where = info.getWhere().clone();
		where.subtract(0, 4.5, 0);
		ArmorStand stand = (ArmorStand) info.getWhere().getWorld()
				.spawnEntity(where, EntityType.ARMOR_STAND);
		stand.setInvisible(true);
		stand.setSmall(true);
		stand.setGravity(false);
		stand.setItem(EquipmentSlot.HEAD,
				new ItemStack(SAFE_MATS.get(RANDOM.nextInt(SAFE_MATS.size()))));
		info.storeData("entity", stand);
		SoundRepeater repeater = SoundRepeater
				.play(Sound.BLOCK_SLIME_BLOCK_PLACE, stand.getLocation(), 0.4f, 1.5f, 5);
		EntityMover.move(stand, new Vector(0, 5, 0), 3, 1, () -> {
			repeater.cancel();
			info.getRunner().goToNextStep();
		});
	}

	@Override
	public void finish(EffectExecutionInfo info) {

	}

	@Override
	public int getExecutionDelay() {
		return 0;
	}

	@Override
	public String getId() {
		return "rise-up-to-frame";
	}


}
