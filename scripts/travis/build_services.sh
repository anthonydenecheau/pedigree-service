echo "Building with travis commit of $BUILD_NAME ..."
mvnw package -Pnative -Dnative-image.docker-build=true -Dquarkus.profile=docker-profile
docker build -f src/main/docker/Dockerfile.native -t anthonydenecheau/scc-pedigree-service:$BUILD_NAME .
