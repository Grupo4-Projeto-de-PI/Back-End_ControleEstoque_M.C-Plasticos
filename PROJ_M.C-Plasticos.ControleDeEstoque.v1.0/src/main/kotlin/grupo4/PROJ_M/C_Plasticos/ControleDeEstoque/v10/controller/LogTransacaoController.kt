package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.LogTransacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.LogTransacaoRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/log-transacao")
class LogTransacaoController(val repositorio: LogTransacaoRepositorio) {

    @GetMapping
    fun listar(): List<LogTransacao> = repositorio.findAll()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<LogTransacao> {
        val log = repositorio.findById(id)
        return if (log.isPresent) ResponseEntity.ok(log.get())
        else ResponseEntity.notFound().build()
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody logEntrada: LogTransacao): ResponseEntity<LogTransacao> {
        val existe = repositorio.findById(id)
        return if (existe.isPresent) {
            val atualizado = repositorio.save(logEntrada.copy(id = id))
            ResponseEntity.ok(atualizado)
        } else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Int): ResponseEntity<Void> {
        val existe = repositorio.findById(id)
        return if (existe.isPresent) {
            repositorio.deleteById(id)
            ResponseEntity.noContent().build()
        } else ResponseEntity.notFound().build()
    }
}