package com.capitalone.dashboard.executive.service.vz;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitalone.dashboard.exec.model.vz.ApplicationDetails;
import com.capitalone.dashboard.exec.repository.vz.ApplicationDetailsRepository;

/**
 * 
 * ApplicationServiceImpl class
 *
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationDetailsRepository applicationDetailsRepository;
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	/**
	 * 
	 * @param applicationDetailsRepository
	 */
	@Autowired
	public ApplicationServiceImpl(ApplicationDetailsRepository applicationDetailsRepository) {
		this.applicationDetailsRepository = applicationDetailsRepository;
	}

	@Override
	public List<ApplicationDetails> getAllApplicationDetails() {
		List<ApplicationDetails> appDetails = applicationDetailsRepository.getLimitedAppDetails();
		try {
			return appDetails.stream().sorted(
					Comparator.comparing(ApplicationDetails::getAppId).thenComparing(ApplicationDetails::getLob))
					.collect(Collectors.toList());
		} catch (Exception e) {
			LOG.error("Error in getAllApplicationDetails :: " + e);
		}
		return appDetails;
	}

}
