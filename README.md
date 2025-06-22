# Energy Community System – Semesterprojekt DISYS (FHTW SS2025)

### Projektbeschreibung

In diesem Semesterprojekt wurde ein verteiltes System zur Simulation einer **Energie-Community** entwickelt.  
Ziel ist es, den Energiefluss zwischen **Produzenten**, **Verbrauchern** und dem **öffentlichen Stromnetz** über eine Message Queue zu koordinieren und die Daten anschließend über eine REST API und grafische Oberfläche bereitzustellen.

Die Community besteht aus mehreren Komponenten, die eigenständig Nachrichten austauschen und den aktuellen Energieverbrauch und -produktion dynamisch abbilden.

---

### Verwendete Technologien

- **Spring Boot** (Services & REST API)
- **RabbitMQ** (Message Queue)
- **PostgreSQL** (Datenhaltung)
- **JavaFX** (Benutzeroberfläche)
- **Docker** (Containerisierung)
- **JUnit** (Unit-Tests)
- **Wetter-API** (für realistische Produktionswerte – optional geplant)

---

### Komponentenübersicht

#### Community Energy Producer
- Simuliert die Energieproduktion anhand von Wetterdaten und sendet PRODUCER-Nachrichten an die Queue.
- Nachricht enthält: `kwh`, `datetime`, `type: PRODUCER`, `association: COMMUNITY`

#### Community Energy User
- Simuliert den Energieverbrauch von Community-Mitgliedern.
- Sendet USER-Nachrichten an die Queue.
- Spitzenverbrauch zu typischen Tageszeiten (morgens/abends).

#### Usage Service
- Empfängt PRODUCER- und USER-Nachrichten.
- Berechnet stündlich:
    - community_produced
    - community_used
    - grid_used
- Speichert Ergebnisse in der Tabelle `usage_data`.

#### Current Percentage Service
- Reagiert auf Usage-Updates.
- Berechnet:
    - Anteil verbrauchter Community-Energie (`community_depleted`)
    - Anteil Stromnetzbezug (`grid_portion`)
- Ergebnisse werden in der Tabelle `current_percentage` abgelegt.

#### REST API
- Bereitstellung aktueller und historischer Daten:
    - `GET /energy/current`
    - `GET /energy/historical?start=...&end=...`

#### JavaFX GUI 
- Visualisiert die aktuellen und historischen Energieverteilungen (Chart- und Tabellenansichten).
- Konsumiert die REST API.

---

### Tests

### Unit Tests

Für folgende Komponenten wurden Unit-Tests geschrieben:

- *energy-user*
  - EnergyUsageServiceTest → prüft das Versenden von USER-Nachrichten

- *usage-service*
  - UsageControllerTest → prüft REST-Endpoints
  - UsageMessageHandlerTest → prüft Verarbeitung von PRODUCER/USER-Nachrichten
  - MyRabbitSenderTest → prüft das Versenden von Update-Nachrichten

---

### Entwicklungsstand

- Alle Kernkomponenten erfolgreich implementiert und getestet.
- Austausch über RabbitMQ funktioniert zuverlässig.
- REST API liefert korrekte Ergebnisse.
- GUI-Entwicklung zeigt die aktuellen und historischen Energieverteilungen.

---


