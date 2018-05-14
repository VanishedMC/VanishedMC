package com.webmets.vanishedmc.gui.settings.menu;

import java.io.IOException;

import com.webmets.vanishedmc.gui.SetKeypadLocation;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
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
		final ToggleButton showSpacebar = new ToggleButton(0, 140, 45, 100, 20, "Spacebar");
		final ToggleButton showMouseButtons = new ToggleButton(0, 140, 70, 100, 20, "Mouse buttons");
		final ToggleButton showMouseButtonsCPS = new ToggleButton(0, 245, 70, 100, 20, "Cps");
		final ToggleButton showCPSInline = new ToggleButton(0, 350, 70, 100, 20, "Cps inline");
		final ToggleButton setLocation = new ToggleButton(0, 140, 95, 100, 20, "Set location");
	
		// Set states
		showSpacebar.setToggled(keyPad.isSpaceBar());
		showMouseButtons.setToggled(keyPad.isShowMouseButtons());
		showMouseButtonsCPS.setToggled(keyPad.isShowCps());
		showCPSInline.setToggled(keyPad.isCpsInLine());
		showMouseButtonsCPS.visible = showMouseButtons.isToggled();
		showCPSInline.visible = showMouseButtonsCPS.isToggled() && showMouseButtons.isToggled();

		// Actions
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
		buttonList.add(showSpacebar);
		buttonList.add(showMouseButtons);
		buttonList.add(showMouseButtonsCPS);
		buttonList.add(showCPSInline);
		buttonList.add(setLocation);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof ToggleButton) {
			((ToggleButton) button).press();
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawString(fontRendererObj, "Keypad", 140, 35, -1);
	}
}
