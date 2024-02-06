psql -c 'select * from notifications'
psql -c 'select * from notifications where viewed = false'


# Get notifications for a user. Do not forget to authorize first
curl -sS -X GET 'http://localhost:8080/api/v1/notifications' \
	-H "Authorization: Bearer $(cat /tmp/token)"

# Mark notifications as viewed
curl -sS -X POST 'http://localhost:8080/api/v1/notifications' \
	-H "Authorization: Bearer $(cat /tmp/token)" \
	-H 'Content-Type: application/json' -d '[1]'

# Send notification for a user with test message
curl -sS -X POST 'http://localhost:8080/api/v1/notifications/send/2'
