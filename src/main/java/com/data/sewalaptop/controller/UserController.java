//package com.data.sewalaptop.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.validation.*;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//
//import com.data.sewalaptop.service.Userservice;
//
//import jakarta.validation.Valid;
//
//@RestController
//@ControllerAdvice
//@RequestMapping("user")
//public class UserController {
//    @Autowired Userservice usr;
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//
//    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
//        BindingResult result = ex.getBindingResult();
//        List<FieldError> fieldErrors = result.getFieldErrors();
//
//        // Ambil pesan error validasi
//        StringBuilder errorMessage = new StringBuilder();
//        for (FieldError fieldError : fieldErrors) {
//            errorMessage.append(fieldError.getDefaultMessage()).append("; ");
//        }
//
//        return ResponseEntity.badRequest().body(errorMessage.toString());
//    }
//
//    @GetMapping("/index")
//    public ResponseEntity<Response> index() {
//        try {
//            Response resp = Response.builder()
//            .code("200")
//            .data(usr.index())
//            .message("data index")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.OK);
//        } catch (Exception e) {
//            Response resp = Response.builder()
//            .code("400")
//            .data(usr.index())
//            .message("Error")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
//            }
//        }
//
//    @PostMapping("/save")
//    public ResponseEntity<Response> register(@Valid @RequestBody User body,  Userdto userdto) {
//        try {
//            Response resp = Response.builder()
//            .code("200")
//            .data(usr.register(body))
//            .message("data save")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.OK);
//        } catch (Exception e) {
//            Response resp = Response.builder()
//            .code("400")
//            .data(usr.register(body))
//            .message("Error")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<Response> show(@PathVariable Long id) {
//        try {
//            Response resp = Response.builder()
//            .code("200")
//            .data(usr.show(id))
//            .message("get data")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.OK);
//        } catch (Exception e) {
//            Response resp = Response.builder()
//            .code("400")
//            .data(usr.show(id))
//            .message("Error")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<Response> update(@RequestBody User body, @PathVariable Long id) {
//        try {
//            Response resp = Response.builder()
//            .code("200")
//            .data(usr.update(id, body))
//            .message("update data")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.OK);
//        } catch (Exception e) {
//            Response resp = Response.builder()
//            .code("400")
//            .data(usr.update(id, body))
//            .message("Error")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Response> delete(@PathVariable Long id) {
//        try {
//            Response resp = Response.builder()
//            .code("200")
//            .data(usr.delete(id))
//            .message("delete data")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.OK);
//        } catch (Exception e) {
//            Response resp = Response.builder()
//            .code("400")
//            .data(usr.delete(id))
//            .message("Error")
//            .build();
//            return new ResponseEntity<Response>(resp, HttpStatus.NOT_FOUND);
//        }
//    }
//}
