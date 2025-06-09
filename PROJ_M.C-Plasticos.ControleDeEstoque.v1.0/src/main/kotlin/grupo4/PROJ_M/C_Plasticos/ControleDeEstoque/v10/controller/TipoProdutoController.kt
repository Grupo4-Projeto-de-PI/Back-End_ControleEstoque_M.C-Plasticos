package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.tipoProdutoDto.AtualizarTipoProdutoDTO
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoProduto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.TipoProdutoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/tipo-produto")
class TipoProdutoController(val tipoProdutoService : TipoProdutoService) {

    @GetMapping
    fun listarTodosTiposProdutos() : ResponseEntity<List<TipoProduto>> {
        return tipoProdutoService.listarTodosTiposProdutos()
    }

    @PostMapping
    fun cadastrarTipoProduto(@RequestBody tipoProduto: TipoProduto): ResponseEntity<TipoProduto> {
        return tipoProdutoService.cadastrarTipoProduto(tipoProduto)
    }

    @DeleteMapping("/{id}")
    fun deletarTipoProduto(@PathVariable id: Int): ResponseEntity<Void> {
        return tipoProdutoService.deletarTipoProduto(id)
    }

    @PatchMapping("/{id}")
    fun atualizarTipoProduto(
        @PathVariable id: Int,
        @RequestBody dto: AtualizarTipoProdutoDTO
    ): ResponseEntity<TipoProduto> {
        return tipoProdutoService.atualizarTipoProduto(id, dto)
    }

}