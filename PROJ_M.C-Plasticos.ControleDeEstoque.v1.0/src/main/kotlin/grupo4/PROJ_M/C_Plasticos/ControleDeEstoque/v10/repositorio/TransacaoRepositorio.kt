package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import org.springframework.data.jpa.repository.JpaRepository

interface TransacaoRepositorio: JpaRepository <Transacao, Int> {

}