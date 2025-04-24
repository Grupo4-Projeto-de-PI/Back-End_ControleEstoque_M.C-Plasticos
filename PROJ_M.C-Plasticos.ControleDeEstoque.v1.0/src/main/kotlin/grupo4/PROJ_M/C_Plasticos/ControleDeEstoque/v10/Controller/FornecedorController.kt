package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller


import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Fornecedor
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.FornecedorRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/fornecedores")
class FornecedorController(val repositorio: FornecedorRepositorio) {

    @GetMapping
    fun getListafornecedor():ResponseEntity<List<Fornecedor>>{

        val fornecedores = repositorio.findAll()

        return if (fornecedores.isEmpty()){

            ResponseEntity.status(204).build()

        } else {

            ResponseEntity.status(200).body(fornecedores)

        }

    }

    @PostMapping
    fun post(@RequestBody novosFornecedores: Fornecedor):ResponseEntity<Fornecedor> {
        val fornecedores = repositorio.save(novosFornecedores)
        return ResponseEntity.status(201).body(fornecedores)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int):ResponseEntity<Void> {

        if(repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

    }


    @PatchMapping("/fornecedores-Tipo/{id}/{tipo}")
    fun patchfornecedorsTipo(@PathVariable id:Int, @PathVariable tipo:String): ResponseEntity<Fornecedor> {
        if (repositorio.existsById(id)) {
            val fornecedoresEncontrados = repositorio.findById(id).get()
            fornecedoresEncontrados.tipo = tipo
            return ResponseEntity.status(200).body(fornecedoresEncontrados)
        }

        return ResponseEntity.status(404).build()
    }

    @PatchMapping("/fornecedores-Telefone/{id}/{telefone}")
    fun patchFornecedoresTelefone(@PathVariable id:Int, @PathVariable telefone:Int): ResponseEntity<Fornecedor> {
        if (repositorio.existsById(id)) {
            val fornecedoresEncontrados = repositorio.findById(id).get()
            fornecedoresEncontrados.telefone = telefone
            return ResponseEntity.status(200).body(fornecedoresEncontrados)
        }

        return ResponseEntity.status(404).build()
    }

    @PatchMapping("/fornecedores-Nome/{id}/{nome}")
    fun patchFornecedoresNome(@PathVariable id:Int, @PathVariable nome:String): ResponseEntity<Fornecedor> {
        if (repositorio.existsById(id)) {
            val fornecedoresEncontrados = repositorio.findById(id).get()
            fornecedoresEncontrados.nome = nome
            return ResponseEntity.status(200).body(fornecedoresEncontrados)
        }

        return ResponseEntity.status(404).build()
    }

}