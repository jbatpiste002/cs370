#!/bin/bash

server=${1-localhost:8085} #take the first parameter
 
curl -s -H Content-Type:application/json -d '["one"]' http://$server/checkclearing

history=`curl http://$server/checkclearing`
response=`curl -s -H Content-Type:application/json -d "$history" http://$server/checkclearing`

if [ "$response" != '{"one":100}' ]; then
echo $response
echo "TEST FAILED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
exit 1
else
echo "Test succeeded"
fi