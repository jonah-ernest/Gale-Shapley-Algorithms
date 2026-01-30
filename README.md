# Stable Matching Algorithms in Java (Gale–Shapley)

This repository contains an algorithms project implementing the Gale–Shapley stable matching algorithm in Java completed in Fall 2021.

The project evolves across three versions, starting from a student–school matching system and progressing toward a generalized stable matching framework using shared abstractions and solver classes.

---

## Overview

The stable matching problem pairs two sets of participants with ranked preferences such that no blocking pair exists.

In this implementation:

- Students act as suitors and schools act as receivers
- Each participant provides a complete ranking over the other side
- A Gale–Shapley–style algorithm produces a stable matching
- Post-matching statistics such as regret and stability are computed

Later versions refactor the design to separate solver logic from domain entities and introduce a generalized `Participant` abstraction.

---

## Versions

The repository is organized into three incremental versions:

### Stable Marriage Problem V1
- Initial implementation of student–school matching
- Menu-driven interface
- Explicit `Student` and `School` classes

### Stable Marriage Problem V2
- Introduces a dedicated `SMPSolver` class to run Gale–Shapley
- Adds runtime tracking and regret statistics
- Supports loading participants from files
- Computes stability of the final matching

### Stable Marriage Problem V3
- Generalizes students and schools into a shared `Participant` abstraction
- Supports configurable capacity per participant
- Further separates input handling, solver logic, and reporting
- Improves extensibility for other matching scenarios

---

## Features

- Interactive command-line interface
- Preference editing and validation
- Stable matching via Gale–Shapley
- Regret metrics for both sides
- Stability verification
- Runtime measurement
- Multi-match capacity support in later versions

---

## Repository Structure

- `Stable Marriage Problem V1/`
- `Stable Marriage Problem V2/`
- `Stable Marriage Problem V3/`
- `README.md`
