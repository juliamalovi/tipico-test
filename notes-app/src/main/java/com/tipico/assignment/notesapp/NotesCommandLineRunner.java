package com.tipico.assignment.notesapp;

import com.tipico.assignment.notesapp.entity.Note;
import com.tipico.assignment.notesapp.repository.NotesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

@Component
public class NotesCommandLineRunner implements CommandLineRunner {

    private final NotesRepository notesRepository;

    public NotesCommandLineRunner(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public static final String TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt. ";

    @Override
    public void run(String... args) throws Exception {
        int totalNotes = 15;
        for (int i = 1; i <= totalNotes; i++) {
            Note note = new Note("Title "+i,"Author "+i,TEXT+i);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, i-totalNotes);
            note.setCreateDate(cal.getTime());
            notesRepository.save(note);
        }
    }
}
