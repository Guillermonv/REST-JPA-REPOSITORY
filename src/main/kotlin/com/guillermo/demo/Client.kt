package com.guillermo.demo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@RestController @RequestMapping(value = "/client") @EnableWebMvc
class ClientResurce(val clientRepo : ClientRepository){

    @GetMapping(value = "/")
    fun getAll() = clientRepo.findAll()

    @GetMapping(value ="/{id}")
    fun get(@PathVariable id :Long ) = clientRepo.getOne(id)

    @DeleteMapping(value = "/{id}")
    fun del(@PathVariable id:Long)= clientRepo.deleteById(id)

    @PostMapping(value = "/")
    fun save(@RequestBody client:Clients)= clientRepo.save(client)

    @PutMapping(value ="/{id}")
    fun update(@PathVariable id: Long, @RequestBody client: Clients):Clients{
     val clientUpdaed :Clients =clientRepo.findById(id).orElseThrow { Exception("ID not found") }
        clientUpdaed.Apellido = client.Apellido
        clientUpdaed.DNI = client.DNI
        clientUpdaed.Edad = client.Edad
        clientUpdaed.Nombre = client.Nombre
        return clientRepo.save(clientUpdaed)
    }
}

interface ClientRepository : JpaRepository<Clients,Long>


@Entity
class Clients(@Id @GeneratedValue (strategy = GenerationType.IDENTITY)val Id: Long = 0, var DNI: Long = 0, var Nombre: String = "", var Apellido: String = "", var Edad: Long = 0)

