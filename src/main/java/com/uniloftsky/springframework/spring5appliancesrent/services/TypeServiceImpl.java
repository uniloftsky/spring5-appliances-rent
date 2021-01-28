package com.uniloftsky.springframework.spring5appliancesrent.services;

import com.uniloftsky.springframework.spring5appliancesrent.model.Type;
import com.uniloftsky.springframework.spring5appliancesrent.repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Set<Type> findAll() {
        return new HashSet<>(typeRepository.findAll());
    }

    @Override
    public Type findById(Long id) {
        Optional<Type> optional = typeRepository.findById(id);
        if(optional.isEmpty()) {
            throw new RuntimeException("Expected type not found!");
        }
        return optional.get();
    }

    @Override
    public Type save(Type obj) {
        return typeRepository.save(obj);
    }

    @Override
    public List<Type> saveAll(List<Type> types) {
        return typeRepository.saveAll(types);
    }

    @Override
    public void delete(Type obj) {
        typeRepository.delete(obj);
    }
}
