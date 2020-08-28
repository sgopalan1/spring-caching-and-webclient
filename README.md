# spring-caching-and-webclient
A simple POC to showcase the Spring Caching abstraction to implement a JVM based Cache.
Also has a simple implementation that uses WebClient to invoke a REST service.

# Running the app
gradle bootRun

# Caching
## Testing
Invoke http://localhost:8080/slow-service-quotes a few times to observe about the same response time (~2 sec) for all attempts.
Invoke http://localhost:8080/cached-slow-service-quotes a few times to observe a swift response time from the second attempt onwards.

## Reference links
https://docs.spring.io/spring/docs/5.1.8.RELEASE/spring-framework-reference/integration.html#cache
https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-caching 
https://spring.io/guides/gs/caching/

# WebClient
## Testing
Invoke http://localhost:8080/quotes-blocking to get the response using a regular RestTemplate.
Invoke http://localhost:8080/quotes-non-blocking to get the response using WebClient. It returns a Flux stream, though can be changed to blocking.

## Testing
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
https://www.baeldung.com/spring-webclient-resttemplate
