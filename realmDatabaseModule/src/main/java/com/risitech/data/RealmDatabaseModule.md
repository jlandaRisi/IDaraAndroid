# RealmModelManager

## Descripción del Módulo
`realmDatabaseModule` es un módulo diseñado para centralizar la configuración y el manejo de la base de datos Realm en proyectos Android. Este módulo permite registrar dinámicamente los modelos que se utilizarán en la base de datos antes de inicializarla, asegurando que la configuración sea flexible, reutilizable y fácil de mantener.

Además, el módulo incluye clases e interfaces que definen y gestionan las operaciones CRUD para interactuar con la base de datos de manera estructurada y desacoplada.

---

## Definición de Clases e Interfaces

### **1. RealmModelManager**
- **Propósito**: Centralizar la configuración de Realm y proporcionar una instancia única de la base de datos.
- **Métodos Clave**:
  - `registerModel(model: KClass<out RealmObject>)`: Registra un modelo para que sea gestionado por Realm.
  - `initialize()`: Inicializa Realm con los modelos registrados.

---

### **2. LocalDataSource**
- **Propósito**: Implementa las operaciones CRUD para interactuar directamente con la base de datos Realm.
- **Interfaz Asociada**: `AppDataSource`
  - Define las operaciones básicas (CRUD) para interactuar con una fuente de datos.
- **Métodos Clave**:
  - `insert(model: T)`: Inserta un modelo en la base de datos.
  - `update(model: T)`: Actualiza un modelo existente.
  - `getAll()`: Recupera todos los modelos.
  - `deleteById(id: String, hardDelete: Boolean)`: Elimina un modelo por su ID.
  - `deleteAll(hardDelete: Boolean)`: Elimina todos los modelos.

---

### **3. AppRepository**
- **Propósito**: Actúa como intermediario entre la capa de datos y la lógica de negocio o presentación.
- **Interfaz Asociada**: `DataRepository`
  - Define las operaciones básicas (CRUD) para interactuar con la capa de datos desde la lógica de negocio.
- **Métodos Clave**:
  - `insert(model: T)`: Inserta un modelo en la base de datos.
  - `update(model: T)`: Actualiza un modelo existente.
  - `getAll()`: Recupera todos los modelos.
  - `deleteById(id: String, hardDelete: Boolean)`: Elimina un modelo por su ID.
  - `deleteAll(hardDelete: Boolean)`: Elimina todos los modelos.

---

## Forma de Uso

### **1. Configuración del Módulo**
En la clase `Application` del proyecto, registra los modelos y luego inicializa el módulo:

```kotlin
import android.app.Application
import com.risitech.data.infrastructure.RealmModelManager
import com.risitech.data.domain.models.BaseRealmModel

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Registrar los modelos necesarios
        RealmModelManager.registerModel(BaseRealmModel::class)

        // Inicializar Realm
        RealmModelManager.initialize()
    }
}
```

---

### **2. Uso de LocalDataSource**
Crea una instancia de `LocalDataSource` para interactuar directamente con la base de datos Realm:

```kotlin
import com.risitech.data.infrastructure.datasources.LocalDataSource
import com.risitech.data.domain.models.BaseRealmModel

val localDataSource = LocalDataSource(BaseRealmModel::class)

// Insertar un modelo
localDataSource.insert(BaseRealmModel().apply {
    id = "123"
    deleted = false
})

// Recuperar todos los modelos
val models = localDataSource.getAll()
```

---

### **3. Uso de AppRepository**
Crea una instancia de `AppRepository` para interactuar con la capa de datos desde la lógica de negocio:

```kotlin
import com.risitech.data.infrastructure.repositories.AppRepository
import com.risitech.data.infrastructure.datasources.LocalDataSource
import com.risitech.data.domain.models.BaseRealmModel

val localDataSource = LocalDataSource(BaseRealmModel::class)
val appRepository = AppRepository(localDataSource)

// Insertar un modelo
appRepository.insert(BaseRealmModel().apply {
    id = "123"
    deleted = false
})

// Recuperar todos los modelos
val models = appRepository.getAll()
```

---

## Notas Importantes
- **`LocalDataSource`**: Se utiliza para interactuar directamente con la base de datos Realm.
- **`AppRepository`**: Se utiliza para desacoplar la lógica de negocio de los detalles de implementación de la base de datos.
- **Separación de Responsabilidades**: Usa `LocalDataSource` para operaciones específicas de la base de datos y `AppRepository` para integraciones con la lógica de negocio.

---

## Ventajas del Módulo
- **Centralización**: Toda la configuración de Realm está en un único lugar.
- **Flexibilidad**: Permite registrar modelos dinámicamente según las necesidades del proyecto.
- **Reutilización**: Puede ser utilizado en diferentes proyectos sin modificaciones significativas.
- **Seguridad**: Asegura que Realm esté correctamente configurado antes de su uso.
