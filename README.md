# RepairLink · App del cliente

![Kotlin](https://img.shields.io/badge/Kotlin-2.4-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-173F4F?logo=jetpackcompose&logoColor=white)
![Hilt](https://img.shields.io/badge/DI-Hilt-2F6F82)
![minSdk](https://img.shields.io/badge/minSdk-26-3E8E6B?logo=android&logoColor=white)
![Estado](https://img.shields.io/badge/estado-MVP-E8752A)

**RepairLink** conecta **talleres mecánicos** con sus **clientes**. El cliente busca taller, pide cita y sigue en todo momento el estado de su coche. El taller gestiona sus citas y los coches que tiene dentro, y va marcando en qué punto está cada reparación.

Este repositorio contiene la **app Android para el cliente**. Los talleres usan una **web en Angular**, y las dos aplicaciones trabajan contra la misma API.

```
App cliente (Android · Kotlin) ─┐
                                ├──► API REST/JSON (Node · Express) ──► Prisma ──► PostgreSQL
Web taller (Angular) ───────────┘
```

Proyecto Intermodular de 2º de DAM.

---

## Repositorios del proyecto

| Repositorio | Qué es | Tecnologías | Rama de integración |
|---|---|---|---|
| [**RepairLink**](https://github.com/Daniag56/RepairLink) *(este)* | App Android del **cliente** | Kotlin · Jetpack Compose · Hilt | `dev` |
| [**RepairLinkWeb**](https://github.com/Daniag56/RepairLinkWeb) | Web del **taller** | Angular 22 · TypeScript 6 · Tailwind CSS 4 | `develop` |
| [**RepairLinkApi**](https://github.com/Daniag56/RepairLinkApi) | API REST que usan las dos | Node.js · Express 5 · TypeScript · Prisma 6 · PostgreSQL 16 | `main` (trabajo en `ANA`) |

### Web del taller (RepairLinkWeb)

Es la parte del taller: gestionar citas, aceptar coches, ir marcando los pasos de la reparación, mandar presupuestos y hablar con los clientes. En `develop` está la **maqueta visual** con Tailwind (SCRUM-51), con estas pantallas ya en el router:

| Zona | Pantallas |
|---|---|
| Acceso | Login, registro (acceso + datos del taller + completado), recuperar contraseña (correo + nueva contraseña) |
| Panel | Panel (citas de hoy, coches en el taller), solicitudes de cita, aceptar coche |
| Agenda | Agenda del taller |
| Coches | Coches en el taller, detalle de la reparación, nuevo presupuesto |
| Clientes | Listado y ficha del cliente |
| Mensajes | Lista de conversaciones y conversación |
| Ajustes | Horario, festivos, vacaciones y promociones (+ nueva promoción) |
| Perfil | Perfil del taller |

Por ahora son solo maquetas: los servicios (`Auth`, `CitasApi`, `MensajesApi`, `AjustesApi`), el guard y el interceptor están creados pero vacíos, los `environments` no tienen la URL de la API y todavía no se usa `HttpClient`.

Para arrancarla (Node.js 24 o superior):

```bash
git clone https://github.com/Daniag56/RepairLinkWeb.git
cd RepairLinkWeb
git checkout develop
npm install
npx ng serve        # http://localhost:4200
```

### API (RepairLinkApi)

API REST en Node.js con Express 5 y TypeScript. Usa Prisma como ORM contra PostgreSQL 16, que se levanta con Docker Compose, y Zod para validar las peticiones. Las contraseñas se guardan con bcrypt y la autenticación va con JWT.

Ahora mismo es la **base del proyecto**: tiene usuarios y autenticación, pero todavía no los modelos de RepairLink (talleres, coches, citas, reparaciones, mensajes). En Prisma solo existen `User` y `Task`.

| Endpoint | Qué hace |
|---|---|
| `GET /health` | Comprobar que la API está viva |
| `POST /api/auth/register` · `POST /api/auth/login` | Registro e inicio de sesión (devuelve un JWT) |
| `GET/PATCH/DELETE /api/users…` | Usuarios. En `ANA` también `GET/PATCH /api/users/me` y `PATCH /api/users/me/password`, protegidos con JWT |
| `/api/tasks` | CRUD de ejemplo de tareas |

La rama `ANA` va por delante de `main`. Añade middleware JWT, límite de peticiones, logs con Winston, documentación Swagger en `/api-docs`, tests con Jest + Supertest, `Dockerfile` y `docker-compose.prod.yml`.

Para arrancarla:

```bash
git clone https://github.com/Daniag56/RepairLinkApi.git
cd RepairLinkApi
npm install
cp .env.example .env
docker-compose up -d          # PostgreSQL en el puerto 5432
npx prisma migrate dev
npm run dev                   # http://localhost:3000
```

> Para probar la API desde el emulador de Android, `localhost` es el propio emulador: la API del ordenador está en `http://10.0.2.2:3000`.

---

## Pantallas

Por ahora todas las pantallas son **solo interfaz**: los datos son de ejemplo y están escritos en los ViewModels o en la propia pantalla. Todavía no hay API ni base de datos.

| Pantalla | Qué hace | Estado |
|---|---|---|
| Bienvenida (`LoginScreen`) | Logo, lema y botón *Iniciar sesión*. Es la pantalla de inicio | ✅ Enlazada |
| Inicio de sesión (`InicioSesionScreen`) | Correo, contraseña y enlace a *Regístrate* | 🟡 Hecha, falta enlazarla |
| Mis coches | Aviso de la cita solicitada y lista de coches del cliente con su taller, estado (*En reparación*, *En revisión*, *Listo para recoger*) y paso de la reparación (5/6) | ✅ Enlazada |
| Talleres | Buscador, filtros (*Todos*, *Mecánica*, *Neumáticos*) y talleres cercanos con su próxima hora libre | ✅ Enlazada desde la barra inferior (*Ver calendario* sin acción) |
| Ficha del taller | Valoración, distancia, servicios, oferta, horario, opiniones y botón *Reservar cita* | ✅ Enlazada |
| Elegir día | Calendario del mes con días libres, con pocas horas, completos o no disponibles | ✅ Enlazada |
| Elegir hora | Horas libres del día, motivo de la visita, vehículo y comentario para el taller | ✅ Enlazada |
| Cita solicitada | Resumen de la cita (taller, día, hora, vehículo, motivo), pendiente de confirmar por el taller | ✅ Enlazada (*Ver mis citas* sin acción) |
| Seguimiento | Línea temporal con los pasos de la reparación, pestañas *Estado* / *Chat* y botón *Escribir al taller* | 🟡 Hecha, falta enlazarla |
| Mis reparaciones (`ReparacionesScreen`) | Versión anterior de *Mis coches*, con barra de progreso | 🟡 En la navegación, pero no se llega a ella |
| Inicio (`HomeScreen`) | Menú provisional | 🟡 En la navegación, pero no se llega a ella |

### Pendiente

- Perfil: la ruta existe en `Routes`, pero la pantalla no.
- Registro.
- Añadir coche (*+ Añadir*) y vincularlo con el código de 6 caracteres que genera el taller.
- Chat con el taller.
- Enlazar *Seguimiento* desde *Mis coches* e *Inicio de sesión* desde la bienvenida.
- Que los botones de hora, motivo y filtro se puedan seleccionar (ahora la selección es fija).
- Conectar con la API, Room, DataStore y pasar el estado a StateFlow.

### Navegación actual

```
Bienvenida ──► Mis coches ──► Ficha del taller ──► Elegir día ──► Elegir hora ──► Cita solicitada
                   ▲                                                                   │
                   └──────────────────────── Volver al inicio ─────────────────────────┘

Barra inferior (Mis coches · Talleres):   Talleres ◄──► Mis coches       Perfil: sin pantalla
```

Las rutas `home` (`HomeScreen`) y `reparaciones` (`ReparacionesScreen`) están en `AppNavHost`, pero ninguna pantalla navega hasta ellas.

---

## Tecnologías

| Para qué | Qué se usa |
|---|---|
| Lenguaje | Kotlin 2.4.21 |
| Interfaz | Jetpack Compose (BOM 2026.09.00) + Material 3 |
| Navegación | Navigation Compose 2.10.2 (rutas en una `sealed class` con kotlinx.serialization 1.12.0) |
| Inyección de dependencias | Hilt 2.60.1 (con KSP 2.3.12) + hilt-navigation-compose 1.4.0 |
| Estado | ViewModel (`@HiltViewModel`) con datos de ejemplo; StateFlow *(pendiente)* |
| Persistencia local | Room *(pendiente)* |
| Red | Retrofit o Ktor contra la API propia *(pendiente)* |
| Preferencias | DataStore *(pendiente)* |

- Android Gradle Plugin 9.4.1 · Java 11
- `minSdk 26` (Android 8.0) · `targetSdk 37` · `compileSdk 37`
- Paquete: `com.revup`

---

## Estructura

```
app/src/main/java/com/revup/
├── MainApplication.kt              @HiltAndroidApp
├── MainActivity.kt                 @AndroidEntryPoint, carga AppNavHost
├── core/
│   ├── di/                         DatabaseModule, NetworkModule (vacíos de momento)
│   ├── navigation/                 AppNavHost, Routes
│   └── ui/
│       ├── components/             BarraInferior (Talleres · Mis coches · Perfil)
│       └── theme/                  colores, tipografía y tema
└── features/
    ├── auth/                       LoginScreen (bienvenida)
    ├── iniciosesion/               InicioSesionScreen (correo y contraseña)
    ├── miscoches/                  MisCochesScreen + CocheUi
    ├── talleres/                   TalleresScreen
    ├── fichataller/                FichaTallerScreen
    ├── elegirdia/                  ElegirDiaScreen + DiaUi (calendario)
    ├── elegirhora/                 ElegirHoraScreen + HoraUi
    ├── citasolicitada/             CitaSolicitadaScreen
    ├── seguimiento/                SeguimientoScreen
    ├── reparaciones/               ReparacionesScreen (versión anterior de Mis coches)
    ├── home/                       HomeScreen (menú provisional)
    ├── panel/                      PanelScreen         ⚠ del taller, ver notas
    └── aceptarcoche/               AceptarCocheScreen  ⚠ del taller, ver notas
```

Cada funcionalidad tiene su propio paquete dentro de `features/<nombre>/presentation/`, con su pantalla y su ViewModel. Lo que se comparte entre funcionalidades (navegación, tema, componentes, inyección de dependencias y, más adelante, datos en `core/data/`) va en `core/`.

Las pantallas siguen este patrón:

- `XxxScreen` recibe el ViewModel con `hiltViewModel()` y le pasa los datos a `XxxContent`.
- `XxxContent` solo pinta lo que recibe. Es la que se usa en el `@Preview`.
- Los datos tienen su propia clase `…Ui` (`CocheUi`, `DiaUi`, `HoraUi`). Así, cuando llegue la API, solo cambia el ViewModel y la pantalla se queda igual.

Las pantallas no conocen el `NavController`: reciben callbacks (`onVerTaller`, `onReservarCita`, `onVolver`…) y es `AppNavHost` quien decide a dónde se navega.

### Notas sobre el estado actual

- `panel` y `aceptarcoche` son pantallas del **taller**, de cuando se pensaba hacerlo también en Android. Esa parte la cubre ahora la web en Angular. No están en la navegación (`AppNavHost` importa `PanelScreen`, pero no la usa) y se pueden quitar.
- `ReparacionesScreen` y `HomeScreen` hacen lo mismo que *Mis coches* con su barra inferior. Hay que decidir si se borran.
- `HomeScreen` y `ReparacionesScreen` todavía dibujan su propia `NavigationBar` en lugar de usar `BarraInferior`.
- `LoginScreen`, `InicioSesionScreen` y `SeguimientoScreen` crean el ViewModel con `remember { … }` en lugar de con `hiltViewModel()`. `InicioSesionViewModel`, `SeguimientoViewModel`, `HomeViewModel` y `ReparacionesViewModel` todavía no extienden `ViewModel`, y `TalleresScreen` no tiene ViewModel.

---

## Cómo ejecutarlo

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Daniag56/RepairLink.git
   ```
2. Abrir la carpeta en **Android Studio** y esperar a que termine el *Gradle Sync*.
3. Elegir un emulador o un móvil con **Android 8.0 o superior**.
4. Pulsar **Run ▶**.

---

## Cómo trabajamos

```
main  ←  dev  ←  SCRUM-75-tres-pantallas-reserva
               ←  SCRUM-80-pantalla-perfil
               ←  …
```

- **`main`**: lo que se entrega. Solo llega aquí lo que funciona entero en `dev`.
- **`dev`**: rama de integración.
- **Una rama por cada tarea de Jira**: cada tarea se hace en su propia rama, que sale de `dev` y se llama con el código de la tarea y una descripción corta en minúsculas con guiones (`SCRUM-<número>-<descripcion>`). Cuando la tarea está terminada se abre un Pull Request a `dev`, y la rama se borra después del merge.

Para empezar una tarea:

```bash
git checkout dev
git pull origin dev
git checkout -b SCRUM-75-tres-pantallas-reserva
```

Al terminarla:

```bash
git push -u origin SCRUM-75-tres-pantallas-reserva
# y abrir el Pull Request a dev en GitHub
```

Si mientras tanto los demás han subido cambios a `dev`, tráelos a tu rama con:

```bash
git pull origin dev
```

Pon el código de la tarea en los commits (por ejemplo, `SCRUM-75 Pantallas de reserva…`) para que Jira los enlace con la tarea.

Las tareas se organizan con Scrum en **Jira** (proyecto *EquipoRL*, estimación en story points) y el diseño está en **Figma** (móvil 390 × 844, tipografía Inter).

### Paleta

Colores del diseño en Figma.

| Color | Hex | Uso |
|---|---|---|
| Azul petróleo | `#173F4F` | Principal |
| Azul verdoso | `#2F6F82` | Secundario |
| Gris azulado | `#B8C9CC` | Textos suaves |
| Naranja | `#E8752A` | Solo para destacar |
| Blanco roto | `#F4F6F5` | Fondo |
| Gris oscuro | `#20282B` | Texto |
| Verde | `#3E8E6B` | Estados correctos |

> Ahora mismo el tema (`Theme.kt`) usa un esquema oscuro: fondo `#143F50`, primario naranja `#EB7A28` y secundario `#1E88E5`. Algunos colores del diseño (`Secondary` `#2F6F82`, `Bg` `#F4F6F5`, `Ink` `#20282B`, `Success` `#3E8E6B`…) ya están en `RepairLinkColors`, pero el tema todavía no los usa. El azul petróleo, el naranja y el gris de `Color.kt` tienen valores algo distintos a los de Figma (`#143F50`, `#EB7A28`, `#C8D1D5`). La tipografía tampoco es Inter todavía: se usa la fuente por defecto.

---

## Equipo

| | Rol |
|---|---|
| **Daniel Arias García** | Desarrollo |
| **Ana Isabel Sánchez Fernández** | Desarrollo |
| **Sergio Rodríguez Espinosa** | Desarrollo · Scrum Master |
