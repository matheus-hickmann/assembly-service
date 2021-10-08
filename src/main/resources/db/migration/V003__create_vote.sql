CREATE TABLE IF NOT EXISTS vote (
    id uuid primary key,
    session_id uuid,
    user_document varchar,
    vote_value varchar
)