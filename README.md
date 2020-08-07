## Ports:

- rabbitmq: `5672`,`15672`
- config-server: `8010`
- eureka: `8011`


## Passwords:

### keytools gen command:
`keytool -genkeypair -alias rokomari -keyalg RSA -dname "CN=Fahim,C=BD" -keypass rokomari -keystore rokomari.jks -storepass rokomari`

### rabbitmq username:password 
`rabbitmq:rabbitmq`

### config-server
`config:config`

### eureka-discovery
`eureka:eureka`
