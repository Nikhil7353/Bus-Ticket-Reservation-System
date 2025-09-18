# Bus Ticket Reservation Management

Spring Boot + MySQL backend and React + Bootstrap frontend for bus ticket reservations.

## Structure

- `backend/` — Spring Boot REST API (OpenAPI/Swagger, JPA/Hibernate, dev profile with no JWT)
- `frontend/` — React app (customer and admin flows, role‑guarded routes)
- `docs/` — API spec, schema, UX and plan

See `docs/README.md` for details.

## Quick Start

Backend
- Configure MySQL in `backend/src/main/resources/application.properties` (defaults: db `bus_reservation`, user `root`/`root`).
- Run: `mvn spring-boot:run` (Swagger at `http://localhost:8080/swagger-ui.html`).
- Dev profile (`spring.profiles.active=dev`) permits all requests (no JWT) for easy testing.

Frontend
- From `frontend/`, start dev server (e.g., `npm start`). App at `http://localhost:3000`.

## Highlights

- Auth without JWT (dev): `POST /api/v1/auth/{register,login}`; login returns user only.
- Admin setup: create Routes → Buses → Trips. Trip creation auto‑seeds seats from bus layout or capacity.
- Seat locking: holds pessimistically lock seats; checkout confirms and issues ticket.
- Limit: max 5 seats per user per route+date (enforced during hold).

## Admin User

Registration always creates `CUSTOMER`. Seed an admin directly in DB, e.g.:

```sql
INSERT INTO `user` (name,email,phone,password,role)
VALUES ('Admin','admin@bus.local','0000000000','admin123','ADMIN');
```

Or add a one‑time seeder (CommandLineRunner) to create an admin if missing.

