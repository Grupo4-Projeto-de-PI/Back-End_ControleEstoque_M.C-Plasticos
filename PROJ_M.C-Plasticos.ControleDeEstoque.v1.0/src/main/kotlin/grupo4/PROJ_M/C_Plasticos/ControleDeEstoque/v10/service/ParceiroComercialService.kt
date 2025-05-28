package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.ParceiroComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.tipoComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ParceiroComercialRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ParceiroComercialService(val repositorio: ParceiroComercialRepositorio) {

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

    fun atualizarTipoParceiro(id: Int, tipoPessoa: tipoComercialEnum): ResponseEntity<ParceiroComercial> {
        return if (repositorio.existsById(id)) {
            val parceiro = repositorio.findById(id).get()
            parceiro.tipoComercial = tipoPessoa
            repositorio.save(parceiro)
            ResponseEntity.status(200).body(parceiro)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    fun atualizarPapelParceiro(id: Int, tipoAtor: ParceiroComercial): ResponseEntity<ParceiroComercial> {
        return if (repositorio.existsById(id)) {
            val parceiro = repositorio.findById(id).get()
            parceiro.papelComercial = tipoAtor.papelComercial
            repositorio.save(parceiro)
            ResponseEntity.status(200).body(parceiro)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    fun atualizarTelefoneParceiro(id: Int, telefone: ParceiroComercial): ResponseEntity<ParceiroComercial> {
        return if (repositorio.existsById(id)) {
            val parceiro = repositorio.findById(id).get()
            parceiro.telefone = telefone.telefone
            repositorio.save(parceiro)
            ResponseEntity.status(200).body(parceiro)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    fun atualizarNomeParceiro(id: Int, nome: String): ResponseEntity<ParceiroComercial> {
        return if (repositorio.existsById(id)) {
            val parceiro = repositorio.findById(id).get()
            parceiro.nome = nome
            repositorio.save(parceiro)
            ResponseEntity.status(200).body(parceiro)
        } else {
            ResponseEntity.status(404).build()
        }
    }
}