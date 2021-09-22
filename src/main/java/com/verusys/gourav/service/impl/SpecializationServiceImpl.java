package com.verusys.gourav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verusys.gourav.entity.Specialization;
import com.verusys.gourav.exception.SpecializationNotFoundException;
import com.verusys.gourav.repository.ISpecializationRepo;
import com.verusys.gourav.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService {

	@Autowired
	private ISpecializationRepo repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		// repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		/*Optional<Specialization> optional = repo.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new SpecializationNotFoundException(id+" not found");*/
		return repo.findById(id).orElseThrow(() -> new SpecializationNotFoundException(id + " not found"));
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		/*Integer count = repo.getSpecCodeCount(specCode);
		boolean exist = count>0 ? true : false;
		return exist;*/
		return repo.getSpecCodeCount(specCode) > 0;
	}

	/*@Override
	public boolean isSpecNameExist(String specName) {
		return repo.getSpecNameCount(specName) > 0;
	}*/


	/*@Override
	public boolean isSpecCodeExistForEdit(String specCode, Long id) {
		return repo.getSpecCodeCountForEdit(specCode,id)>0;
	}*/
	@Override
	public boolean isSpecCodeExistForEdit(String specCode, Long id) {
		// TODO Auto-generated method stub
		return repo.getSpecCodeCountForEdit(specCode, id)>0;
	}
}
