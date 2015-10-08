# innometricstest

Spring Boot application for the Innometrics Programming assignment

Technology stack
--

Spring Boot for bootstrapping, 
Spring Boot Actuator for health/metric endpoints,
Spring MVC for the REST endpoints,
Logback/AspectJ for logging,
Hamcrest/JUnit/REST-Assured for unit tests

Heroku
--

Deployed to:
    https://innometricstest.herokuapp.com/ 

Endpoints
--

Add new counter

    curl -X POST https://innometricstest.herokuapp.com/innometrics/internal/counter/{counterName}
    
Get counter value

    curl -X GET https://innometricstest.herokuapp.com/innometrics/internal/counter/{counterName}

Get list of counterNames

    curl -X GET https://innometricstest.herokuapp.com/innometrics/internal/counter/list


Additional questions
--

Persistence. How would you  add a persistent storage layer such that the app could be restarted without
losing counter states?

    For this app either an embedded Redis cache or H2 DB would be easy to implement with Spring Data.

Concurrency. What happens if your service is handling multiple simultaneous requests? How would you update
the app to support many concurrent clients?

    My app already supports a multi-threaded environment with the use of the ConcurrentMap and AtomicInteger
    classes.

Fault tolerance. How would the app need to change in order to continue working if some part of the underlying
system hardware (disk, memory, CPU, network, etc.) were to fail?

    The current implementation already contains a health check endpoint 
    ( https://innometricstest.herokuapp.com/innometrics/health ).
    Putting the app on multiple servers/instances and adding a load balancer would increase the fault
    tolerance in case a server/instance breaks down. A persistence layer shared by all instances must
    be implemented if we were to use this solution.

Scalability. How would the app need to change in order to ensure that it wouldn’t break down if traffic
increased by many orders of magnitude?

    Scale the number of servers/instances and implementing the solution recommended in the previous point
    would help scaling.

Authentication. How would the app need to change in order to ensure that only authorised users can submit
and retrieve data?

    I would use Spring Security to implement a security layer. A basic user/password authentication if the
    endpoint does not return sensitive data.
    
    I would suggest to opt for an OAuth2 authentication mechanism if the level of sensitivity recommends.
    

