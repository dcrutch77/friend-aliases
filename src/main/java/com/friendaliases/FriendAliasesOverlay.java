package com.friendaliases;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

class FriendAliasesOverlay extends Overlay
{
	private final Client client;
	private final FriendAliasesConfig config;
	private final AliasService aliases;

	@Inject
	FriendAliasesOverlay(Client client, FriendAliasesConfig config, AliasService aliases)
	{
		this.client = client;
		this.config = config;
		this.aliases = aliases;
		setLayer(OverlayLayer.ABOVE_SCENE);
		setPosition(OverlayPosition.DYNAMIC);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (config.displayMode() == DisplayMode.RIGHT_CLICK)
		{
			return null;
		}

		for (Player player : client.getPlayers())
		{
			String alias = aliases.aliasFor(player);
			if (alias == null)
			{
				continue;
			}

			String text = aliases.displayName(player.getName(), alias);
			Point point = Perspective.getCanvasTextLocation(
				client, graphics, player.getLocalLocation(), text, player.getLogicalHeight() + 40);
			if (point != null)
			{
				OverlayUtil.renderTextLocation(graphics, point, text, config.overheadColor());
			}
		}

		return null;
	}
}
