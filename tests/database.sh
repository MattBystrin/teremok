#!/bin/bash
# Color variables
red='\033[0;31m'
green='\033[0;32m'
clean='\033[0m'

echo "Performing database tests"

init() {
	echo "Initializing database"
	psql -f tests/dbinit.sql && return

	echo "Failed to init database"
	return 1
}

room_types() {
	# Insert room type
	id=$(psql --csv -c "insert into room_types(capacity, cost, label) values (1, 4, 'room for predators') returning id" | tail +2 | head -1)
	psql -c "insert into rooms(type) values (${id})"
	value=$(psql --csv -c "select id from rooms where type=${id}" | tail +2)
	# Assert correct insertion
	[[ -n $value ]] || return 1
	# Now remove room type and watch for the changes
	psql -c "delete from room_types where id=${id}"
	value=$(psql --csv -c "select id from rooms where type=${id}" | tail +2)
	# Assert correct deletion
	[[ -z $value ]] || return 1
	return 0
}

book_records() {
	room_type=$(psql --csv -c "insert into room_types(capacity, cost, label) values (1, 4, 'room for predators') returning id" | tail +2 | head -1)
	room=$(psql --csv -c "insert into rooms(type) values ($room_type) returning id" | tail +2 | head -1)
	specie=$(psql --csv -c "insert into species (name, description) values ('bear', 'forest predator') returning id" | tail +2 | head -1)
	psql --csv -c "insert into room_compatible (room_type, specie) values($room_type, $specie)"
	user=$(psql --csv -c "insert into users(email, firstname, lastname, \
	pass, role, specie) values ('testuser@mail.ru', 'User', 'Test', \
		'testpass', 'USER', $specie) returning id" | tail +2 | head -1)
	echo $room_type $room $specie
	book=$(psql --csv -c "insert into book_records(arrival, departure, room, uid) \
	values ('2024-03-11', '2024-03-15', $room, $user) returning id" | tail +2 | head -1)
	[[ -n $book ]] || return 1
	psql -c "delete from users where id = $user"
	book=$(psql --csv -c "select id from book_records where uid = $user" | tail +2)
	[[ -z $book ]] || return 1
	psql -c "delete from species where id = $specie"
	return 0
}

clean_tasks() {
	room_type=$(psql --csv -c "insert into room_types(capacity, cost, label) values (1, 4, 'room for predators') returning id" | tail +2 | head -1)
	room=$(psql --csv -c "insert into rooms(type) values ($room_type) returning id" | tail +2 | head -1)
	specie=$(psql --csv -c "insert into species (name, description) values ('fox', 'red forest predator') returning id" | tail +2 | head -1)
	user=$(psql --csv -c "insert into users(email, firstname, lastname, \
	pass, role, specie) values ('testemployee@mail.ru', 'Employee', 'Test', \
		'testpass', 'CLEANER', $specie) returning id" | tail +2 | head -1)
	task=$(psql --csv -c "insert into clean_tasks(date, ready, room_id, user_id)
	values ('2024-01-24', false, $room, $user) returning id")
	task=$(psql --csv -c "select id from clean_tasks where user_id = $user" | tail +2)
	[[ -n $task ]] || return 1
	psql -c "delete from users where id = $user"
	task=$(psql --csv -c "select id from clean_tasks where user_id = $user" | tail +2)
	[[ -z $task ]] || return 1
	psql -c "delete from species where id = $specie"
	return 0
}

furniture () {
	room_type=$(psql --csv -c "insert into room_types(capacity, cost, label) values (1, 4, 'room for predators') returning id" | tail +2 | head -1)
	room=$(psql --csv -c "insert into rooms(type) values ($room_type) returning id" | tail +2 | head -1)
	f_type=$(psql --csv -c "insert into furniture_types(cost, name) values (1, 'bed') returning id" | tail +2 | head -1)
	f=$(psql --csv -c "insert into furniture(state, type, room) values (5, $f_type, $room) returning id" | tail +2 | head -1)
	f_chk=$(psql --csv -c "select id from furniture where type = $f_type")
	[[ -n $f_chk ]] || return 1
	[[ $f == $f_chk ]] || return 1
	psql -c "delete from furniture_types where id = $f_type"
	f_chk=$(psql --csv -c "select id from furniture where type = $f_type")
	[[ -z $f_chk ]] || return 1
	return 0
}

for tcase in book_records room_types clean_tasks furniture
do
	result="${green}Passed${clean}"
	$tcase > /dev/null 2>&1
	if [[ $? != 0 ]]; then
		result="${red}Failed${clean}"
	fi
	echo -e "Test $tcase finished. Result: $result"
done
