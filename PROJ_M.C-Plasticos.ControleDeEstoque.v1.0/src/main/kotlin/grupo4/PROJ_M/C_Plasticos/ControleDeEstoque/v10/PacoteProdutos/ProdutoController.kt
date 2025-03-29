package grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.PacoteProdutos

import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Produto
import grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio.ProdutoRepositorio
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produtos")
class ProdutoController(val repositorio: ProdutoRepositorio) {

    @GetMapping
    fun getListaProduto():ResponseEntity<List<Produto>>{

        val Produtos = repositorio.findAll()

        return if (Produtos.isEmpty()){

            ResponseEntity.status(204).build()

        } else {

            ResponseEntity.status(200).body(Produtos)

        }

    }

    @PostMapping
    fun post(@RequestBody novaProdutos: Produto):ResponseEntity<Produto> {
        val Produtos = repositorio.save(novaProdutos)
        return ResponseEntity.status(201).body(Produtos)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int):ResponseEntity<Void> {

        if(repositorio.existsById(id)){
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }

        return ResponseEntity.status(404).build()

    }

    @GetMapping("/{id}")
    fun getProdutoId(@PathVariable id:Int): ResponseEntity<Produto>{

        val Produtos = repositorio.findById(id)

        return ResponseEntity.of(Produtos)

    }

    // função comentada exige mais pesquisa

//    @GetMapping("/{tipo}")
//    fun getProdutoTipo(@PathVariable tipo:String): ResponseEntity<Produto>{
//
//        val Produtos = repositorio.findBy(tipo)
//
//        return ResponseEntity.of(Produtos)
//
//    }

    @PutMapping("/{id}")
    fun put(@PathVariable id:Int, @RequestBody ProdutosAtualizados: Produto):ResponseEntity<Produto> {

        if (repositorio.existsById(id)) {

            ProdutosAtualizados.id = id
            val Produtos = repositorio.save(ProdutosAtualizados)
            return ResponseEntity.status(200).body(Produtos)
        }

        return ResponseEntity.status(404).build()

    }

    @PatchMapping("/Produtos-Preco/{id}/{preco}")
    fun patchProdutosPreco(@PathVariable id:Int, @PathVariable preco:Double): ResponseEntity<Produto> {
        if (repositorio.existsById(id)) {
            val ProdutosEncontrados = repositorio.findById(id).get()
            ProdutosEncontrados.preco = preco
            return ResponseEntity.status(200).body(ProdutosEncontrados)
        }

        return ResponseEntity.status(404).build()
    }

}