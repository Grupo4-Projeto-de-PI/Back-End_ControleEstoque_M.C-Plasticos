package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.EstoqueAtualService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/estoque-atual")
class EstoqueAtualController(val estoqueAtualService: EstoqueAtualService) {

    @GetMapping
    fun listarEstoqueAtual(): ResponseEntity<List<Map<String, Any>>> {
        val estoque = estoqueAtualService.listarEstoqueAtual()
        return if (estoque.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(estoque)
        }
    }

    @GetMapping("/produto/{nome}")
    fun buscarProdutoPorNome(@PathVariable nome: String): ResponseEntity<List<Map<String, Any>>> {
        val estoque = estoqueAtualService.buscarProdutoPorNome(nome)
        return if (estoque.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(estoque)
        }
    }

}