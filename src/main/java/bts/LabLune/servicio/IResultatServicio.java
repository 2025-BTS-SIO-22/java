package bts.LabLune.servicio;


import bts.LabLune.modelo.Resultat;

import java.util.List;


public interface IResultatServicio {


    List<Resultat> listar();

    Resultat buscarResultadosporId(Integer idResult);

    void guardarResultat(Resultat resultat);

    void eliminarResultat(Resultat resultat);
}
