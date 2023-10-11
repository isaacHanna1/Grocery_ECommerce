package com.watad.Dao;

import com.watad.model.Unit;
import java.util.List;


public interface UnitDao {

	public Unit addUnit(Unit unit);
	public List<Unit> getAllUnit();
	public Unit DeleteUnit(long id);
	public Unit EditUnit(Unit unit);
	public boolean findByName(String unitName);
	
}
