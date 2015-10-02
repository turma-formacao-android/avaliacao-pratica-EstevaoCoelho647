package br.com.cast.turmaformacao.agenda.model.persistence;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Telefone;

/**
 * Created by Administrador on 02/10/2015.
 */
public class TelefoneBusinessService {

    private TelefoneBusinessService() {
        super();
    }

    public static List<Telefone> findAll() {
        List<Telefone> telefones = TelefoneRepository.getAll();

        return telefones;
    }

    public static void save(Telefone telefone) {
        TelefoneRepository.save(telefone);

    }

    public static void delete(Contact selectedcontact) {
        TelefoneRepository.delete(selectedcontact.getId());
    }
}
