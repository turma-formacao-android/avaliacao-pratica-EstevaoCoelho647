package br.com.cast.turmaformacao.agenda.controllers.activities;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactBusinessService {
    private ContactBusinessService() {
        super();
    }

    public static List<Contact> findAll(){
        List<Contact> contacts = ContactRepository.getAll();

        return contacts;
    }

    public static void save(Contact contact){
        ContactRepository.save(contact);

    }
    public static void delete(Contact selectedcontact) {
        ContactRepository.delete(selectedcontact.getId());
    }
}
