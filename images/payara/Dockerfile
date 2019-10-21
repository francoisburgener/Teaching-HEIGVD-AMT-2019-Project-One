FROM payara/server-full

ADD drivers/mysql-connector-java-8.0.11.jar /opt/payara/appserver/glassfish/lib/


ENTRYPOINT ${SCRIPT_DIR}/init_1_generate_deploy_commands.sh && \
    echo 'create-jdbc-connection-pool --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --restype javax.sql.ConnectionPoolDataSource --property user=root:password=admin:DatabaseName=amt-db:ServerName=db:port=3306:useSSL=false pool_amt' > mycommands.asadmin && echo 'create-jdbc-resource --connectionpoolid pool_amt jdbc/amt-db' >> mycommands.asadmin && cat ${DEPLOY_COMMANDS} >> mycommands.asadmin && ${PAYARA_DIR}/glassfish/bin/asadmin start-domain -v --postbootcommandfile mycommands.asadmin ${PAYARA_DOMAIN}

