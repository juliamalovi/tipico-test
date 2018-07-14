package com.tipico.assignment.notesapp.rest;

import com.tipico.assignment.notesapp.bean.GetNotesResponse;
import com.tipico.assignment.notesapp.bean.NotesBean;
import com.tipico.assignment.notesapp.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @GetMapping("/notes")
    @CrossOrigin(origins = "http://localhost:3000")
    public GetNotesResponse getNotes(@RequestParam(value = "p", required = true) int page, @RequestParam(value = "s", required = true)  int size, @RequestParam(value = "d", required = true)  Sort.Direction sort, @RequestParam(value = "o", required = true)  String orderField){
        return notesService.getNotes(page, size, sort, orderField);
    }

    @RequestMapping(value = "/add-note", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> addNote(@RequestBody NotesBean notesBean, UriComponentsBuilder ucBuilder) {
        Long id  = notesService.addNote(notesBean);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> deleteNote(@PathVariable("id") long id) {
        notesService.deleteNote(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}
