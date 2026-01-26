UC-ID: SB-000
UC-TYPE: SYSTEM_BASE
VERSION: 1.0
AUTHOR: <author_name>
CREATED_AT: YYYY-MM-DD

SYSTEM OVERVIEW
Describe at a high level what the system does.
Focus on responsibilities, goals, and system boundaries.
Avoid implementation details.

TECHNOLOGIES
List all required technologies that MUST be used by the system.
Each technology listed here is mandatory unless explicitly overridden by a newer SYSTEM_BASE UC.
Examples:
- Node Js 24
- Typescript
- Express

CONSTRAINTS AND HARD RULES
List rules that MUST NOT be violated under any circumstance.
Examples:
- Forbidden libraries
- Forbidden patterns
- Mandatory validations
- If there is any conflict between an UC and a SYSTEM_BASE UC, the SYSTEM_BASE UC takes precedence unless explicitly stated otherwise

AI agents MUST respect all constraints, technologies, and patterns
defined in the SYSTEM_BASE UC.

BUILD AND RUNTIME CONFIGURATION
(If applicable) Define how the system must be built and executed.

GLOBAL ARCHITECTURAL PATTERNS
Define mandatory architectural and design patterns.

PROJECT STRUCTURE
Define the required folder and file structure of the project.
Include:
- Folder responsibilities
- File naming conventions
- Structural constraints

HTTP AND COMMUNICATION STANDARDS
(If applicable) Define system-wide communication rules.
Define:
- Allowed HTTP methods
- Status code conventions
- Default error response structure