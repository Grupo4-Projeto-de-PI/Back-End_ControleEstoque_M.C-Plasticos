package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Categoria
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.CategoriaRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categorias")
class CategoriaController(val repositorio: CategoriaRepositorio) {

    @GetMapping
    fun getListaCategoria():ResponseEntity<List<Categoria>>{

        val categorias = repositorio.findAll()

        return if (categorias.isEmpty()){

            ResponseEntity.status(204).build()

        } else {

            ResponseEntity.status(200).body(categorias)

        }

    }

    @PostMapping
    fun post(@RequestBody novacategoria: Categoria):ResponseEntity<Categoria> {
        val categorias = repositorio.save(novacategoria)
        return ResponseEntity.status(201).body(categorias)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int):ResponseEntity<Void> {

        if(repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

    }


    @PatchMapping("/categorias-Tipo/{id}/{tipo}")
    fun patchCategoriasTipo(@PathVariable id:Int, @PathVariable tipo:String): ResponseEntity<Categoria> {
        if (repositorio.existsById(id)) {
            val categoriasEncontrados = repositorio.findById(id).get()
            categoriasEncontrados.tipo = tipo
            return ResponseEntity.status(200).body(categoriasEncontrados)
        }

        return ResponseEntity.status(404).build()
    }



}