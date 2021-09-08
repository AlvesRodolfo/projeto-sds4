package com.devsuperior.dsvendas.service;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsvendas.dto.SaleDTO;
import com.devsuperior.dsvendas.dto.SalesSucessDTO;
import com.devsuperior.dsvendas.dto.SalesSumDTO;
import com.devsuperior.dsvendas.entities.Sale;
import com.devsuperior.dsvendas.repositories.SaleRepository;
import com.devsuperior.dsvendas.repositories.SellerRepository;

@Service
public class SaleService {
	
	@Autowired //framework injeta automatica a instancia do objeto
	private SaleRepository repository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Transactional(readOnly = true) //toda operação com banco sera resolvida no service
	public Page<SaleDTO> findAll(Pageable pageable){
		sellerRepository.findAll(); //buscando os sellers e armazenando em memoria para evitar interações repetidas ao banco de dados
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x -> new SaleDTO(x)); //page não precisa do stream pois ele já é um stream
	}
	
	@Transactional(readOnly = true)
	public List<SalesSumDTO> amountGroupedBySeller(){
	return repository.amountGroupedBySeller();
	}
	
	@Transactional(readOnly = true)
	public List<SalesSucessDTO> sucessGroupedBySeller(){
	return repository.sucessGroupedBySeller();
	}

}
