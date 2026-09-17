package com.friendaliases;

public enum DisplayMode
{
	OVERHEAD("Overhead only"),
	RIGHT_CLICK("Right-click menu only"),
	BOTH("Overhead and right-click menu");

	private final String label;

	DisplayMode(String label)
	{
		this.label = label;
	}

	@Override
	public String toString()
	{
		return label;
	}
}
