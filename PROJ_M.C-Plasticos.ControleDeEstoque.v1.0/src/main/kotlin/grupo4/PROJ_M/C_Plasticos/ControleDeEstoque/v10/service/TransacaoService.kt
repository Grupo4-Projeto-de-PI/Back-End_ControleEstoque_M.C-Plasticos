package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.FiltroTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.transacaoDto.EditarTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.transacaoDto.NovaTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.helper.TransacaoHelper
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@Service
class TransacaoService(
    val repositorio: TransacaoRepositorio,
    val produtoRepositorio: ProdutoRepositorio,
    val parceiroComercialRepositorio: ParceiroComercialRepositorio,
    val usuarioRepositorio: UsuarioRepositorio,
    private val transacaoHelper: TransacaoHelper,
    private val logTransacaoService: LogTransacaoService
) {

    private fun <T> buscarId(repositorio: JpaRepository<T, Int>, id: Int, mensagemErro: String): T {
        return repositorio.findById(id).orElseThrow { throw ResponseStatusException(HttpStatus.BAD_REQUEST, mensagemErro) }
    }

    fun criarTransacao(novaTransacao: NovaTransacaoDto): ResponseEntity<Transacao> {

        val fkProduto = buscarId(produtoRepositorio, novaTransacao.fkProduto, "Produto não encontrado")
        val fkParceiroComercial = novaTransacao.fkParceiroComercial?.let {
            buscarId(parceiroComercialRepositorio, it,
                "Parceiro Comercial não encontrado")
        }
        val fkUsuario = novaTransacao.fkUsuario?.let { buscarId(usuarioRepositorio, it, "Usuário não encontrado") }


        val novoHistorico = Transacao(
            fkProduto = fkProduto,
            categoria = novaTransacao.categoria,
            peso = novaTransacao.peso,
            valorTotal = novaTransacao.valorTotal,
            tipoOperacao = novaTransacao.tipoOperacao,
            fkParceiroComercial = fkParceiroComercial,
            fkUsuario = fkUsuario
        )

        val transacao = repositorio.save(novoHistorico)
        logTransacaoService.criar(novoHistorico)
        return ResponseEntity.status(201).build()
    }

    fun listarTransacoes(): ResponseEntity<List<Transacao>> {
        val transacoes = repositorio.findAll()
        return if (transacoes.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(transacoes)
        }
    }

    fun listarTransacaoPorId(id: Int): ResponseEntity<Transacao> {
        if (!repositorio.existsById(id)) {
            return ResponseEntity.status(404).build()
        }

        val transacaoEncontrada = repositorio.findById(id)
        return if (transacaoEncontrada.isEmpty) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(transacaoEncontrada.get())
        }
    }

    fun filtrarTransacoes(filtro: FiltroTransacaoDto): ResponseEntity<List<Transacao>> {

        val dataInicioConvertida = filtro.dataInicio?.let { LocalDate.parse(it) }
        val dataFimConvertida = filtro.dataFim?.let { LocalDate.parse(it) }

        val transacoes = repositorio.findByDynamicFilters(
            fkProduto = filtro.fkProduto,
            categoria = filtro.fkCategoria,
            fkCliente = filtro.fkCliente,
            fkFornecedor = filtro.fkFornecedor,
            fkTipoParceiroComercial = filtro.fkTipoParceiroComercial,
            tipoOperacao = filtro.tipoOperacao,
            dataInicio = dataInicioConvertida,
            dataFim = dataFimConvertida,
            pesoMinimo = filtro.pesoMinimo,
            pesoMaximo = filtro.pesoMaximo,
            fkTipoProduto = filtro.fkTipoProduto
        )

        return if (transacoes.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(transacoes)
        }
    }

    fun atualizarTransacao(idTransacao: Int, transacaoAtualizada: EditarTransacaoDto): ResponseEntity<Transacao> {
        if (!repositorio.existsById(idTransacao)) {
            return ResponseEntity.status(404).build()
        }
        val transacaoExistente = repositorio.findById(idTransacao).get()
        transacaoHelper.atualizarTransacao(transacaoAtualizada, transacaoExistente)

        val transacaoAtualizadaFinal = repositorio.save(transacaoExistente)
        return ResponseEntity.status(200).body(transacaoAtualizadaFinal)
    }

    fun deletarTransacao(id: Int): ResponseEntity<Void> {
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(404).build()
    }
}