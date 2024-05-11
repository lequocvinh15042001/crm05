package service;

import java.util.List;

import entity.Status;
import repository.StatusRepository;

public class StatusService {
	
	StatusRepository statusRepository = new StatusRepository();

	public List<Status> getAllStatus(){
		return statusRepository.getAllStatus();
	}
	
}
