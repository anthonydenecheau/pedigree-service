# Developpement
mvn compile quarkus:dev -Dquarkus.profile=dev

# Documentation
http://localhost:8085/public/documentation/

# CloudSql proxy
cd C:\developpement\app\Cloud SDK
cloud_sql_proxy_x64.exe -instances=myProject:us-central1:myInstance=tcp:5432
    psql -U myUser -h 127.0.0.1

pgAdmin ...