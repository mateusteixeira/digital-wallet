FROM openjdk:11
MAINTAINER mateus.teixeira
COPY /target/digital-wallet-0.0.1-SNAPSHOT.jar digital-wallet.jar
ENTRYPOINT ["java","-jar","/digital-wallet.jar"]