package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.AtualizarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.CriarProdutoDto
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

    @GetMapping("/tipo/{tipoId}")
    fun getProdutoTipo(@PathVariable tipoId: Int): ResponseEntity<List<Produto>> {
        return produtoService.getProdutoPorTipo(tipoId)
    }

    @PostMapping
    fun criarProduto(@RequestBody novoProduto: CriarProdutoDto): ResponseEntity<Produto> {
        return produtoService.criarProduto(novoProduto)
    }

    @DeleteMapping("/{id}")
    fun deletarProduto(@PathVariable id: Int): ResponseEntity<Void> {
        return produtoService.deletarProduto(id)
    }

    @PutMapping("/atualizar-produto/{id}")
    fun atualizarTodoProduto(@PathVariable id: Int, @RequestBody produtosAtualizado: AtualizarProdutoDto): ResponseEntity<Produto> {
        return produtoService.atualizarProduto(id, produtosAtualizado)
    }
}