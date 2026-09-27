## 2026-09-25

**Summary:** Add window scale variable, switch UI fields to primitives, and add project docs.

**Changes:**
- `UserInterface.java`:
  - Added `windowSizeDefaultScale` so the window's share of the screen is set in one place. The value is now `0.7`, which shrinks the default window from 90% to 70% of the screen.
  - Changed `gameStarted`, `gameWidth` and `gameHeight` from wrapper types (`Boolean` / `Integer`) to primitives (`boolean` / `int`).
  - Added comments explaining the default size and padding values.
- `docs/ProjectNotes.md` (new): project intent, goals, naming and architecture conventions, current work, known issues and long-running to-dos. Includes a new rule that project documents are written in Markdown.
- `docs/devlog.md` (new): this log. Newest entries go at the top.
- `.idea/codeStyles/codeStyleConfig.xml` (new): IntelliJ code style set to the Default preset.
- `CLAUDE.md` (already committed in `b2830bc`): instructions for Claude to act as a reviewer, check work against ProjectNotes, and keep this devlog.

**Open issues / next steps:**
- The combat loop known issues in ProjectNotes are still open:
  - There's no splash screen when combat starts.
  - The post-combat text doesn't appear until Enter is pressed, and choosing "fight again" needs an extra input.
  - Critical hits happen but the player isn't told.
- Continue replacing hardcoded pixel values with `padToGameWidth` / `padToGameHeight`.
- Convention check: `gameStarted` (a line changed today) is a boolean without an auxiliary-verb prefix. The ProjectNotes rule suggests `isGameStarted` or `hasGameStarted`.
- The comment above the auto-size code in `createUIBox()` still says "half current screen resolution." It should mention `windowSizeDefaultScale`.
