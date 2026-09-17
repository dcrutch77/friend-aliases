package com.friendaliases;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.Player;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "Friend Aliases",
	description = "Give friends local aliases in overhead labels and player menus.",
	tags = {"friend", "alias", "nickname", "name"}
)
public class FriendAliasesPlugin extends Plugin
{
	@Inject private Client client;
	@Inject private FriendAliasesConfig config;
	@Inject private AliasService aliases;
	@Inject private FriendAliasesOverlay overlay;
	@Inject private OverlayManager overlayManager;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		if (config.displayMode() == DisplayMode.OVERHEAD)
		{
			return;
		}

		MenuEntry menuEntry = event.getMenuEntry();
		Player player = menuEntry.getPlayer();
		if (player == null || player.getName() == null)
		{
			return;
		}

		String alias = aliases.aliasFor(player);
		if (alias == null)
		{
			return;
		}

		String target = menuEntry.getTarget();
		String replacement = aliases.displayName(player.getName(), alias);
		menuEntry.setTarget(target.replace(player.getName(), replacement));
	}

	@Provides
	FriendAliasesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FriendAliasesConfig.class);
	}
}
