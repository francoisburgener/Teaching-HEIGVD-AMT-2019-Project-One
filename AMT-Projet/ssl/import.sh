#!/bin/sh

sudo keytool -trustcacerts -keystore /Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/security/cacerts -storepass changeit -alias payara -import -file ~/Documents/heig-vd/teaching/AMT/repos/Teaching-HEIGVD-AMT-Example-Notes/ssl/payara-self-signed-certificate.crt
