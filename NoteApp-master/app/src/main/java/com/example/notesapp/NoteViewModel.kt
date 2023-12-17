package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application : Application) : AndroidViewModel(application) {

    var repositary = NoteRepositary
    val allNotes : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repositary = NoteRepositary(dao)
        allNotes = repositary.allNote
    }

    fun deleteNode(note: Note)=viewModelScope.launch{

        repositary.delete(note)
    }

    fun insertNode(note : Note) = viewModelScope.launch (Dispatchers.IO){
        repositary.insert(note)
    }

}