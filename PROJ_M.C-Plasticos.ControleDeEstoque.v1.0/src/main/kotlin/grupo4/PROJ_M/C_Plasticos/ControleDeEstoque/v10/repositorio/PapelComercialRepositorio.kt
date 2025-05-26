package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.PapelComercial
import org.springframework.data.jpa.repository.JpaRepository

interface PapelComercialRepositorio: JpaRepository<PapelComercial, Int> {
}