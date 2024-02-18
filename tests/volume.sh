#!/bin/bash

# Init database
# Specie id:   1
# Room id:     1
# Cleaner id:  3

# Create clean request 
# Create clean report 
# Create clean diff
# Create booking

rand_str() {
	cat /dev/urandom | tr -dc '[:alpha:]' | head -c 20
}

business_cycle() {
	arrival=$1
	departure=$1

	# Creating guest account
	guest=$(psql --csv -c "insert into users(email, firstname, lastname, pass, role, specie) \
	values ('$(rand_str)', '$(rand_str)', '$(rand_str)', '$(rand_str)', 'CLEANER', 1) returning id" \
	| tail +2 | head -1)

	# Create booking
	psql -c "insert into book_records(arrival, departure, room, uid) \
	values ('$arrival', '$departure', 1, 1)"

	# Create task
	task=$(psql --csv -c "insert into clean_tasks(date, ready, room_id, user_id)
	values ('2024-01-24', false, 1, 3) returning id" | tail +2 | head -1)

	# Create report
	report=$(psql --csv -c "insert into clean_reports(task_id, comment) \
	values ($task, '$(rand_str)') returning id" | tail +2 | head -1)

	# Insert 2 diffs for a room
	psql --csv -c "insert into clean_diffs (diff, furniture_id, report_id)
	values (1, 1, $report)" > /dev/null
}

check_size() {
	psql --csv -c "select pg_database_size('teremok')" |
		tail +2
}

volume_worker() {
	num=$(($1 * 10))
	for i in {1..1000}
	do
		arrival=$(date +%F -d "$date + $num year + $i day")
		business_cycle $arrival $arrival
		echo $i > /tmp/volume_$2
	done
	echo $(check_size) > /dev/stdout
}

rm -f /tmp/volume_*

for i in {1..15}
do
	volume_worker $i $(rand_str) &
done

wait

