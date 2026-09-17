package com.friendaliases;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.util.Text;

@Singleton
class AliasService
{
	private final Client client;
	private final FriendAliasesConfig config;
	private volatile String cachedSource = null;
	private volatile Map<String, String> aliases = Collections.emptyMap();

	@Inject
	AliasService(Client client, FriendAliasesConfig config)
	{
		this.client = client;
		this.config = config;
	}

	String aliasFor(Player player)
	{
		return player == null ? null : aliasFor(player.getName());
	}

	String aliasFor(String playerName)
	{
		if (playerName == null || !client.isFriended(playerName, false))
		{
			return null;
		}

		refreshIfNeeded();
		return aliases.get(normalize(playerName));
	}

	String displayName(String realName, String alias)
	{
		return config.showRealName() ? alias + " (" + realName + ")" : alias;
	}

	private void refreshIfNeeded()
	{
		String source = config.aliases();
		if (source.equals(cachedSource))
		{
			return;
		}

		Map<String, String> parsed = new HashMap<>();
		for (String entry : source.split("[\\r\\n,;]+"))
		{
			String[] pair = entry.split("=", 2);
			if (pair.length != 2)
			{
				continue;
			}

			String realName = normalize(pair[0]);
			String alias = pair[1].trim();
			if (!realName.isEmpty() && !alias.isEmpty())
			{
				parsed.put(realName, alias);
			}
		}

		aliases = Collections.unmodifiableMap(parsed);
		cachedSource = source;
	}

	private static String normalize(String name)
	{
		return Text.standardize(name == null ? "" : Text.removeTags(name).trim());
	}
}
