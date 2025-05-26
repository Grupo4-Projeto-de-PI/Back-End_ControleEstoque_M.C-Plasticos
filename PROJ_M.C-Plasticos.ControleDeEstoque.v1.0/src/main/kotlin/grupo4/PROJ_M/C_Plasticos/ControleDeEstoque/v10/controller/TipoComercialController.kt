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
}