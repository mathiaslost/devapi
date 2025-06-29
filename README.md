# 📦 Teste Prático - API Java
> Este projeto foi desenvolvido como parte de um Teste Prático de Desenvolvimento Java.

## 🚀 Tecnologias Utilizadas no Desenvolvimento

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **Lombok**
- **H2 Database** (banco em memória para testes locais)
- **JUnit 5** (para testes unitários)
- **Mockito** (para simulação de dependências)
- **Maven** (gerenciador de dependências)
- **IntelliJ IDEA Community Edition** (IDE utilizada no desenvolvimento)

## 🎨 Implementação do Front-End
Para a implementação do Front-End, poderão ser utilizadas quaisquer tecnologias que permitam requisições de API, uma vez
que os dados são tratados no formato JSON, não há problemas de compatibilidade.

Abaixo trago algumas tecnologias recomendadas para a implementação:

### React.js
  - ✅ Fácil integração via API
  - ❌ Por não ser um framework e sim uma lib, pode gerar desorganização.

### Angular
  - ✅ Framework completo, com soluções integradas (roteamento, validação, injeção de dependências).
  - ❌ Pode ter uma curva de aprendizado mais alta.

## 📑 Documentação da API no Postman

A documentação completa dos endpoints está disponível no link abaixo.  
Você pode visualizar, testar e importar diretamente para o Postman:

🔗 [Acessar documentação no Postman](https://documenter.getpostman.com/view/22905184/2sB2xFdn8p)

> Inclui exemplos de requisições, headers, bodies e códigos de resposta para facilitar o entendimento da API.


---

## 🧪 Testes Unitários

Os testes foram criados utilizando **JUnit 5** e **Mockito**, focando na camada de serviços da aplicação.

### 🎯 Objetivo dos Testes
O propósito principal dos testes é garantir que as principais regras de negócio da aplicação estejam funcionando corretamente.  
Embora seja possível expandir os cenários de teste, o foco aqui foi em validar os fluxos essenciais de cada funcionalidade.

### 🔍 Cobertura de Testes

#### `UsuarioServiceTest`

- `testBuscaUsuarioPorId`
  > Verifica se o método `findById` retorna corretamente o usuário ao receber um ID válido.

- `testBuscaUsuariosPorNome`
  > Garante que a busca por nome parcial retorna apenas usuários cujos nomes contenham a string informada.

- `testCreateUsuario_EnviaEmailComSucesso`
  > Valida que o usuário é salvo corretamente e que o e-mail é enviado.

- `testCreateUsuario_EnviaEmailFalha`
  > Mesmo que ocorra uma falha no envio do e-mail, o usuário ainda deve ser persistido com sucesso.

#### `EmailServiceTest`

- `testMensagemDeSucessoNoConsole`
  > Como o envio real de e-mails não foi implementado, este teste verifica se a mensagem simulada no console é exibida corretamente.

---

## 📝 Importando o projeto

Siga os passos abaixo para clonar o projeto localmente.

### ✅ Pré-requisitos

Antes de tudo, você precisa ter instalado na sua máquina:

- **Java 17** ou superior
- **Maven 3.8+**
- **Git**
- Uma IDE como **IntelliJ**, **Eclipse**, ou outra de sua preferência

> 💡 O projeto está configurado para usar o **H2 Database (em memória)** por padrão. Não é necessário configurar banco de dados externo para rodar localmente.

### 📥 Clone o repositório

```bash
git clone https://github.com/mathiaslost/devapi.git
```

### 🚀 Rode o projeto
```bash
mvn clean install

mvn spring-boot:run
```

---

## 🙋‍♂️ Autor
Desenvolvido por **Gabriel Mathias**
- 💼 [LinkedIn](https://www.linkedin.com/in/gabriel-mathias)
- 💻 [GitHub](https://github.com/mathiaslost)