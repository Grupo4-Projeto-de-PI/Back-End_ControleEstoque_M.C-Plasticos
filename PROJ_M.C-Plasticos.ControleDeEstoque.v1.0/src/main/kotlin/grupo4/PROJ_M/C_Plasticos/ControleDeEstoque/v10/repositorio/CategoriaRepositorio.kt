package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Categoria
import org.springframework.data.jpa.repository.JpaRepository


interface CategoriaRepositorio: JpaRepository<Categoria, Int> {
}