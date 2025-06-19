package com.msp.employeeService.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.msp.employeeService.entity.Address;
import com.msp.employeeService.util.AddressResponse;
//he path attribute can be used to specify a contex-path for all requests, alternatively we are using feign 
//requestinterceptor which helps fetch the context-path from metadat-map

@FeignClient(name = "address-service"/* , path = "/address-service" */ /* , url = "localhost:8084/address-service" */)
public interface AddressClient {
	
	@GetMapping("/address/{id}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("id") int id);

	@PostMapping("/address")
	public ResponseEntity<String> saveAddressForEmployee(@RequestBody Address addr);
}
