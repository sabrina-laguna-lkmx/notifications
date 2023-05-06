package com.example.notifications.service;

import java.util.List;
import java.util.Optional;

import com.example.notifications.data.CenadiUserRepository;
import com.example.notifications.model.CenadiUser;
import com.example.notifications.model.UserNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserService {

    @Autowired
    private CenadiUserRepository repository;

    @GetMapping("")
    public List<CenadiUser> getListCenadiUser() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CenadiUser> getById(@PathVariable String id){
        return repository.findById(id);
    }
    
    @PostMapping("")
    public String addNotification( @RequestBody CenadiUser cenadiUser){
        repository.save(cenadiUser);
        return String.format("Agregado %s", cenadiUser.getId());
    } 
    
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id){
        repository.deleteById(id);
        return "Eliminado";
    }

    @PutMapping("/{id}")
    public void updateNotification(@PathVariable String id, @RequestBody CenadiUser newCenadiUser){
        CenadiUser cenadiUser = repository.findById(id).get();
        repository.save(updateNotificationCheck(newCenadiUser, cenadiUser));
    }

    // @GetMapping("")
    // public List<UserNotification> getNotigicationType(@RequestParam(required = false) String type) {
    //     if(type == null) {
    //         return repository.findAll();
    //     } else {
    //         return repository.findByType(type);
    //     }
    // }

    public CenadiUser updateNotificationCheck(CenadiUser newCenadiUser, CenadiUser cenadiUser){     
            cenadiUser.setRefreshToken(newCenadiUser.getRefreshToken());
            cenadiUser.setClientId(newCenadiUser.getClientId());
            cenadiUser.setRoles(newCenadiUser.getRoles());
            cenadiUser.setExp(newCenadiUser.getExp());
            cenadiUser.setCreatedAt(newCenadiUser.getCreatedAt());
            cenadiUser.setLastName(newCenadiUser.getLastName());
            cenadiUser.setPasswordExpiration(newCenadiUser.getPasswordExpiration());
            cenadiUser.setPhone(newCenadiUser.getPhone());
            cenadiUser.setName(newCenadiUser.getName());
            cenadiUser.setLegalAccepted(newCenadiUser.getLegalAccepted());
            cenadiUser.setEmail(newCenadiUser.getEmail());
            cenadiUser.setUsername(newCenadiUser.getUsername());
            cenadiUser.setPartners(newCenadiUser.getPartners());
            cenadiUser.setTherapeuticLines(newCenadiUser.getTherapeuticLines());
            cenadiUser.setClients(newCenadiUser.getClients());
            cenadiUser.setBillableClients(newCenadiUser.getBillableClients());
            cenadiUser.setClientGroups(newCenadiUser.getClientGroups());
            cenadiUser.setStates(newCenadiUser.getStates());
            cenadiUser.setPrograms(newCenadiUser.getPrograms());
            cenadiUser.setGroups(newCenadiUser.getGroups());
        return cenadiUser;
    }

    

}
