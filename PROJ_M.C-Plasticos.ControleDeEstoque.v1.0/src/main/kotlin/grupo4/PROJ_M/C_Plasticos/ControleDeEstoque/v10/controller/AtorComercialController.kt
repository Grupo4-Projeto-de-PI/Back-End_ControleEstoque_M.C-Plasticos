package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller


import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.AtorComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.AtorComercialRepositorio
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/ator-comercial")
class AtorComercialController(val repositorio: AtorComercialRepositorio) {

    @GetMapping
    fun getListaAtorComercial():ResponseEntity<List<AtorComercial>>{

        val atoresComercial = repositorio.findAll()

        return if (atoresComercial.isEmpty()){

            ResponseEntity.status(204).build()

        } else {

            ResponseEntity.status(200).body(atoresComercial)

        }

    }

    @PostMapping
    fun post(@Valid @RequestBody novosFornecedores: AtorComercial):ResponseEntity<AtorComercial> {
        val salvandoNovoAtor = repositorio.save(novosFornecedores)
        return ResponseEntity.status(201).body(salvandoNovoAtor)
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int):ResponseEntity<Void> {

        if(repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

    }

    //Alterando a FkTipoPessoa onde 1 = PF e 2 = PJ
    @PatchMapping("/tipo-ator/{id}/{tipoPessoa}")
    fun patchTipoAtorComercial(@PathVariable id:Int, @PathVariable tipoPessoa: TipoComercial): ResponseEntity<AtorComercial> {
        if (repositorio.existsById(id)) {
            val atoresEncontrados = repositorio.findById(id).get()
            atoresEncontrados.fkTipoPessoa = tipoPessoa
            repositorio.save(atoresEncontrados)
            return ResponseEntity.status(200).body(atoresEncontrados)
        }

        return ResponseEntity.status(404).build()
    }

    @PatchMapping("/papel-comercial/{id}/{tipoAtor}")
    fun patchPapelAtorComercial(@PathVariable id:Int, @PathVariable tipoAtor: AtorComercial): ResponseEntity<AtorComercial> {
        if (repositorio.existsById(id)) {
            val atoresEncontrados = repositorio.findById(id).get()
            atoresEncontrados.fkPapelComercial = tipoAtor.fkPapelComercial
            repositorio.save(atoresEncontrados)
            return ResponseEntity.status(200).body(atoresEncontrados)
        }

        return ResponseEntity.status(404).build()
    }
//
    @PatchMapping("/ator-telefone/{id}/{telefone}")
    fun patchFornecedoresTelefone(@PathVariable id:Int, @PathVariable telefone: AtorComercial): ResponseEntity<AtorComercial> {
        if (repositorio.existsById(id)) {
            val atorEncontrado = repositorio.findById(id).get()
            atorEncontrado.telefone = telefone.telefone
            repositorio.save(atorEncontrado)
            return ResponseEntity.status(200).body(atorEncontrado)
        }

        return ResponseEntity.status(404).build()
    }

    @PatchMapping("/ator-nome/{id}/{nome}")
    fun patchFornecedoresNome(@PathVariable id:Int, @PathVariable nome:String): ResponseEntity<AtorComercial> {
        if (repositorio.existsById(id)) {
            val atorEncontrado = repositorio.findById(id).get()
            atorEncontrado.nome = nome
            repositorio.save(atorEncontrado)
            return ResponseEntity.status(200).body(atorEncontrado)
        }

        return ResponseEntity.status(404).build()
    }

}