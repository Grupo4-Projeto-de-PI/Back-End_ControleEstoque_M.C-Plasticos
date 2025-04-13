package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Cliente
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Entrada
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.EntradaRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/entradas")
class EntradaController(val repositorio: EntradaRepositorio) {

    //CREATE
    @PostMapping
    fun post(@RequestBody novaEntrada: Entrada): ResponseEntity<Entrada> {
        val entrada = repositorio.save(novaEntrada)
        return ResponseEntity.status(201).body(novaEntrada)
    }

    //READ
    @GetMapping
    fun listaEntradas(): ResponseEntity<List<Entrada>> {

        val entradas = repositorio.findAll()

        return if (entradas.isEmpty()){

            ResponseEntity.status(204).build()

        } else {

            ResponseEntity.status(200).body(entradas)

        }

    }

    //UPDATE
    @PatchMapping("/entradas-peso/{id}/{novoPeso}")
    fun patchClientesTelefone(@PathVariable id:Int, @PathVariable novPeso:Double): ResponseEntity<Entrada> {
        if (repositorio.existsById(id)) {
            val entradasEncontradas = repositorio.findById(id).get()
            entradasEncontradas.peso = novPeso
            return ResponseEntity.status(200).body(entradasEncontradas)
        }

        return ResponseEntity.status(404).build()
    }

    //DELETE
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {

        if(repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

    }


}