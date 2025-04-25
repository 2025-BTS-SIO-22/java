package bts.LabLune.servicio;

import bts.LabLune.modelo.Resultat;
import bts.LabLune.repositorio.ResultatRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//para que sea componente de fabrica de spring y pueda hacer uso de spring
public class ResultatServicio implements IResultatServicio {
    @Autowired
    //Inyectar de manera automatico
    private ResultatRepositorio resultatRepositorio;

    @Override
    public List<Resultat> listar() {
        return resultatRepositorio.findAll();
    }

    @Override
    public Resultat buscarResultadosporId(Integer idResult) {
        return resultatRepositorio.findById(idResult).orElse(null);
    }

    @Override
    public void guardarResultat(Resultat resultat) {
        resultatRepositorio.save(resultat);
    }

    @Override
    public void eliminarResultat(Resultat resultat) {
        resultatRepositorio.delete(resultat);
    }
}
