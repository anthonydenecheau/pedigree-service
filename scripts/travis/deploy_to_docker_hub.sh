echo "Pushing service docker images to docker hub ...."
docker logout
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push anthonydenecheau/scc-pedigree-service:$BUILD_NAME