package com.tipico.assignment.notesapp.bean;

import com.tipico.assignment.notesapp.entity.Note;

import java.io.Serializable;
import java.util.List;

public class GetNotesResponse implements Serializable {

    private int currentPage;
    private int totalPages;
    private List<NotesBean> notes;

    public GetNotesResponse(int currentPage, int totalPages, List<NotesBean> notes) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.notes = notes;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<NotesBean> getNotes() {
        return notes;
    }

    public void setNotes(List<NotesBean> notes) {
        this.notes = notes;
    }
}
