# Auth
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"admin@mail.ru", "pass":"root"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

psql -c '\d clean_tasks'
psql -c 'select * from clean_tasks'
psql -c '\d clean_reports'
psql -c 'select * from clean_reports'

# Send clean report
curl -sS -X POST 'http://localhost:8080/api/v1/cleaning/report' \
	-H 'Content-Type: application/json' \
-d '{ "task":1, "diffs": [ {"id" :1, "diff": 1}, { "id" :2, "diff" :1} ], "comment": "everything is fine" }'

# Get furniture from room
curl -sS -X GET 'http://localhost:8080/api/v1/furniture/room/1' | json_pp

# Get cleaning queue
curl -sS -X GET  'http://localhost:8080/api/v1/cleaning/queue/self' \
	-H "Authorization: Bearer $(cat /tmp/token)" | json_pp

curl -sS -X GET  'http://localhost:8080/api/v1/cleaning/queue' | json_pp

# Add to cleaning queue for specific date
curl -sS -X POST 'http://localhost:8080/api/v1/cleaning/queue' \
	-H 'Content-Type: application/json' \
	-d '{ "date": "2024-10-09", "room": 2 }'

# Add for today
curl -sS -X POST 'http://localhost:8080/api/v1/cleaning/queue' \
	-H 'Content-Type: application/json' \
	-d '{ "room": 2 }'
