package com.verusys.gourav.service;

import java.util.List;

import com.verusys.gourav.entity.SlotRequest;

public interface ISlotRequestService {
	//patient can book slot
	Long saveSlotRequest(SlotRequest sr);
	//fetch one
	SlotRequest getOneSlotRequest(Long id);
	
	//ADMIN can view all slots
	List<SlotRequest> getAllSlotRequests();
	//ADMIN/patient can update status
	void updateSlotRequestStatus(Long id,String status);
	//PATIENT can see his slots
	List<SlotRequest> viewSlotsByPatientMail(String patientMail);
	
	//DOCTOR can see his slots
	List<SlotRequest> viewSlotsByDoctorMail(String doctorMail);
	
	List<Object[]> getSlotsStatusAndCount();
	
	public void removeSlots(Long id);
	public SlotRequest getOneSlot(Long id);
	public void updateSLots(SlotRequest sr);
}
