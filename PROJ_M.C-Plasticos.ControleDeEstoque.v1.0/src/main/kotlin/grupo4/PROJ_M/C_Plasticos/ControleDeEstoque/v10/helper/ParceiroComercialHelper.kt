package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.ParceiroComercial
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.papelComercialEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.parceiroComercialEnum.tipoComercialEnum
import org.springframework.stereotype.Component

@Component
class ParceiroComercialHelper {

    fun atualizarTipoParceiro(tipoComercial: tipoComercialEnum?, parceiro: ParceiroComercial) {
        parceiro.tipoComercial = tipoComercial
    }

    fun atualizarPapelParceiro(papelComercialEnum: papelComercialEnum?, parceiro: ParceiroComercial,) {
        parceiro.papelComercial = papelComercialEnum
    }

    fun atualizarTelefoneParceiro(telefone: String?, parceiro: ParceiroComercial) {
        parceiro.telefone = telefone
    }

    fun atualizarNomeParceiro(nome: String?, parceiro: ParceiroComercial) {
        parceiro.nome = nome
    }

    fun atualizarParceiro(parceiroAtualizado: ParceiroComercial, parceiroExistente: ParceiroComercial): ParceiroComercial {
        atualizarTipoParceiro(parceiroAtualizado.tipoComercial, parceiroExistente)
        atualizarPapelParceiro(parceiroAtualizado.papelComercial, parceiroExistente)
        atualizarTelefoneParceiro(parceiroAtualizado.telefone, parceiroExistente)
        atualizarNomeParceiro(parceiroAtualizado.nome, parceiroExistente)
        return parceiroAtualizado
    }
}