package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.controller

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.LogPrecoProduto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.entidades.TipoProduto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.LogPrecoProdutoRepositorio
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.repositorio.ProdutoRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/produto")
class ProdutoController(val repositorio: ProdutoRepositorio, val logRepositorio: LogPrecoProdutoRepositorio) {

    @GetMapping
    fun listarTodosProdutos():ResponseEntity<List<Produto>>{

        val produtos = repositorio.findAll()

        return if (produtos.isEmpty()){
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(produtos)
        }

    }

    @GetMapping("/id")
    fun getProdutoId(@RequestParam id:Int): ResponseEntity<Produto>{
        val produtos = repositorio.findById(id)
        return ResponseEntity.of(produtos)
    }

    @GetMapping("/tipo")
    fun getProdutoTipo(@RequestParam tipo: TipoProduto): ResponseEntity<List<Produto>>{
        if (tipo.tipo?.isBlank() == true){
            return ResponseEntity.status(400).build()
        }
        val produtos = repositorio.findByTipo(tipo)

        if(produtos.isEmpty()){
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(produtos)
    }

    @PostMapping
    fun criarProduto(@RequestBody novoProduto: Produto):ResponseEntity<Produto> {
        val produtos = repositorio.save(novoProduto)
        return ResponseEntity.status(201).body(produtos)
    }


    @DeleteMapping("/{id}")
    fun deletarProduto(@PathVariable id: Int):ResponseEntity<Void> {
        if(repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(404).build()
    }

    @PutMapping("/atualizar-produto/{id}")
    fun atualizarTodoProduto(@PathVariable id:Int, @RequestBody produtosAtualizado: Produto):ResponseEntity<Produto> {

        if (repositorio.existsById(id)) {
            produtosAtualizado.id = id
            val produto = repositorio.save(produtosAtualizado)
            return ResponseEntity.status(200).body(produto)
        }

        return ResponseEntity.status(404).build()
    }

    @PatchMapping("/produtos-preco/{id}/{preco}")
    fun patchProdutosPreco(@PathVariable id:Int, @PathVariable preco:Double): ResponseEntity<Produto> {
        if (repositorio.existsById(id)) {
            val produtosEncontrado = repositorio.findById(id).get()
            produtosEncontrado.precoMedio = preco
            repositorio.save(produtosEncontrado)

            val existLog = logRepositorio.existsByFkProdutoId(id)

            val log = LogPrecoProduto(
                fkProduto = produtosEncontrado,
                precoAntigo = if (existLog) logRepositorio.findByTop1FkProdutoIdOrderByDataAlteracaoDesc(id).precoNovo else null,
                precoNovo = preco.toBigDecimal(),
                dataAlteracao = LocalDateTime.now()
            )

            logRepositorio.save(log)

            return ResponseEntity.status(200).body(produtosEncontrado)
        }

        return ResponseEntity.status(404).build()
    }



}