#!/bin/bash

java -Djava.security.egd=file:/dev/./urandom  -Dserver.port=80 -Dspring.profiles.active=prod -Xms600m -Xmx600m -jar sns-api-1.0.0.jar >/dev/null 2>&1 &