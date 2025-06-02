package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.LogTransacaoDto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.logTransacaoEnum.logTransacaoEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import java.time.LocalDateTime

data class LogTransacaoDto(
    val id: Int? = null,
    val peso: Double? = null,
    val valorTotal: Double? = null,
    val tipoEdicao: logTransacaoEnum? = null,
    val tipoOperacao: tipoOperacaoEnum? = null,
    val dataLog: LocalDateTime = LocalDateTime.now(),
    val fkTransacaoId: Int? = null,
    val fkTransacaoProdutoId: Int? = null

)
