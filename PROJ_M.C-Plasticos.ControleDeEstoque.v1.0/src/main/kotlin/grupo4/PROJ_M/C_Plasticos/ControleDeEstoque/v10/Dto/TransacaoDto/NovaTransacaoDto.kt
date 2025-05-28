package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.TransacaoDto

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import jakarta.validation.constraints.NotBlank

data class NovaTransacaoDto(
    @field: NotBlank(message = "O ID do produto é obrigatório")
    val fkProduto: Int,
    @field: NotBlank(message = "O ID da categoria é obrigatório")
    val categoria: categoriaEnum,
    @field: NotBlank(message = "Insira o peso da transação")
    val peso: Double,
    @field: NotBlank(message = "Insira o preço da transação")
    val valorTotal: Double,
    @field: NotBlank(message = "Insira o tipo de operação")
    val tipoOperacao: tipoOperacaoEnum,
    @field: NotBlank(message = "Insira id do parceiro comercial")
    val fkParceiroComercial: Int,
    @field: NotBlank(message = "Insira o ID do usuário que realizou a transação")
    val fkUsuario: Int,
)
