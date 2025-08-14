package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.FiltroTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.transacaoDto.EditarTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.transacaoDto.NovaTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service.TransacaoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transacoes")
class TransacaoController(
    val transacaoService: TransacaoService
) {

    @PostMapping
    fun post(@RequestBody novaTransacao: NovaTransacaoDto): ResponseEntity<out Any> {
        return transacaoService.criarTransacao(novaTransacao)
    }

    @GetMapping
    fun listaTransacoes(): ResponseEntity<List<Transacao>> {
        return transacaoService.listarTransacoes()
    }

    @GetMapping("/{id}")
    fun listaTransacaoPorId(@PathVariable id: Int): ResponseEntity<Transacao> {
        return transacaoService.listarTransacaoPorId(id)
    }

    @PostMapping("/filtro")
    fun filtrarTransacoes(@RequestBody filtro: FiltroTransacaoDto): ResponseEntity<List<Transacao>> {
        return transacaoService.filtrarTransacoes(filtro)
    }

    @PutMapping("/{idTransacao}")
    fun atualizarTransacao(
        @PathVariable idTransacao: Int,
        @RequestBody transacaoAtualizada: EditarTransacaoDto
    ): ResponseEntity<Transacao> {
        return transacaoService.atualizarTransacao(idTransacao, transacaoAtualizada)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        return transacaoService.deletarTransacao(id)
    }
}