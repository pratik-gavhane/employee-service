package com.msp.employeeService.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.msp.employeeService.entity.Address;
import com.msp.employeeService.entity.Employee;
import com.msp.employeeService.repo.EmployeeDao;
import com.msp.employeeService.restClient.AddressClient;
import com.msp.employeeService.util.AddressResponse;
import com.msp.employeeService.util.EmployeeResponse;

@Service
public class EmployeeService {

	private EmployeeDao empDao;
	
	private AddressClient addressClient;
	
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	EmployeeService(ModelMapper modelMapper, EmployeeDao empDao, AddressClient addressClient){
		this.empDao = empDao;
		this.modelMapper = modelMapper;
		this.addressClient = addressClient;
	}
	
	public EmployeeResponse getEmpById(int id) {
		Employee emp = empDao.findById(id).get();		
		//modelMapper helps with mapping objects between two classes		
		EmployeeResponse empRes = modelMapper.map(emp, EmployeeResponse.class);
		
		
		AddressResponse addressResponse = null;
		//rest call with feign client to address-service
		addressResponse = addressClient.getAddressByEmployeeId(id).getBody();

		System.out.println(addressResponse);
		empRes.setAddress(addressResponse);
		
		return empRes;
				
		//rest template impl
		
		//the below code can be used when manually setting instance
		/*
		 * List<ServiceInstance> instanceList =
		 * discoveryClient.getInstances("address-service"); String baseUri =
		 * instanceList.get(0).getUri().toString();
		 */
		/*
		 * //use below code with @loadbalanced annotation to automatically get availabel
		 * instance in a load balanced way String baseUrl =
		 * "http://ADDRESS-SERVICE/address-service/address/"+id;
		 * 
		 * System.out.println("uri : "+baseUrl); addressResponse =
		 * restTemplate.getForEntity(baseUrl, AddressResponse.class).getBody();
		 */
		
	}
	
	public void saveEmployeedetails(EmployeeResponse emp) {
				
		//saving employee object
		Employee employee = new Employee();
		modelMapper.map(emp, employee);
		Employee empResult = empDao.save(employee);
		//saving address obj
		AddressResponse addr = emp.getAddress();
		Address address = new Address();
		modelMapper.map(addr, address);
		address.setEmpId(empResult.getId());
		
		ResponseEntity<String> result = addressClient.saveAddressForEmployee(address);
		System.out.println(result.getStatusCode());
		System.out.println(result.getBody());		
	}

	public List<EmployeeResponse> getEmployeeList() {
		
		List<Employee> empList = empDao.findAll();
		List<EmployeeResponse> empResponseList = new ArrayList();
		
		for(Employee emp: empList) {			
			EmployeeResponse empRes = modelMapper.map(emp,EmployeeResponse.class);
			AddressResponse addressResponse = null;
			//rest call with feign client to address-service
			addressResponse = addressClient.getAddressByEmployeeId(emp.getId()).getBody();			
			
			empRes.setAddress(addressResponse);
			empResponseList.add(empRes);
		}
		// TODO Auto-generated method stub
		return empResponseList;
	}
}
