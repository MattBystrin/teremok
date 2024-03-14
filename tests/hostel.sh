# Inspect database
psql -c 'select * from species'
curl -sS -X GET 'http://localhost:8080/api/v1/species' | json_pp
# Auth as admin
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"admin@mail.ru", "pass":"root"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

# Rooms
psql -c 'select * from rooms'
psql -c 'select * from room_types'

psql -c 'select * from room_compatible'

psql -c 'select * from book_records'
psql -c 'select * from rooms where rooms.type in (select room_type from room_compatible where specie = 1)'

export ARR="'2023-12-14'"; \
export DEP="'2023-12-15'"; \
export SP="1"; \
psql -c "select * from rooms where rooms.type in (select room_type from room_compatible where specie = $SP) \
		and rooms.id not in (select room from book_records as br where \
			($ARR >= br.arrival and $ARR < br.departure) or \
			($DEP > br.arrival and $DEP <= br.departure) or \
			(br.arrival >= $ARR and br.departure <= $DEP))"

curl -sS -X GET 'http://localhost:8080/api/v1/rooms' | json_pp
curl -sS -X GET 'http://localhost:8080/api/v1/rooms/1' | json_pp
curl -sS -X GET 'http://localhost:8080/api/v1/rooms/available/2023-12-14/2023-12-15' \
	-H "Authorization: Bearer $(cat /tmp/token)" | json_pp
curl -sS -X GET 'http://localhost:8080/api/v1/rooms/available/2024-12-17/2023-12-15' \
	-H "Authorization: Bearer $(cat /tmp/token)" | json_pp

# Furniture
psql -c 'select * from furniture'
psql -c 'select * from furniture_types'

curl -sS -X GET 'http://localhost:8080/api/v1/furniture/room/1' | json_pp
curl -sS -X GET 'http://localhost:8080/api/v1/furniture/1' | json_pp
