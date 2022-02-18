# Repositório SCA-SERVICE


## Descrição

Aplicação responsável pela integração do processo de autenticação das **aplicações MP-RJ** com o módulo de login **RH-SSO**.

### Estrutura
```
.
├── Dockerfile
├── README.md ~> Documentação do repositório
├── k8s ~> Diretório que possui as configurações Kubernetes por ambiente
│   ├── beta
│   ├── dev
│   ├── hml
│   └── prod
├── pom.xml
├── src
│   ├── main
│   │   ├── java ~> Diretório que contém código-fonte da aplicação
│   │   │   ├── META-INF
│   │   │   └── br
│   │   │       └── mp
│   │   │           └── mprj
│   │   │                   └── services
│   │   │                       └── api
│   │   │                           ├── controller
│   │   │                           ├── enums
│   │   │                           ├── impl
│   │   │                           ├── jms
│   │   │                           │   └── router
│   │   │                           ├── repository
│   │   │                           ├── rest
│   │   │                           │   └── router
│   │   │                           │       └── exception
│   │   │                           ├── util
│   │   │                           └── vo
│   │   ├── resources ~> Diretório responsável por possuir arquivos de configuração
│   │   └── webapp ~> Diretório possuidor do código fonte relacionado ao container web
│   │       ├── WEB-INF
│   │       ├── css
│   └── test ~> Diretório para classes de testes
│       └── resources
└── target ~> Diretório gerado pelo processo de compilação Maven
```

## How to build ?
```
git clone http://gitlab.mprj.mp.br/redhat/sca-service.git
cd sca-service
mvn clean package
```

## How to run ?

### Local sem Docker
```
git clone http://gitlab.mprj.mp.br/redhat/sca-service.git
cd sca-service
mvn spring-boot:run 
```

### Local com Docker
Baixe o código da aplicação e realize o processo de `build`.
```
git clone http://gitlab.mprj.mp.br/redhat/sca-service.git
cd sca-service
mvn clean package
```
Uma vez realizado o `build` com sucesso, altere o Dockerfile da aplicação com o conteúdo abaixo.
```
FROM registry.access.redhat.com/ubi8/openjdk-11
WORKDIR aplicacao
COPY target/*.jar .
EXPOSE 8080
CMD ["java", "-Dfile.encoding=UTF-8", "-jar","app.jar"]
```
Salve e feche o arquivo. Depois, faça a construção da imagem Docker e ponha para funcionar.
```
docker build -t sca-service/sca-service:1.0.0.SNAPSHOT .
docker run -it -p 8080:8080 sca-service/sca-service:1.0.0.SNAPSHOT
```
A tag `1.0.0-SNAPSHOT` foi retirada do arquivo `pom.xml`.
