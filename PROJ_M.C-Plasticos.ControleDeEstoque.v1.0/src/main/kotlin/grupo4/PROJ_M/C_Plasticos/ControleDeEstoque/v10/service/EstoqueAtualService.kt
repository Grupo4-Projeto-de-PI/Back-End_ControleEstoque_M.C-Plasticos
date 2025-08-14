package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.EstoqueAtualRepositorio
import org.springframework.stereotype.Service

@Service
class EstoqueAtualService(val estoqueAtualRepositorio: EstoqueAtualRepositorio) {

    fun listarEstoqueAtual(): List<Map<String, Any>> {
        return estoqueAtualRepositorio.buscarEstoqueAtual()
    }

    fun buscarProdutoPorNome(nome: String): List<Map<String, Any>> {
        return estoqueAtualRepositorio.buscarProdutoPorNome(nome)
    }
}