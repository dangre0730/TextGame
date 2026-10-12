## 2026-10-12

**Summary:** Rename classes, enums, methods and variables project-wide to match ProjectNotes conventions.

**Changes:**
- `Player.java`:
  - Enum types are now PascalCase: `ClassType`, `AttackModifier`, `WeaponType`.
  - Fields no longer repeat the class name: `playerHealth` / `maxPlayerHealth` / `playerStrength`… became `health`, 
    `maxHealth`, `strength`, etc. `playerWeapon` became `weaponType`, and `experience` became `experienceTotal`.
  - Methods are now verbs: `classBuilder` → `buildClass`, `statPrinter` → `printStats`, `setWeaponChoice` → 
    `loadWeaponStats`.
- `EnemyNPC.java`:
  - Dropped the `enemy` prefix from stat fields (`health`, `armor`, `damage`, `damageMod`, `criticalChance`), and 
    `earnedExp` became `experienceValue`.
  - `combatOver` → `isCombatOver`; `enemyStats` → `loadStats`.
- `Game.java`:
  - Input handlers renamed to say what they do: `exploringLoop` → `handleExplorationInput`, `combatLoop` → 
    `handleCombatInput`, `statAcknowledge` → `acknowledgeStats`.
  - Booleans use auxiliary verbs (`isValidInput`, `isGoingBack`).
  - Deleted the commented-out console combat loop, the unused variables in `main`, and the empty `updatePlayer`.
- `Room.java`:
  - Enum `RoomObjects` → `RoomId`.
  - `getRoom` → `enterRoom`, with its parameter renamed to `room`.
  - `revisit*` flags → `hasVisited*`, and `hasVisitedEntryRoomBase` → `hasVisitedEntryRoom` to match the others.
  - Room-tracking fields renamed to match the enum: `currentRoomObject` / `lastRoomObject` / `tempRoomObject` → 
    `currentRoomId` / `lastRoomId` / `tempRoomId`.
- `UserInterface.java`:
  - `mainCon` → `contentPane`.
  - `TitleHandler` → `TitleScreenHandler`.
  - `tsHandler` / `pHandler` → `titleScreenHandler` / `playerInputHandler`.
  - `gameStarted` → `isGameStarted`.
- `NPC.java`: enum `type` → `Type`; `alreadyMet` → `hasMet`.
- `Dialog.java`: `chatLevel` is now a primitive `int`. Deleted the unused console-era `chatter` method.
- `Functions.java`:
  - `nextLevel` → `levelUpPlayer`, and its unused `playerLevel` parameter was removed.
  - Updated references to the renamed `Player` / `EnemyNPC` fields.
- `QuestNPC.java`: updated to the `Room.RoomId` type.
- `docs/Templates.md`: moved from `src/Templates.txt` so project documents are Markdown in `docs/`.
- `docs/ProjectNotes.md`:
  - Added the enum naming rule, and changed "had" to "has" in the boolean rule.
  - Added an idea for a dedicated "start combat" method under the splash-screen known issue.
  - Updated "Last things I did".
- `CLAUDE.md`: synced with the new method and enum names, and re-wrapped.

**Open issues / next steps:**
- The combat known issues in ProjectNotes are still open:
  - There's no splash screen when combat starts (see the "start combat" idea).
  - The post-combat text needs an extra Enter press.
  - Players aren't told when they land a critical hit.
- Naming items postponed on purpose:
  - `stringToChar` / `string` in `Functions`
  - `tempList` in `handleCombatInput`
  - `atkMod` vs `classMod` in `Player`
- The deprecated `UserInterface` methods are kept for now. `updatePlayerHitDieLabel` and `updatePlayerClassModLabel` 
  would crash if called.
- `EnemyNPC.loadStats` and `Player.printStats` still use `slowPrint` for console debug output, which freezes the 
  window for 1–2 seconds.
- Next: split `handleCombatInput` into single-purpose methods before starting on the combat known issues.

## 2026-09-25

**Summary:** Add window scale variable, switch UI fields to primitives, and add project docs.

**Changes:**
- `UserInterface.java`:
  - Added `windowSizeDefaultScale` so the window's share of the screen is set in one place. The value is now `0.7`, 
    which shrinks the default window from 90% to 70% of the screen.
  - Changed `gameStarted`, `gameWidth` and `gameHeight` from wrapper types (`Boolean` / `Integer`) to primitives 
    (`boolean` / `int`).
  - Added comments explaining the default size and padding values.
- `docs/ProjectNotes.md` (new): project intent, goals, naming and architecture conventions, current work, known 
  issues and long-running to-dos. Includes a new rule that project documents are written in Markdown.
- `docs/devlog.md` (new): this log. Newest entries go at the top.
- `.idea/codeStyles/codeStyleConfig.xml` (new): IntelliJ code style set to the Default preset.
- `CLAUDE.md` (already committed in `b2830bc`): instructions for Claude to act as a reviewer, check work against 
  ProjectNotes, and keep this devlog.

**Open issues / next steps:**
- The combat loop known issues in ProjectNotes are still open:
  - There's no splash screen when combat starts.
  - The post-combat text doesn't appear until Enter is pressed, and choosing "fight again" needs an extra input.
  - Critical hits happen but the player isn't told.
- Continue replacing hardcoded pixel values with `padToGameWidth` / `padToGameHeight`.
- Convention check: `gameStarted` (a line changed today) is a boolean without an auxiliary-verb prefix. The 
  ProjectNotes rule suggests `isGameStarted` or `hasGameStarted`.
- The comment above the auto-size code in `createUIBox()` still says "half current screen resolution." It should 
  mention `windowSizeDefaultScale`.
