package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.ParceiroComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.ParceiroComercialService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/parceiro-comercial")
class ParceiroComercialController(val parceiroComercialService: ParceiroComercialService) {

    @GetMapping
    fun getListaParceiroComercial(): ResponseEntity<List<ParceiroComercial>> {
        return parceiroComercialService.listarTodosParceiros()
    }

    @GetMapping("/{id}")
    fun getParceiroPorId(@PathVariable id: Int): ResponseEntity<ParceiroComercial> {
        return parceiroComercialService.obterParceiroPorId(id)
    }

    @PostMapping
    fun post(@Valid @RequestBody novosFornecedores: ParceiroComercial): ResponseEntity<ParceiroComercial> {
        return parceiroComercialService.criarParceiro(novosFornecedores)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        return parceiroComercialService.deletarParceiro(id)
    }

    @PutMapping("/{id}")
    fun atualizarParceiro(
        @PathVariable id: Int,
        @Valid @RequestBody parceiroAtualizado: ParceiroComercial
    ): ResponseEntity<ParceiroComercial> {
        return parceiroComercialService.atualizarParceiro(id, parceiroAtualizado)
    }
}