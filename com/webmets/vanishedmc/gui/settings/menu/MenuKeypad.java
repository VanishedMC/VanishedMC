package com.webmets.vanishedmc.gui.settings.menu;

import java.io.IOException;

import com.webmets.vanishedmc.gui.SetKeypadLocation;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.SliderButton;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Menu;
import com.webmets.vanishedmc.modules.GuiHudKeypadModule;

import net.minecraft.client.gui.GuiButton;

public class MenuKeypad extends Menu {

	@Override
	public void initGui() {
		// Variables
		final GuiHudKeypadModule keyPad = client.getKeypadModule();
		super.initGui();
		((ToggleButton) buttonList.get(1)).setToggled(true);

		// Initialize buttons
		final ToggleButton showMouseButtons = new ToggleButton(0, 140, 30, 100, 20, "Mouse buttons");
		final ToggleButton showMouseButtonsCPS = new ToggleButton(0, 245, 30, 100, 20, "Cps");
		final ToggleButton showCPSInline = new ToggleButton(0, 350, 30, 100, 20, "Cps inline");

		final SliderButton scaleSlider = new SliderButton(0, 140, 55, 100, 20, 1, 9, "Button size");
		final SliderButton textScaleSlider = new SliderButton(0, 245, 55, 100, 20, 0, 10, "Text scale");
		
		final ToggleButton showSpacebar = new ToggleButton(0, 140, 80, 100, 20, "Spacebar");

		final SliderButton distanceSlider = new SliderButton(0, 140, 105, 100, 20, 0, 5, "Distance");
		
		final ToggleButton setLocation = new ToggleButton(0, 140, 130, 100, 20, "Set location");

		// Set states
		showSpacebar.setToggled(keyPad.isSpaceBar());
		showMouseButtons.setToggled(keyPad.isShowMouseButtons());
		showMouseButtonsCPS.setToggled(keyPad.isShowCps());
		showCPSInline.setToggled(keyPad.isCpsInLine());
		showMouseButtonsCPS.visible = showMouseButtons.isToggled();
		showCPSInline.visible = showMouseButtonsCPS.isToggled() && showMouseButtons.isToggled();
		textScaleSlider.setValue(keyPad.getScale() / 0.5f);
		scaleSlider.setValue((keyPad.getSize() / 5) - 1);
		distanceSlider.setValue(keyPad.getDistanceBetween());

		// Actions
		distanceSlider.addAction(new ButtonAction() {
			@Override
			public void execute() {
				keyPad.setDistanceBetween((int) distanceSlider.getDisplayValue());
			}
		});

		scaleSlider.addAction(new ButtonAction() {
			@Override
			public void execute() {
				keyPad.setSize((int) scaleSlider.getDisplayValue() * 5);
			}
		});

		textScaleSlider.addAction(new ButtonAction() {
			@Override
			public void execute() {
				keyPad.setScale(textScaleSlider.getDisplayValue() * 0.5f);
			}
		});

		showSpacebar.addAction(new ButtonAction() {
			@Override
			public void execute() {
				showSpacebar.setToggled(!showSpacebar.isToggled());
				keyPad.setSpaceBar(showSpacebar.isToggled());
			}
		});

		showMouseButtons.addAction(new ButtonAction() {
			@Override
			public void execute() {
				showMouseButtons.setToggled(!showMouseButtons.isToggled());
				keyPad.setShowMouseButtons(showMouseButtons.isToggled());
				showMouseButtonsCPS.visible = showMouseButtons.isToggled();
				showCPSInline.visible = showMouseButtons.isToggled() && showMouseButtonsCPS.isToggled();
			}
		});

		showMouseButtonsCPS.addAction(new ButtonAction() {
			@Override
			public void execute() {
				showMouseButtonsCPS.setToggled(!showMouseButtonsCPS.isToggled());
				keyPad.setShowCps(showMouseButtonsCPS.isToggled());
				showCPSInline.visible = showMouseButtonsCPS.isToggled();
			}
		});

		showCPSInline.addAction(new ButtonAction() {
			@Override
			public void execute() {
				showCPSInline.setToggled(!showCPSInline.isToggled());
				keyPad.setCpsInLine(showCPSInline.isToggled());
			}
		});

		setLocation.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mc.displayGuiScreen(new SetKeypadLocation());
			}
		});

		// Adding to list
		buttonList.add(distanceSlider);
		buttonList.add(showSpacebar);
		buttonList.add(showMouseButtons);
		buttonList.add(showMouseButtonsCPS);
		buttonList.add(showCPSInline);
		buttonList.add(textScaleSlider);
		buttonList.add(scaleSlider);
		if(mc.theWorld != null) {
			buttonList.add(setLocation);
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof ToggleButton) {
			((ToggleButton) button).press();
		}
	}
}
