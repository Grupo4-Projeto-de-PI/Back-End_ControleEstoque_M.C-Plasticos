package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.TipoComercial
import org.springframework.data.jpa.repository.JpaRepository

interface TipoComercialRepositorio: JpaRepository<TipoComercial, Int> {
}