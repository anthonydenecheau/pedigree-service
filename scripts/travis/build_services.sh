echo "Building with travis commit of $BUILD_NAME ..."
mvn clean package -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=docker
docker build -t anthonydenecheau/scc-pedigree-service:$BUILD_NAME -f ./src/main/docker/Dockerfile.native .
