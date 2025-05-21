package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.LogTransacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.LogEntradaRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/log-transacao")
class LogEntradaController(val repositorio: LogEntradaRepositorio) {

    @GetMapping
    fun listar(): List<LogTransacao> = repositorio.findAll()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<LogTransacao> {
        val log = repositorio.findById(id)
        return if (log.isPresent) ResponseEntity.ok(log.get())
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun criar(@RequestBody logEntrada: LogTransacao): ResponseEntity<LogTransacao> {
        val salvo = repositorio.save(logEntrada)
        return ResponseEntity.ok(salvo)
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