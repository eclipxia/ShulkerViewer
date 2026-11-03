package org.example;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.rusherhack.client.api.events.render.EventRender2D;

/** Thin wrapper over the version specific gui graphics class (1.21.4). */
public final class Gfx {
	private final GuiGraphics g;

	public Gfx(EventRender2D event) {
		this.g = event.getRenderContext().graphics();
	}

	public void pushScale(float scale) {
		g.pose().pushPose();
		g.pose().scale(scale, scale, 1.0f);
	}

	public void popScale() {
		g.pose().popPose();
	}

	public void fill(int x1, int y1, int x2, int y2, int color) {
		g.fill(x1, y1, x2, y2, color);
	}

	public void item(ItemStack stack, int x, int y) {
		g.renderItem(stack, x, y);
	}

	/** @param text overrides the count text, or null for the default */
	public void itemDecorations(ItemStack stack, int x, int y, String text) {
		Minecraft mc = Minecraft.getInstance();
		if (text == null) {
			g.renderItemDecorations(mc.font, stack, x, y);
		} else {
			g.renderItemDecorations(mc.font, stack, x, y, text);
		}
	}
}
