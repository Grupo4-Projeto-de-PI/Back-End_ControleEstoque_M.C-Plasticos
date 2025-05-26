package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.FiltroTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.TransacaoDto.NovaTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.*
import org.springframework.data.jpa.repository.JpaRepository
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

class TransacaoController (
    val repositorio: TransacaoRepositorio,
    val categoriaRepositorio: CategoriaRepositorio,
    val produtoRepositorio: ProdutoRepositorio,
    val parceiroComercialRepositorio: ParceiroComercialRepositorio,
    val usuarioRepositorio: UsuarioRepositorio,
    val tipoOperacaoRepositorio: TipoOperacaoRepositorio
) {

    //Função generica para poder buscar os ID passados no body do post
    private fun <T> buscarId(repositorio: JpaRepository<T, Int>, id: Int): T {
        return repositorio.findById(id).orElseThrow { RuntimeException("Transação não encontrada") }
    }

    @PostMapping("/criar")
    fun post(@RequestBody novaTransacao: NovaTransacaoDto): ResponseEntity<Transacao> {

        val fkCategoria = buscarId(categoriaRepositorio, novaTransacao.fkCategoria)
        val fkProduto = buscarId(produtoRepositorio, novaTransacao.fkProduto)
        val fkParceiroComercial = buscarId(parceiroComercialRepositorio, novaTransacao.fkParceiroComercial)
        val fkUsuario = buscarId(usuarioRepositorio, novaTransacao.fkUsuario)
        val fkTipoOperacao = buscarId(tipoOperacaoRepositorio, novaTransacao.tipoOperacao)

        val novoHistorico = Transacao(
            fkProduto = fkProduto,
            fkCategoria = fkCategoria,
            peso = novaTransacao.peso,
            valorTotal = novaTransacao.valorTotal,
            tipoOperacao = fkTipoOperacao,
            fkParceiroComercial = fkParceiroComercial,
            fkUsuario = fkUsuario
        )
        val transacao = repositorio.save(novoHistorico)
        return ResponseEntity.status(201).body(transacao)
    }

    @GetMapping
    fun listaTransacoes(): ResponseEntity<List<Transacao>> {

        val transacoes = repositorio.findAll()

        return if (transacoes.isEmpty()){
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(transacoes)
        }
    }

    @GetMapping("/{id}")
    fun listaTransacaoPorId(@PathVariable id: Int): ResponseEntity<Transacao> {
        if(!repositorio.existsById(id)){
            return ResponseEntity.status(404).build()
        }

        val transacaoEncontrada = repositorio.findById(id)
        if(transacaoEncontrada.isEmpty) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(transacaoEncontrada.get())
    }

    @PostMapping("/filtro")
    fun filtrarTransacoes(@RequestBody filtro: FiltroTransacaoDto): ResponseEntity<List<Transacao>> {
        val transacoes = repositorio.findByDynamicFilters(
            fkProduto = filtro.fkProduto,
            fkCategoria = filtro.fkCategoria,
            fkParceiroComercial = filtro.fkParceiroComercial,
            tipoOperacao = filtro.tipoOperacao,
            dataInicio = filtro.dataInicio,
            dataFim = filtro.dataFim,
            pesoMinimo = filtro.pesoMinimo,
            pesoMaximo = filtro.pesoMaximo,
            tipoProdutoId = filtro.tipoProdutoId
        )

        return if (transacoes.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(transacoes)
        }
    }

    @GetMapping()
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