echo "Tagging build with $BUILD_NAME"
export TARGET_URL="https://api.github.com/repos/anthonydenecheau/pedigree-service/releases"

body="{
  \"tag_name\": \"$BUILD_NAME\",
  \"target_commitish\": \"master\",
  \"name\": \"$BUILD_NAME\",
  \"body\": \"Release of version $BUILD_NAME\",
  \"draft\": true,
  \"prerelease\": true
}"

curl \
 -X POST \
 -u anthonydenecheau:$GITHUB_TOKEN \
 -H "Accept: application/vnd.github.v3+json" \
 -d "{\"tag_name\": \"$BUILD_NAME\",\"target_commitish\": \"master\",\"name\": \"$BUILD_NAME\",\"body\": \"Release of version $BUILD_NAME\",\"draft\": true,\"prerelease\": true}" \
 $TARGET_URL
 