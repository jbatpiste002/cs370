#!/bin/bash

function cleanup {
  kill $server_pid
  rm gradle.properties
  exit 0
}

trap "cleanup" INT TERM EXIT

function unknown_files {
  unknown_file_count=`git status --porcelain | grep "^??" | wc -l`
  [[ "$unknown_file_count" -gt 0 ]]
}

function uncommitted_changes {
  count=`git diff HEAD --shortstat | wc -l`
  [[ "$count" -gt 0 ]]
}

if unknown_files; then
echo "Unknown files in project!"
  exit 1
fi

if uncommitted_changes; then
echo "Uncommitted files in project!"
  exit 1
fi

gradle clean build
if [ "$?" -gt 0 ]; then
exit 1
fi

gradle gaeRun &
server_pid=$!
if [ "$?" -gt 0 ]; then
  #add count to while loop for optional timeout
  echo "Server fialed to start"
  exit 1
fi

server_status=1
echo -n "Waiting for local server to start..."
while [ $server_status -gt 0 ]; do
echo -n
  curl http://localhost:8085
  server_status=$?
  sleep 1
done

history=`curl http://dgladecs370.appspot.com/checkclearing`
response=`curl -s -H Content-Type:application/json -d "$history" http://localhost:8085/checkclearing`

echo $response | python -mjson.tool > /dev/null
if [ "$?" -eq 0 ]; then
echo "Response is valid JSON"
else
echo "Response is NOT valid JSON"
  echo $response
  exit 1
fi

kill $server_pid
wait $server_pid

echo "Build successful! Enter AppEngine password to deploy"
stty -echo
read -p "Password: " password
echo
stty echo

echo "gaePassword=$password" > gradle.properties
gradle gaeUpload
rm gradle.properties

timestamp=`date "+%Y-%m-%d_%H_%M_%S"`
echo $timestamp
git tag DEPLOY_$timestamp

echo "Exiting..."
exit 0





