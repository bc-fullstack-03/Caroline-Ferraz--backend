<h1 align="center">🐦 Parrot (backend) 🐦</h1>

<h2>📝 Descrição:</h2>
<p>Repositório destinado ao versionamento e compartilhamento dos códigos referentes ao backend da aplicação <b>Parrot</b>, feita durante o bootcamp de desenvolvimento full stack promovido pela Sysmap Solutions.</p>

<h2>🎯 Objetivos:</h2>
<p>O objetivo central do projeto foi desenvolver uma solução funcional para o backend de um Blog, que atendesse a um <a href="https://www.figma.com/file/vepLgESqoFwshCzJ5lqPkn/bootcamp-2?node-id=0-1&t=KmvWdbESuj29VN4r-0">modelo figma</a> pré-estabelecido e que aplicasse o máximo possível dos princípios/conceitos de desenvolvimento de software abordados durante as aulas. Dentre os requisitos mínimos, foram solicitados a implementação de autenticação de usuário com token JWT, o upload de fotos utilizando o LocalStack, a documentação da API utilizando o Swagger e que ao final fosse utilizado um arquivo docker-compose.yml para orquestrar os serviços utilizados e subir a imagem da aplicação para o Docker Hub.</P>

<h2>🛠 Tecnologias utilizadas:</h2>
<p align="center">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" height="70px"/> - 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" height="75px"/> - 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original-wordmark.svg" height="70px"/> - 
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/IntelliJ_IDEA_Icon.svg/2048px-IntelliJ_IDEA_Icon.svg.png" height="65px"/> - 
<img src="https://i.imgur.com/bKBldW2.png" height="65px"/> - 
<img src="https://static1.smartbear.co/swagger/media/images/logos/swagger_icon_clr.svg?ext=.svg" height="65px"/> - 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original-wordmark.svg" height="70px"/> - 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/ubuntu/ubuntu-plain.svg" height="65px"/> - 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/windows8/windows8-original.svg" height="60px"/>
</p>

<h2>👨‍💻 Utilizando a Aplicação:</h2>
<h4>• Tendo o Docker instalado, crie um arquivo <u>docker-compose.yml</u> em uma pasta vazia e cole no arquivo o seguinte conteúdo:</h4>
<p align="center">
<pre>
version: "3"
services:
    mongodb:
        image: mongo:latest
        container_name: "mongo_parrot"
        ports:
            - "27070:27017"
    localstack:
        image: localstack/localstack
        container_name: localstack_parrot
        ports:
            - "4566:4566"
            - "4510-4559:4510-4559"
            - "8055:8080"
        environment:
            - SERVICES=s3
            - DEBUG=1
            - DATA_DIR=/tmp/localstack/data
        volumes:
            - ./tmp/localstack:/tmp/localstack
            - /var/run/docker.sock:/var/run/docker.sock
    parrot_backend:
        image: carolineferraz/parrot_backend:latest
        build: .
        container_name: parrot_backend
        ports:
            - "8082:8082"
</pre>
</p>
<h4>• Em seguida abra o terminal na pasta que contém o arquivo e digite o seguinte comando:</h4>
<p align="center"><pre>docker compose up -d</pre></p>
<h4>• Agora que os contêineres das imagens <u>carolineferraz/parrot_backend</u>, <u>mongo</u> e <u>localstack/localstack</u> estão rodando localmente com as configurações do arquivo docker-compose.yml, já é possível utilizar a aplicação.</h4>
<h4>• Para utilizar a aplicação utilizando o client do Swagger e acessar a documentação dos endpoints, acesse a seguinte URL no navegador:</h4>
<p align="center">🔗 <a href="http://localhost:8082/swagger-ui/swagger-ui/index.html">http://localhost:8082/swagger-ui/swagger-ui/index.html</a> 🔗</p>
<h4>• Para passar informações no header da requisição, como o token, pode-se consumir os endpoints da API via Postman:</h4>
<p align="center">
<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/95757534/237135008-cb46a3a3-615b-4f9c-90f6-c6dd1ee44183.gif" width="100%">
</p>