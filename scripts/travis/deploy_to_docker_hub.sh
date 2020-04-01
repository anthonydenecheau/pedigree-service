echo "Pushing service docker images to docker hub ..."
travis login --github-token $DOCKER_TOKEN
docker push anthonydenecheau/scc-pedigree-service:$BUILD_NAME
