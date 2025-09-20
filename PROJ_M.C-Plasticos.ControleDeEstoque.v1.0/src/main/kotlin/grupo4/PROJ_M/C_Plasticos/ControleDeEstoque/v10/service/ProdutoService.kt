package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.produtoDto.AtualizarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.produtoDto.CriarProdutoDto
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

    fun criarProduto(novoProduto: CriarProdutoDto): ResponseEntity<Produto> {

        val existeTipoProduto = tipoProdutoRepositorio.findById(novoProduto.tipo)
        val usuarioEncontrado = usuarioRepositorio.findById(novoProduto.fkUsuario)

        if(!existeTipoProduto.isPresent || !usuarioEncontrado.isPresent){
            return ResponseEntity.status(400).body("Tipo de produto ou usuário não encontrado".let { null })
        }

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

    fun adicionarImagem(imagem: ByteArray, id: Int): ResponseEntity<Void> {
        val produtoAchado = repositorio.findById(id).get()
        produtoAchado.fotoProduto = imagem
        repositorio.save(produtoAchado)
        return ResponseEntity.status(200).build()
    }

    fun getFoto(@PathVariable id: Int): ResponseEntity<ByteArray>{
        val produto = repositorio.findById(id).get()
        val fotoProduto = produto.fotoProduto
        return ResponseEntity.status(200).body(fotoProduto)
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