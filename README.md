#activator-play-elasticsearch-autocomplete

This is a Play activator project.It describes how to build autocomplete search on the Elasticsearch.

#Pre-Requisites for this project

       1. [Download](https://www.elastic.co/downloads/elasticsearch) the latest version of Elasticsearch official distribution and unzip it.

       2. $ ./bin/elasticsearch



 ###Indexing:

   Create an index named testing with mapping:

       $ curl -XPOST 'http://localhost:9200/movie' --data-binary @extra/es-mapping.json

   Ingest the movies in the index:

        $ curl -XPOST 'http://localhost:9200/_bulk' --data-binary @extra/movies.json


###Getting Started:

 Clone and run the app(default database is H2):

      $ git clone git@github.com:knoldus/activator-play-elasticsearch-autocomplete.git
      $ cd activator-play-elasticsearch-autocomplete
      $ ./activator run

 Run the all unit test:

     $ ./activator test


-----------------------------------------------------------------------
###All the Screens :-
-----------------------------------------------------------------------
### Home Page

![alt-tag](/public/images/homePage.png)

### Movie Search

![alt-tag](/public/images/movie.png)
