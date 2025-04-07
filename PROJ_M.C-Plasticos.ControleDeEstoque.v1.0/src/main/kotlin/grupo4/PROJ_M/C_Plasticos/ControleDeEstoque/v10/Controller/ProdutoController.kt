package Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Controller

import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Produto
import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.ProdutoRepositorio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produto")
class ProdutoController(val repositorio: ProdutoRepositorio) {

    @GetMapping("/listar")
    fun listarTodosProdutos():ResponseEntity<List<Produto>>{

        val produtos = repositorio.findAll()

        return if (produtos.isEmpty()){
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(produtos)
        }

    }

    @GetMapping("/listar/id")
    fun getProdutoId(@RequestParam id:Int): ResponseEntity<Produto>{
        val produtos = repositorio.findById(id)
        return ResponseEntity.of(produtos)
    }

    @GetMapping("/listar/tipo")
    fun getProdutoTipo(@RequestParam tipo: String): ResponseEntity<List<Produto>>{
        if (tipo.isBlank()){
            return ResponseEntity.status(400).build()
        }
        val produtos = repositorio.findByTipo(tipo)

        if(produtos.isEmpty()){
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(produtos)
    }

    @PostMapping("/criar")
    fun criarProduto(@RequestBody novoProduto: Produto):ResponseEntity<Produto> {
        val produtos = repositorio.save(novoProduto)
        return ResponseEntity.status(201).body(produtos)
    }

    @DeleteMapping("/deletar/{id}")
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
            val produtosEncontrados = repositorio.findById(id).get()
            produtosEncontrados.preco = preco
            return ResponseEntity.status(200).body(produtosEncontrados)
        }

        return ResponseEntity.status(404).build()
    }

}