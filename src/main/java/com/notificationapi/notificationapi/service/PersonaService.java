package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.crossCutting.utils.UtilEmail;
import com.notificationapi.notificationapi.crossCutting.utils.UtilText;
import com.notificationapi.notificationapi.crossCutting.utils.UtilUUID;
import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import com.notificationapi.notificationapi.entity.PersonaEntity;
import com.notificationapi.notificationapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public class PersonaService {


    @Autowired
    private PersonaRepository personaRepository;


    public PersonaService(){

    }
    public List<PersonaDomain> findAll(){
        return personaRepository.findAll().stream().map(new PersonaService()::toDomain).toList();
    }

    private PersonaDomain toDomain(PersonaEntity entity){
        return new PersonaDomain(entity.getIdentificador(),entity.getPrimerNombre(),entity.getSegundoNombre(),entity.getPrimerApellido()
        ,entity.getSegundoApellido(),entity.getCorreoElectronico());
    }

    public PersonaDomain consult(String correoElectronico){
        return toDomain(personaRepository.findBycorreoElectronico(correoElectronico));
    }

    public void save(PersonaDomain persona) throws NotificationException {
        if(!datosSonValidos(persona)){
            throw new NotificationException();
        }

        var personaEntity = new PersonaEntity(persona.getIdentificador(),persona.getPrimerNombre(),persona.getSegundoNombre(),persona.getPrimerApellido(),persona.getSegundoApellido(),
                persona.getCorreoElectronico());
        try {
            personaRepository.save(personaEntity);
        }catch (Exception e){
            throw e;
        }
    }

    public String update(PersonaDomain persona){
        if(persona.getCorreoElectronico().equals(UtilText.getDefaultTextValue()) || persona.getCorreoElectronico().equals(UtilEmail.getDefaultValueMail())){
            return "Error, correo electronico no válido";
        }
        return "se conectó bien";
    }

    public String delete(UUID identificador){
        if(identificador.equals(UtilUUID.getUuidDefaultValue())){
            return "identificador no valido";
        }
        return "se conecta bien";
    }


    private boolean datosSonValidos(PersonaDomain persona){
        if(persona.getPrimerNombre().equals(UtilText.getDefaultTextValue()) || persona.getPrimerApellido().equals(UtilText.getDefaultTextValue())){
            return false;
        }
        return  true;
    }
}
