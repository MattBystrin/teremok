curl -v -sS -X GET  'http://localhost:8080/test'

# Register
curl -v -sS -X POST 'http://localhost:8080/api/v1/auth/register' -H 'Content-Type: application/json' \
-d '{"firstname":"mihail", "lastname":"medvedev", "email":"mihamed@mail.ru", "pass":"1234"}'

# Auth
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"mihamed@mail.ru", "pass":"1234"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

curl -sS -X GET 'http://localhost:8080/api/v1/species' \
	-H "Authorization: Bearer $(cat /tmp/token)"
