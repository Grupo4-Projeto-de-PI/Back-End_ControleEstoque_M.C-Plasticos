package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.PapelComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.PapelComercialRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/papelComercial")
class PapelComercialController(val repositorio: PapelComercialRepositorio) {

    @GetMapping("/listar")
    fun listarPapelComercial(): ResponseEntity<List<PapelComercial>>{
        val papelComercial = repositorio.findAll()

        if (papelComercial.isEmpty()){
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(papelComercial)
    }


    @PostMapping("/criar")
    fun criarPapelComercial(@RequestBody novoPapel: PapelComercial): ResponseEntity<PapelComercial>{
        novoPapel.id = null
        val papelSalvo = repositorio.save(novoPapel)
        return ResponseEntity.status(201).body(papelSalvo)
    }


    @DeleteMapping("/deletar/{id}")
    fun deletarPorId(@PathVariable id: Int): ResponseEntity<Void>{
        if (repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(404).build()
    }

}