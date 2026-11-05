# RepairLink · App Android

![Kotlin](https://img.shields.io/badge/Kotlin-2.4-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-173F4F?logo=jetpackcompose&logoColor=white)
![Hilt](https://img.shields.io/badge/DI-Hilt-2F6F82)
![minSdk](https://img.shields.io/badge/minSdk-26-3E8E6B?logo=android&logoColor=white)
![Estado](https://img.shields.io/badge/estado-MVP-E8752A)

**RepairLink** conecta **talleres mecánicos** con sus **clientes**. El cliente busca taller, pide cita y sigue en todo momento el estado de su coche. El taller gestiona sus citas y los coches que tiene dentro, y va marcando en qué punto está cada reparación.

Este repositorio contiene la **app Android**. Ahora mismo tiene sobre todo las pantallas del **cliente** (sus coches, buscar taller, calendario del taller y seguimiento de la reparación) y las primeras del **taller** (panel y aceptar coche). Todas las aplicaciones trabajan contra la misma API.

```
Web (Angular) ─────┐
                   ├──► API REST/JSON ──► ORM ──► PostgreSQL
App Android ───────┘
```

Proyecto Intermodular de 2º de DAM.

---

## Pantallas

Por ahora todas las pantallas son **solo interfaz**: los datos son de ejemplo y están escritos en los ViewModels, todavía sin API ni base de datos.

### Cliente

| Pantalla | Qué hace | Estado |
|---|---|---|
| Bienvenida (`LoginScreen`) | Logo, lema y botón *Iniciar sesión*. Es la pantalla de inicio | ✅ Enlazada |
| Inicio de sesión (`InicioSesionScreen`) | Correo, contraseña y enlace a *Regístrate* | 🟡 Hecha, falta enlazarla |
| Mis coches | Lista de coches del cliente: taller, estado (*En reparación*, *En revisión*, *Listo para recoger*) y progreso de la reparación (paso 5/6) | ✅ Enlazada |
| Ficha del taller | Datos del taller (valoración, distancia, horario) y calendario del mes con días libres, con pocas horas o completos | ✅ Enlazada (*Ver horas* pendiente) |
| Talleres | Buscador, filtros (*Todos*, *Mecánica*, *Neumáticos*…) y talleres cercanos con su próxima hora libre | 🟡 En la navegación, aún no se llega desde el inicio |
| Mis reparaciones | Cita solicitada y coche en el taller con su paso actual | 🟡 En la navegación, aún no se llega desde el inicio |
| Seguimiento | Línea temporal con los pasos de la reparación, pestañas *Estado* / *Chat* y botón *Escribir al taller* | 🟡 Hecha, falta enlazarla |

### Taller

| Pantalla | Qué hace | Estado |
|---|---|---|
| Panel | Nombre del taller, citas de hoy y botón *Aceptar coche* | 🟡 Hecha, falta enlazarla |
| Aceptar coche | Genera un código de 6 caracteres para que el cliente vincule su coche | 🟡 Hecha, falta enlazarla |

### Pendiente

- Perfil (la ruta ya existe en `Routes`, la pantalla no).
- Chat entre taller y cliente.
- Pedir cita eligiendo hora (*Ver horas*) y añadir coche (*+ Añadir*).
- Que el taller vaya marcando los pasos de la reparación.
- Conectar con la API, Room y StateFlow.

### Navegación actual

```
Bienvenida ──► Mis coches ──► Ficha del taller
                                  │
                                  └── ‹ Volver

Home ──► Mis reparaciones ◄──► Talleres      (barra inferior: Talleres · Mis coches · Perfil)
```

---

## Tecnologías

| Para qué | Qué se usa |
|---|---|
| Lenguaje | Kotlin 2.4.21 |
| Interfaz | Jetpack Compose (BOM 2026.09.00) + Material 3 |
| Navegación | Navigation Compose 2.10.2 + kotlinx.serialization 1.12.0 |
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
│   └── ui/theme/                   colores, tipografía y tema
└── features/
    ├── auth/                       LoginScreen (bienvenida)
    ├── iniciosesion/               InicioSesionScreen (correo y contraseña)
    ├── home/                       HomeScreen (menú provisional)
    ├── miscoches/                  MisCochesScreen + CocheUi
    ├── fichataller/                FichaTallerScreen + DiaUi (calendario)
    ├── talleres/                   TalleresScreen
    ├── reparaciones/               ReparacionesScreen
    ├── seguimiento/                SeguimientoScreen
    ├── panel/                      PanelScreen (taller)
    └── aceptarcoche/               AceptarCocheScreen (taller)
```

Cada funcionalidad tiene su propio paquete dentro de `features/<nombre>/presentation/`, con su pantalla y su `@HiltViewModel`. Lo que se comparte entre funcionalidades (navegación, tema, inyección de dependencias y, más adelante, datos) va en `core/`.

Los datos que se pintan en pantalla tienen su propia clase `…Ui` (`CocheUi`, `DiaUi`) y los da el ViewModel; así, cuando llegue la API, solo cambia el ViewModel y no la pantalla.

Las pantallas no conocen el `NavController`: reciben callbacks (`onVerTaller`, `onVolver`, `onTalleres`…) y es `AppNavHost` quien decide a dónde se navega.

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
