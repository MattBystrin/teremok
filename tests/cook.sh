# Admin
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"admin@mail.ru", "pass":"root"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token
# Cook
curl -sS -X POST 'http://localhost:8080/api/v1/auth/authenticate' -H 'Content-Type: application/json' \
-d '{"email":"cook@mail.ru", "pass":"cook"}' | sed 's/.*"access_token":"\(.*\)",.*/\1/' > /tmp/token

# Useful relations
psql -c '\d dishes'
psql -c '\d cook_orders'
psql -c '\d cook_tasks'
psql -c '\d dish_compatible'
psql -c 'select * from cook_tasks'
psql -c 'select * from cook_orders'

curl -sS -X GET 'http://localhost:8080/api/v1/cooking/menu' \
	-H "Authorization: Bearer $(cat /tmp/token)" | json_pp

curl -sS -X POST 'http://localhost:8080/api/v1/cooking/order' \
	-H "Authorization: Bearer $(cat /tmp/token)" \
	-H 'Content-Type: application/json' \
-d '[{"dish":1, "qntt":1}, {"dish":2, "qntt": 2}]'

curl -sS -X GET 'http://localhost:8080/api/v1/cooking/orders' | json_pp

curl -sS -X GET 'http://localhost:8080/api/v1/cooking/orders/self' \
	-H "Authorization: Bearer $(cat /tmp/token)" | json_pp

curl -sS -X PATCH 'http://localhost:8080/api/v1/cooking/orders/1'
