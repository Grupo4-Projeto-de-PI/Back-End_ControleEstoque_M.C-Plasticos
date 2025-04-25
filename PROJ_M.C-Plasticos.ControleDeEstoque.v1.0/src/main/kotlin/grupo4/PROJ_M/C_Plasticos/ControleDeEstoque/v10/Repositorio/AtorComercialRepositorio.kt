package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.AtorComercial
import org.springframework.data.jpa.repository.JpaRepository

interface AtorComercialRepositorio: JpaRepository<AtorComercial, Int> {
}