# User Profile Manager

Um projeto para gerenciamento de perfis de usuários.

## Funcionamento
O sistema disponibiliza uma API Rest com três endpoints, descritos a seguir.

Obs.: deve ser utilizado ContentType application/json no header das mensagens.

### /register
Este endpoint realiza o cadastro do usuário. Deve ser enviado um POST com o seguinte formato:

    {
       "name": "Vinicius Souza",
       "email": "vini@email.com",
       "password": "1234",
       "phones": [
           {
               "number": "987654321",
               "ddd": "48"
           }
       ]
    }

Em caso se sucesso, retorna com o objeto criado e código HTTP 201.

### /login
Este endpoint realiza o login do usuário. Deve ser enviado um POST com o seguinte formato:

    {
        "email": "vini@email.com",
        "password": "1234"
    }

Em caso se sucesso, retorna com os dados do usuário e código HTTP 200. Além disso, retorna um token de autenticação, que deve ser utilizado no header das próximas requisições, no campo Authorization (deve ser utilizada a sintaxe "Bearer \<token\>". A validade do token é de 30 minutos.

### /userProfile
Este endpoint disponibiliza o perfil do usuário através de um GET e deve ser indicado o parâmetro "id", indicando o ID do profile a ser obtido. Exemplo:

    /userProfile?id=dce8e961-94b2-4849-a39c-cd65bcf32608
      
Em caso se sucesso, retorna com o perfil do usuário e código HTTP 200.

Obs.: cada usuário só pode obter seu próprio perfil.

## Execução

Para executar o sistema, é necessário o JAVA na versão 11 e maven 3.6.0. 

    $ mvn spring-boot:run

Para parar o sistema, execute CTRL + C.


