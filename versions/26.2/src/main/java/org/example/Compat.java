package org.example;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/** Version specific calls for 26.2. */
public final class Compat {
	private Compat() {}

	public static Screen screen(Minecraft mc) {
		return mc.gui.screen();
	}

	/** @return the contents of a container item (shulker box), or null if it has none */
	public static List<ItemStack> containerItems(ItemStack stack) {
		ItemContainerContents component = stack.getComponents().get(DataComponents.CONTAINER);
		return component == null ? null : component.allItemsCopyStream().toList();
	}

	public static double scaledMouseX(Minecraft mc) {
		return mc.mouseHandler.getScaledXPos(mc.getWindow());
	}

	public static double scaledMouseY(Minecraft mc) {
		return mc.mouseHandler.getScaledYPos(mc.getWindow());
	}

	public static int dyeColor(DyeColor dye) {
		return dye.getTextureDiffuseColor();
	}
}
