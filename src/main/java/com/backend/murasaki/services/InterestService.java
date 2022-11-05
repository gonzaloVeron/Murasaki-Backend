package com.backend.murasaki.services;

import com.backend.murasaki.dtos.InterestDTO;
import com.backend.murasaki.exceptions.NotFoundException;
import com.backend.murasaki.models.Interest;
import com.backend.murasaki.repositories.InterestRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InterestService {

    @Autowired
    private InterestRespository interestRespository;

    @Transactional
    public Interest save(InterestDTO dto) {
        Interest interest = new Interest(dto.getName(), dto.getIcon());
        return this.interestRespository.save(interest);
    }

    @Transactional(readOnly = true)
    public Interest findById(int interest_id) throws NotFoundException {
        Interest interest = this.interestRespository.findById(interest_id).orElseThrow(() -> new NotFoundException("The requested interest was not found"));
        return interest;
    }

    @Transactional(readOnly = true)
    public List<Interest> findAll() {
        return this.interestRespository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Interest> find(String search_text, int page, int size){
        Pageable p = PageRequest.of(page, size);
        return this.interestRespository.findByNameLike(p, "%"+search_text+"%");
    }

    @Transactional
    public void delete(int interest_id){
        Interest interestFound = this.interestRespository.findById(interest_id).orElseThrow(() -> new NotFoundException("The requested interest was not found"));
        this.interestRespository.delete(interestFound);
    }

}
