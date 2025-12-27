# ER図（MVP）

```mermaid
erDiagram
  USERS ||--o{ MENUS : "has"
  MENUS ||--o{ MENU_SETS : "contains"
  USERS ||--o{ WORKOUT_LOGS : "has"
  WORKOUT_LOGS ||--o{ WORKOUT_SET_LOGS : "contains"
  MENUS ||--o{ WORKOUT_LOGS : "referenced by (nullable)"

  USERS {
    bigint id PK
    varchar email "UNIQUE NOT NULL"
    varchar password_hash "NOT NULL"
    varchar display_name "NOT NULL"
    timestamp created_at
    timestamp updated_at
  }

  MENUS {
    bigint id PK
    bigint user_id FK
    varchar title "NOT NULL"
    text description
    int target_total_distance_m
    timestamp created_at
    timestamp updated_at
  }

  MENU_SETS {
    bigint id PK
    bigint menu_id FK
    int order_no "NOT NULL"
    varchar stroke "NOT NULL"
    int distance_m "NOT NULL"
    int reps "NOT NULL"
    int interval_sec
    varchar intensity
    text note
  }

  WORKOUT_LOGS {
    bigint id PK
    bigint user_id FK
    bigint menu_id "FK (NULLABLE)"
    date workout_date "NOT NULL"
    varchar title
    int total_distance_m
    timestamp created_at
    timestamp updated_at
  }

  WORKOUT_SET_LOGS {
    bigint id PK
    bigint workout_log_id FK
    bigint menu_set_id "FK (NULLABLE)"
    int order_no "NOT NULL"
    int actual_reps
    int actual_interval_sec
    int time_sec
    text note
  }
