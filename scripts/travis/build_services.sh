echo "Building with travis commit of $BUILD_NAME ..."
travis_wait 15 ./mvnw package -Pnative -Dnative-image.docker-build=true -Dquarkus.profile=local
