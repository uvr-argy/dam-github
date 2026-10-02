#  Multigoya 
Aplicación Java para la gestión de una biblioteca multimedia, desarrollada con Java Swing y pensada para un entorno educativo o un pequeño ámbito empresarial.
Permite administrar usuarios, recursos, préstamos y devoluciones, trabajando con libros, películas y videojuegos. La información se guarda de forma persistente en el fichero biblioteca.txt.

#  Integrantes
| Integrante | Responsabilidad principal |
|-|:-:|
| Unax Vizcaíno	| Ficheros, persistencia, lectura y escritura de datos |
| Imanol Hermosilla	| Vistas, interfaz gráfica y Java Swing |
| Santiago Nahuel |	Funcionalidades, modelos, clases y lógica |
|Juan Puertas	| Funcionalidades, modelos, clases y lógica |

Aunque existió un reparto inicial, las distintas partes del proyecto tuvieron que integrarse, adaptarse y revisarse conjuntamente durante el desarrollo.

# Funcionalidades

## Gestión de usuarios
La aplicación permite:
- Crear usuarios.
- Listar usuarios.
- Buscar usuarios.
- Modificar usuarios.
- Eliminar usuarios.
- Evitar identificadores duplicados.
- Consultar usuarios que nunca han realizado un préstamo.

## Gestión de recursos
La biblioteca trabaja con tres tipos de recursos:
- Libros
- Películas
- Videojuegos
Todos los recursos comparten información como:
- Identificador.
- Título.
- Año.
- Estado de disponibilidad.

Además, cada tipo de recurso dispone de información específica:
| Tipo | Información específica |
|-|:-:|
|Libro | Autor y número de páginas |
| Película | Director y duración|
| Videojuego | Plataforma y clasificación PEGI |


La aplicación permite:
- Crear recursos.
- Listar recursos.
- Buscar recursos.
- Modificar recursos.
- Eliminar recursos.
- Consultar si un recurso está disponible o prestado.
- Consultar recursos disponibles.
- Consultar recursos prestados.
- Buscar recursos por título.
- Filtrar recursos por tipo.
- Consultar recursos que han sido prestados un determinado número de veces.

## Gestión de préstamos y devoluciones
Antes de realizar un préstamo se comprueba que:
- El usuario existe.
- El recurso existe.
- El recurso está disponible.

Cuando se realiza un préstamo:
1. Se registra el usuario.
2. Se registra el recurso.
3. Se guarda la fecha del préstamo.
4. El préstamo queda marcado como activo.
5. El recurso pasa a estar no disponible.

Cuando se realiza una devolución:
1. Se localiza el préstamo activo correspondiente al recurso.
2. Se registra la fecha de devolución.
3. El préstamo pasa a estar finalizado.
4. El recurso vuelve a estar disponible.

También es posible consultar:
- Los préstamos realizados por un usuario.
- Los préstamos actualmente activos.

## Persistencia de datos
La aplicación utiliza un único fichero de texto para almacenar la información de forma permanente:
```
biblioteca.txt
```
Gracias a este fichero, los datos pueden recuperarse al volver a iniciar la aplicación.
La gestión de datos está separada de la lógica principal del programa para facilitar el mantenimiento y la organización del código.

## Interfaz gráfica
La aplicación utiliza Java Swing para proporcionar una interfaz gráfica.
Las principales clases de la vista son:
- FormularioContenido.java
- FormularioEliminar.java
- PanelContenido.java
- VentanaDetalleContenido.java
- VentanaPrincipal.java

La interfaz permite realizar las operaciones de la biblioteca mediante ventanas, formularios y paneles, sin necesidad de utilizar comandos por consola.

## Estructura del proyecto
El proyecto está organizado en distintos paquetes, cada uno con una responsabilidad concreta.
```
src/
├── app/
├── datos/
├── funcionalidades/
├── modelo/
└── vista/
```
### app

Contiene el punto de entrada de la aplicación.
La ejecución comienza desde:
```
Main.java
```
### datos
Contiene las clases relacionadas con el almacenamiento, la lectura y la escritura de datos.

La persistencia se realiza mediante:
```
biblioteca.txt
funcionalidades
```

Contiene la lógica principal de la aplicación:
- Gestión de usuarios.
- Gestión de recursos.
- Gestión de préstamos.
- Gestión de devoluciones.
- Consultas sobre los datos.

### modelo

Contiene las clases que representan los objetos principales del sistema:
- Usuario
- Recurso
- Libro
- Pelicula
- Videojuego
- Prestamo

### vista

Contiene las clases relacionadas con la interfaz gráfica desarrollada con Java Swing.

Principales clases:
- FormularioContenido.java
- FormularioEliminar.java
- PanelContenido.java
- VentanaDetalleContenido.java
- VentanaPrincipal.java

## Otros elementos del proyecto
El repositorio también contiene archivos y carpetas utilizados por Eclipse y Git:
```
bin/
.settings/
.gitignore
README.md
```
La carpeta bin/ contiene los archivos compilados generados por Java y Eclipse.

El archivo .gitignore indica qué archivos o carpetas no deben incluirse en el repositorio.

## Tecnologías utilizadas
- Java
- Java Swing
- Eclipse IDE
- Git
- GitHub
- Ficheros de texto para persistencia de datos

### Versión de Java
El proyecto se ha desarrollado utilizando:
- OpenJDK 25.0.4.1 LTS
- Eclipse Temurin
openjdk version "25.0.4.1" 2026-08-18 LTS
OpenJDK Runtime Environment Temurin-25.0.4.1+1
OpenJDK 64-Bit Server VM Temurin-25.0.4.1+1-LTS

## Ejecución del proyecto
Para ejecutar la aplicación:
1. Clonar o descargar el repositorio.
2. Importar el proyecto en Eclipse.
3. Comprobar que se utiliza una versión compatible de Java.
4. Abrir la clase Main.java.
5. Ejecutar mediante:
Run As → Java Application

Al iniciarse la aplicación se abrirá la ventana principal desarrollada con Java Swing.

La aplicación utilizará biblioteca.txt para cargar y guardar los datos.

## Reparto inicial del trabajo

### Unax Vizcaíno
Responsable principalmente de:
- Gestión de ficheros.
- Persistencia de datos.
- Lectura de información.
- Escritura de información.

### Imanol Hermosilla
Responsable principalmente de:
- Desarrollo de las vistas.
- Diseño de la interfaz gráfica.
- Implementación mediante Java Swing.
- Creación de ventanas, formularios y paneles.

### Santiago Nahuel y Juan Puertas
Responsables principalmente de:
- Desarrollo de las funcionalidades.
- Creación de los modelos.
- Creación de las clases principales.
- Gestión de usuarios.
- Gestión de recursos.
- Gestión de préstamos.
- Gestión de devoluciones.
- Consultas.
- Lógica general de la aplicación.

## Uso de Git y GitHub
Git y GitHub se han utilizado durante todo el desarrollo para permitir que los integrantes trabajasen de forma paralela.

### Ramas principales
|Rama |	Uso principal |
|-|:-:|
|main	| Versiones integradas y estables |
|develop | Integración de cambios durante el desarrollo |
|funcionalidades | Lógica y funcionalidades de la aplicación |
|controladores | Organización de controladores y conexión entre partes |
|feature/vista | Desarrollo de la interfaz gráfica | 
|persistencia	| Gestión de datos y persistencia mediante ficheros |
|recursos |	Funcionalidades relacionadas con los recursos |


Durante el desarrollo también se han utilizado:
- Commits.
- push.
- pull.
- Pull Requests.
- Merges.
- Resolución de conflictos.
- Actualización de ramas.
- Revisión del código.
- Integración del trabajo realizado por distintos integrantes.

## Problemas encontrados durante el desarrollo
### Aprendizaje de Git y GitHub

Al comienzo del proyecto fue necesario familiarizarse con:
- Clonar repositorios.
- Crear ramas.
- Cambiar entre ramas.
- Realizar commits.
- Subir cambios mediante push.
- Descargar cambios mediante pull.
- Mantener sincronizado el repositorio local con GitHub.

### Conflictos entre ramas
Al trabajar varias personas sobre el mismo proyecto aparecieron conflictos durante la integración.

Fue necesario aprender a:
- Identificar archivos en conflicto.
- Comparar versiones.
- Decidir qué cambios conservar.
- Resolver conflictos.
- Confirmar las resoluciones.
- Comprobar que el proyecto continuaba funcionando después de la integración.

### Pull Requests

Las Pull Requests se utilizaron para:
- Revisar cambios.
- Comparar ramas.
- Integrar funcionalidades.
- Comprobar el trabajo realizado por otros integrantes.

### Merges
La unión de ramas requirió especial atención para evitar perder cambios realizados por otros miembros del equipo.

### Integración final

Una de las partes más importantes del proyecto fue la integración final de:
- Modelos.
- Funcionalidades.
- Gestión de datos.
- Persistencia.
- Interfaz gráfica.
- Préstamos y devoluciones.
- Usuarios.
- Recursos.

Al haberse desarrollado distintas partes en paralelo, fue necesario adaptar clases, paquetes, nombres y llamadas entre componentes para conseguir que todo funcionase correctamente de forma conjunta.

## Organización general
La aplicación sigue una separación de responsabilidades entre sus diferentes paquetes.
```
Vista
  ↓
Funcionalidades
  ↓
Modelo / Datos
  ↓
biblioteca.txt
```
- La vista se encarga de mostrar la información y permitir la interacción con el usuario.
- Las funcionalidades contienen la lógica principal.
- El modelo representa los objetos utilizados por el sistema.
- El paquete datos se encarga de la lectura, escritura y persistencia.
- biblioteca.txt permite conservar los datos entre ejecuciones.
### - Resolución de conflictos.
Además del desarrollo de la aplicación, uno de los objetivos principales ha sido aprender a organizar un proyecto realizado por varias personas, integrar diferentes partes de código y utilizar correctamente Git y GitHub durante todo el proceso de desarrollo.
