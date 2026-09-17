# Friend Aliases

A local-only RuneLite plugin that gives players on your friends list custom aliases.

## Features

- Shows aliases above nearby friends, in player right-click menu entries, or in both places.
- Optionally displays `Alias (Real Name)`.
- Configurable overhead text color.
- Ignores aliases for players who are not currently on your RuneScape friends list.
- Does not change any server-side data or send alias information anywhere.

## Configure aliases

Open **RuneLite Settings → Friend Aliases**. In **Friend aliases**, enter mappings in this format:

```text
Original Name=Alias,Another Friend=Other Alias
```

Separate entries with a comma, semicolon, or newline. RuneScape name matching is case-insensitive and treats spaces/underscores normally.

Then choose **Show aliases in**:

- Overhead only
- Right-click menu only
- Overhead and right-click menu

## Local development

1. Install JDK 11 and Gradle.
2. Open this directory as a Gradle project in IntelliJ IDEA.
3. Run `gradle build`.

For Plugin Hub publication, fork `runelite/plugin-hub`, follow its current acceptance checklist, and point the manifest at a tagged release of this repository.

## Limitations

- “Right-click menu” means player interaction entries such as Walk here, Follow, Trade, and Lookup. RuneLite does not rewrite the server-owned Friends List widget itself.
- Only friends currently visible as players in the scene receive overhead labels.
