# Tokens
psql -c 'select * from tokens'
psql -c 'select id, user_id, expired, revoked, token_type from tokens'

# Users
psql -c '\d users'
psql -c 'select * from users'
psql -c 'select id, email, role, specie from users'

psql -c "select * from users where role = 'USER'"
psql -c "select * from users where role = 'ADMIN'"

# Register
curl -v -sS -X POST 'http://localhost:8080/api/v1/auth/register' -H 'Content-Type: application/json' \
-d '{"firstname":"mihail", "lastname":"medvedev", "email":"mihamed2@mail.ru", "pass":"1234", "specie": 1}'

# Auth
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"mihamed2@mail.ru", "pass":"1234"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

# Auth as admin
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"admin@mail.ru", "pass":"root"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

# Auth as cook
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"kekmail@mail.ru", "pass":"pass"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

# Logout
curl -sS -X GET 'http://localhost:8080/api/v1/auth/logout' \
	-H "Authorization: Bearer $(cat /tmp/token)"
