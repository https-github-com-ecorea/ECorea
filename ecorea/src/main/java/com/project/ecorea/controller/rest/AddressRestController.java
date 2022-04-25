package com.project.ecorea.controller.rest;

import java.security.*;
import java.util.List;

import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.entity.*;
import com.project.ecorea.service.*;

import lombok.*;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_MEMBER")
public class AddressRestController {

	private final AddressService service;
	
	
	/* 배송지 추가 */
	@PostMapping("/mypage/member/addAddressRest")
	public ResponseEntity<Void> addAddressRest(Address address, Principal principal) {
		Boolean result = service.addAddress(principal.getName(), address);
		if (result == true)
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
	
	/* 배송지 리스트 출력 */
	@GetMapping("/mypage/member/addressListRest")
	public ResponseEntity<List<Address>> addressListRest(Principal principal) {
		List<Address> list = service.addressList(principal.getName());
		return ResponseEntity.ok(list);
	}
	
	/* 선택한 배송지 정보 출력 */
	@GetMapping("/mypage/member/chosenAddress/{ano}")
	public ResponseEntity<Address> chosenAddress(@PathVariable Integer ano, Principal principal) {
		Address address = service.chosenAddress(principal.getName(), ano);
		return ResponseEntity.ok(address);
	}
	
}
