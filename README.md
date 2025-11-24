# EDA - Event Driven Architecture

Projeto para estudo da arquitetura orientada a eventos.

## Principais endereços

Para acessar os containers:

- PgAdmin4
  - http://172.28.1.12/login
- UI for Apache Kafka
  - http://172.28.0.20:8080/
- Prometheus
  - http://172.28.2.12:9090/

# Para uso no desenvolvimento

## Padrão de commits

Este guia detalha como configurar Husky (para hooks de Git) no seu projeto Spring Boot baseado em Maven.
Essa ferramenta, baseada em Node.js, ajuda a automatizar a qualidade de código de release do projeto Java.

As configurações já estão no `package.json`, basta rodar os comandos:

- Preparar o husky no início do projeto: `npm run prepare`.

Agora, os commits seguirão o template do Hasky.

## Ciclo de Release no Java/Maven

O standard-version automatiza o versionamento e a geração do CHANGELOG.md com base em commits seguindo o Conventional Commits.

O standard-version atualizará o package.json automaticamente, mas não atualizará o <version> no seu pom.xml. Você precisará usar o Maven para isso.

Passos Manuais Envolvidos:

1. Fazer os Commits: Com commits seguindo o padrão (ex: feat: add new endpoint).
2. Rodar o standard-version: `npm run release`.

Isso fará:
- Incremento da versão no package.json.
- Criação/Atualização do CHANGELOG.md.
- Criação de um commit com as mudanças (ex: chore(release): 1.0.0).
- Criação da tag Git (ex: v1.0.0).

3. Atualizar o pom.xml: Você deve manualmente (ou com um plugin Maven como o versions-maven-plugin) sincronizar a nova versão do pom.xml com a versão gerada pelo standard-version: `mvn versions:set -DnewVersion=1.0.0 -DgenerateBackupPoms=false`



## Formatando a classe o VSCODE

Para usar o formatter no VSCODE:

- Abra as Configurações do VS Code `(Ctrl + ,)`.
- Pesquise por java.format.settings.url.
- Defina o caminho para o arquivo que você acabou de criar. Se estiver na raiz do projeto:
 - "java.format.settings.url": `${workspaceFolder}/config/fomatter/java-formatter.xml`

## Usando as tasks do vscode

Par ao vscode, está na pasta `.vscode` algumas task:

- Use o comando do VS Code `(Ctrl + shift + P)`.
- Usar `Task: Run Task`.
- Selecione a ação.