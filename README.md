# EventClean API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

Uma API REST robusta e de alta performance para a gestão de eventos, desenvolvida em **Java** com o framework **Spring Boot**. O projeto foi inteiramente desenhado seguindo as diretrizes e princípios da **Clean Architecture**, garantindo o isolamento total das regras de negócio em relação a frameworks, bases de dados, bibliotecas e detalhes de infraestrutura externa.

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework Principal:** Spring Boot 3.x
* **Base de Dados:** PostgreSQL
* **Migrações de Schema:** Flyway
* **Contentorização:** Docker & Docker Compose
* **Gestor de Dependências:** Maven

---

## 🏛️ Arquitetura e Design (Clean Architecture)

A aplicação está estruturada para manter o núcleo (Core/Domain) completamente independente e desacoplado de dependências tecnológicas externas:

* **Domain / Core:** Contém as entidades de negócio puras e as validações fundamentais.
* **Use Cases (Casos de Uso):** Centraliza as regras de aplicação e fluxos específicos (ex: `DeletarEventoCase`, criação de eventos), comunicando-se com o exterior através de interfaces/ports.
* **Adapters / Presenters:** Responsável por converter os dados entre o formato ideal dos casos de uso e os formatos externos (como HTTP/JSON).
* **Infrastructure (Infra):** Implementação concreta de ferramentas e frameworks. Aqui residem as configurações do Spring Boot, os repositórios JPA/PostgreSQL, as migrações do Flyway e o tratamento global de exceções (`ControllerExceptionHandler`).

---

## 🛠️ Como Executar o Projeto

### Pré-requisitos
Certifique-se de que tem instalado na sua máquina:
* [Git](https://git-scm.com)
* [Docker & Docker Compose](https://www.docker.com/)
* [Java 17+](https://adoptium.net/)

### 1. Clonar o Repositório

```bash
git clone [https://github.com/pedro-bibiano/EventClean.git](https://github.com/pedro-bibiano/EventClean.git)
cd EventClean
```
2. Configurar as Variáveis de Ambiente
Crie ou configure um arquivo .env na raiz do projeto com as credenciais de acesso para o banco de dados:
```
POSTGRES_USER=EventCleanAdmin
POSTGRES_PASSWORD=1312
POSTGRES_DB=EventClean
POSTGRES_PORT=5432
```
3. Subir a Infraestrutura com Docker Compose Externo
Para iniciar o banco de dados PostgreSQL configurado num ambiente Docker isolado e persistente, execute:
```
docker compose up -d
```
4. Executar a Aplicação Spring Boot
Com a infraestrutura externa ativa e o banco a rodar, execute a aplicação através do terminal:
```
./mvnw spring-boot:run
```
🛣️ Camada de Endpoints (API REST)
Gestão de Eventos
| Método | Endpoint | Descrição | Status HTTP Esperado |
| :--- | :--- | :--- | :--- |
| `POST` | `/eventos` | Cria um novo evento no sistema. | `201 Created` |
| `DELETE` | `/eventos/{id}` | Remove um evento do sistema utilizando o seu ID (Long). | `200 OK` / `204 No Content` |

🛡️ Tratamento de Exceções (Global Exception Handler)
A API responde com payloads JSON padronizados para garantir consistência em cenários de erro de negócio:

409 Conflict: Retornado quando o identificador do evento já está em uso na base de dados (IdentificadorEventoException).

404 Not Found: Retornado quando o recurso solicitado ou classe de evento mapeada não é encontrada no fluxo de dados (ClassNotFoundException).

📝 Licença
Este projeto está sob a licença MIT. Para mais informações, consulte o arquivo LICENSE.
