package Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Repositorio

import Grupo4.PROJ_M.C_Plasticos.ControleDeEstoque.v10.Entidades.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepositorio: JpaRepository<Cliente, Int> {
}
