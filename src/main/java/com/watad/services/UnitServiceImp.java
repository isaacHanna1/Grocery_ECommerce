package com.watad.services;

import com.watad.Dao.UnitDao;
import com.watad.model.Item;
import com.watad.model.Unit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class UnitServiceImp implements UnitService{

    private  final UnitDao unitDao;

    public UnitServiceImp(UnitDao unitDao) {
        this.unitDao = unitDao;
    }

    @Override
    @Transactional
    public Unit addUnit(Unit unit) {
        return unitDao.addUnit(unit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Unit> getAllUnit() {
      return  unitDao.getAllUnit();
    }

    @Override
    @Transactional
    public Unit DeleteUnit(long id) {
        Unit unit  = findById(id);
        if(unit != null){
            List<Item> items =  unit.getItems();
           if(items.size() > 0){
               throw  new RuntimeException("We Can not Delete this Unit it tied with many items");
           }
        }
        return unitDao.DeleteUnit(id);
    }

    @Override
    @Transactional
    public Unit EditUnit(Unit unit) {
        return unitDao.EditUnit(unit);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findByName(String unitName) {
        boolean result = false ;
        try {
            result = unitDao.findByName(unitName) != null;
        }catch (NoResultException e) {
            result = false;
        }
        return result;
    }

    @Override
    public Unit findById(long id) {
        return unitDao.findById(id);
    }
}
