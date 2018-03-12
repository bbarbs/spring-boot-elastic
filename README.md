# Introduction
Sample spring boot project with elasticsearch implementation you can check it [here](https://www.elastic.co/).

## Installation
* Download elasticsearch [here](https://www.elastic.co/downloads)
* Then follow the steps are describe [here](https://www.elastic.co/downloads/elasticsearch)
* After running the elasticsearch check the http://localhost:9200/ to know if it is running. Sample response is shown below.
```
{
  "name": "trbTH4P",
  "cluster_name": "elasticsearch",
  "cluster_uuid": "eZD4rzQrTryJZWS1NMMxAA",
  "version": {
      "number": "6.2.2",
      "build_hash": "10b1edd",
      "build_date": "2018-02-16T19:01:30.685723Z",
      "build_snapshot": false,
      "lucene_version": "7.2.1",
      "minimum_wire_compatibility_version": "5.6.0",
      "minimum_index_compatibility_version": "5.0.0"
  },
  "tagline": "You Know, for Search"
}
```

## Configuration
Sample elastic configuration, it uses the port 9300. It uses also transport client instead of node see more: https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
```
  @Bean
    public Client client() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchOperations() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }
```
## Testing
Before running the application make sure to run the elasticsearch first. You could use other tools like POSTMAN to make query using elastic QUERY DSL https://www.elastic.co/guide/en/elasticsearch/reference/5.5/_introducing_the_query_language.html.

## Swagger
Aside from POSTMAN added also a swagger to perform CRUD operation easily. http://localhost:8080/swagger-ui.html
