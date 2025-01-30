# Willimath API

Ce projet est une API REST développée avec Spring Boot en Java et Maven. La base de données est déployée via Docker à l'aide d'un fichier `docker-compose`.

---

## Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre machine :

- **Java 21** ou une version compatible
- **Docker** et **Docker Compose**

---

## Installation et Configuration



1. **Démarrer la base de données avec Docker :**

   Exécutez :

   ```bash
   docker compose -f docker/postgresql.yml up -d
   ```

   Cela lancera la base de données définie dans le fichier `postgresql.yml`.

2. **Construire et exécuter l'API :**

   Utilisez Maven pour construire le projet et démarrer l'application :

   ```bash
   ./mvn.cmd clean install
   ./mvn.cmd spring-boot:run
   ```

---

## Utilisation

L'API est accessible à l'adresse suivante une fois démarrée : `http://localhost:8080`.

### Endpoints disponibles

Vous pouvez accéder aux endpoints et à la documentation de l'API à l'adresse suivante : `http://localhost:8080/swagger-ui/index.html`.
