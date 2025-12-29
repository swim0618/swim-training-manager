@startuml
entity "users" as users {
  * id : bigint <<PK>>
  --
  * email : varchar <<UQ>>
  password_hash : varchar
  role : varchar
  created_at : timestamp
  updated_at : timestamp
}

entity "training_sessions" as sessions {
  * id : bigint <<PK>>
  --
  * user_id : bigint <<FK>>
  * start_at : timestamp
  end_at : timestamp
  title : varchar
  notes : text
  created_at : timestamp
  updated_at : timestamp
}

entity "sets" as sets {
  * id : bigint <<PK>>
  --
  * training_session_id : bigint <<FK>>
  * order_no : int
  * distance_m : int
  * reps : int
  interval_sec : int
  stroke : varchar
  intensity : varchar
  memo : text
}

users ||--o{ sessions
sessions ||--o{ sets
@enduml
