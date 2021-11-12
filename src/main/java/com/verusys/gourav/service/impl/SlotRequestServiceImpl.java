package com.verusys.gourav.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verusys.gourav.constant.SlotStatus;
import com.verusys.gourav.entity.SlotRequest;
import com.verusys.gourav.exception.DoctorNotFoundException;
import com.verusys.gourav.exception.SlotsNotFoundException;
import com.verusys.gourav.repository.SlotRequestRepository;
import com.verusys.gourav.service.ISlotRequestService;

@Service
public class SlotRequestServiceImpl implements ISlotRequestService {

	@Autowired
	private SlotRequestRepository repo;
	
	public Long saveSlotRequest(SlotRequest sr) {
		return repo.save(sr).getId();
	}
	
	public SlotRequest getOneSlotRequest(Long id) {
		Optional<SlotRequest> opt = repo.findById(id);
		if(opt!=null) {
			return opt.get();
		}
		return null;
	}

	public List<SlotRequest> getAllSlotRequests() {
		return repo.findAll();
	}

	@Transactional
	public void updateSlotRequestStatus(Long id, String status) {
		repo.updateSlotRequestStatus(id, status);
	}
	
	public List<SlotRequest> viewSlotsByPatientMail(String patientMail) {
		return repo.getAllPatientSlots(patientMail);
	}
	
	@Override
	public List<SlotRequest> viewSlotsByDoctorMail(String doctorMail) {
		return repo.getAllDoctorSlots(doctorMail,SlotStatus.ACCEPTED.name());
	}
	
	@Override
	public List<Object[]> getSlotsStatusAndCount() {
		return repo.getSlotsStatusAndCount();
	}

	@Override
	public void removeSlots(Long id) {
			repo.delete(getOneSlot(id));
	}

	@Override
	public SlotRequest getOneSlot(Long id) {
		return repo.findById(id).orElseThrow(
				()-> new SlotsNotFoundException(id+", not exist"));
	}

	@Override
	public void updateSLots(SlotRequest sr) {
		if(repo.existsById(sr.getId()))
			repo.save(sr);
		else
			throw new SlotsNotFoundException(sr.getId()+", not exist");
	}
}
