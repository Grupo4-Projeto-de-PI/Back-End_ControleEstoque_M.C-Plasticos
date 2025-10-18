package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.produtoEnum.ProdutoPrioridade
import jakarta.validation.constraints.NotBlank
import org.springframework.web.multipart.MultipartFile

data class CriarProdutoSemArquivoDto(
    @field: NotBlank
    val nome: String,
    val tipo: Int,
    val fkUsuario: Int,
    val prioridade: ProdutoPrioridade,
)