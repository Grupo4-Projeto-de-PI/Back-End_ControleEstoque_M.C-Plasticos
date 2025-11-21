package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.AtualizarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.CriarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.ProdutoDetalhesDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper.ProdutoHelper
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ProdutoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.TipoProdutoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.UsuarioRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class ProdutoService(
    val repositorio: ProdutoRepositorio,
    val tipoProdutoRepositorio: TipoProdutoRepositorio,
    val usuarioRepositorio: UsuarioRepositorio,
    val produtoHelper: ProdutoHelper
) {

    fun listarTodosProdutos(): ResponseEntity<List<Produto>> {
        val produtos = repositorio.findAll()
        return if (produtos.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(produtos)
        }
    }

    fun getProdutoPorNome(nome: String): ResponseEntity<List<Produto>> {
        val produtos = repositorio.findByNome(nome)
        return if (produtos.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(produtos)
        }
    }

    fun getProdutoPorId(id: Int): ResponseEntity<ProdutoDetalhesDto> {
        val produto = repositorio.findProdutoComDetalhes(id)
        return ResponseEntity.status(200).body(produto[0])
    }

    fun getProdutoPorTipo(tipoId: Int): ResponseEntity<List<Produto>> {
        val produtos = repositorio.findByTipoProduto(tipoId)
        return if (produtos.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(produtos)
        }
    }

    fun criarProduto(novoProduto: CriarProdutoDto): ResponseEntity<Produto> {

        val existeTipoProduto = tipoProdutoRepositorio.findById(novoProduto.tipo)
        val usuarioEncontrado = usuarioRepositorio.findById(novoProduto.fkUsuario)
        val fotoBytes: ByteArray? = novoProduto.fotoProduto?.bytes

        val produto = Produto(
            nome = novoProduto.nome,
            tipo = existeTipoProduto.get(),
            prioridade = novoProduto.prioridade,
            fkUsuario = usuarioEncontrado.get(),
            preco = 0.0,
            fotoProduto = fotoBytes,
        )

        repositorio.save(produto)
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

    fun atualizarProduto(id: Int, produtosAtualizado: AtualizarProdutoDto): ResponseEntity<Produto> {
        if(!repositorio.existsById(id)) {
            return ResponseEntity.status(404).build()
        }
        val produtoExistente = repositorio.findById(id).get()
        produtoHelper.atualizarProduto(produtosAtualizado, produtoExistente)

        repositorio.save(produtoExistente)
        return ResponseEntity.status(200).body(produtoExistente)
    }
}