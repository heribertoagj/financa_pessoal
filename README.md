# Finança Pessoal — Ambiente e instruções (pt-BR)

Este repositório contém o backend (Spring Boot) e o frontend (Angular) do sistema "Finança Pessoal".
Este README descreve como executar o projeto em desenvolvimento com Docker Compose, como gerar imagens para produção e como usar os perfis (profiles) nas aplicações.

## Estrutura do repositório

- `backend/` — aplicação Java (Spring Boot, Gradle)
- `frontend/` — aplicação Angular
- `docker-compose-dev.yaml` — compose para ambiente de desenvolvimento (hot-reload)
- `docker-compose-prod.yaml` — compose para ambiente de produção (imagens construídas)

---

## Requisitos locais

- Docker e Docker Compose (Docker Desktop no Windows)
- Node.js e npm (apenas se quiser rodar o frontend localmente sem Docker)
- Java (apenas se quiser rodar o backend localmente sem Docker)

---

## Desenvolvimento com Docker (recomendado)

1. Subir o ambiente de desenvolvimento (MySQL, backend com Gradle wrapper e frontend com ng serve):

```powershell
cd C:\git\financa_pessoal
docker compose -f docker-compose-dev.yaml up --build
```

- O `backend` roda usando o Gradle wrapper (`./gradlew bootRun`) dentro do container (hot-reload se você alterar código e tiver dependências configuradas).
- O `frontend` roda com `ng serve` no container e é exposto na porta `4200`.
- O MySQL fica disponível na porta `3316` do host (container: `3306`).

Dicas:
- Se o `backend` não iniciar por falta de ferramentas (ex.: `xargs`), certifique-se de usar o `backend/Dockerfile.dev` que já instala utilitários.
- No Windows, se o `gradlew` não estiver presente no host, a imagem construída já copia o wrapper; garantindo que o script `gradlew` (Unix) exista é útil para containers baseados em Linux.

---

## Perfis / Profiles

O backend usa `spring.profiles.active` para selecionar o profile.
- Dev: `application.properties` (configuração padrão para desenvolvimento)
- Prod: `SPRING_PROFILES_ACTIVE=prod` usado na imagem/prod

No `docker-compose-prod.yaml` o backend já define `SPRING_PROFILES_ACTIVE=prod`. Você também pode setar via variável de ambiente:

```powershell
docker compose -f docker-compose-prod.yaml up --build
# ou
export JWT_SECRET="segredo"
docker compose -f docker-compose-prod.yaml up --build
```

---

## Produção (build das imagens)

Para gerar imagens de produção e subir:

```powershell
cd C:\git\financa_pessoal
docker compose -f docker-compose-prod.yaml up --build -d
```

- O `backend` é construído com Gradle e executa o JAR (profile `prod`).
- O `frontend` é construído em uma imagem Nginx que serve os arquivos estáticos na porta `80`.

---

## Executando apenas localmente sem Docker

### Backend

```powershell
cd backend
# compilar
./gradlew build
# rodar
./gradlew bootRun
```

### Frontend

```powershell
cd frontend
npm install
npm run start
# Acesse http://localhost:4200
```

---

## Variáveis de ambiente importantes

- `SPRING_DATASOURCE_URL` — URL do banco de dados
- `SPRING_DATASOURCE_USERNAME` — usuário do banco
- `SPRING_DATASOURCE_PASSWORD` — senha do banco
- `SPRING_PROFILES_ACTIVE` — profile do Spring (ex: `prod`)
- `JWT_SECRET` — secret para tokens JWT (defina em produção)

Você pode armazenar essas variáveis em um `.env` e referenciar no `docker-compose-prod.yaml` ou setar no ambiente do servidor.

---

## Problemas comuns e soluções rápidas

- Erro `xargs is not available` no container do backend: use o `backend/Dockerfile.dev` (já criado) que instala `findutils`.
- Conflitos de dependências no frontend (npm): alinhe versões do Angular e do `zone.js` no `frontend/package.json` (já foi ajustado para Angular `19.2.15` e `zone.js ~0.15.0`). Em caso de erro, rode `npm ci` (limpa `node_modules` e reinstala com lockfile).
- Permissões em arquivos `gradlew` no Linux: execute `chmod +x gradlew` ou garanta que o Dockerfile defina a permissão.

---

## Próximos passos sugeridos

- Criar pipeline CI (GitHub Actions/GitLab CI) para buildar as imagens e publicar em registry.
- Adicionar um `healthcheck` no `docker-compose-prod.yaml` para o backend.
- Centralizar variáveis sensíveis em um secret manager (ex.: Vault, AWS Secrets Manager) em produção.

---

Se quiser, eu posso:
- Rodar `docker compose -f docker-compose-prod.yaml up --build` e coletar logs caso algo falhe.
- Gerar um `README` menor por serviço (backend/frontend) ou um `CONTRIBUTING.md` com guidelines de desenvolvimento.

Quer que eu faça mais alguma coisa agora? (ex.: rodar o compose de produção ou ajustar o README com mais detalhes)