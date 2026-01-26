AI-CLDD
AI Change-Log Driven Development

---

## What is ai-cldd?

**ai-cldd (AI Change-Log Driven Development)** is a design standard for software development where **Artificial Intelligence acts as an active development agent**, operating under **explicit intent documents** and producing **verifiable change logs**.

The main goal of ai-cldd is to **preserve intent, context, and decision history**, allowing AI agents and human developers to evolve a system **safely, consistently, and audibly over time**.

In ai-cldd, **nothing is implemented by AI without an explicit document**, and **every applied change must be recorded**.

---

## The problem ai-cldd solves

When AI is used to build software, common problems emerge:

- Loss of context between prompts  
- Inconsistent architectural decisions  
- Features implemented without clear intent  
- No traceability of what the AI actually changed  
- Difficulty for a future AI (or developer) to understand the system state  

ai-cldd addresses these issues by introducing **structure, contracts, and traceability** into AI-assisted development.

---

## Core principle

> **No change without intent. No intent without a record.**

Every change in the system must follow this sequence:

1. An intent document is written
2. The AI applies the intent
3. The AI generates an immutable change-log

---

## Key concepts

### 1. SYSTEM_BASE documents (SB-*)

SYSTEM_BASE documents define the **global technical contract of the system**.

They specify:
- System responsibilities
- Mandatory technologies
- Architectural patterns
- Hard constraints
- Project structure
- Communication standards

SYSTEM_BASE documents:
- Use the prefix `SB-`
- Are mandatory context for all features
- Always take precedence in case of conflict

Example:
- `SB-000 - System Base`

---

### 2. FEATURE Use Cases (UC-*)

FEATURE documents define **behavioral changes or additions** to the system.

They describe:
- What the system should do
- What is in scope and out of scope
- Data definitions
- Required endpoints or interfaces
- Explicit implementation tasks

FEATURE documents:
- Use the prefix `UC-`
- Must always reference an active SYSTEM_BASE document
- Define intent, not global rules

Example:
- `UC-002 - Game State Endpoints`

---

### 3. Change Logs

Every applied SYSTEM_BASE or FEATURE document **must generate exactly one change-log**.

Change logs record:
- Which document was applied
- The exact content checksum
- When it was applied
- Which AI agent applied it
- What was changed

Change logs are:
- Immutable
- One-to-one with documents
- The execution history of the system

---

## Repository structure

The ai-cldd standard expects the following structure inside a project:

```
/ai-cldd
  /docs
    SB-000 - System Base.md
    UC-001 - Some Feature.md
    UC-002 - Another Feature.md

  /change-logs
    SB-000.yaml
    UC-001.yaml
    UC-002.yaml

  ai-cldd-v1.yaml
```

---

## ai-cldd-v1.yaml

The file `ai-cldd-v1.yaml` is the **operational contract for AI agents**.

It defines:
- How AI must interpret documents
- The order of operations
- Document types and priorities
- Change-log rules and schema
- Forbidden and mandatory AI behaviors

This file **must be copied into the project** and **explicitly referenced in the AI prompt**.

Example instruction to AI:

> “Operate according to ai-cldd-v1.yaml.  
> Apply only documented intent and generate the required change logs.”

---

## How ai-cldd is used in practice

1. Write or update a SYSTEM_BASE document (`SB-*`)
2. Write a FEATURE document (`UC-*`)
3. Place documents in `ai-cldd/docs`
4. Ask the AI to apply the FEATURE
5. The AI:
   - Reads SYSTEM_BASE
   - Applies the FEATURE
   - Generates a change-log in `ai-cldd/change-logs`

---

## What ai-cldd is NOT

- Not a framework  
- Not a methodology like Scrum or Kanban  
- Not a prompt library  
- Not language- or stack-dependent  

ai-cldd is a **design standard for AI-assisted development**.

---

## Why ai-cldd matters

ai-cldd enables:

- Traceable AI-driven development
- Safer evolution of complex systems
- Consistent architectural decisions
- Better collaboration between humans and AI
- Future-proof projects where context is never lost

---

## Status

ai-cldd is currently at **version 1.0** and considered **experimental but stable for real use**.

The standard is expected to evolve as AI development practices mature.

---

## Author

Created by **Paulo César Medeiros Porto**.
