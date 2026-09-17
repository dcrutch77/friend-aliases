package com.friendaliases;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(FriendAliasesConfig.GROUP)
public interface FriendAliasesConfig extends Config
{
	String GROUP = "friendaliases";

	@ConfigItem(
		keyName = "aliases",
		name = "Friend aliases",
		description = "Enter Original Name=Alias. Separate entries with commas or semicolons.",
		position = 0
	)
	default String aliases()
	{
		return "";
	}

	@ConfigItem(
		keyName = "displayMode",
		name = "Show aliases in",
		description = "Choose where local friend aliases are displayed.",
		position = 1
	)
	default DisplayMode displayMode()
	{
		return DisplayMode.BOTH;
	}

	@Alpha
	@ConfigItem(
		keyName = "overheadColor",
		name = "Overhead color",
		description = "Color used for aliases drawn over friends.",
		position = 2
	)
	default Color overheadColor()
	{
		return new Color(0, 255, 128, 255);
	}

	@ConfigItem(
		keyName = "showRealName",
		name = "Include real name",
		description = "Display Alias (Real Name) instead of only Alias.",
		position = 3
	)
	default boolean showRealName()
	{
		return false;
	}
}
