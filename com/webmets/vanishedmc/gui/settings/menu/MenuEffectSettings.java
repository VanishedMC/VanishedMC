package com.webmets.vanishedmc.gui.settings.menu;

import java.io.IOException;
import java.util.Arrays;

import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.RGBSelectorButton;
import com.webmets.vanishedmc.gui.buttons.SelectorButton;
import com.webmets.vanishedmc.gui.buttons.SliderButton;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Menu;
import com.webmets.vanishedmc.utils.effects.EffectMode;
import com.webmets.vanishedmc.utils.effects.EffectUtils;

import net.minecraft.client.gui.GuiButton;

public class MenuEffectSettings extends Menu {

	private EffectUtils effect;

	public MenuEffectSettings(EffectUtils effect) {
		this.effect = effect;
	}

	@Override
	public void initGui() {
		// Variables
		super.initGui();
		((ToggleButton) buttonList.get(4)).setToggled(true);

		// Initialize buttons
		final SelectorButton mode = new SelectorButton(0, 140, 30, 100, 20, "Mode", Arrays.asList("Rainbow", "Static"));

		final SliderButton rainbowSpeed = new SliderButton(0, 140, 55, 100, 20, 5, 10, "Speed");
		final SliderButton rainbowSize = new SliderButton(0, 140, 80, 100, 20, 1, 4, "Size");

		final RGBSelectorButton staticColorSlider = new RGBSelectorButton(140, 55);

		// Set states
		mode.setCurrent(effect.getMode().toString().toLowerCase());

		rainbowSpeed.setValue((2000 - effect.getRainbowSpeed()) / 100 - 5);
		rainbowSpeed.visible = effect.getMode() == EffectMode.RAINBOW;
		rainbowSize.setValue(effect.getRainbowSize() - 1);
		rainbowSize.visible = effect.getMode() == EffectMode.RAINBOW;

		staticColorSlider.setVisible(effect.getMode() == EffectMode.STATIC);
		staticColorSlider.setRed(effect.getStaticColor().getRed());
		staticColorSlider.setGreen(effect.getStaticColor().getGreen());
		staticColorSlider.setBlue(effect.getStaticColor().getBlue());

		// Actions
		mode.addAction(new ButtonAction() {
			@Override
			public void execute() {
				effect.setMode(EffectMode.valueOf(mode.getCurrent().toUpperCase()));

				rainbowSize.visible = effect.getMode() == EffectMode.RAINBOW;
				rainbowSpeed.visible = effect.getMode() == EffectMode.RAINBOW;
				staticColorSlider.setVisible(effect.getMode() == EffectMode.STATIC);
			}
		});

		rainbowSpeed.addAction(new ButtonAction() {
			@Override
			public void execute() {
				effect.setRainbowSpeed(rainbowSpeed.getDisplayValue() * 100);
			}
		});

		rainbowSize.addAction(new ButtonAction() {
			@Override
			public void execute() {
				effect.setRainbowSize(rainbowSize.getDisplayValue());
			}
		});

		staticColorSlider.setAction(new ButtonAction() {
			@Override
			public void execute() {
				effect.setStaticColorRed(staticColorSlider.getRed());
				effect.setStaticColorGreen(staticColorSlider.getGreen());
				effect.setStaticColorBlue(staticColorSlider.getBlue());
			}
		});

		// Adding to list
		this.buttonList.add(mode);
		this.buttonList.add(rainbowSpeed);
		this.buttonList.add(rainbowSize);
		this.buttonList.add(staticColorSlider.getSliders().get(0));
		this.buttonList.add(staticColorSlider.getSliders().get(1));
		this.buttonList.add(staticColorSlider.getSliders().get(2));
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof ToggleButton) {
			((ToggleButton) button).press();
		}
		if (button instanceof SelectorButton) {
			((SelectorButton) button).press();
		}
	}
}
