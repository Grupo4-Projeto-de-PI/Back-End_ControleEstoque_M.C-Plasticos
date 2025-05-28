package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.TransacaoDto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum

data class EditarTransacaoDto(
    val fkProduto: Int? = null,
    val peso: Double? = null,
    val preco: Double? = null,
    val tipoOperacao: tipoOperacaoEnum? = null,
    val categoria: categoriaEnum? = null,
    val fkParceiroComercial: Int? = null,
 )