package com.balkis.delivery.services;
import com.balkis.delivery.models.Provider;
import com.balkis.delivery.repository.ProviderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ProviderService{
    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(ProviderService.class);
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final ProviderRepository providerrepository;

    public ProviderService(ProviderRepository providerrepository) {
        this.providerrepository = providerrepository;
    }

    public List<Provider> getAllProviders() {
        return (List<Provider>) providerrepository.findAll();
    }

    public Optional<Provider> getProviderById(Long id) {
        return providerrepository.findById(id);
    }

    public Provider saveProvider(Provider provider) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(provider.getPassword());
        provider.setPassword(hashedPassword);

        // Save the provider
        return providerrepository.save(provider);
    }

    public void deleteProvider(Long id) {
        providerrepository.deleteById(id);
    }




    public void registerProvider(Provider provider) {

        saveProvider(provider);
    }

    public Provider findProviderByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT p FROM Provider p WHERE p.email = :email", Provider.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            // Handle the case where no client is found
            return null;
        } catch (NonUniqueResultException ex) {
            // Log the exception or handle it based on your application's needs
            log.error("Multiple providers found for email: {}", email);
            // You might want to throw a custom exception or return a specific result based on your needs
            // throw new CustomNonUniqueResultException("Multiple clients found for email: " + email);
            return null;
        } catch (EmptyResultDataAccessException ex) {
            // In case you are using Spring Data JPA and EmptyResultDataAccessException is thrown
            // Handle the case where no client is found
            return null;
        } catch (Exception ex) {
            // Handle other exceptions
            log.error("Error while querying for providers by email", ex);
            throw new RuntimeException("Error while querying for providers by email", ex);
        }
    }



    // Add the following method for login
}



