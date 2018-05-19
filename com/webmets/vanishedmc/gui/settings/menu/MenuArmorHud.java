package com.webmets.vanishedmc.gui.settings.menu;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.webmets.vanishedmc.gui.SetVectorLocation;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.SliderButton;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Configurable;
import com.webmets.vanishedmc.gui.settings.Menu;
import com.webmets.vanishedmc.modules.GuiArmorModule;

import net.minecraft.client.gui.GuiButton;

public class MenuArmorHud extends Menu{

	@Override
	public void initGui() {
		// Variables
		super.initGui();
		((ToggleButton) buttonList.get(2)).setToggled(true);
		final GuiArmorModule hud = client.getArmorModule();

		// Initialize buttons
		final ToggleButton armorhud = new ToggleButton(0, 140, 30, 100, 20, "Armor Hud");
		
		final SliderButton scale = new SliderButton(0, 140, 55, 100, 20, 10, 10, "Scale");
		final ToggleButton showDurability = new ToggleButton(0, 245, 55, 100, 20, "Show durability");
		
		final ToggleButton showHand = new ToggleButton(0, 140, 80, 100, 20, "Show hand");
		final ToggleButton showArrows = new ToggleButton(0, 245, 80, 100, 20, "Show arrows");

		final ToggleButton helmetLocation = new ToggleButton(0, 140, 105, 100, 20, "Helmet location");
		final ToggleButton chestLocation = new ToggleButton(0, 245, 105, 100, 20, "Chestplate location");
		final ToggleButton leggingLocation = new ToggleButton(0, 140, 130, 100, 20, "Leggings location");
		final ToggleButton bootsLocation = new ToggleButton(0, 245, 130, 100, 20, "Boots location");
		final ToggleButton handLocation = new ToggleButton(0, 350, 105, 100, 20, "Hand location");
		final ToggleButton arrowLocation = new ToggleButton(0, 350, 130, 100, 20, "Arrows location");

		// Set states
		armorhud.setToggled(hud.isEnabled());
		scale.setValue((hud.getScale() - 1) * 10);
		showHand.setToggled(hud.isShowHand());
		showArrows.setToggled(hud.isShowArrows());
		showDurability.setToggled(hud.isShowDurability());
		
		showHand.visible = armorhud.isToggled();
		showArrows.visible = armorhud.isToggled();
		handLocation.visible = armorhud.isToggled() && showHand.isToggled();
		arrowLocation.visible = armorhud.isToggled() && showArrows.isToggled();
		scale.visible = armorhud.isToggled();
		helmetLocation.visible = armorhud.isToggled();
		chestLocation.visible = armorhud.isToggled();
		leggingLocation.visible = armorhud.isToggled();
		bootsLocation.visible = armorhud.isToggled();
		showDurability.visible = armorhud.isToggled();

		// Actions
		armorhud.addAction(new ButtonAction() {
			@Override
			public void execute() {
				armorhud.setToggled(!armorhud.isToggled());
				hud.setEnabled(armorhud.isToggled());
				showHand.visible = armorhud.isToggled();
				showArrows.visible = armorhud.isToggled();
				handLocation.visible = armorhud.isToggled() && showHand.isToggled();
				arrowLocation.visible = armorhud.isToggled() && showArrows.isToggled();
				scale.visible = armorhud.isToggled();
				helmetLocation.visible = armorhud.isToggled();
				chestLocation.visible = armorhud.isToggled();
				leggingLocation.visible = armorhud.isToggled();
				bootsLocation.visible = armorhud.isToggled();
				showDurability.visible = armorhud.isToggled();
			}
		});

		showDurability.addAction(new ButtonAction() {
			@Override
			public void execute() {
				showDurability.setToggled(!showDurability.isToggled());
				hud.setShowDurability(showDurability.isToggled());
			}
		});
		
		showHand.addAction(new ButtonAction() {
			@Override
			public void execute() {
				showHand.setToggled(!showHand.isToggled());
				hud.setShowHand(showHand.isToggled());
				handLocation.visible = armorhud.isToggled() && showHand.isToggled();
			}
		});

		showArrows.addAction(new ButtonAction() {
			@Override
			public void execute() {
				showArrows.setToggled(!showArrows.isToggled());
				hud.setShowArrows(showArrows.isToggled());
				arrowLocation.visible = armorhud.isToggled() && showArrows.isToggled();
			}
		});

		scale.addAction(new ButtonAction() {
			@Override
			public void execute() {
				hud.setScale((scale.getDisplayValue() * 0.1f));
			}
		});

		helmetLocation.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mc.displayGuiScreen(new SetVectorLocation(hud.getHelmetPos()));
			}
		});

		chestLocation.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mc.displayGuiScreen(new SetVectorLocation(hud.getChestplatePos()));
			}
		});

		leggingLocation.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mc.displayGuiScreen(new SetVectorLocation(hud.getleggingPos()));
			}
		});

		bootsLocation.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mc.displayGuiScreen(new SetVectorLocation(hud.getBootsPos()));
			}
		});

		handLocation.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mc.displayGuiScreen(new SetVectorLocation(hud.getHandPos()));
			}
		});

		arrowLocation.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mc.displayGuiScreen(new SetVectorLocation(hud.getArrowPos()));
			}
		});

		// Adding to list
		buttonList.add(armorhud);
		buttonList.add(scale);
		buttonList.add(helmetLocation);
		buttonList.add(chestLocation);
		buttonList.add(leggingLocation);
		buttonList.add(bootsLocation);
		buttonList.add(handLocation);
		buttonList.add(arrowLocation);
		buttonList.add(showHand);
		buttonList.add(showArrows);
		buttonList.add(showDurability);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof ToggleButton) {
			((ToggleButton) button).press();
		}
	}
}
