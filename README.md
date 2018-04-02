# elasticsearch-client

This is a small demo project to query aws elasticsearch. 

It's built on Spring framework with Swagger UI and uses the [Java High Level Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high.html) to connect to to elasticsearch on AWS.

It has three api for now
- get records by field and value (for example we want to get records where ack_id = 000, we need to provide `field=ack_id`, `value=000` in query parameter)
- get records by field and value, the same as above, but it's using the `ActionListener` so it's an async call.
- get all the distinct values for a given field

I uploaded all test data (~130MB) from S3 to elasicsearch using [flex.io pipe](https://www.flex.io/) and set the access policy open to all domain. Feel free to download and compile and using the Swagger interface or directly go to [elastic server](https://search-personal-capital-ar4adi5d2ldej7qpyxojgtvoay.us-east-1.es.amazonaws.com/) or [kibana](https://search-personal-capital-ar4adi5d2ldej7qpyxojgtvoay.us-east-1.es.amazonaws.com/_plugin/kibana/app/kibana#/home?_g=())
