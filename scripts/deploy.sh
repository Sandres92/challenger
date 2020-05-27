#!/usr/bin/env bash

./gradlew clean build

echo 'Copy files...'

scp -i ~/.ssh/challenger-start.pem \
    build/libs/challenger-0.0.1-SNAPSHOT.jar \
    ec2-user@ec2-18-130-224-166.eu-west-2.compute.amazonaws.com:/home/ec2-user/

echo 'Restart server...'

ssh -i ~/.ssh/challenger-start.pem ec2-user@ec2-18-130-224-166.eu-west-2.compute.amazonaws.com << EOF

pgrep java | xargs kill -9
nohup java -jar challenger-0.0.1-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'