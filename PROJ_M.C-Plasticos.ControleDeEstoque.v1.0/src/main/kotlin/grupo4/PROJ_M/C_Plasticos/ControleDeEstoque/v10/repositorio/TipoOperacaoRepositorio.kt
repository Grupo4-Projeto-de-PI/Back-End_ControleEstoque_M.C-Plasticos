package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoOperacao
import org.springframework.data.jpa.repository.JpaRepository

interface TipoOperacaoRepositorio: JpaRepository<TipoOperacao, Int> {
}