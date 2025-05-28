package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.TransacaoDto.EditarTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ParceiroComercialRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ProdutoRepositorio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class TransacaoHelper (
    val produtoRepositorio: ProdutoRepositorio,
    val parceiroComercialRepositorio: ParceiroComercialRepositorio
) {

    fun atualizarTransacao(transacaoAtualizada: EditarTransacaoDto, transacaoExistente: Transacao) {
        atualizarFkProduto(transacaoAtualizada.fkProduto, transacaoExistente)
        atualizarCategoria(transacaoAtualizada.categoria, transacaoExistente)
        atualizarTipoOperacao(transacaoAtualizada.tipoOperacao, transacaoExistente)
        atualizarPeso(transacaoAtualizada.peso, transacaoExistente)
        atualizarPreco(transacaoAtualizada.preco, transacaoExistente)
        atualizarFkParceiroComercial(transacaoAtualizada.fkParceiroComercial, transacaoExistente)
    }

     private fun atualizarFkProduto(fkProduto: Int?, transacao: Transacao) {
        fkProduto?.let { transacao.fkProduto = buscarId(produtoRepositorio, it) }
    }

     private fun atualizarCategoria(categoria: Int?, transacao: Transacao) {
        val categoriaEnum = when(categoria) {
            0 -> categoriaEnum.GR
            1 -> categoriaEnum.MS
            else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida")
        }
         transacao.categoria = categoriaEnum
    }

     private fun atualizarPeso(peso: Double?, transacao: Transacao) {
        peso?.let { transacao.peso = it }
    }

     private fun atualizarPreco(preco: Double?, transacao: Transacao) {
        preco?.let { transacao.valorTotal = it }
    }

     private fun atualizarTipoOperacao(tipoOperacao: Int?, transacao: Transacao) {
        val tipoOperacaoEnum = when(tipoOperacao) {
            0 -> tipoOperacaoEnum.Entrada
            1 -> tipoOperacaoEnum.Saida
            else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de operação inválido")
        }
         transacao.tipoOperacao = tipoOperacaoEnum
    }

     private fun atualizarFkParceiroComercial(fkParceiroComercial: Int?, transacao: Transacao) {
        fkParceiroComercial?.let { transacao.fkParceiroComercial = buscarId(parceiroComercialRepositorio, it) }
    }

     private fun <T> buscarId(repositorio: JpaRepository<T, Int>, id: Int): T {
        return repositorio.findById(id).orElseThrow {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Entidade não encontrada")
        }
    }
}