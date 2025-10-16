package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.produtoDto.AtualizarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.produtoDto.CriarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.produtoDto.CriarProdutoSemArquivoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.ProdutoService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

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

    @PostMapping(consumes = ["multipart/form-data"])
    fun criarProduto(
        @RequestPart("produto") novoProduto: CriarProdutoSemArquivoDto,
        @RequestPart(value = "fotoProduto", required = false) fotoProduto: MultipartFile?
    ): ResponseEntity<Produto> {

        println("produto recebido $novoProduto")
        println("arquivo recebido $fotoProduto")
        val produtoCompleto = CriarProdutoDto(
            nome = novoProduto.nome,
            tipo = novoProduto.tipo,
            prioridade = novoProduto.prioridade,
            fkUsuario = novoProduto.fkUsuario,
            fotoProduto = fotoProduto
        )

        return produtoService.criarProduto(produtoCompleto)
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