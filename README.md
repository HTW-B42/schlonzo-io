# schlonzo.io

gesamtes projekt bauen:

```shell
mvn clean install
```

nur frontend:

```shell
mvn clean install -Pfrontend
```

nur backend:

```shell
mvn clean install -Pbackend
```

server starten:

```shell
cd quizgame-server
mvn spring-boot:run
```

website starten:

```shell
cd quizgame-frontend
npm start
```
