# RepairLink · App del taller

![Kotlin](https://img.shields.io/badge/Kotlin-2.4-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-173F4F?logo=jetpackcompose&logoColor=white)
![Hilt](https://img.shields.io/badge/DI-Hilt-2F6F82)
![minSdk](https://img.shields.io/badge/minSdk-26-3E8E6B?logo=android&logoColor=white)
![Estado](https://img.shields.io/badge/estado-MVP-E8752A)

**RepairLink** conecta **talleres mecánicos** con sus **clientes**. El cliente busca taller, pide cita y sigue en todo momento el estado de su coche. El taller gestiona sus citas y los coches que tiene dentro, y va marcando en qué punto está cada reparación.

Este repositorio contiene la **app Android para el taller**. Los clientes usan una web en Angular, y las dos aplicaciones trabajan contra la misma API.

```
Web cliente (Angular) ─┐
                       ├──► API REST/JSON ──► ORM ──► PostgreSQL
App taller (Kotlin) ───┘
```

Proyecto Intermodular de 2º de DAM.

---

## Qué puede hacer el taller

| Pantalla | Descripción | Estado |
|---|---|---|
| Login | Acceso del taller a la app | ✅ Interfaz hecha |
| Panel | Citas de hoy, coches en el taller y contadores (citas, en taller, por aceptar) | ✅ Interfaz hecha |
| Aceptar coche | Aceptar el coche de un cliente y generar un código de 6 caracteres para vincularlo | ✅ Interfaz hecha |
| Estado de la reparación | Ir marcando cada paso de la reparación; el cliente lo ve en su línea temporal | 🔜 Pendiente |
| Chat con el cliente | Mensajes entre taller y cliente sobre cada coche | 🔜 Pendiente |

---

## Tecnologías

| Para qué | Qué se usa |
|---|---|
| Lenguaje | Kotlin 2.4.21 |
| Interfaz | Jetpack Compose (BOM 2026.09.00) + Material 3 |
| Navegación | Navigation Compose 2.10.2 + kotlinx.serialization |
| Inyección de dependencias | Hilt 2.60.1 (con KSP) + hilt-navigation-compose |
| Estado | ViewModel (`@HiltViewModel`); StateFlow *(pendiente)* |
| Persistencia local | Room *(pendiente)* |
| Red | Retrofit o Ktor contra la API propia *(pendiente)* |
| Preferencias | DataStore *(pendiente)* |

- `minSdk 26` (Android 8.0) · `targetSdk 37`
- Paquete: `com.revup`

---

## Estructura

```
app/src/main/java/com/revup/
├── MainApplication.kt          @HiltAndroidApp
├── MainActivity.kt             @AndroidEntryPoint
├── core/
│   ├── data/                   local (Room), remote (API), repository
│   ├── di/                     DatabaseModule, NetworkModule
│   ├── navigation/             AppNavHost, Routes
│   └── ui/theme/               colores, tipografía y tema
└── features/
    ├── auth/presentation/      LoginScreen + LoginViewModel
    ├── panel/presentation/     PanelScreen + PanelViewModel
    └── aceptarcoche/presentation/  AceptarCocheScreen + AceptarCocheViewModel
```

Cada funcionalidad tiene su propio paquete dentro de `features/` con su pantalla y su `@HiltViewModel`. Lo que se comparte entre funcionalidades (navegación, tema, inyección de dependencias, datos) va en `core/`.

Las pantallas no conocen el `NavController`: reciben callbacks (`onAceptarCoche`, `onVolver`…) y es `AppNavHost` quien decide a dónde se navega.

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
main  ←  dev  ←  sergio / ana / dani
```

- **`main`**: lo que se entrega. Solo llega aquí lo que funciona entero en `dev`.
- **`dev`**: rama de integración.
- **Una rama por persona**: cada uno trabaja en la suya y abre un Pull Request a `dev`.

Para traer lo último de los demás a tu rama:

```bash
git pull origin dev
```

Las tareas se organizan con Scrum en **Jira** (proyecto *EquipoRL*, estimación en story points) y el diseño está en **Figma** (móvil 390 × 844, tipografía Inter).

### Paleta

| Color | Hex | Uso |
|---|---|---|
| Azul petróleo | `#173F4F` | Principal |
| Azul verdoso | `#2F6F82` | Secundario |
| Gris azulado | `#B8C9CC` | Textos suaves |
| Naranja | `#E8752A` | Solo para destacar |
| Blanco roto | `#F4F6F5` | Fondo |
| Gris oscuro | `#20282B` | Texto |
| Verde | `#3E8E6B` | Estados correctos |

---

## Equipo

| | Rol |
|---|---|
| **Daniel Arias García** | Desarrollo |
| **Ana Isabel Sánchez Fernández** | Desarrollo |
| **Sergio Rodríguez Espinosa** | Desarrollo · Scrum Master |
