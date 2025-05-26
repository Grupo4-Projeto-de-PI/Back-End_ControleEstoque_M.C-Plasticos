package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.TipoComercialRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tipoComercial")
class TipoComercialController(val repositorio: TipoComercialRepositorio) {

    @GetMapping
    fun listarTipoComercial(): ResponseEntity<List<TipoComercial>>{

        val tipoComercial = repositorio.findAll();

        return if(tipoComercial.isEmpty()) {
            ResponseEntity.status(204).build()
        }else{
            ResponseEntity.status(200).body(tipoComercial)
        }

    }


//    @PostMapping("/criar")
//    fun criarTipoComercial(@RequestBody novoTipo: TipoComercial): ResponseEntity<TipoComercial>{
//        novoTipo.id = null
//        val tipoComercial = repositorio.save(novoTipo)
//        return ResponseEntity.status(201).body(tipoComercial)
//    }
//
//    @DeleteMapping("/deletar/{id}")
//    fun deletarTipoComercial(@PathVariable id: Int):ResponseEntity<Void>{
//        if (repositorio.existsById(id)){
//            repositorio.deleteById(id)
//            return ResponseEntity.status(204).build()
//        }
//
//        return ResponseEntity.status(404).build()
//    }
//
//    @PatchMapping("/tipo/{id}/{tipo}")
//    fun atualizarTipoComercial(@PathVariable id: Int, @PathVariable tipo: String): ResponseEntity<TipoComercial>{
//
//        if (repositorio.existsById(id)) {
//            val tipoEncontrado = repositorio.findById(id).get()
//            tipoEncontrado.tipo = tipo
//            return ResponseEntity.status(200).body(tipoEncontrado)
//
//        }
//        return ResponseEntity.status(404).build()
//    }








}