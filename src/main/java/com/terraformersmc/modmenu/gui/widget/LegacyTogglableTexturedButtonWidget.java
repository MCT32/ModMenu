package com.terraformersmc.modmenu.gui.widget;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LegacyTogglableTexturedButtonWidget extends LegacyTexturedButtonWidget {
	private final int toggledUOffset;

	private boolean toggled;

	public LegacyTogglableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, int toggledUOffset, boolean toggled, Identifier texture, int textureWidth, int textureHeight, PressAction pressAction, Text message) {
		super(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, message);

		this.toggledUOffset = toggledUOffset;

		this.toggled = toggled;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}

	public void toggle() {
		this.toggled = !this.toggled;
	}

	@Override
	public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
		int u = this.u;
		int v = this.v;

		if (!this.isNarratable()) {
			v += this.hoveredVOffset * 2;
		} else {
			if (this.isSelected()) {
				v += this.hoveredVOffset;
			}

			if (this.isToggled()) {
				u += this.toggledUOffset;
			}
		}

		context.drawTexture(this.texture, this.getX(), this.getY(), u, v, this.width, this.height, this.textureWidth, this.textureHeight);
	}

	public static Builder legacyTogglableTexturedBuilder(Text message, ButtonWidget.PressAction onPress) {
		return new Builder(message, onPress);
	}

	public static class Builder {
		private final Text message;
		private final ButtonWidget.PressAction onPress;

		private int x;
		private int y;

		private int width;
		private int height;

		private int u;
		private int v;
		private int hoveredVOffset;
		private int toggledUOffset;

		private Identifier texture;

		private int textureWidth;
		private int textureHeight;

		private boolean toggled;

		public Builder(Text message, PressAction onPress) {
			this.message = message;
			this.onPress = onPress;
		}

		public Builder position(int x, int y) {
			this.x = x;
			this.y = y;

			return this;
		}

		public Builder size(int width, int height) {
			this.width = width;
			this.height = height;

			return this;
		}

		public Builder uv(int u, int v, int hoveredVOffset, int toggledUOffset) {
			this.u = u;
			this.v = v;

			this.hoveredVOffset = hoveredVOffset;
			this.toggledUOffset = toggledUOffset;

			return this;
		}

		public Builder texture(Identifier texture, int textureWidth, int textureHeight) {
			this.texture = texture;

			this.textureWidth = textureWidth;
			this.textureHeight = textureHeight;

			return this;
		}

		public Builder toggled(boolean toggled) {
			this.toggled = toggled;

			return this;
		}

		public LegacyTogglableTexturedButtonWidget build() {
			return new LegacyTogglableTexturedButtonWidget(this.x, this.y, this.width, this.height, this.u, this.v, this.hoveredVOffset, this.toggledUOffset, this.toggled, this.texture, this.textureWidth, this.textureHeight, this.onPress, this.message);
		}
	}
}
