#activator-play-elasticsearch-autocomplete

This is a Play activator project.It's describe how to build autocomplete search on the Elastichsearch. 

 Clone and run the app(default database is H2):

      $ git clone git@github.com:knoldus/activator-play-elasticsearch-autocomplete.git
      $ cd activator-play-elasticsearch-autocomplete
      $ ./activator run
    
 Run the all unit test:

     $ ./activator test

 Getting Started:
    1. Download the latest version of Elasticsearch official distribution and unzip it.
    2. Run ./bin/elasticsearch
    3. Run curl -X GET http://localhost:9200/ to get information about the server.

 Indexing:
    1. Run "curl -XPOST 'http://localhost:9200/testing' --data-binary @extra/es-mapping.json" to create an index
       named testing with mapping.
    2. Run "curl -XPOST 'http://localhost:9200/_bulk' --data-binary @extra/movies.json" to ingest the movies in the index
