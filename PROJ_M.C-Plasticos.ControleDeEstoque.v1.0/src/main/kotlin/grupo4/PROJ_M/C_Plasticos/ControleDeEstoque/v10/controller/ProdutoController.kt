package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.CriarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.CriarProdutoSemArquivoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.AtualizarProdutoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.ProdutoDto.ProdutoDetalhesDto
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
    fun getProdutoId(@RequestParam id: Int): ResponseEntity<ProdutoDetalhesDto> {
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

    @PutMapping("/atualizar-produto/{id}", consumes = ["multipart/form-data"])
    fun atualizarTodoProduto(
        @PathVariable id: Int,
        @RequestPart("produto") produtoAtualizado: AtualizarProdutoDto,
        @RequestPart(value = "fotoProduto", required = false) fotoProduto: MultipartFile?
    ): ResponseEntity<Produto> {
        val produtoCompletoAtualizado = AtualizarProdutoDto(
            nome = produtoAtualizado.nome,
            tipo = produtoAtualizado.tipo,
            prioridade = produtoAtualizado.prioridade,
            fotoProduto = fotoProduto
        )
        return produtoService.atualizarProduto(id, produtoCompletoAtualizado)
    }
}