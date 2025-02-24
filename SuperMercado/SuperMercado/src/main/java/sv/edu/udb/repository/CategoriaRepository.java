package sv.edu.udb.repository;

import sv.edu.udb.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
//Utiliza Spring Data JPA para crear interfaces de repositorio que permitan interactuar con la base de datos
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
