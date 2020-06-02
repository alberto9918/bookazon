package com.santiagorodriguezalberto.bookazon

import com.santiagorodriguezalberto.bookazon.entity.Biblioteca
import com.santiagorodriguezalberto.bookazon.entity.Copia
import com.santiagorodriguezalberto.bookazon.entity.Usuario
import com.santiagorodriguezalberto.bookazon.repository.BibliotecaRepository
import com.santiagorodriguezalberto.bookazon.repository.CopiaRepository
import com.santiagorodriguezalberto.bookazon.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class MyData(
        val userRepository: UserRepository,
        val bibliotecaRepository: BibliotecaRepository,
        val copiaRepository: CopiaRepository,
        private val encoder: PasswordEncoder
) {
    @PostConstruct
    fun initData(){
        val usuarios = listOf(
                Usuario("Alberto","Santiago Rodríguez","alberto@gmail.com",
                encoder.encode("12345678"),"12345678A",123456789, mutableSetOf("USER"))
        )

        val bibliotecas = listOf(
                Biblioteca("Biblioteca_Los_Carteros",37.411256,-5.971490),
                Biblioteca("Biblioteca_Antonio_Machado", 37.370383, -5.985854),
                Biblioteca("Biblioteca_Pública_Infanta_Elena", 37.377103, -5.991697)
        )

        val copias = listOf(

                //COPIAS EN LOS CARTEROS
                Copia("DUNE","FRANK_HERBERT","Dune es la primera novela de la serie homónima «Dune» de Frank Herbert," +
                        " una obra maestra unánimemente reconocida como la mejor saga de ciencia ficción de todos los tiempos.",
                        "CIENCIA_FICCION",
                        "9788497596824","DEBOLSILLO",
                        "https://imagessl4.casadellibro.com/a/l/t5/24/9788497596824.jpg",
                        1,false,bibliotecas[0]),

                Copia("DUNE","FRANK_HERBERT","Dune es la primera novela de la serie homónima «Dune» de Frank Herbert," +
                        " una obra maestra unánimemente reconocida como la mejor saga de ciencia ficción de todos los tiempos.",
                        "CIENCIA_FICCION",
                        "9788497596824","DEBOLSILLO",
                        "https://imagessl4.casadellibro.com/a/l/t5/24/9788497596824.jpg",
                        2,false,bibliotecas[0]),

                Copia("FAHRENHEIT 451","RAY_BRADBURY","Fahrenheit 451: la temperatura a la que el papel se enciende y arde." +
                        " Guy Montag es un bombero y el trabajo de un bombero es quemar libros, que están prohibidos porque son causa de discordia y sufrimiento.",
                        "CIENCIA_FICCION",
                        "9788490321478","DEBOLSILLO",
                        "https://imagessl8.casadellibro.com/a/l/t5/78/9788490321478.jpg",
                        1,false,bibliotecas[0]),

                Copia("FAHRENHEIT 451","RAY_BRADBURY","Fahrenheit 451: la temperatura a la que el papel se enciende y arde." +
                        " Guy Montag es un bombero y el trabajo de un bombero es quemar libros, que están prohibidos porque son causa de discordia y sufrimiento.",
                        "CIENCIA_FICCION",
                        "9788490321478","DEBOLSILLO",
                        "https://imagessl8.casadellibro.com/a/l/t5/78/9788490321478.jpg",
                        2,false,bibliotecas[0]),

                Copia("FAHRENHEIT 451","RAY_BRADBURY","Fahrenheit 451: la temperatura a la que el papel se enciende y arde." +
                        " Guy Montag es un bombero y el trabajo de un bombero es quemar libros, que están prohibidos porque son causa de discordia y sufrimiento.",
                        "CIENCIA_FICCION",
                        "9788490321478","DEBOLSILLO",
                        "https://imagessl8.casadellibro.com/a/l/t5/78/9788490321478.jpg",
                        3,false,bibliotecas[0]),

                Copia("EL MESIAS DE DUNE","FRANK_HERBERT","Arrakis, también llamado Dune: un mundo desierto en pos del sueño de convertirse en un paraíso," +
                        " cuna de mil guerras que se han extendido por todo el universo...",
                        "MISTERIO",
                        "9788497596671","DEBOLSILLO",
                        "https://imagessl1.casadellibro.com/a/l/t5/71/9788497596671.jpg",
                        1,false,bibliotecas[0]),

                Copia("22/11/63","STEPHEN_KING","Viajar al pasado nunca ha sido tan creíble." +
                        " Ni tan terrorífico.Jake Epping es un profesor de inglés que se gana un sueldo extra impartiendo clases nocturnas a" +
                        " adultos con problemas de lectura.",
                        "CIENCIA_FICCION",
                        "9788490321287","DEBOLSILLO",
                        "https://imagessl7.casadellibro.com/a/l/t5/87/9788490321287.jpg",
                        1,false,bibliotecas[0]),

                //COPIAS EN ANTONIO MACHADO
                Copia("DUNE","FRANK_HERBERT","Dune es la primera novela de la serie homónima «Dune» de Frank Herbert," +
                        " una obra maestra unánimemente reconocida como la mejor saga de ciencia ficción de todos los tiempos.",
                        "CIENCIA_FICCION",
                        "9788497596824","DEBOLSILLO",
                        "https://imagessl4.casadellibro.com/a/l/t5/24/9788497596824.jpg",
                        3,false,bibliotecas[1]),

                Copia("FAHRENHEIT 451","RAY_BRADBURY","Fahrenheit 451: la temperatura a la que el papel se enciende y arde." +
                        " Guy Montag es un bombero y el trabajo de un bombero es quemar libros, que están prohibidos porque son causa de discordia y sufrimiento.",
                        "CIENCIA_FICCION",
                        "9788490321478","DEBOLSILLO",
                        "https://imagessl8.casadellibro.com/a/l/t5/78/9788490321478.jpg",
                        4,false,bibliotecas[1]),

                Copia("FAHRENHEIT 451","RAY_BRADBURY","Fahrenheit 451: la temperatura a la que el papel se enciende y arde." +
                        " Guy Montag es un bombero y el trabajo de un bombero es quemar libros, que están prohibidos porque son causa de discordia y sufrimiento.",
                        "CIENCIA_FICCION",
                        "9788490321478","DEBOLSILLO",
                        "https://imagessl8.casadellibro.com/a/l/t5/78/9788490321478.jpg",
                        5,false,bibliotecas[1]),

                Copia("22/11/63","STEPHEN_KING","Viajar al pasado nunca ha sido tan creíble." +
                        " Ni tan terrorífico.Jake Epping es un profesor de inglés que se gana un sueldo extra impartiendo clases nocturnas a" +
                        " adultos con problemas de lectura.",
                        "CIENCIA_FICCION",
                        "9788490321287","DEBOLSILLO",
                        "https://imagessl7.casadellibro.com/a/l/t5/87/9788490321287.jpg",
                        2,false,bibliotecas[1]),

                //COPIAS EN INFANTA ELENA
                Copia("DUNE","FRANK_HERBERT","Dune es la primera novela de la serie homónima «Dune» de Frank Herbert," +
                        " una obra maestra unánimemente reconocida como la mejor saga de ciencia ficción de todos los tiempos.",
                        "CIENCIA_FICCION",
                        "9788497596824","DEBOLSILLO",
                        "https://imagessl4.casadellibro.com/a/l/t5/24/9788497596824.jpg",
                        4,false,bibliotecas[2]),

                Copia("DUNE","FRANK_HERBERT","Dune es la primera novela de la serie homónima «Dune» de Frank Herbert," +
                        " una obra maestra unánimemente reconocida como la mejor saga de ciencia ficción de todos los tiempos.",
                        "CIENCIA_FICCION",
                        "9788497596824","DEBOLSILLO",
                        "https://imagessl4.casadellibro.com/a/l/t5/24/9788497596824.jpg",
                        5,false,bibliotecas[2]),

                Copia("FAHRENHEIT 451","RAY_BRADBURY","Fahrenheit 451: la temperatura a la que el papel se enciende y arde." +
                        " Guy Montag es un bombero y el trabajo de un bombero es quemar libros, que están prohibidos porque son causa de discordia y sufrimiento.",
                        "CIENCIA_FICCION",
                        "9788490321478","DEBOLSILLO",
                        "https://imagessl8.casadellibro.com/a/l/t5/78/9788490321478.jpg",
                        6,false,bibliotecas[2]),

                Copia("FAHRENHEIT 451","RAY_BRADBURY","Fahrenheit 451: la temperatura a la que el papel se enciende y arde." +
                        " Guy Montag es un bombero y el trabajo de un bombero es quemar libros, que están prohibidos porque son causa de discordia y sufrimiento.",
                        "CIENCIA_FICCION",
                        "9788490321478","DEBOLSILLO",
                        "https://imagessl8.casadellibro.com/a/l/t5/78/9788490321478.jpg",
                        7,false,bibliotecas[2]),

                Copia("22/11/63","STEPHEN_KING","Viajar al pasado nunca ha sido tan creíble." +
                        " Ni tan terrorífico.Jake Epping es un profesor de inglés que se gana un sueldo extra impartiendo clases nocturnas a" +
                        " adultos con problemas de lectura.",
                        "CIENCIA_FICCION",
                        "9788490321287","DEBOLSILLO",
                        "https://imagessl7.casadellibro.com/a/l/t5/87/9788490321287.jpg",
                        3,false,bibliotecas[2])
        )

        userRepository.saveAll(usuarios)
        bibliotecaRepository.saveAll(bibliotecas)
        copiaRepository.saveAll(copias)
    }
}