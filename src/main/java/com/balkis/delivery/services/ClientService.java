package com.balkis.delivery.services;
import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.Driver;
import com.balkis.delivery.repository.ClientRepository;
import com.balkis.delivery.repository.DriverRepository;
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
public class ClientService {
    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientrepository;

    public ClientService(ClientRepository clientrepository) {
        this.clientrepository = clientrepository;
    }

    public List<Client> getAllClients() {
        return (List<Client>) clientrepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientrepository.findById(id);
    }

    public Client saveClient(Client client) {
        return clientrepository.save(client);
    }

    public void deleteClient(Long id) {
        clientrepository.deleteById(id);
    }


        public void registerClient(Client client) {
            String hashedPassword = new BCryptPasswordEncoder().encode(client.getPassword());
            client.setPassword(hashedPassword);
            saveClient(client);
        }
    public Client findClientByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            // Handle the case where no client is found
            return null;
        } catch (NonUniqueResultException ex) {
            // Log the exception or handle it based on your application's needs
            log.error("Multiple clients found for email: {}", email);
            // You might want to throw a custom exception or return a specific result based on your needs
            // throw new CustomNonUniqueResultException("Multiple clients found for email: " + email);
            return null;
        } catch (EmptyResultDataAccessException ex) {
            // In case you are using Spring Data JPA and EmptyResultDataAccessException is thrown
            // Handle the case where no client is found
            return null;
        } catch (Exception ex) {
            // Handle other exceptions
            log.error("Error while querying for client by email", ex);
            throw new RuntimeException("Error while querying for client by email", ex);
        }
    }


    // Add the following method for login
}
