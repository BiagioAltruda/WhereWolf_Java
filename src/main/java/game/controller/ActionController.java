package game.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import game.lupus.ActionDTO;

//creare una mappa e raccogliere le azioniDTO, una volta raccolte, rioridianrle in base alla priorita dei ruoli, ed eseguirle per ogni ruolo (nightAction()) 

@RestController
public class ActionController {

    public static Map<Integer, ActionDTO> actions; //mappa con id giocatore e azione da eseguire

    

    

}
