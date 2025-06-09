package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.service

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.LogTransacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.logTransacaoEnum.logTransacaoEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.LogTransacaoRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LogTransacaoService(val repositorio: LogTransacaoRepositorio) {


    fun criar(transacao: Transacao): ResponseEntity<LogTransacao> {
        val logTransacao = LogTransacao(
            id = null,
            peso = transacao.peso,
            valorTotal = transacao.valorTotal,
            tipoEdicao = logTransacaoEnum.CREATE,
            tipoOperacao = transacao.tipoOperacao,
            dataLog = LocalDateTime.now(),
            fkTransacao = transacao,
            fkTransacaoProduto = transacao.fkProduto
        )
        val salvo = repositorio.save(logTransacao)
        return ResponseEntity.ok(salvo)
    }
}