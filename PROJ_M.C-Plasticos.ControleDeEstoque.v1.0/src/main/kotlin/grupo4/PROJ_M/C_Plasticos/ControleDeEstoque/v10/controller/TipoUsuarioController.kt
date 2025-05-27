package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoUsuario
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.TipoUsuarioRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tipoUsuario")
class TipoUsuarioController(val repositorio: TipoUsuarioRepositorio) {

    @GetMapping
    fun listarTipoUsuario(): ResponseEntity<List<TipoUsuario>>{
        val tipoUsuario = repositorio.findAll()

        if (tipoUsuario.isEmpty()){
           return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(tipoUsuario)

    }

    @PostMapping
    fun criarTipoUsuario(@RequestBody novoTipo: TipoUsuario): ResponseEntity<TipoUsuario>{
        novoTipo.id = null
        val tipoSalvo = repositorio.save(novoTipo)
        return ResponseEntity.status(201).body(tipoSalvo)
    }

    @DeleteMapping("/{id}")
    fun deletarTipoUsuario(@PathVariable id: Int): ResponseEntity<Void>{
        if (repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(404).build()
    }

    @GetMapping("/{id}")
    fun listarPorId(@PathVariable id: Int): ResponseEntity<TipoUsuario>{
        val listar = repositorio.findById(id)
        return ResponseEntity.of(listar)
    }




}