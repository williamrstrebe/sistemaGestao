# DesafioPublica


1. INTRODUÇÃO
>>>Obs: com pressa? As instruções de uso estão no tópico três!<<<


Seja bem vindo!

Este repositório foi criado como parte dos requisitos da seletiva para a empresa Pública,
de Blumenau-SC.

Este pequeno projeto, em desenvolvimento, foi minha primeira interação com o GitHub (e todas
outras ferramentas envolvidas) e o primeiro projeto a ser começado depois de regressar à área
de programação. Servirá como plataforma de testes e comparação de desenvolvimento posterior.


2. FERRAMENTAS UTILIZADAS

Neste projeto foram utilizadas as seguintes bibliotecas (ou seja lá como se chama isso hoje em dia):

O projeto foi gerado para ser gerenciado pela ferramenta Maven, tendo suas dependências iniciais carregadas 
através do facilitador em https://start.spring.io

Utilizando as tags de dependência do supracitado Maven, a aplicação trabalho com um banco embutido, o H2 (https://h2.database.com)

Spring Framework - Spring Boot / Spring Data / Spring Security : gerenciando o projeto com o Spring e seus módulos de  framework (Spring MVC, Spring Webflux). Através principalmente de anotações este framework permite focar mais na lógica necessária a ser desenvolvida e deixar que o Spring gerencie injeções de dependência, Data Access (DAO, JDBC, etc), entre muitas outras coisas. Em https://spring.io/projects você encontra mais detalhes sobre a ferramenta.

Thymeleaf: ferramenta que gerencia templates para Java, alternativa para JSP. Nas classes controle reduz o trabalho necessário para receber e direcionar requisições. Veja mais em https://thymeleaf.org.

Bootstrap: ferramenta com inúmeras opções para personalização e criação de design responsivo, plugins javascript e muitos componenentes integrados. Mais detalhes em https://getbootstrap.com



3. INSTRUÇÕES DE USO


Basta baixar a release mais atual e executar!

Executando o . jar, basta acessar um navegador utilizando localhost:8080/home.
As credenciais de acesso são root/root.

Obs: o banco está configurado para não guardar informações em memória, logo, todos jogos cadastrados serão apagados quando o executável for encerrado (pelo gerenciador). Salvar em memória é configurável, mas entendi por desnecessário agora persistir dados de jogos.
