curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"admin@mail.ru", "pass":"root"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

psql -c 'select id, email, role, specie from users'
psql -c 'select * from users'

curl -sS -X GET 'http://localhost:8080/api/v1/users/roles' | json_pp

curl -sS -X GET 'http://localhost:8080/api/v1/users/self' \
	-H "Authorization: Bearer $(cat /tmp/token)" | json_pp

curl -sS -X GET 'http://localhost:8080/api/v1/users/employees' | json_pp

curl -sS -X POST 'http://localhost:8080/api/v1/users/employees/add' \
	-H 'Content-Type: application/json' \
-d '{"firstname":"mihail", "lastname":"medvedev", "email":"mihamed2@mail.ru", "pass":"1234", "specie":1, "role":"CLEANER" }'
