package Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Cliente
import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.ClienteRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Clientes")
class ClienteController(val repositorio: ClienteRepositorio) {

    @GetMapping
    fun getListaCliente(): ResponseEntity<List<Cliente>> {

        val Clientes = repositorio.findAll()

        return if (Clientes.isEmpty()){

            ResponseEntity.status(204).build()

        } else {

            ResponseEntity.status(200).body(Clientes)

        }

    }

    @PostMapping
    fun post(@RequestBody novoCliente: Cliente): ResponseEntity<Cliente> {
        val Clientes = repositorio.save(novoCliente)
        return ResponseEntity.status(201).body(Clientes)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {

        if(repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

    }

    @GetMapping("/{id}")
    fun getClienteId(@PathVariable id:Int): ResponseEntity<Cliente> {

        val Clientes = repositorio.findById(id)

        return ResponseEntity.of(Clientes)

    }

//    @GetMapping("/{tipo}")
//    fun getClienteTipo(@PathVariable tipo:String): ResponseEntity<Cliente> {
//
//        val Clientes = repositorio.findBy(tipo)
//
//        return ResponseEntity.of(Clientes)
//
//    }

    @PutMapping("/{id}")
    fun put(@PathVariable id:Int, @RequestBody ClientesAtualizados: Cliente): ResponseEntity<Cliente> {

        if (repositorio.existsById(id)) {

            ClientesAtualizados.id = id
            val Clientes = repositorio.save(ClientesAtualizados)
            return ResponseEntity.status(200).body(Clientes)
        }

        return ResponseEntity.status(404).build()

    }

    @PatchMapping("/Clientes-telefone/{id}/{tel}")
    fun patchClientesTelefone(@PathVariable id:Int, @PathVariable tel:Int): ResponseEntity<Cliente> {
        if (repositorio.existsById(id)) {
            val ClientesEncontrados = repositorio.findById(id).get()
            ClientesEncontrados.telefone = tel
            return ResponseEntity.status(200).body(ClientesEncontrados)
        }

        return ResponseEntity.status(404).build()
    }

    @PatchMapping("/Clientes-Nome/{id}/{nome}")
    fun patchClientesNome(@PathVariable id:Int, @PathVariable nome:String): ResponseEntity<Cliente> {
        if (repositorio.existsById(id)) {
            val ClientesEncontrados = repositorio.findById(id).get()
            ClientesEncontrados.nome = nome
            return ResponseEntity.status(200).body(ClientesEncontrados)
        }

        return ResponseEntity.status(404).build()
    }


}