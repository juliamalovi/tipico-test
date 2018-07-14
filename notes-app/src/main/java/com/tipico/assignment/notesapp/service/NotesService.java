package com.tipico.assignment.notesapp.service;

import com.tipico.assignment.notesapp.bean.GetNotesResponse;
import com.tipico.assignment.notesapp.bean.NotesBean;
import com.tipico.assignment.notesapp.entity.Note;
import com.tipico.assignment.notesapp.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public List<NotesBean> getAllNotes() {
        List<Note> notes = notesRepository.findAll();
        List<NotesBean> noteBeans = mapToBeans(notes);
        return noteBeans;
    }

    public Long addNote(NotesBean noteBean) {
        Note note = new Note(noteBean.getTitle(),noteBean.getAuthor(), noteBean.getText());
        note = notesRepository.save(note);
        return note.getId();
    }

    public void deleteNote(Long id) {
        notesRepository.deleteById(id);
    }

    public GetNotesResponse getNotes(int page, int size, Sort.Direction order, String orderField) {
        PageRequest pageRequest = PageRequest.of(page, size, order, orderField);
        Page<Note> notes = notesRepository.findAll(pageRequest);
        List<NotesBean> noteBeans = mapToBeans(notes.getContent());
        GetNotesResponse response = new GetNotesResponse(page, notes.getTotalPages(), noteBeans);
        return response;
    }

    private List<NotesBean> mapToBeans(List<Note> notes) {
        return notes.stream().map(entity->{
            LocalDateTime date = LocalDateTime.ofInstant(entity.getCreateDate().toInstant(), ZoneId.systemDefault());
            String day = date.getDayOfMonth()+"";
            String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            int year = date.getYear();
            String dateString = month + " " + year;
            return new NotesBean(entity.getId(), entity.getTitle(), entity.getAuthor(), day, dateString, entity.getText());
        }).collect(Collectors.toList());
    }




}
