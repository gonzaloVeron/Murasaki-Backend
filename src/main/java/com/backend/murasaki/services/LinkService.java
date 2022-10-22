package com.backend.murasaki.services;

import com.backend.murasaki.dtos.LinkDTO;
import com.backend.murasaki.models.Link;
import com.backend.murasaki.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

    @Autowired
    public LinkRepository linkRepository;

    public Link create(LinkDTO dto){
        Link link = new Link(dto.getTitle(), dto.getUrl());
        return this.linkRepository.save(link);
    }

    public Iterable<Link> createAll(List<LinkDTO> dtos){
        return this.linkRepository.saveAll(dtos.stream().map(dto -> new Link(dto.getTitle(), dto.getUrl())).toList());
    }

}
