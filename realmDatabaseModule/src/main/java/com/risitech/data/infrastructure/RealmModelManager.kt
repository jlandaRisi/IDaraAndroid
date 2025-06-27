/**
 * Módulo que centraliza la configuración y el manejo de la base de datos Realm.
 * Permite registrar modelos dinámicamente y proporciona una instancia única de Realm.
 */
object RealmModelManager {
    /**
     * Conjunto de modelos registrados que serán gestionados por Realm.
     */
    private val registeredModels = mutableSetOf<KClass<out RealmObject>>()

    /**
     * Indica si Realm ya ha sido inicializado.
     */
    private var isInitialized = false

    /**
     * Instancia única de Realm configurada con los modelos registrados.
     */
    lateinit var realm: Realm
        private set

    /**
     * Registra un modelo para que sea gestionado por Realm.
     * @param model Clase del modelo a registrar.
     * @throws IllegalStateException Si se intenta registrar un modelo después de inicializar Realm.
     */
    fun registerModel(model: KClass<out RealmObject>) {
        if (isInitialized) {
            throw IllegalStateException("No se pueden registrar modelos después de inicializar Realm.")
        }
        registeredModels.add(model)
    }

    /**
     * Inicializa Realm con los modelos registrados.
     * @throws IllegalStateException Si no se han registrado modelos antes de inicializar.
     */
    fun initialize() {
        if (isInitialized) return
        if (registeredModels.isEmpty()) {
            throw IllegalStateException("No se han registrado modelos para Realm.")
        }
        val config = RealmConfiguration.Builder(schema = registeredModels).build()
        realm = Realm.open(config)
        isInitialized = true
    }
}