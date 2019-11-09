#!/bin/sh

sudo keytool -trustcacerts -keystore /Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/lib/security/cacerts -storepass changeit -alias payara -import -file ~/Documents/amt/Teaching-HEIGVD-AMT-2019-Project-One/AMT-Projet/ssl/payara-self-signed-certificate.crt
# sudo keytool -trustcacerts -keystore /Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home/lib/security/cacerts -storepass changeit -alias payara -import -file ~/Documents/amt/Teaching-HEIGVD-AMT-2019-Project-One/AMT-Projet/ssl/payara-self-signed-certificate.crt
