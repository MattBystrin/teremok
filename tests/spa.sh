# Admin
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"admin@mail.ru", "pass":"root"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token
# Spa
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"spa@mail.ru", "pass":"spa"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

# Useful relations
psql -c '\d spa_records'
psql -c '\d spa_types'
psql -c '\d spa_compatible'
psql -c 'select * from spa_records'
psql -c 'select * from spa_types'
 
psql -c 'select * from spa_compatible'

curl -sS -X GET 'http://localhost:8080/api/v1/spa/available/2023-12-14/19:00' \
	-H "Authorization: Bearer $(cat /tmp/token)" | json_pp

curl -sS -v -X POST 'http://localhost:8080/api/v1/spa/book/1/2023-12-14/18:00' \
	-H "Authorization: Bearer $(cat /tmp/token)"

curl -sS -X GET 'http://localhost:8080/api/v1/spa/records/self' \
	-H "Authorization: Bearer $(cat /tmp/token)" | json_pp

curl -sS -X GET 'http://localhost:8080/api/v1/spa/records' | json_pp
