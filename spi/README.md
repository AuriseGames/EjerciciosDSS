# Ejercicio 2 - Inyeccion de dependencias

## Apartado a)

En este apartado rediseñamos las interfaces del ejemplo KnightsOfTheRoundTable para que la genericidad no se implemente con java.lang.Object. Para ello, modificamos las clases e interfaces con los siguientes cambios:

#### `Quest.java` 
```java
public interface Quest<T> {
    T embark() throws QuestFailedException;
}
```

#### `HolyGrailQuest.java`
```java
public class HolyGrailQuest implements Quest<HolyGrail> { ... }
```

#### `Knight.java`
```java
public interface Knight<T> {
    T embarkOnQuest() throws QuestFailedException;
    void setQuest(Quest<T> quest);
}
```

#### `KnightOfTheRoundTable.java`
```java
public class KnightOfTheRoundTable<T> implements Knight<T> {
    private String name;
    private Quest<T> quest;

    public KnightOfTheRoundTable(String name) {
        this.name = name;
    }

    public T embarkOnQuest() throws QuestFailedException {
        return quest.embark();
    }

    public void setQuest(Quest<T> quest) {
        this.quest = quest;
    }
}
```

Como podemos observar, ahora las interfaces son genericas y la clase KnightOfTheRoundTable tambien. De esta forma evitamos provocar posibles ClassCastException.

Para probar la funcionalidad de este apartado, creamos el siguiente test unitario:

#### `KnightOfTheRoundTableTest1.java`
```java
@SpringBootTest
class KnightOfTheRoundTableTest1 {

    Knight<HolyGrail> knight;
    Quest<HolyGrail> quest;

    @BeforeEach
    void setUp() {
        knight = new KnightOfTheRoundTable<HolyGrail>("Lancelot");
        quest = new HolyGrailQuest();
        knight.setQuest(quest);
    }

    @Test
    void testEmbarkOnQuest() {
        try {
            knight.embarkOnQuest();
        } catch (QuestFailedException e) {
            e.printStackTrace();
        }
    }

}
```

Este test unitario prueba que el caballero Lancelot pueda realizar la busqueda del santo grial sin problemas. Aunque hay un problema, ya que la inyección de dependencias no se realiza correctamente. Solucionaremos el problema en el siguiente apartado.

## Apartado b)

En este apartado solucionamos el problema de la inyección de dependencias. Para ello, creamos una clase de configuración llamada `Test2Config.java` con el siguiente contenido:

#### `Test2Config.java`
```java
@Configuration
public class Test2Config {
    
    @Bean
    public KnightOfTheRoundTableTest2<HolyGrail> knightOfTheRoundTableTest2() {
        return new KnightOfTheRoundTableTest2<HolyGrail>();
    }

    @Bean
    public Knight<HolyGrail> knight() {
        return new KnightOfTheRoundTable<HolyGrail>("Lancelot");
    }

    @Bean
    public Quest<HolyGrail> quest() {
        return new HolyGrailQuest();
    }
    
}
```

En esta clase realizamos dos tareas muy importantes. La primera es indicarle a spring que Test2Config es una clase de configuración con la anotación `@Configuration`. Esto significa que Spring debe leer esta clase para obtener los beans que se van a utilizar en la aplicación. La segunda tarea es indicarle a Spring que los beans que se van a utilizar. Los beans son funciones que devuelven un objeto que Spring se encarga de almacenar para su posterior uso.

Una vez que tenemos la clase de configuración, debemos indicarle a Spring que la utilice. Para ello, modificamos el test unitario de la siguiente forma:

#### `KnightOfTheRoundTableTest2.java`
```java
@SpringBootTest
class KnightOfTheRoundTableTest2<T> {

    @Autowired
    Knight<T> knight;

    @Autowired
    Quest<T> quest;

    @Test
    void testEmbarkOnQuest() {
        try {
            knight.setQuest(quest);
            knight.embarkOnQuest();
        } catch (QuestFailedException e) {
            e.printStackTrace();
        }
    }
}
```

Como se puede observar, se utiliza la etiqueta `@Autowired` para indicarle a Spring que debe inyectar los beans que se han definido en la clase de configuración. De esta forma, se soluciona el problema de la inyección de dependencias. Además, se ha modificado el test unitario para que se pueda utilizar con cualquier tipo de quest usando el tipo generico T. Para cambiar el tipo de quest, solo hay que cambiar el tipo `<HolyGrail>` por el tipo que queramos en la clase de configuración y no hace falta modificar el test unitario.
