# CMorenoMusicApp

Examen de segundo parcial para la materia de **Desarrollo para dispositivos Android**. Proyecto Android escrito en **Kotlin** con Gradle KTS.

> Repositorio: `Kuripipeer/CMorenoMusicApp`

---

## ✨ Descripción

CMorenoMusicApp es una aplicación de música que consume una API para listar álbumes/canciones y muestra un **mini-reproductor** persistente en la parte inferior.  
Comportamiento actual esperado:

- En **Home** se muestra por defecto el primer registro que llega desde la API en el mini-reproductor.  
- En **Detail** (al abrir un álbum) el mini-reproductor muestra ese álbum temporalmente; al volver a Home, el mini-reproductor regresa al primer registro de la API.

---

## 🧱 Tecnologías y stack

- **Android** (Kotlin) con **Gradle KTS**  
- **Arquitectura sugerida**: MVVM + capas (data/domain/ui)  
- **UI**: Jetpack Compose  
- **Networking**: Retrofit/OkHttp  
- **Imagen/Media**: Coil / Media3

---

## 🚀 Cómo ejecutar el proyecto

1. **Clona el repositorio**
   ```bash
   git clone https://github.com/Kuripipeer/CMorenoMusicApp.git
   cd CMorenoMusicApp
   ```

2. **Abre en Android Studio**
   - Android Studio Iguana o superior recomendado.  
   - Sincroniza Gradle al abrir el proyecto.

3. **Configura la versión de Android SDK**
   - Asegúrate de tener instalados los SDKs necesarios (compileSdk / targetSdk).

4. **Ejecuta**
   - Selecciona un emulador o dispositivo físico.
   - Ejecuta desde Android Studio (▶️ Run).

---

## 🧩 Funcionalidades

- Listado de álbumes/canciones desde una API  
- Mini-reproductor con datos dinámicos  
- Navegación entre Home ↔ Detail

---

## 👤 Autor

**Christian Axel Moreno Flores (Kuripipeer)**  
Proyecto académico desarrollado en Kotlin.  
Repositorio: [github.com/Kuripipeer/CMorenoMusicApp](https://github.com/Kuripipeer/CMorenoMusicApp)
