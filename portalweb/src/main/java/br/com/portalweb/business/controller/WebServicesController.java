package br.com.portalweb.business.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marce
 */
@RestController
public class WebServicesController {

    /*@Autowired
    VenPedidoBO pedidoNegocio;

    @RequestMapping(value = "/pedido/listar/{canal}/{status}/{quantidade}", method = RequestMethod.GET)
    public ResponseEntity<List<VenPedidoTO>> listar(@PathVariable("canal") String canal, @PathVariable("status") String status, @PathVariable("quantidade") Integer quantidade) {
        List<VenPedidoTO> pedidoTOs = pedidoNegocio.listarPedidosPorCanalSetor(EnumStatusPedido.PENDENTE_EXPORTACAO);
        if (pedidoTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidoTOs, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pedido/listar/{canal}/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<VenPedidoTO>> listar(@PathVariable("canal") String canal, @PathVariable("status") String status) {
        List<VenPedidoTO> pedidoTOs = pedidoNegocio.listarPedidosPorCanalSetor(EnumStatusPedido.PENDENTE_EXPORTACAO);
        if (pedidoTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidoTOs, HttpStatus.OK);
    }*/

    /*@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<User> getUser(@PathVariable("id") long id) {
     System.out.println("Fetching User with id " + id);
     User user = userService.findById(id);
     if (user == null) {
     System.out.println("User with id " + id + " not found");
     return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
     }
     return new ResponseEntity<User>(user, HttpStatus.OK);
     }

     @RequestMapping(value = "/user/", method = RequestMethod.POST)
     public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
     System.out.println("Creating User " + user.getName());

     if (userService.isUserExist(user)) {
     System.out.println("A User with name " + user.getName() + " already exist");
     return new ResponseEntity<Void>(HttpStatus.CONFLICT);
     }

     userService.saveUser(user);

     HttpHeaders headers = new HttpHeaders();
     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
     }

     @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
     public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
     System.out.println("Updating User " + id);

     User currentUser = userService.findById(id);

     if (currentUser == null) {
     System.out.println("User with id " + id + " not found");
     return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
     }

     currentUser.setName(user.getName());
     currentUser.setAge(user.getAge());
     currentUser.setSalary(user.getSalary());

     userService.updateUser(currentUser);
     return new ResponseEntity<User>(currentUser, HttpStatus.OK);
     }

     @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
     public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
     System.out.println("Fetching & Deleting User with id " + id);

     User user = userService.findById(id);
     if (user == null) {
     System.out.println("Unable to delete. User with id " + id + " not found");
     return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
     }

     userService.deleteUserById(id);
     return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
     }

     @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
     public ResponseEntity<User> deleteAllUsers() {
     System.out.println("Deleting All Users");

     userService.deleteAllUsers();
     return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
     }*/
}
