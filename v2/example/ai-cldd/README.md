# event-admin

Projeto base em Spring Boot para o sistema de administracao de eventos, seguindo o SB-000 e o padrao ai-cldd.

## Perfil padrao

Sem perfil explicitamente definido, a aplicacao sobe em `development`.

Configuracao usada no perfil `development`:

- Banco: `event-manager`
- Host: `localhost`
- Usuario: `root`
- Senha: `root`

## PostgreSQL local com Docker

Comando para subir o PostgreSQL localmente para desenvolvimento:

```bash
docker run --name event-manager-postgres -e POSTGRES_DB=event-manager -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres:17
```

## Configuracao de banco por ambiente

### development

Nao exige variaveis externas. Usa automaticamente:

- `spring.datasource.url=jdbc:postgresql://localhost:5432/event-manager`
- `spring.datasource.username=root`
- `spring.datasource.password=root`

### staging e production

Os perfis `staging` e `production` exigem configuracao externa para banco de dados.

Variaveis aceitas:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Exemplo usando linha de comando:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=staging --DB_URL=jdbc:postgresql://db-host:5432/event-manager --DB_USERNAME=app_user --DB_PASSWORD=strong_password"
```

Exemplo para producao:

```bash
java -jar target/event-adm-0.0.1-SNAPSHOT.jar --spring.profiles.active=production --DB_URL=jdbc:postgresql://db-host:5432/event-manager --DB_USERNAME=app_user --DB_PASSWORD=strong_password
```
