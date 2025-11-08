# ❤️ WispSMP

Recreate the **Wisp SMP** experience on your server! Players start with a limited number of lives displayed as custom hearts next to their nametags. Run out of lives and you become a spectator - permanently! This hardcore survival system brings the tension and stakes of popular SMP series to your own server.

**Visual Lives System**
Custom heart indicators (❤️) appear in player nametags showing exactly how many lives they have left. Hearts update in real-time as players lose lives, making everyone's status visible at a glance.

**Spectator on Death**
When a player loses their final life, they're automatically switched to spectator mode. No more respawning - they can watch but can't interact with the world anymore. Adds real consequences to risky plays!

**Admin Revival**
Admins can bring eliminated players back into the game with full lives restored. Perfect for special events, accidents, or just giving someone another chance.

---

## Commands & Permissions

| Command | Permission | Description |
|---------|-----------|-------------|
| `/revive <player>` | `wispsmp.revive` | Revive a dead player with full lives |

**Aliases:** `/res`, `/restore`

| Permission | Default | Description |
|-----------|---------|-------------|
| `wispsmp.revive` | OP | Allows reviving eliminated players |
| `wispsmp.*` | OP | Grants all plugin permissions |

---

## Configuration

Edit `config.yml` to customize the plugin:
```yaml
max-lives: 3

spawn:
  world: "world"
  x: 0.0
  y: 64.0
  z: 0.0
  yaw: 0.0
  pitch: 0.0

players: {}
```

**Settings:**
- `max-lives` - How many lives players start with (default: 3)
- `spawn` - Where revived players teleport to
- `players` - Automatically stores player data

---

## Installation

1. Download the latest `WispSMP-x.x.x.jar`
2. Drop it in your `plugins` folder
3. Restart your server
4. Set your spawn location in the config
5. Done!

---

## Requirements

- Minecraft 1.21 or newer
- Paper, Spigot, or Purpur server
- Java 21+

---

## How It Works

1. **New players join** → Get configured lives (default 3)
2. **Player dies** → Loses one life, hearts update
3. **Out of lives** → Becomes permanent spectator
4. **Admin revives** → Back to full lives at spawn

---

## Some Advice

- Lives data is saved in the config file, so it persists across restarts
- Spectators can still fly around and watch, but can't break blocks or interact
- Set your spawn location before reviving players or they'll teleport to 0,0

Need help or have suggestions? Feel free to reach out via my [Discord Server](https://discord.gg/BrGdf2GMuT).

---

**Made by Lusik21556** (@lusik_2155)
