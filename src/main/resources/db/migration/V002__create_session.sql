CREATE TABLE IF NOT EXISTS agenda_session (
    id uuid primary key,
    agenda_id uuid,
    start_date timestamp,
    end_date timestamp
)