## Projects:

- rabbitmq (massage broker... runs via docker-compose... using image with stomp plugin enable)
- Config-server (centralized property management)
- eureka (microservice discovery service)
- gateway-service (Gateway service + load balancer)
- order-ws (microservice for listening new order creation and notifing other clients)
- web-client (vaadin app to create new order randomly in every 1-5 secs)

## Ports:

- rabbitmq: `5672`,`15672`, `61613`
- config-server: `8010`
- eureka: `8011`
- gateway-service: `8012`
- web-client: `8013`

## Passwords:

### keytools gen command:
`keytool -genkeypair -alias rokomari -keyalg RSA -dname "CN=Fahim,C=BD" -keypass rokomari -keystore rokomari.jks -storepass rokomari`

### rabbitmq username:password 
`rabbitmq:rabbitmq`

### config-server
`config:config`

### eureka-discovery
`eureka:eureka`


## Locations:

### shared properties:
`\config-server\sharedProperties\`

### keytool:
`\config-server\keytools\`
