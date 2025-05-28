package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ProdutoRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProdutoService(val repositorio: ProdutoRepositorio) {

    fun listarTodosProdutos(): ResponseEntity<List<Produto>> {
        val produtos = repositorio.findAll()
        return if (produtos.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(produtos)
        }
    }

    fun getProdutoPorId(id: Int): ResponseEntity<Produto> {
        val produto = repositorio.findById(id)
        return ResponseEntity.of(produto)
    }

    fun getProdutoPorTipo(tipoId: Int): ResponseEntity<List<Produto>> {
        val produtos = repositorio.findByTipoProduto(tipoId)
        return if (produtos.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(produtos)
        }
    }

    fun criarProduto(novoProduto: Produto): ResponseEntity<Produto> {
        val produto = repositorio.save(novoProduto)
        return ResponseEntity.status(201).body(produto)
    }

    fun deletarProduto(id: Int): ResponseEntity<Void> {
        return if (repositorio.existsById(id)) {
            repositorio.deleteById(id)
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(404).build()
        }
    }

    fun atualizarProduto(id: Int, produtosAtualizado: Produto): ResponseEntity<Produto> {
        return if (repositorio.existsById(id)) {
            produtosAtualizado.id = id
            val produto = repositorio.save(produtosAtualizado)
            ResponseEntity.status(200).body(produto)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    fun atualizarPrecoProduto(id: Int, preco: Double): ResponseEntity<Produto> {
        return if (repositorio.existsById(id)) {
            val produtoEncontrado = repositorio.findById(id).get()
            produtoEncontrado.preco = preco
            repositorio.save(produtoEncontrado)
            ResponseEntity.status(200).body(produtoEncontrado)
        } else {
            ResponseEntity.status(404).build()
        }
    }
}