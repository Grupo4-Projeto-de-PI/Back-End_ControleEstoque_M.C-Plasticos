package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.AtualizarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.TipoProdutoRepositorio
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class ProdutoHelper(
    private val tipoProdutoRepositorio: TipoProdutoRepositorio,
) {

    fun atualizarNome(nome: String?, produto: Produto) {
        nome?.let { produto.nome = it }
    }

    fun atualizarTipoProduto(tipoId: Int?, produto: Produto) {
        tipoId?.let {
            val tipoProduto = tipoProdutoRepositorio.findById(it)
            if (!tipoProduto.isPresent) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de produto não encontrado")
            }
            produto.tipo = tipoProduto.get()
        }
    }

    fun atualizarProduto(produtoAtualizado: AtualizarProdutoDto, produtoExistente: Produto) {
        atualizarNome(produtoAtualizado.nome, produtoExistente)
        atualizarTipoProduto(produtoAtualizado.tipo, produtoExistente)
    }
}