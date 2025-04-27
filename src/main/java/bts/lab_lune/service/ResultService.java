package bts.lab_lune.service;

import bts.lab_lune.model.Result;
import bts.lab_lune.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//para que sea componente de fabrica de spring y pueda hacer uso de spring
public class ResultService implements IResultService {
    @Autowired
    //Inyectar de manera automatico
    private ResultRepository resultRepository;

    @Override
    public List<Result> list() {
        return resultRepository.findAll();
    }

    @Override
    public Result findResultById(Integer idResult) {
        return resultRepository.findById(idResult).orElse(null);
    }

    @Override
    public void saveResult(Result result) {
        resultRepository.save(result);
    }

    @Override
    public void deleteResult(Result result) {
        resultRepository.delete(result);
    }
}
