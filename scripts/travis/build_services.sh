echo "Building with travis commit of $BUILD_NAME ..."
docker build -f src/main/docker/Dockerfile.native -t anthonydenecheau/scc-pedigree-service:$BUILD_NAME .
