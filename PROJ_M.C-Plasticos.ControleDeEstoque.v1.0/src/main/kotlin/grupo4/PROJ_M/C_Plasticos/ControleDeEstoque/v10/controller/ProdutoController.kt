package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.ProdutoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produto")
class ProdutoController(val produtoService: ProdutoService) {

    @GetMapping
    fun listarTodosProdutos(): ResponseEntity<List<Produto>> {
        return produtoService.listarTodosProdutos()
    }

    @GetMapping("/id")
    fun getProdutoId(@RequestParam id: Int): ResponseEntity<Produto> {
        return produtoService.getProdutoPorId(id)
    }

    @PostMapping
    fun criarProduto(@RequestBody novoProduto: Produto): ResponseEntity<Produto> {
        return produtoService.criarProduto(novoProduto)
    }

    @DeleteMapping("/{id}")
    fun deletarProduto(@PathVariable id: Int): ResponseEntity<Void> {
        return produtoService.deletarProduto(id)
    }

    @PutMapping("/atualizar-produto/{id}")
    fun atualizarTodoProduto(@PathVariable id: Int, @RequestBody produtosAtualizado: Produto): ResponseEntity<Produto> {
        return produtoService.atualizarProduto(id, produtosAtualizado)
    }

    @PatchMapping("/produtos-preco/{id}/{preco}")
    fun patchProdutosPreco(@PathVariable id: Int, @PathVariable preco: Double): ResponseEntity<Produto> {
        return produtoService.atualizarPrecoProduto(id, preco)
    }
}