# 💬 Chat em Tempo Real - Backend

Servidor que permite comunicação em tempo real entre usuários através de WebSocket.

## 🛠️ Tecnologias

- Java 21
- Spring Boot 3.5.8
- Spring WebSocket
- Maven

## 📝 Pré-requisitos

- Java 21 instalado
- Maven instalado

## 🚀 Como rodar

**Opção 1: Usando Maven**
```bash
mvn spring-boot:run
```

**Opção 2: Gerando JAR**
```bash
mvn clean package
java -jar target/chat-0.0.1-SNAPSHOT.jar
```

O servidor iniciará em: `http://localhost:8080`

## 📂 Estrutura do Projeto

```
src/main/java/br/com/sistema/chat/
├── Startup.java                      # Classe principal
├── config/
│   └── WebSocketConfig.java          # Configuração do WebSocket
├── controller/
│   └── ChatController.java           # Controlador de mensagens
└── model/
    └── ChatMessage.java              # Modelo de dados
```

## 💡 Como Funciona

1. Cliente conecta no endpoint `/ws`
2. Cliente se inscreve em uma sala `/topic/chat/{nomeDaSala}`
3. Cliente envia mensagem para `/app/chat.send/{nomeDaSala}`
4. Servidor adiciona timestamp e envia para todos da sala
5. Todos os clientes inscritos recebem a mensagem

## 🔌 Endpoints WebSocket

| Endpoint | Tipo | Descrição |
|----------|------|-----------|
| `/ws` | Conexão | Endpoint para conectar via SockJS |
| `/app/chat.send/{room}` | Enviar | Envia mensagem para uma sala |
| `/topic/chat/{room}` | Receber | Recebe mensagens de uma sala |

## 📋 Modelo de Dados

### ChatMessage
```java
{
  "from": "string",           // Nome do usuário
  "content": "string",        // Conteúdo da mensagem
  "timestamp": "LocalDateTime" // Data/hora (gerada pelo servidor)
}
```

**Exemplo:**
```json
{
  "from": "João",
  "content": "Olá pessoal!",
  "timestamp": "2024-12-17T18:30:00"
}
```

## ⚙️ Configurações

### application.properties
```properties
spring.application.name=Chat
```

### WebSocket
- **Prefixo de aplicação**: `/app`
- **Brokers habilitados**: `/topic`, `/queue`
- **CORS**: Aceita requisições de qualquer origem (`*`)

## 🔐 CORS

Atualmente configurado para aceitar requisições de qualquer origem. Para produção, recomenda-se criar um arquivo `CorsConfig.java`:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
```

## 🧪 Testando

### Teste de Contexto
O projeto inclui um teste básico em `StartupTests.java` que verifica se o contexto Spring carrega corretamente.

```bash
mvn test
```

## ❗ Solução de Problemas

**Porta 8080 já está em uso?**
- Adicione no `application.properties`:
```properties
server.port=8081
```

**Cliente não conecta?**
- Verifique se o servidor está rodando
- Confirme que o firewall não está bloqueando a porta 8080
- Veja os logs no console para mensagens de erro

**Mensagens não chegam?**
- Verifique se o cliente está inscrito no tópico correto
- Confirme que está enviando para a sala certa
- Veja os logs do STOMP no console

## 🐳 Docker (Opcional)

**Dockerfile:**
```dockerfile
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/chat-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

**Comandos:**
```bash
mvn clean package
docker build -t chat-backend .
docker run -p 8080:8080 chat-backend
```

## 📦 Dependências

```xml
<!-- API REST -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- WebSocket -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>

<!-- Validação -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- Testes -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

## 📊 Informações do Projeto

- **Group ID**: `br.com.sistema`
- **Artifact ID**: `chat`
- **Version**: `0.0.1-SNAPSHOT`
- **Nome**: Chat
- **Descrição**: Projeto de estudo - Chat utilizando websocket

## 🚀 Build para Produção

```bash
# Build sem rodar testes
mvn clean package -DskipTests

# O JAR será gerado em:
target/chat-0.0.1-SNAPSHOT.jar
```

---

Desenvolvido com ❤️ usando Spring Boot