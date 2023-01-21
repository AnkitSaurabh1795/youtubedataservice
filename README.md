# youtubedataservice
take clone of this repo in local
And in constant.java only need to add mongo uri by default it is:mongodb://localhost:27017"
Run maven application
then you can do search of stored data sample api: http://localhost:8080/video/data/search?query="cricket "
you can do get api for paginated data sample curl: http://localhost:8080/video/data/find/50/1
here pageSize is 50 and pageNo is 1. Note pageNo is 0 indexed
