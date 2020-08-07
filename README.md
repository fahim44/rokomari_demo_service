## Ports:

- rabbitmq: `5672`,`15672`
- config-server: `8010`
- eureka: `8011`
- zuul: `8012`


## Passwords:

### keytools gen command:
`keytool -genkeypair -alias rokomari -keyalg RSA -dname "CN=Fahim,C=BD" -keypass rokomari -keystore rokomari.jks -storepass rokomari`

### rabbitmq username:password 
`rabbitmq:rabbitmq`

### config-server
`config:config`

### eureka-discovery
`eureka:eureka`

### zuul-api-gateway
`zuul:zuul`


## Locations:

### shared properties:
`\config-server\sharedProperties\`

### keytool:
`\config-server\keytools\`
