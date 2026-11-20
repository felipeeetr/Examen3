# 🩸 Sistema de Gestión de Exámenes de Sangre

Proyecto de gestión de exámenes de laboratorio clínico desarrollado en **Java 11+** con interfaz gráfica en **JavaFX 17** y gestión de dependencias con **Maven**.

El sistema está diseñado bajo los principios de la **Arquitectura Hexagonal (Ports and Adapters)** y reforzado con tres patrones de diseño de software del catálogo **GoF (Gang of Four)**: **Builder**, **Strategy** y **Facade**.

---

## 🏛️ Arquitectura del Sistema

La solución adopta una **Arquitectura Hexagonal**, desacoplando totalmente las reglas de negocio de los detalles técnicos, interfaces de usuario y mecanismos de persistencia.

```
       [ Infraestructura: UI ]  ──> [ Puertos de Entrada ]
                                             │
                                             ▼
                                  [ Servicios de Aplicación ]
                                             │
                                             ▼
                                    [ Núcleo de Dominio ]
                                             │
                                             ▼
       [ Infraestructura: DB ]  <── [ Puertos de Salida ]
```

### Capas del Proyecto (`co.edu.poli.Examen2`)

1. **Dominio (`dominio`)**:
   - **`modelo`**: Entidades puras e invariantes (`ExamenSangre`, `TipoSangre`, `FactorRH`). Sin dependencias externas.
   - **`constructor`**: Patrón creacional **Builder** (`ExamenSangreBuilder`) para la construcción y validación fluida de entidades.
   - **`costo`**: Patrón comportamental **Strategy** (`EstrategiaCosto`, `CostoEstandar`, `CostoRecargoRhNegativo`, `CostoRecargoTipoSangre`) para cálculo flexible de tarifas y recargos.
2. **Aplicación (`aplicacion`)**:
   - **`puerto.entrada`**: Casos de uso (`GuardarExamenUseCase`, `ListarExamenesUseCase`, `SerializarExamenesUseCase`, `DeserializarExamenesUseCase`).
   - **`puerto.salida`**: Puertos para persistencia e infraestructura (`GuardarExamenPort`, `BuscarExamenPort`, `ListarExamenesPort`, `ReemplazarExamenesPort`, `SerializarExamenesPort`, `DeserializarExamenesPort`, `PersistenciaException`).
   - **`servicio`**: Lógica de aplicación que orquesta casos de uso (`GuardarExamenServicio`, `ListarExamenesServicio`, etc.).
   - **`fachada`**: Patrón estructural **Facade** (`ExamenesFacade`) que unifica todos los casos de uso para clientes externos.
3. **Infraestructura (`infraestructura`)**:
   - **`ui`**: Adaptador primario (driving). Controlador JavaFX (`PrimaryController`) y vista FXML.
   - **`persistencia`**: Adaptadores secundarios (driven). Almacenamiento en memoria (`ExamenMemoriaRepository`) y serialización binaria en disco (`ExamenArchivoAdapter`).
4. **Vista / Raíz de Composición (`vista`)**:
   - **`App.java`**: *Composition Root*. Ensambla manualmente las dependencias, inyecta estrategias y controladores, y lanza el entorno gráfico JavaFX.

---

## 🧩 Patrones de Diseño GoF Implementados

| Patrón | Tipo | Paquete | Propósito en el Sistema |
| :--- | :--- | :--- | :--- |
| **Builder** | Creacional | `dominio.constructor` | **`ExamenSangreBuilder`**: Desacopla la vista de la conversión y parseo de tipos (cadenas a `double` o `enum`). Garantiza que ningún objeto `ExamenSangre` sea instanciado en un estado inconsistente mediante su método terminal `construir()`. |
| **Strategy** | Comportamiento | `dominio.costo` | **`EstrategiaCosto`**: Permite calcular el costo final de los exámenes aplicando diferentes políticas de recargo (por ejemplo, recargo del 20% para factor RH negativo con `CostoRecargoRhNegativo`) sin alterar el modelo ni los servicios. |
| **Facade** | Estructural | `aplicacion.fachada` | **`ExamenesFacade`**: Oculta la complejidad de los 4 casos de uso ante la capa de presentación. El controlador UI solo interactúa con la fachada, reduciendo el acoplamiento y facilitando la adición de nuevas interfaces (CLI, REST). |

---

## 📂 Estructura de Directorios

```text
Examen3/
├── README.md
├── diagrama_arquitectura.png
├── diagrama_dominio.png
├── diagrama_patrones.png
└── Examen2/
    ├── pom.xml
    ├── .gitignore
    └── src/
        ├── main/
        │   ├── java/
        │   │   ├── module-info.java
        │   │   └── co/edu/poli/Examen2/
        │   │       ├── dominio/
        │   │       │   ├── modelo/         # ExamenSangre, TipoSangre, FactorRH
        │   │       │   ├── constructor/    # ExamenSangreBuilder (Builder)
        │   │       │   └── costo/          # EstrategiaCosto y variantes (Strategy)
        │   │       ├── aplicacion/
        │   │       │   ├── puerto/entrada/ # Interfaces UseCase
        │   │       │   ├── puerto/salida/  # Interfaces Port y PersistenciaException
        │   │       │   ├── servicio/       # Servicios de aplicación
        │   │       │   └── fachada/        # ExamenesFacade (Facade)
        │   │       ├── infraestructura/
        │   │       │   ├── ui/             # PrimaryController (JavaFX)
        │   │       │   └── persistencia/   # Repositorio en memoria y archivo
        │   │       └── vista/              # App.java (Composition Root)
        │   └── resources/
        │       └── co/edu/poli/Examen2/
        │           └── view/
        │               └── primary.fxml    # Vista FXML moderna
        └── test/
            └── java/
                └── co/edu/poli/Examen2/
```

---

## ⚙️ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado en tu sistema:

1. **Java Development Kit (JDK)**: Versión **11** o superior (probado y compatible hasta **JDK 21 LTS**).
   - Verificar en consola:
     ```bash
     java -version
     ```
2. **Apache Maven**: Versión **3.8+**.
   - Verificar en consola:
     ```bash
     mvn -version
     ```
3. **Variables de Entorno**:
   - `JAVA_HOME` apuntando al directorio de instalación de tu JDK.
   - `M2_HOME` o ruta de Maven agregada a la variable `PATH`.

---

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

Abre una terminal y clona el proyecto:

```bash
git clone https://github.com/felipeeetr/Examen3.git
cd Examen3
```

### 2. Ingresar a la carpeta del proyecto Maven

El proyecto compilable se encuentra dentro del subdirectorio `Examen2`:

```bash
cd Examen2
```

---

## 🛠️ Compilación y Ejecución

Todos los comandos deben ejecutarse desde la carpeta `Examen2`.

### Limpiar y Compilar

Para verificar la integridad del código fuente y descargar dependencias:

```bash
mvn clean compile
```

### Ejecutar la Aplicación

Para compilar y levantar la interfaz gráfica JavaFX:

```bash
mvn clean javafx:run
```

---

## 🖥️ Guía de Uso de la Aplicación

La interfaz gráfica presenta los siguientes controles y operaciones:

### 1. 💾 Guardar Examen
- **Campos:**
  - **Código:** Identificador único (ej: `EXM-001`).
  - **Nombre del Paciente:** Texto obligatorio (ej: `Juan Pérez`).
  - **Costo ($):** Valor numérico mayor a cero (ej: `100.0`).
  - **Tipo de Sangre:** Selección del ComboBox (`A`, `B`, `AB`, `O`).
  - **Factor RH:** RadioButton (`Positivo (Rh+)` o `Negativo (Rh−)`).
- **Comportamiento GoF:**
  - El **Builder** valida los formatos y datos obligatorios.
  - El **Strategy** evalúa el factor RH: si se selecciona `Negativo (Rh−)`, aplica automáticamente un **20% de recargo** (ejemplo: un costo base de `100.0` se registra como `120.0`).
  - El servicio valida que no existan códigos duplicados.

### 2. 🧹 Limpiar Formulario
- Restablece los campos de texto, limpia la selección de tipo de sangre y reposiciona el factor RH en Positivo.

### 3. 📋 Listar Exámenes
- Muestra en el área de texto inferior todos los exámenes en memoria con el formato:
  `CODIGO | PACIENTE | COSTO | TIPO_SANGRE(+/-)`

### 4. 📦 Serializar
- Guarda la lista actual de exámenes en memoria en un archivo binario `examenes.ser` en el directorio de ejecución.
- Informa la cantidad de registros serializados.

### 5. 📂 Deserializar
- Lee el archivo `examenes.ser` y repuebla la memoria RAM de la aplicación con los exámenes guardados previamente.

---

## 📊 Diagramas del Proyecto

En la raíz del repositorio se incluyen los diagramas visuales en formato PNG:
- **`diagrama_arquitectura.png`**: Diagrama de bloques de la Arquitectura Hexagonal completa (Driving Adapters, Inbound Ports, Core, Outbound Ports, Driven Adapters).
- **`diagrama_dominio.png`**: Diagrama de clases UML del núcleo de dominio.
- **`diagrama_patrones.png`**: Diagrama de clases detallando la interacción de los patrones **Builder**, **Strategy** y **Facade** con el dominio.

---

## 🤖 Declaración de Uso de Inteligencia Artificial

En cumplimiento con los lineamientos académicos, se declara el uso de herramientas de Inteligencia Artificial en el desarrollo del proyecto:

- **Herramienta:** Google Antigravity (Gemini).
- **Actividades aplicadas:**
  - **Transformación a Arquitectura Hexagonal:** Reestructuración y refactorización completa del proyecto monolítico original hacia Arquitectura Hexagonal (Ports & Adapters), estableciendo la separación en capas de Dominio, Aplicación, Infraestructura y Puertos.
  - **Generación de Diagramas:** Creación y exportación de los diagramas visuales y técnicos de arquitectura hexagonal, modelo de dominio y patrones GoF (en formatos PNG, Mermaid y PlantUML).
  - **Implementación de Patrones GoF:** Estructuración y codificación de los patrones de diseño Builder, Strategy y Facade.
  - **Documentación Técnica:** Generación de la documentación de código JavaDoc y redacción de las instrucciones de instalación y uso en este README.

---

## 👨‍💻 Autor y Licencia

- **Repositorio:** [https://github.com/felipeeetr/Examen3](https://github.com/felipeeetr/Examen3)
- Desarrollado con propósitos académicos para la materia de **Software 2 / Paradigmas de Programación**.
