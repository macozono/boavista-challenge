# boavista-challenge
Desafio relacionado a construção de API para manutenção de usuários

## Arquitetura e componentes envolvidos na construção

O projeto foi construído utilizando o framework Spring Boot para disponibilização dos endpoints da API.

Para persistência dos dados, foi utilizado o framework do Spring Data JPA, o qual já nos fornece uma série de abstrações que automatizam funcionalidades relacionadas a persistência de dados com base na especificação JPA.

A construção dos testes unitários foi utilizado o JUnit em conjunto com o starter de testes do Spring Boot e com base de dados em memória H2DB.

A aplicação foi construída para uso em container e foi disponibilizada em uma VM no Google Cloud Platform (o endereço para acesso encontra-se no final deste README). 

A estrutura do projeto foi construída utilizando o Maven. Mantendo de certa forma o gerenciamento das dependências necessárias para a execução e testes.

## Documentação da API

Para documentação da API foi utilizado o framework Swagger no qual proporciona recursos visuais para consulta e execução dos endpoints.

## Importação do projeto

Para a importação do projeto, recomendo que utilize o wizard da sua IDE preferida e realize o import do projeto através da leitura do arquivo POM.xml. Dessa forma, garante-se que o projeto será construído através da estrutura na qual está definida no POM.

## Execução stand alone

Para execução da API, execute o seguinte comando:

```
mvn spring-boot:run
```

Esse comando irá executar o plugin do spring-boot no qual irá iniciar a execução do container embarcado e disponibilizará os endpoints localmente para testes.

## Uso de JWT para permissão na execução dos endpoints de alteração, exclusão e lista de usuários

Para garantir a autenticidade do usuário que está solicitando a manutenção. As operações de alteração, exclusão e lista de usuários será necessário informar no header da requisição o parâmetro Authorizantion com o hash do token em JWT que é retornado na resposta do endpoint de inclusão do usuário.

o JWT será utilizado durante essas requisições para certificar que o usuário informado como recurso na execução da API realmente é o mesmo no qual o token foi gerado.

Caso negativo, a API retornará um código 401 de não autorizado para o acesso.

Segue exemplo de formatação do header de autorização:

```
Authorization: Bearer <hash do token retornado na resposta da inclusão de usuário>
```

## Execução em container

Na estrutura da aplicação foi disponibilizada os arquivos de configuração associados ao processo de container via Docker.

Para o processo de container foi utilizado o framework docker-compose para que permita a orquestração e inicialização dos containers da aplicação em Spring boot e do banco de dados MySQL.

Portanto, caso seja necessário o teste localmente em containers. Por favor, utilizar o docker-compose para que seja inicializado os serviços e construção dos containers.

Executar o seguinte comando: 

```
docker-compose up -d --build
```

Parâmetro --build necessário apenas na primeira execução para que sejam criadas as imagens dos serviços declarados no arquivo docker-compose.yaml.

**Importante!!** Antes de executar o compose é necessário gerar o JAR da aplicação para que este seja copiado ao container e depois executado. Portanto, executar o seguinte comando para geração do JAR:

```
mvnw -Pdocker package
```

**Obs:** Parâmetro -P indica o profile docker que foi configurado no POM para execução de plugin específico para geração do JAR para uso no container.

## URLs para os endpoints disponíveis na nuvem para testes

Foram disponibilizados os seguintes endpoints na cloud do Google:

**Adicionar dados do usuário**

Método POST: [http://140.92.71.34.bc.googleusercontent.com:8080/usuarios](http://140.92.71.34.bc.googleusercontent.com:8080/usuarios)

**Atualizar dados do usuário**

Método PUT: [http://140.92.71.34.bc.googleusercontent.com:8080/usuarios/{usuarioId}](http://140.92.71.34.bc.googleusercontent.com:8080/usuarios/usuarioId)

**Descadastrar usuário**

Método DELETE: [http://140.92.71.34.bc.googleusercontent.com:8080/usuarios/{usuarioId}](http://140.92.71.34.bc.googleusercontent.com:8080/usuarios/usuarioId)

**Adicionar usuários (somente perfil ADMIN)**

Método GET: [http://140.92.71.34.bc.googleusercontent.com:8080/usuarios](http://140.92.71.34.bc.googleusercontent.com:8080/usuarios)

**Documentação da API**

[http://140.92.71.34.bc.googleusercontent.com:8080/swagger-ui.html](http://140.92.71.34.bc.googleusercontent.com:8080/swagger-ui.html)
