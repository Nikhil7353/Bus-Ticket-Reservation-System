# Bus Ticket Reservation Management

Spring Boot + MySQL backend and React + Bootstrap frontend for bus ticket reservations.

## Structure

- `backend/` — Spring Boot REST API (OpenAPI/Swagger, JPA/Hibernate, dev profile with no JWT)
- `frontend/` — React app (customer and admin flows, role‑guarded routes)
- `docs/` — API spec, schema, UX and plan

See `docs/README.md` for details.

## Tech Stack

- Backend: Spring Boot 3, Spring Web, Spring Data JPA (Hibernate), MySQL, HikariCP, Swagger/OpenAPI
- Frontend: React 18, React Router 6, Axios, Bootstrap 5, Webpack/Babel toolchain
- Tooling: Maven 3.9+, Node.js/npm, MySQL 8+, Java 17

## Prerequisites

- Java 17 (JDK) and Maven installed and on your `PATH`
- Node.js 18+ with npm
- MySQL server running locally (or update the JDBC URL)
- Optional: IDEs such as IntelliJ/VS Code and Docker if you plan to containerize later

## Quick Start

Backend
1. Update `backend/src/main/resources/application.properties` if your MySQL username/password differ (defaults: `root` / `root`, DB `bus_reservation`; it will auto-create the schema).
2. From `backend/` run:
   ```bash
   mvn spring-boot:run
   ```
   The API starts on `http://localhost:8080` with Swagger UI at `/swagger-ui.html`. The `dev` profile (default) disables authentication for easier testing.
3. To build or run tests:
   ```bash
   mvn clean install
   mvn test
   ```

Frontend
1. From `frontend/` install dependencies (first run only):
   ```bash
   npm install
   ```
2. Start the dev server:
   ```bash
   npm start
   ```
   The UI runs on `http://localhost:3000` and proxies `/api` calls to the backend.
3. To build a production bundle:
   ```bash
   npm run build
   ```

## Highlights

- Auth without JWT (dev): `POST /api/v1/auth/{register,login}`; login returns user only.
- Admin setup: create Routes → Buses → Trips. Trip creation auto‑seeds seats from bus layout or capacity.
- Seat locking: holds pessimistically lock seats; checkout confirms and issues ticket.
- Limit: max 5 seats per user per route+date (enforced during hold).

## Admin User

Registration always creates `CUSTOMER`. For local/dev runs an admin user is seeded automatically via `AdminSeeder` (profile `dev`):

- Email: `admin@bus.local`
- Password: `admin123`

Override these with the `app.admin.*` properties in `backend/src/main/resources/application.properties`, or disable seeding by setting `app.admin.seed=false`. In other environments you can still seed manually or add your own provisioning logic.

