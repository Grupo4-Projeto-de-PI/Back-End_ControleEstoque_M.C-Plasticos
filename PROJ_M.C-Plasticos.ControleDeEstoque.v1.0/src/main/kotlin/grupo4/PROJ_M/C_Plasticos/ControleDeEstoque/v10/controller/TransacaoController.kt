package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.FiltroTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.TransacaoDto.EditarTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.dto.TransacaoDto.NovaTransacaoDto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Transacao
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.categoriaEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.enum.transacaoEnum.tipoOperacaoEnum
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transacoes")

class TransacaoController (
    val repositorio: TransacaoRepositorio,
    val produtoRepositorio: ProdutoRepositorio,
    val parceiroComercialRepositorio: ParceiroComercialRepositorio,
    val usuarioRepositorio: UsuarioRepositorio,
) {

    //Função generica para poder buscar os ID passados no body do post
    private fun <T> buscarId(repositorio: JpaRepository<T, Int>, id: Int): T {
        return repositorio.findById(id).orElseThrow { RuntimeException("Transação não encontrada") }
    }

    @PostMapping("/criar")
    fun post(@RequestBody novaTransacao: NovaTransacaoDto): ResponseEntity<Transacao> {
        
        val fkProduto = buscarId(produtoRepositorio, novaTransacao.fkProduto)
        val fkParceiroComercial = buscarId(parceiroComercialRepositorio, novaTransacao.fkParceiroComercial)
        val fkUsuario = buscarId(usuarioRepositorio, novaTransacao.fkUsuario)
        
        val categoriaEnum = try {
            categoriaEnum.valueOf(novaTransacao.categoria)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.status(400).body(null)
        }

        val tipoOperacaoEnum = try {
            tipoOperacaoEnum.valueOf(novaTransacao.tipoOperacao)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.status(400).body(null)
        }

        val novoHistorico = Transacao(
            fkProduto = fkProduto,
            categoria = categoriaEnum,
            peso = novaTransacao.peso,
            valorTotal = novaTransacao.valorTotal,
            tipoOperacao = tipoOperacaoEnum,
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
        val categoriaEnum = filtro.categoria?.let {
            try {
                categoriaEnum.valueOf(it)
            } catch (e: IllegalArgumentException) {
                return ResponseEntity.status(400).build()
            }
        }

        val tipoOperacaoEnum = filtro.tipoOperacao?.let {
            try {
                tipoOperacaoEnum.valueOf(it)
            } catch (e: IllegalArgumentException) {
                return ResponseEntity.status(400).build()
            }
        }

        val transacoes = repositorio.findByDynamicFilters(
            fkProduto = filtro.fkProduto,
            categoria = categoriaEnum?.name,
            fkParceiroComercial = filtro.fkParceiroComercial,
            tipoOperacao = tipoOperacaoEnum?.name,
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

    @PutMapping("/{idTransacao}")
    fun atualizarTransacao(@PathVariable idTransacao: Int, @RequestBody transacaoAtualizada: EditarTransacaoDto)
            : ResponseEntity<Transacao> {
        if (!repositorio.existsById(idTransacao)) {
            return ResponseEntity.status(404).build()
        }

        val transacaoExistente = repositorio.findById(idTransacao).get()

        transacaoAtualizada.fkProduto?.let { transacaoExistente.fkProduto = buscarId(produtoRepositorio, it) }
        transacaoAtualizada.fkCategoria?.let {
            transacaoExistente.categoria = categoriaEnum.valueOf(it)
        }
        transacaoAtualizada.peso?.let { transacaoExistente.peso = it }
        transacaoAtualizada.preco?.let { transacaoExistente.valorTotal = it }
        transacaoAtualizada.fkTipoOperacao?.let {
            transacaoExistente.tipoOperacao = tipoOperacaoEnum.valueOf(it)
        }
        transacaoAtualizada.fkParceiroComercial?.let {
            transacaoExistente.fkParceiroComercial = buscarId(parceiroComercialRepositorio, it)
        }

        val transacaoAtualizadaFinal = repositorio.save(transacaoExistente)
        return ResponseEntity.status(200).body(transacaoAtualizadaFinal)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {

        if(repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

    }
}