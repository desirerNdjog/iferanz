package com.kimia_technologies.dao_implement;

import com.kimia_technologies.dao_service.AdministratorDaoService;
import com.kimia_technologies.dto.AdministorResponse;
import com.kimia_technologies.mapper.AdministratorServiceMapper;
import com.kimia_technologies.models.Administrator;
import com.kimia_technologies.models.Administrator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 08/09/2023
 */
@Repository
@RequiredArgsConstructor
public class AdministratorDaoImpl implements AdministratorDaoService {
    private final EntityManager entityManager;
    private final AdministratorServiceMapper mapper;
    @Override
    public Page<AdministorResponse> findAll(String element, int page, int size) {
        try{
            Pageable pageable = PageRequest.of(page, size);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Administrator> criteriaQuery = criteriaBuilder.createQuery(Administrator.class);
            Root<Administrator> root = criteriaQuery.from(Administrator.class);
            List<Predicate> predicates = new ArrayList<>();

            if (element != null){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+element+"%"));
            }

            if (element != null){
                predicates.add(criteriaBuilder.like(root.get("phone"), "%"+element+"%"));
            }


            criteriaQuery.where(
                    predicates.toArray(new Predicate[predicates.size()])
            );

            List<Administrator> list = entityManager.createQuery(criteriaQuery)
                    .setFirstResult((int) pageable.getOffset())
                    .setMaxResults(pageable.getPageSize())
                    .getResultList();

            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            countQuery.select(criteriaBuilder.count(countQuery.from(Administrator.class)));
            Long count = entityManager.createQuery(countQuery).getSingleResult();

            Page<Administrator> finalResul = new PageImpl<>(list, pageable, count);

            return finalResul.map(mapper);
        }catch (NoResultException exception){
            return null;
        }
    }
}
