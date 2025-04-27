package bts.lab_lune.service;

import bts.lab_lune.model.Result;

import java.util.List;

public interface IResultService {
    List<Result> list();

    Result findResultById(Integer idResult);

    void saveResult(Result result);

    void deleteResult(Result result);
}
