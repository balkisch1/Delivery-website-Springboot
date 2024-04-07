package com.balkis.delivery.services;

import com.balkis.delivery.models.Ordre;
import com.balkis.delivery.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderrepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderrepository) {
        this.orderrepository = orderrepository;
    }

    public List<Ordre> getAllOrders() {
        return (List<Ordre>) orderrepository.findAll();
    }

    public Optional<Ordre> getOrderById(Long id) {
        return orderrepository.findById(id);
    }

    public Ordre saveOrder(Ordre order) {
        return orderrepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderrepository.deleteById(id);
    }

    @Transactional
    public boolean placeOrder(Ordre order) {
        // Ajoutez ici la logique nécessaire pour placer la commande, par exemple, envoyer une notification à l'administrateur, etc.
        // Vous pouvez également implémenter la logique pour enregistrer la commande dans la base de données, etc.
        logger.info("Placing order: {}", order);
        // Exemple de logique : Vérifier si la commande est valide et la placer
        if (orderIsValid(order)) {
            // Logique pour placer la commande (envoi de notification, enregistrement en base de données, etc.)
            // Remarque : ceci est un exemple, vous devez implémenter la logique appropriée en fonction de vos besoins

            // Retourne true pour indiquer que la commande a été placée avec succès
            return true;
        } else {
            // Retourne false si la commande n'est pas valide ou si elle n'a pas pu être placée
            return false;
        }
    }

    private boolean orderIsValid(Ordre order) {
        // Implémentez ici la logique pour vérifier si la commande est valide
        // Par exemple, vérifiez si tous les champs requis sont remplis, etc.

        // Retourne true si la commande est valide, sinon false
        return true; // Pour l'exemple, nous retournons toujours true
    }

    public boolean createOrder(Ordre order) {
        // Your logic to save the order
        Ordre savedOrder = orderrepository.save(order);
        return savedOrder != null; // Return true if the order is saved successfully
    }

}
