# Requests for testing booking module
# -H "Authorization: Bearer $(cat /tmp/token)" \

# Book records
psql -c '\d book_records'
psql -c 'select * from book_records'

# Book a room
curl -v -sS -X POST 'http://localhost:8080/api/v1/booking/reserve' \
	-H 'Content-Type: application/json' \
-d '{"arrival":"2023-12-10", "departure":"2023-12-17", "room":1}'

curl -v -sS -X POST 'http://localhost:8080/api/v1/booking/reserve' \
	-H 'Content-Type: application/json' \
-d '{"arrival":"2023-12-17", "departure":"2023-12-19", "room":1}'
