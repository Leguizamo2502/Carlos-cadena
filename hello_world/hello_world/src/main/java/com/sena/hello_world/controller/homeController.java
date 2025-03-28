package com.sena.hello_world.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * para indicar que eta clase es un controller se utiliza la anotacion bean RestController
 */

 @RestController

 /**
  * se configura el mappin del controller, para organizar las peticiones
  */
  @RequestMapping("api/v1/home")

public class homeController {
    /**
     * GET: consultar informacion
     * POST: crear un registro
     * PUT: Actualizar un registro, todo el registro
     * PATCH: Actualizacion parcial, una parte del registro
     * DELETE: Eliminacion
     */

     /**
      * para atender las solicitudes se tiene que diseñar un end-point
      para definir un end-pont tiene que definir el tipo de peticiones y url para la solucitud
      la url debe ser unica
      */


      @GetMapping("/saludo1")
      public String saludo1(){
        return "hello world";
      }

      @GetMapping("/saludo2/{name}")
      public String saludo2(@PathVariable String name){
        return "hello "+name;
      }

      @GetMapping("/saludo3")
      public ResponseEntity<Object> saludo3(@RequestBody requestDTO request){
        responseDTO response = new responseDTO(
          "Hola "+request.getFirstName()+" "+request.getLastName()+" su correo es "+request.getEmail()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
}
