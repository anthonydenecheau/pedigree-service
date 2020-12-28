echo "Building with travis commit of $BUILD_NAME ..."
./mvnw package -Pnative -Dnative-image.docker-build=true -Dquarkus.profile=local
