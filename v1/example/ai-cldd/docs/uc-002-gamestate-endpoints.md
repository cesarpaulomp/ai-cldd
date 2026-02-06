UC-ID: UC-002
UC-TYPE: FEATURE
VERSION: 1.0
AUTHOR: Paulo César Medeiros Porto
CREATED_AT: 2026-01-25

FEATURE OVERVIEW

This feature defines the endpoints and internal logic required to manage the game lifecycle state.
It allows the system to start a game, persist its initial state, and mark the game as completed.

The feature is responsible for controlling the current game state, ensuring consistency between game rounds and clearing previous match data when a new game starts.

REFERENCE CONTEXT FOR AI
- UC-001-create-project-structure.md (initial project configuration and middleware setup)

MANDATORY CONTEXT FOR AI
This FEATURE UC MUST be applied together with the following SYSTEM_BASE UCs:

SB-000 (required)

DATA DEFINITIONS

Game State (Redis key: game:status)

Fields:

- state: GameStateEnum
Description: Current game lifecycle state
- game: GameEnum
Description: Game type currently being executed
- numberOfRounds: number
Description: Total number of rounds in the match
- currentRound: number 
Description: Current round number
- started_at: string
Description: UTC timestamp when the game was started
- options: Array
Description: Game options and correct answers (structure defined by game type)

Enums:

GameEnum:
- FIND_THE_DIFFERENCE
- COUNT_OBJECTS
- RESOLVE_THE_EQUASION

GameStateEnum:
- GAME_STARTED
- GAME_COMPLETED

Request Interface:

StartGameRequest:
- game: GameEnum
- numberOfRounds: number
- currentRound: number
- options?: Array

IMPLEMENTATION TASKS

Create enums in src/types/types.ts under the enums namespace:

GameEnum

GameStateEnum
- Create StartGameRequest interface in src/types/types.ts under the request namespace.

Implement endpoint POST /v1/game/start in src/routes/game_v1_route.ts:
- Apply middleware game_security_check
- Validate required request body fields
- Return HTTP 400 if validation fails
- Return HTTP 204 on success
- Implement start(StartGameRequest) method in src/core/game/game_service.ts:
- Set state to GAME_STARTED
- Set started_at to current UTC datetime
- If currentRound equals 1:
- Clear Redis keys match and score:ranking
- Implement persistence logic in src/repo/game_state.ts to store the game state in Redis.

Implement endpoint PATCH /v1/game/complete in src/routes/game_v1_route.ts:
- Apply middleware game_security_check
- Implement complete() method in src/core/game/game_service.ts:
- Update game state to GAME_COMPLETED
- Update persistence logic in src/repo/game_state.ts to reflect completed state.