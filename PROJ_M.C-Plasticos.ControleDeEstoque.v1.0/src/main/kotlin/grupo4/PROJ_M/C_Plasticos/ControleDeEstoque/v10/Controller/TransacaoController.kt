package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.TransacaoRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transacoes")

class TransacaoController (val repositorio: TransacaoRepositorio) {

    //CREATE
    @PostMapping
    fun post(@RequestBody novaTransacao: Transacao): ResponseEntity<Transacao> {

        if (novaTransacao.tipoOperacao=="entrada"){
            novaTransacao.fkCliente = 0
        } else if (novaTransacao.tipoOperacao == "saída")  {
            novaTransacao.fkFornecedor = 0
            novaTransacao.fkCategoria = 0
        }

        val transacao = repositorio.save(novaTransacao)
        return ResponseEntity.status(201).body(transacao)
    }

    //READ
    @GetMapping
    fun listaTransacoes(): ResponseEntity<List<Transacao>> {

        val transacoes = repositorio.findAll()

        return if (transacoes.isEmpty()){

            ResponseEntity.status(204).build()

        } else {

            ResponseEntity.status(200).body(transacoes)

        }

    }

    //UPDATE
    @PatchMapping("/transacoes-peso/{idTransacao}/{novoPeso}")
    fun patchTransacoesPeso(@PathVariable idTransacao:Int, @PathVariable novoPeso:Double): ResponseEntity<Transacao> {
        if (repositorio.existsById(idTransacao)) {
            val transacoesEncontradas = repositorio.findById(idTransacao).get()
            transacoesEncontradas.peso = novoPeso
            repositorio.save(transacoesEncontradas)
            return ResponseEntity.status(200).body(transacoesEncontradas)
        }

        return ResponseEntity.status(404).build()
    }

    //DELETE
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {

        if(repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

    }



}