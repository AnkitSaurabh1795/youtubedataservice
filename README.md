# youtubedataservice
take clone of this repo in local
And in constant.java only need to add mongo uri by default it is:mongodb://localhost:27017"
make sure you have created composite index for text on field title and tags
mongodb command to create index(db.getCollection("video").createIndex({title:"text",tags:"text"}))
Run maven application
then you can do search of stored data sample api: http://localhost:8080/video/data/search?query="cricket "
Note: search api will work only if text index created in mongodb
you can do get api for paginated data sample curl: http://localhost:8080/video/data/find/50/1
Note: here pageSize is 50 and pageNo is 1. Note pageNo is 0 indexed
