package br.com.cast.turmaformacao.agenda.model.persistence;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.RedeSocial;

/**
 * Created by Administrador on 02/10/2015.
 */
public class RedeSocialBusinessService {
    private RedeSocialBusinessService() {
        super();
    }

    public static List<RedeSocial> findAll() {
        List<RedeSocial> redeSocials = RedeSocialRepository.getAll();

        return redeSocials;
    }

    public static void save(RedeSocial redeSocial) {
        RedeSocialRepository.save(redeSocial);

    }

    public static void delete(Contact selectedContact) {
        RedeSocialRepository.delete(selectedContact.getId());
    }
}
