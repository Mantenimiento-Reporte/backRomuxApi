package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.crossCutting.utils.UtilEmail;
import com.notificationapi.notificationapi.crossCutting.utils.UtilText;
import com.notificationapi.notificationapi.crossCutting.utils.UtilUUID;
import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.domain.BuzonNotificacionDomain;
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

    @Autowired
    private BuzonNotificacionService buzonNotificacionService;

    public List<PersonaDomain> findAll(){
        return personaRepository.findAll().stream().map(new PersonaService()::toDomain).toList();
    }

    public PersonaDomain toDomain(PersonaEntity entity){
        return new PersonaDomain(entity.getIdentificador(),entity.getPrimerNombre(),entity.getSegundoNombre(),entity.getPrimerApellido()
        ,entity.getSegundoApellido(),entity.getCorreoElectronico());
    }
    public PersonaEntity toEntity(PersonaDomain domain){
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
        BuzonNotificacionDomain buzonNotificacionDomain = new BuzonNotificacionDomain();
        buzonNotificacionDomain.setPropietario(persona);
        buzonNotificacionService.saveBuzonNotificacion(buzonNotificacionDomain);
    }

    public void update(PersonaDomain persona) throws NotificationException {
        var  Persona = personaRepository.findBycorreoElectronico(persona.getCorreoElectronico());
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

    public void delete(String correo) throws NotificationException {
        if(correo.equals(UtilEmail.getDefaultValueMail())) {
            throw new NotificationException();
        }
        try {
            PersonaDomain persona = toDomain(personaRepository.findBycorreoElectronico(correo));
            personaRepository.deleteById(persona.getIdentificador());
            buzonNotificacionService.eliminar(persona);
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
