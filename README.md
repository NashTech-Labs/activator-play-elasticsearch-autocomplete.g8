#activator-play-elasticsearch-autocomplete

This is a Play activator project.It describes how to build autocomplete search on the Elasticsearch.

#Pre-Requisites for this project

i) [Download](https://www.elastic.co/downloads/elasticsearch) the latest version of Elasticsearch official distribution and unzip it.

ii) Run the following command.

        $ ./bin/elasticsearch


-----------------------------------------------------------------------
###Getting Started:
-----------------------------------------------------------------------

 Clone the code:

        $ git clone git@github.com:knoldus/activator-play-elasticsearch-autocomplete.git
        $ cd activator-play-elasticsearch-autocomplete

###Indexing:

   i) Create an index named testing with mapping(extra/es-mapping.json located in project home directory):

        $ curl -XPOST 'http://localhost:9200/movie' --data-binary @extra/es-mapping.json

   ii) Ingest the movies in the index:

        $ curl -XPOST 'http://localhost:9200/_bulk' --data-binary @extra/movies.json

#### Start App:
        $ ./bin/activator run

####Run the all unit test:

        $ ./bin/activator test


-----------------------------------------------------------------------
###All the Screens :-
-----------------------------------------------------------------------

### Home Page :


![alt-tag](/public/images/homePage.png)


### Movie Search :


![alt-tag](/public/images/movie.png)

