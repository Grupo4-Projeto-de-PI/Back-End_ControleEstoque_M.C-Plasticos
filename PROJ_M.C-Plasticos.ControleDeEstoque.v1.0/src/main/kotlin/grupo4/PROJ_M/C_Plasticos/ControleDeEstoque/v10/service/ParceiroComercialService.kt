package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.ParceiroComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.tipoComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper.ParceiroComercialHelper
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ParceiroComercialRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ParceiroComercialService(
    val repositorio: ParceiroComercialRepositorio,
    val parceiroComercialHelper: ParceiroComercialHelper
) {

    fun listarTodosParceiros(): ResponseEntity<List<ParceiroComercial>> {
        val parceiros = repositorio.findAll()
        return if (parceiros.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(parceiros)
        }
    }

    fun criarParceiro(novosFornecedores: ParceiroComercial): ResponseEntity<ParceiroComercial> {
        val parceiro = repositorio.save(novosFornecedores)
        return ResponseEntity.status(201).body(parceiro)
    }

    fun deletarParceiro(id: Int): ResponseEntity<Void> {
        return if (repositorio.existsById(id)) {
            repositorio.deleteById(id)
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(404).build()
        }
    }

    fun atualizarParceiro(id: Int, parceiroAtualizado: ParceiroComercial): ResponseEntity<ParceiroComercial> {
        return if (repositorio.existsById(id)) {
            val parceiro = repositorio.findById(id).get()
            parceiroComercialHelper.atualizarParceiro(parceiroAtualizado, parceiro)
            repositorio.save(parceiro)
            ResponseEntity.status(200).body(parceiro)
        } else {
            ResponseEntity.status(404).build()
        }
    }

}