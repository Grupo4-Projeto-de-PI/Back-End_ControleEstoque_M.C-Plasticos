package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.TransacaoDto

 data class EditarTransacaoDto(
     val fkProduto: Int? = null,
     val peso: Double? = null,
     val preco: Double? = null,
     val fkTipoOperacao: Int? = null,
     val fkCategoria: Int? = null,
     val fkParceiroComercial: Int? = null,
 )