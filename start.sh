#!/bin/bash
cd AMT-Projet
name="AMT Project One - start.sh"
echo "$name...................................................................."
echo "Running start.sh.............................................................................."
echo "$name: - mvn clean......................................................."
mvn clean
echo "$name: - mvn compile war:war............................................."
mvn compile war:war
echo "$name: - copying to correct location....................................."
cd target
mv AMT-Projet.war ../../images/payara/deployements
cd ../../topology
echo "$name: - docker-compose.................................................."
echo "Press CTRL+C to stop.....have a nice day :D .................................................."
docker-compose down
docker-compose up --build