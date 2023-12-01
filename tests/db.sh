# Db queries for fast check
# Set password in env or add variable before command invokation
# PGPASSWORD=... psql ...
psql -c '\dt'

# Tokens
psql -c 'select * from tokens'
psql -c 'select id, user_id, expired, revoked, token_type from tokens'

# Users
psql -c '\d users'
psql -c 'select * from users'
psql -c 'select id, email, role, specie from users'

# Species
psql -c 'select * from species'

# Rooms
psql -c 'select * from rooms'
psql -c 'select * from room_types'
