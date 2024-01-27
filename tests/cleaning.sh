
psql -c '\d clean_tasks'
psql -c '\d clean_reports'

# Send clean report
curl -sS -X POST 'http://localhost:8080/api/v1/cleaning/report' \
-H 'Content-Type: application/json' \
-d '{ "task":1, "diffs": [ {"id" :1, "diff": 1} ], "comment": "everything is fine" }'

# Get furniture from room
curl -sS -X GET 'http://localhost:8080/api/v1/furniture/room/1' | json_pp

# Get cleaning queue
curl -sS -X GET  'http://localhost:8080/api/v1/cleaning/queue/employee/1'
curl -sS -X GET  'http://localhost:8080/api/v1/cleaning/queue' | json_pp

# Add to cleaning queue
curl -sS -X POST 'http://localhost:8080/api/v1/cleaning/queue' \
-H 'Content-Type: application/json' \
-d '{ "date": "2023-10-09", "employee": 1, "room": 1 }'
