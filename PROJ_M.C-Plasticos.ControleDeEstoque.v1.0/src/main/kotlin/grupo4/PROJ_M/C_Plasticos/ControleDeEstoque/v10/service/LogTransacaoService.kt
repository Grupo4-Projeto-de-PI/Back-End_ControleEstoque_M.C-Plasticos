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


    fun criar(Transacao: Transacao): ResponseEntity<LogTransacao> {
        val logTransacao = LogTransacao(
            id = null,
            peso = Transacao.peso,
            valorTotal = Transacao.valorTotal,
            tipoEdicao = logTransacaoEnum.CREATE,
            tipoOperacao = Transacao.tipoOperacao,
            dataLog = LocalDateTime.now(),
            fkTransacao = Transacao,
            fkTransacaoProduto = Transacao.fkProduto
        )
        val salvo = repositorio.save(logTransacao)
        return ResponseEntity.ok(salvo)
    }
}