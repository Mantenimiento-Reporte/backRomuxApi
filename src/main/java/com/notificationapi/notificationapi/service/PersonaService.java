package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.crossCutting.utils.UtilEmail;
import com.notificationapi.notificationapi.crossCutting.utils.UtilText;
import com.notificationapi.notificationapi.crossCutting.utils.UtilUUID;
import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import com.notificationapi.notificationapi.entity.PersonaEntity;
import com.notificationapi.notificationapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PersonaService {


    @Autowired
    private PersonaRepository personaRepository;


    public List<PersonaDomain> findAll(){
        return personaRepository.findAll().stream().map(new PersonaService()::toDomain).toList();
    }

    private PersonaDomain toDomain(PersonaEntity entity){
        return new PersonaDomain(entity.getIdentificador(),entity.getPrimerNombre(),entity.getSegundoNombre(),entity.getPrimerApellido()
        ,entity.getSegundoApellido(),entity.getCorreoElectronico());
    }
    private PersonaEntity toEntity(PersonaDomain domain){
        return new PersonaEntity(domain.getIdentificador(),domain.getPrimerNombre(),domain.getSegundoNombre(),domain.getPrimerApellido()
                ,domain.getSegundoApellido(),domain.getCorreoElectronico());
    }

    public PersonaDomain consult(String correoElectronico){
        return toDomain(personaRepository.findBycorreoElectronico(correoElectronico));
    }

    public void save(PersonaDomain persona) throws NotificationException {
        if(!datosSonValidos(persona)){
            throw new NotificationException();
        }
        try {
            personaRepository.save(toEntity(persona));
        }catch (Exception e){
            throw e;
        }
    }

    public void update(PersonaDomain persona) throws NotificationException {
        if(persona.getCorreoElectronico().equals(UtilText.getDefaultTextValue()) || persona.getCorreoElectronico().equals(UtilEmail.getDefaultValueMail())){
            throw new NotificationException();
        }
        try{
            personaRepository.updateBycorreoElectronico(persona.getPrimerApellido(),persona.getPrimerNombre(),persona.getSegundoApellido(),persona.getSegundoNombre(),persona.getCorreoElectronico());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(UUID identificador) throws NotificationException {
        if(identificador.equals(UtilUUID.getUuidDefaultValue())) {
            throw new NotificationException();
        }
        try {
            personaRepository.deleteById(identificador);
        }catch (Exception e){
            throw e;
        }
    }


    private boolean datosSonValidos(PersonaDomain persona){
        if(persona.getPrimerNombre().equals(UtilText.getDefaultTextValue()) || persona.getPrimerApellido().equals(UtilText.getDefaultTextValue())){
            return false;
        }
        return  true;
    }
}
