package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.tipoProdutoDto.AtualizarTipoProdutoDTO
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoProduto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.TipoProdutoRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TipoProdutoService(
    val repositorio : TipoProdutoRepositorio
) {

    fun listarTodosTiposProdutos(): ResponseEntity<List<TipoProduto>> {
        val tiposProdutos = repositorio.findAll()
        return if (tiposProdutos.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(tiposProdutos)
        }
    }

    fun cadastrarTipoProduto(tipoProduto: TipoProduto): ResponseEntity<TipoProduto> {
        val tipoProdutoSalvo = repositorio.save(tipoProduto)
        return ResponseEntity.status(201).body(tipoProdutoSalvo)
    }

    fun deletarTipoProduto(id: Int): ResponseEntity<Void> {
        return if (repositorio.existsById(id)) {
            repositorio.deleteById(id)
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(404).build()
        }
    }
    fun atualizarTipoProduto(id: Int, dto: AtualizarTipoProdutoDTO): ResponseEntity<TipoProduto> {
        val existente = repositorio.findById(id)

        return if (existente.isPresent) {
            val tipoProduto = existente.get()
            dto.tipo.let { tipoProduto.tipo = it }
            val tipoProdutoAtualizado = repositorio.save(tipoProduto)
            ResponseEntity.ok(tipoProdutoAtualizado)
        } else {
            ResponseEntity.notFound().build()
        }
    }

}