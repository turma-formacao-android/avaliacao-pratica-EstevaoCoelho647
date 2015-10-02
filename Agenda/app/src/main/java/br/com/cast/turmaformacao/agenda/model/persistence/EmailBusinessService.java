package br.com.cast.turmaformacao.agenda.model.persistence;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Email;

public class EmailBusinessService {

    private EmailBusinessService() {
        super();
    }

    public static List<Email> findAll() {
        List<Email> emails = EmailRepository.getAll();

        return emails;
    }

    public static void save(Email email) {
        EmailRepository.save(email);

    }

    public static void delete(Contact selectedContact) {
        EmailRepository.delete(selectedContact.getId());
    }
}
