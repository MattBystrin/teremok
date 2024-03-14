# Requests for testing booking module
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"admin@mail.ru", "pass":"root"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

# Book records
psql -c '\d book_records'
psql -c 'select * from book_records'

# Book a room
curl -v -sS -X POST 'http://localhost:8080/api/v1/booking/reserve' \
	-H "Authorization: Bearer $(cat /tmp/token)" \
	-H 'Content-Type: application/json' \
-d '{"arrival":"2023-12-10", "departure":"2023-12-17", "type":1}'

curl -v -sS -X POST 'http://localhost:8080/api/v1/booking/reserve' \
	-H "Authorization: Bearer $(cat /tmp/token)" \
	-H 'Content-Type: application/json' \
-d '{"arrival":"2024-12-17", "departure":"2024-12-19", "type":1}'
