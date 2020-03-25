echo "Building with travis commit of $BUILD_NAME ..."
mvn clean package -Dquarkus.container-image.build=true
docker build -t anthonydenecheau/scc-pedigree-service:$BUILD_NAME -f ./src/main/docker/Dockerfile.native .
