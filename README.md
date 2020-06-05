# Bookazon

Este proyecto consiste en una app que permitirá al usuario reservar un libro en la biblioteca más cercana(o en la que desee).
Para ello el usuario, una vez registrado en la app, podráacceder a un mapa en donde se mostrarán las bibliotecas más cercanas a él, y al pulsar en una de ellas, tendrá acceso a la lista de libros que dispone esa biblioteca. Al pulsar en uno de los libros de la lista, podrá acceder a sus detalles, y desde ahí podrá pulsar un botón con el que reservará el libro.
Esta reserva quedará registrada en la lista de reservas que tendrá el usuario, con la posibilidad de cancelar la reserva en cualquier momento.
Cuando el usuario está en la lista de libros de una biblioteca, tendrá la posibilidad de filtrar esos libros, ya sea mediante el género del libro o por su autor.


## Usuarios de prueba

  * username = alberto@gmail.com
  * password = 12345678

## Implementaciones realizadas hasta ahora

  - A nivel general:
    - Seguridad con JWT
    - Login (APP Y API)
    - Registro (APP Y API)

  - A nivel de API
    - Buscar una Biblioteca a través de su nombre
    - Datos de inicio en la base de datos
    - Metodos necesarios para trabajar con las bibliotecas, copias y reservas
    - Método para listar copias por el nombre de una biblioteca. 
    - Método para crear una Reserva pasando como parámetro una Copia y el User logueado.
    - Método para buscar una Reserva pasando el id de una Copia.

  - A nivel de APP
    - Localización de bibliotecas mediante mapa de Google Maps 
    - Abrir el detalle de una biblioteca cuando haces click en una biblioteca a través del mapa de Google Maps 
    - Fragment de la lista de bibliotecas(RecyclerView)
    - Opción de abrir su detalle cuando haces click en un elemento de la lista
    - Menú en el fragmento de Google Maps con el que te diriges al fragmento de la lista de bibliotecas
    - Menú en el fragmento de la lista de bibliotecas con el que te diriges al fragmento de Google Maps
    - Fragment para lista de copias
    - Activity para mostrar el detalle de una copia.
    - Posibilidad de hacer la reserva desde el detalle de una copia.
    - ListFragment para ver la lista de reservas del usuario logueado.
    - Perfil de usuario
    - Editar perfil de usuario
    - Logout
    - Filtros para listar copias por género o autor
    - Eliminación de una reserva


