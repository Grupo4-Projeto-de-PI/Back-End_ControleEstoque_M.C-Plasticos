package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Entrada
import org.springframework.data.jpa.repository.JpaRepository

interface EntradaRepositorio : JpaRepository<Entrada, Int> {


}