# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

CorapoScore is a student participation management system built with Java 25 (with preview features enabled) and Maven. It manages students, courses, and participation records using JSON file-based persistence via Gson.

## Common Commands

### Building and Running
```bash
# Compile the project
mvn compile

# Run the application
mvn exec:java

# Clean and rebuild
mvn clean compile

# Package the application
mvn package
```

### Testing
Note: No test framework is currently configured in this project.

## Architecture

### Package Structure
- **`com.taller`** - Application entry point and core business logic
  - `Main.java` - Entry point with console menu loop
  - `PartiManager.java` - Central manager coordinating all operations
  - `Ui.java` - Console menu display
  - `Read.java` - Input reading utilities

- **`com.models`** - Domain models (immutable with final fields)
  - `Student.java` - Student entity (dni, fullName)
  - `Course.java` - Course entity (name, description)

- **`participation`** - Participation domain
  - `Participation.java` - Links students to participation events

- **`com.repository`** - Data access layer using JSON file persistence
  - `StudentRepository.java` - Student CRUD operations
  - `CourseRepository.java` - Course CRUD operations  
  - `ParticipationRepository.java` - Participation CRUD operations

- **`utils`** - Utility classes
  - `JsonHelper.java` - Generic JSON serialization/deserialization using Gson
  - `Validation.java` - Input validation utilities

### Data Storage
All data is persisted as JSON files in the `data/` directory:
- `data/students.json` - Student records
- `data/courses.json` - Course records
- `data/participations.json` - Participation records

### Key Design Patterns
1. **Repository Pattern**: All data access is abstracted through repository classes
2. **Manager Pattern**: `PartiManager` orchestrates business logic and coordinates repositories
3. **Immutable Models**: Domain models use final fields
4. **Generic JSON Helper**: Type-safe JSON operations using Gson's TypeToken

### Important Notes
- Java 25 preview features are enabled via `--enable-preview` flag
- Maximum 30 students can be registered (hardcoded in Main.java)
- The application uses `IO.println()` and `IO.print()` for output (these classes need to be located/verified)
- No validation framework is used - validation is handled manually
- Student DNI must be unique
- Student full names are compared case-insensitively and trimmed
