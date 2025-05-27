package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.ParceiroComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.tipoComercialEnum
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

    @PostMapping
    fun post(@Valid @RequestBody novosFornecedores: ParceiroComercial): ResponseEntity<ParceiroComercial> {
        return parceiroComercialService.criarParceiro(novosFornecedores)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        return parceiroComercialService.deletarParceiro(id)
    }

    @PatchMapping("/tipo-ator/{id}/{tipoPessoa}")
    fun patchTipoParceiroComercial(
        @PathVariable id: Int,
        @PathVariable tipoPessoa: tipoComercialEnum
    ): ResponseEntity<ParceiroComercial> {
        return parceiroComercialService.atualizarTipoParceiro(id, tipoPessoa)
    }

    @PatchMapping("/papel-comercial/{id}/{tipoAtor}")
    fun patchPapelParceiroComercial(
        @PathVariable id: Int,
        @PathVariable tipoAtor: ParceiroComercial
    ): ResponseEntity<ParceiroComercial> {
        return parceiroComercialService.atualizarPapelParceiro(id, tipoAtor)
    }

    @PatchMapping("/ator-telefone/{id}/{telefone}")
    fun patchFornecedoresTelefone(
        @PathVariable id: Int,
        @PathVariable telefone: ParceiroComercial
    ): ResponseEntity<ParceiroComercial> {
        return parceiroComercialService.atualizarTelefoneParceiro(id, telefone)
    }

    @PatchMapping("/ator-nome/{id}/{nome}")
    fun patchFornecedoresNome(
        @PathVariable id: Int,
        @PathVariable nome: String
    ): ResponseEntity<ParceiroComercial> {
        return parceiroComercialService.atualizarNomeParceiro(id, nome)
    }
}